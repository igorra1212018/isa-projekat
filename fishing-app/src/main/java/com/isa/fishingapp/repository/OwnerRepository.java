package com.isa.fishingapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.fishingapp.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {

	Optional<Owner> findByEmailAndPassword(String email, String password);
}
