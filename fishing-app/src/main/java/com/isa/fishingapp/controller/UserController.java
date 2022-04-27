package com.isa.fishingapp.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isa.fishingapp.dto.OwnerDTO;
import com.isa.fishingapp.dto.UserDTO;
import com.isa.fishingapp.dto.UserProfileChangeDTO;
import com.isa.fishingapp.jwt.JwtUtils;
import com.isa.fishingapp.model.Owner;
import com.isa.fishingapp.model.User;
import com.isa.fishingapp.repository.RoleRepository;
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
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public List<User> getUsers(Model model)
	{
		return userService.getAllUsers();
	}
	
	@GetMapping("/{userId}")
	@PostAuthorize("returnObject.body.email == authentication.principal.email")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int userId) {
		return Optional
	            .ofNullable( userService.getUserById(userId) )
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
		System.out.println(email);
		return ResponseEntity
				.ok()
				.body(userService.isEmailAvailable(email));
	}
	
	@PostMapping("/register")
	@PreAuthorize("not(isAuthenticated())")
	public ResponseEntity<String> register(@RequestBody UserDTO signUpRequest)
	{
		if (userService.findByEmail(signUpRequest.getEmail()) != null) {
			return ResponseEntity
					.badRequest()
					.body("Error: Email is already taken!");
		}
		signUpRequest.setPassword(encoder.encode(signUpRequest.getPassword()));
		userService.registerUser(new User(signUpRequest));
		return new ResponseEntity<>(
			      "Registration successful!", 
			      HttpStatus.OK);
	}
	
	@PostMapping("/register_owner")
	public ResponseEntity<String> registerOwner(@RequestBody OwnerDTO owner) throws Exception
	{
		userService.registerOwner(new Owner(owner));
		return new ResponseEntity<>(
			      "Registration successful!", 
			      HttpStatus.OK);
	}
	
	@PostMapping("/edit")
	public ResponseEntity<String> editUserProfile(@RequestBody UserProfileChangeDTO user)
	{
		return userService.updateUser(user);
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute User user)
	{
		User loggedUser = userService.authenticate(user.getEmail(), user.getPassword());
		if(loggedUser != null)
			return "redirect:/";
		return "redirect:/register";
	}
}
