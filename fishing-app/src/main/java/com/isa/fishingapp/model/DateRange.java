package com.isa.fishingapp.model;

import java.time.LocalDateTime;

public class DateRange {
	private LocalDateTime from;
	private LocalDateTime to;
	
	DateRange(LocalDateTime from, LocalDateTime to) throws Exception {
		this.from = from;
		this.to = to;
		
		validate();
	}
	
	void validate() throws Exception {
		if (from.isAfter(to)) {
			throw new Exception("Invalid DateRange");
		}
	}
}
