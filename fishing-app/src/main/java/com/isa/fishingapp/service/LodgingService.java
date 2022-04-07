package com.isa.fishingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.model.Lodging;
import com.isa.fishingapp.model.Owner;
import com.isa.fishingapp.repository.LodgingRepository;
import com.isa.fishingapp.repository.OwnerRepository;

@Service
public class LodgingService {
	@Autowired
	private LodgingRepository lodgingRepository;
	@Autowired
	private OwnerRepository ownerRepository;
	
	public Lodging registerLodging(Lodging lodging)
	{
		return lodgingRepository.save(lodging);
	}
	
	public List<Lodging> getAllLodgings()
	{
		return lodgingRepository.findAll();
	}
	
	public List<Lodging> getOwnerLodgings(int ownerId)
	{
		Optional<Owner> owner = ownerRepository.findById(ownerId); //Sta god ovo optional bilo
		return lodgingRepository.findByOwnerId(ownerId);
	}
}
