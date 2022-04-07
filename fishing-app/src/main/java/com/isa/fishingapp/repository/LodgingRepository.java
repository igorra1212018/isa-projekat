package com.isa.fishingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.fishingapp.model.Lodging;
import com.isa.fishingapp.model.Owner;

public interface LodgingRepository extends JpaRepository<Lodging, Integer> {

	List<Lodging> findByOwnerId(Integer ownerId);
}
