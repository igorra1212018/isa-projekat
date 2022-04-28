package com.isa.fishingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.fishingapp.model.Lodging;

public interface LodgingRepository extends JpaRepository<Lodging, Integer> {
	
}
