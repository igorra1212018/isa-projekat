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
	protected ReservableRepository<T> reservableRepository;
	private String discriminatorString;
	
	public ReservableService() {
	}
	
	public ReservableService(String discriminatorString) {
		this.discriminatorString = discriminatorString;
	}
	
	public T findById(Integer id)
	{
		return reservableRepository.findById(id).orElse(null);
	}
	
	public List<T> findAll()
	{
		return reservableRepository.findAll(discriminatorString);
	}
	
	public List<T> findAll(ReservableSearchDTO searchParameters)
	{
		System.out.println(searchParameters.toString());
		if(searchParameters.getDateFrom() == null || searchParameters.getDateTo() == null)
			return reservableRepository.findByReservableTypeAndNameContainingIgnoreCaseAndAddressCountryContainingIgnoreCaseAndAddressCityContainingIgnoreCase(discriminatorString, searchParameters.getName(), searchParameters.getLocation().getCountry(), searchParameters.getLocation().getCity());
		return reservableRepository.findBySearch(discriminatorString, searchParameters.getName(), null, null);
	}
	
	public T findByReservableId(Integer id)
	{
		return reservableRepository.findById(id).orElse(null);
	}
	
	public T registerReservable(T reservable)
	{
		return reservableRepository.save(reservable);
	}
}
