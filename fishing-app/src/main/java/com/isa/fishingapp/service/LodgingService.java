package com.isa.fishingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.model.Lodging;
import com.isa.fishingapp.repository.LodgingRepository;

@Service
public class LodgingService {
	@Autowired
	private LodgingRepository lodgingRepository;
	
	public Lodging registerLodging(Lodging lodging)
	{
		return lodgingRepository.save(lodging);
	}
	
	public List<Lodging> getAllLodgings()
	{
		return lodgingRepository.findAll();
	}
}
