package com.isa.fishingapp.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isa.fishingapp.dto.OwnerDTO;
import com.isa.fishingapp.dto.UserDTO;
import com.isa.fishingapp.dto.UserDeletionRequestDTO;
import com.isa.fishingapp.dto.UserProfileChangeDTO;
import com.isa.fishingapp.event.OnRegistrationCompleteEvent;
import com.isa.fishingapp.jwt.JwtUtils;
import com.isa.fishingapp.model.Country;
import com.isa.fishingapp.model.Location;
import com.isa.fishingapp.model.User;
import com.isa.fishingapp.model.UserCreationRequest;
import com.isa.fishingapp.model.VerificationToken;
import com.isa.fishingapp.model.enums.ERequestType;
import com.isa.fishingapp.model.enums.ERole;
import com.isa.fishingapp.repository.CountryRepository;
import com.isa.fishingapp.repository.RoleRepository;
import com.isa.fishingapp.repository.TokenRepository;
import com.isa.fishingapp.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("http://localhost:8081/")
public class UserController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	UserService userService;
	@Autowired
	CountryRepository countryRepository;
	@Autowired
	TokenRepository tokenRepository;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
	public List<User> getUsers(Model model)
	{
		return userService.getAllUsers();
	}
	
	@GetMapping("/countries/all")
	@PreAuthorize("permitAll")
	public List<Country> getCountries()
	{
		return countryRepository.findAll();
	}
	
	@GetMapping("/{userId}")
	@PreAuthorize("#userId == authentication.principal.id")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int userId) {
		return Optional
	            .ofNullable( userService.findById(userId) )
	            .map( user -> ResponseEntity.ok().body(new UserDTO(user)) )
	            .orElseGet( () -> ResponseEntity.notFound().build() );
    }
	
	@GetMapping("/email/{userEmail}")
	@PostAuthorize("returnObject.body.email == authentication.principal.email")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String userEmail) {
		return Optional
	            .ofNullable( userService.findByEmail(userEmail) )
	            .map( user -> ResponseEntity.ok().body(new UserDTO(user)) )
	            .orElseGet( () -> ResponseEntity.notFound().build() );
    }
	
	@PostMapping("/emailavailability")
	@PreAuthorize("permitAll")
	public ResponseEntity<Boolean> isEmailAvailable(@RequestBody String email)
	{
		return ResponseEntity
				.ok()
				.body(userService.isEmailAvailable(email));
	}
	
	@PostMapping("/register")
	@PreAuthorize("not(isAuthenticated())")
	public ResponseEntity<String> register(@RequestBody UserDTO signUpRequest, HttpServletRequest request)
	{
		if (userService.findByEmail(signUpRequest.getEmail()) != null) {
			return ResponseEntity
					.badRequest()
					.body("Error: Email is already taken!");
		}
		signUpRequest.setPassword(encoder.encode(signUpRequest.getPassword()));
		User registeredUser = new User(signUpRequest);
		Country country = countryRepository.findById(signUpRequest.getCountry().getId()).orElse(null);
		if(country == null)
			return ResponseEntity
					.badRequest()
					.body("Error: Country does not exist!");
		registeredUser.setResidence(new Location(signUpRequest.getAddress(), signUpRequest.getCity(), country, 0, 0));
		userService.registerUser(registeredUser);
		
		String appUrl = request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registeredUser, 
          request.getLocale(), appUrl));
		return new ResponseEntity<>(
			      "Registration successful!", 
			      HttpStatus.OK);
	}
	
	@PostMapping("/register_owner")
	@PreAuthorize("not(isAuthenticated())")
	public ResponseEntity<String> registerOwner(@RequestBody OwnerDTO signUpRequest, HttpServletRequest request) throws Exception
	{
		if (userService.findByEmail(signUpRequest.getEmail()) != null) {
			return ResponseEntity
					.badRequest()
					.body("Error: Email is already taken!");
		}
		signUpRequest.setPassword(encoder.encode(signUpRequest.getPassword()));
		User createdUser = new User(signUpRequest);
		Country country = countryRepository.findById(signUpRequest.getCountry().getId()).orElse(null);
		if(country == null)
			return ResponseEntity
					.badRequest()
					.body("Error: Country does not exist!");
		createdUser.setResidence(new Location(signUpRequest.getAddress(), signUpRequest.getCity(), country, 0, 0));
		
		ERequestType type = null;
		if(signUpRequest.getRole().equals("BOAT_OWNER")) type = ERequestType.BOAT_OWNER;
		if(signUpRequest.getRole().equals("LODGING_OWNER")) type = ERequestType.LODGING_OWNER;
		if(signUpRequest.getRole().equals("FISHING_INSTRUCTOR")) type = ERequestType.FISHING_INSTRUCTOR;
		
		createdUser.addRole(roleRepository.findByName(ERole.valueOf(signUpRequest.getRole())).get());
			
		userService.registerUser(createdUser, new UserCreationRequest(createdUser, type, signUpRequest.getApplicationDetails()));
		String appUrl = request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(createdUser, 
          request.getLocale(), appUrl));
		return new ResponseEntity<>(
			      "Registration successful!", 
			      HttpStatus.OK);
	}
	
	@PostMapping("/edit")
	@PreAuthorize("isAuthenticated() and #user.id == authentication.principal.id")
	public ResponseEntity<String> editUserProfile(@RequestBody UserProfileChangeDTO user)
	{
		//Sanitization to prevent JSON parser attacks on the country database
		Country country = countryRepository.findById(user.getCountry().getId()).orElse(null);
		if(country == null)
			return new ResponseEntity<>(
				      "Country not found!", 
				      HttpStatus.NOT_FOUND);
		user.setCountry(country);
		return userService.updateUser(user);
	}
	
	@GetMapping("/registration_confirm")
	public ResponseEntity<String> confirmRegistration(@RequestParam("token") String token) {
		
	    VerificationToken verificationToken = tokenRepository.findByToken(token).orElse(null);
	    if (verificationToken == null) {
	    	return new ResponseEntity<>(
				      "Verification token not found!", 
				      HttpStatus.NOT_FOUND);
	    }
	    
	    User user = verificationToken.getUser();
	    Calendar cal = Calendar.getInstance();
	    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	    	return new ResponseEntity<>(
				      "Verification token expired!", 
				      HttpStatus.BAD_REQUEST);
	    } 
	    
	    user.setActivated(true); 
	    userService.save(user); 
	    return new ResponseEntity<>(
			      "Successful verification!", 
			      HttpStatus.OK);
	}
	
	@PostMapping("/changeActivation")
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<String> changeActivation(@RequestBody String email)
	{
		userService.changeActivation(email);

		return new ResponseEntity<>(
			      "Changed status!",
			      HttpStatus.OK);
	}
	
	@GetMapping("/getAllUserCreationRequests")
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
	public List<UserCreationRequest> getAllUserCreationRequests(){

		return userService.getAllUserCreationRequests();
	}
	
	@PostMapping("/{id}/approveRequest")
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<String> approveRequest(@PathVariable int id)
	{
		return userService.approveRequest(id);
	}
	
	@PostMapping("/{id}/rejectRequest")
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<String> rejectRequest(@PathVariable int id, @RequestBody String description)
	{
		return userService.rejectRequest(id, description);
	}
	
	@PostMapping("/registerAdmin")
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR') and authentication.principal.email == 'admin@gmail.com'")
	public ResponseEntity<String> registerAdmin(@RequestBody OwnerDTO user)
	{	
		return userService.registerAdmin(user);
	}
	
	@PostMapping("/requestDeletion")
	@PreAuthorize("authentication.principal.id == #deletionRequest.userId")
	public ResponseEntity<?> requestDeletion(@RequestBody UserDeletionRequestDTO deletionRequest)
	{	
		return userService.requestDeletion(deletionRequest);
	}
}
