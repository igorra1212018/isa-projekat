package com.isa.fishingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.isa.fishingapp.model.User;
import com.isa.fishingapp.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
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
