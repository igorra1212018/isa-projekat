package com.isa.fishingapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.fishingapp.model.Image;
import com.isa.fishingapp.model.Reservable;

public interface ImageRepository extends JpaRepository<Image, Integer> {
	
}
