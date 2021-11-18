package com.isa.fishingapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FishingController {

	@GetMapping("/home")
	public String home() {
		return "Homepage";
	}
}
