package com.isa.fishingapp.controller;

import java.awt.SystemColor;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		List<User> users = userService.getAllUsers();
		for(User u : users)
		{
			System.out.println(u.getEmail());
		}
		return userService.getAllUsers();
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
	public String register(@ModelAttribute User user)
	{
		User registeredUser = userService.registerUser(user.getEmail(), user.getPassword());
		return "redirect:/login";
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
