package com.isa.fishingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.dto.ReservableSearchDTO;
import com.isa.fishingapp.model.Country;
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
	
	public Page<T> findAll(ReservableSearchDTO searchParameters, Pageable pageable)
	{
		if(searchParameters == null)
			return reservableRepository.findAll(pageable);
		Country country = searchParameters.getLocation().getCountry();
		Integer countryId = null;
		System.out.println(searchParameters);
		if(country != null)
			countryId = country.getId();
		if(searchParameters.getDateRange() == null || searchParameters.getDateRange().getFromDate() == null || searchParameters.getDateRange().getToDate() == null) {
			if(countryId != null && countryId != 0)
				return reservableRepository.findByReservableTypeAndNameContainingIgnoreCaseAndAddressCountryIdAndAddressCityContainingIgnoreCase(discriminatorString, searchParameters.getName(), countryId, searchParameters.getLocation().getCity(), pageable);
			return reservableRepository.findByReservableTypeAndNameContainingIgnoreCaseAndAddressCityContainingIgnoreCase(discriminatorString, searchParameters.getName(), searchParameters.getLocation().getCity(), pageable);
		}
		return reservableRepository.findBySearch(discriminatorString, searchParameters.getName(), searchParameters.getLocation().getCity(), countryId, searchParameters.getDateRange().getFromDate(), searchParameters.getDateRange().getToDate(), pageable);
	}
	
	public T findByReservableId(Integer id)
	{
		return reservableRepository.findById(id).orElse(null);
	}
	
	public T registerReservable(T reservable)
	{
		return reservableRepository.save(reservable);
	}
	
	public boolean isUserSubscribed(Integer userId, Integer reservableId)
	{
		if(reservableRepository.isUserSubscribed(userId, reservableId).orElse(null) != null)
			return true;
		return false;
	}

	public void save(T reservable) {
		reservableRepository.save(reservable);
	}
}
