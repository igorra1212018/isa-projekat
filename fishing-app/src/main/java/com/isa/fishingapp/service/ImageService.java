package com.isa.fishingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.model.Image;
import com.isa.fishingapp.repository.ImageRepository;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;
	
	public void save(Image image) {
		imageRepository.save(image);
	}
}
