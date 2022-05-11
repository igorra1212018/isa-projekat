package com.isa.fishingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.fishingapp.model.PresetAmenity;

public interface AmenityRepository extends JpaRepository<PresetAmenity, Integer> {

}
