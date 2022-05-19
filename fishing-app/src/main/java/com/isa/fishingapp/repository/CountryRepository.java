package com.isa.fishingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.fishingapp.model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

}
