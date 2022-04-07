package com.isa.fishingapp.model;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

@Embeddable
public class DateRange {
	private final LocalDateTime fromDate;
	private final LocalDateTime toDate;
	
	public DateRange() {
		//No validation for now
		fromDate = null;
		toDate = null;
	}
	
	public DateRange(LocalDateTime from, LocalDateTime to) throws Exception {
		this.fromDate = from;
		this.toDate = to;
		
		validate();
	}
	
	void validate() throws Exception {
		if (fromDate.isAfter(toDate)) {
			throw new Exception("Invalid DateRange");
		}
	}
}
