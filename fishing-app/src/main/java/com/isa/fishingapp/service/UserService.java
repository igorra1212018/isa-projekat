package com.isa.fishingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.model.User;
import com.isa.fishingapp.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User registerUser(String email, String password)
	{
		if(email != null && password != null)
		{
			User user = new User();
			user.setEmail(email);
			user.setPassword(password);
			return userRepository.save(user);
		}
		else return null;
	}

	public User authenticate(String email, String password)
	{
		return userRepository.findByEmailAndPassword(email, password).orElse(null);
	}
}
