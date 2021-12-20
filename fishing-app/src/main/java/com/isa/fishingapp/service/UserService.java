package com.isa.fishingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.model.User;
import com.isa.fishingapp.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User registerUser(User user)
	{
		return userRepository.save(user);
	}

	public User authenticate(String email, String password)
	{
		return userRepository.findByEmailAndPassword(email, password).orElse(null);
	}
	
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
}
