package com.isa.fishingapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.fishingapp.dto.UserDTO;
import com.isa.fishingapp.dto.UserProfileChangeDTO;
import com.isa.fishingapp.model.User;
import com.isa.fishingapp.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4000/")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public List<User> getUsers(Model model)
	{
		return userService.getAllUsers();
	}
	
	@GetMapping("/user/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int userId) {
		return Optional
	            .ofNullable( userService.getUserById(userId) )
	            .map( user -> ResponseEntity.ok().body(new UserDTO(user)) )
	            .orElseGet( () -> ResponseEntity.notFound().build() );
    }
	
	@GetMapping("/register")
	public String getRegisterPage(Model model)
	{
		model.addAttribute("registerRequest", new User());
		return "register";
	}
	
	@GetMapping("/login")
	public String getLoginPage(Model model)
	{
		model.addAttribute("loginRequest", new User());
		return "login";
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserDTO user)
	{
		userService.registerUser(new User(user));
		return new ResponseEntity<>(
			      "Registration successful!", 
			      HttpStatus.OK);
	}
	
	@PostMapping("/edit_user_profile")
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
