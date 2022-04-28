package com.isa.fishingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.dto.ReservableSearchDTO;
import com.isa.fishingapp.model.Reservable;
import com.isa.fishingapp.repository.ReservableRepository;

@Service
public class ReservableService<T extends Reservable> {
	@Autowired
	private ReservableRepository<T> reservableRepository;
	
	public T findById(Integer id)
	{
		return reservableRepository.findById(id).orElse(null);
	}
	
	public List<T> findAll()
	{
		return reservableRepository.findAll();
	}
	
	public T findByReservableId(Integer id)
	{
		return reservableRepository.findById(id).orElse(null);
	}
	
	public List<T> findAll(ReservableSearchDTO searchParameters)
	{
		return reservableRepository.findByName(searchParameters.getName());
	}
}
