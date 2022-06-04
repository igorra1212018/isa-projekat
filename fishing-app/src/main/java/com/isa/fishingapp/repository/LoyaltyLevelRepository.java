package com.isa.fishingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.fishingapp.model.LoyaltyLevel;

public interface LoyaltyLevelRepository extends JpaRepository<LoyaltyLevel, Integer> {

}
