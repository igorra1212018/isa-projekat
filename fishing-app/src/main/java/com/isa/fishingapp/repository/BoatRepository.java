package com.isa.fishingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.fishingapp.model.Boat;

public interface BoatRepository extends JpaRepository<Boat, Integer> {

}
