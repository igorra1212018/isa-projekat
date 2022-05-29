package com.isa.fishingapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.isa.fishingapp.model.Role;
import com.isa.fishingapp.model.User;
import com.isa.fishingapp.repository.RoleRepository;
import com.isa.fishingapp.repository.UserRepository;

@SpringBootApplication
public class FishingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishingAppApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner (UserRepository userRepository, RoleRepository roleRepository) {
		return args -> {
//			User admin = new User("admin@gmail.com", "$2a$10$P1CfLeK3z/.xsUXuzPyXde4XySiuLuh70INu3bEh/3lFA4a7u/zbe", "Admin", "Adminovic", "123456789", true);
//			//Add ADMINISTRATOR role
//			admin.addRole(roleRepository.getById(5));
//			
//			userRepository.save(admin);
		};
	}

}
