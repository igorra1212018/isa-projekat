package com.isa.fishingapp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

	public LocalDateTime getFromDate() {
		return fromDate;
	}

	public LocalDateTime getToDate() {
		return toDate;
	}
	
	public static boolean dateRangesOverlap(DateRange dateRange1, DateRange dateRange2) {
		if(dateRange1.getToDate().isBefore(dateRange2.getFromDate()) || dateRange1.getFromDate().isAfter(dateRange2.getToDate()))
			return false;
		return true;
	}
	
	public List<DateRange> splitByDateRange(DateRange dateRangeToSplitBy) throws Exception {
		List<DateRange> result = new ArrayList<>();
		if(!dateRangesOverlap(this, dateRangeToSplitBy))
		{
			result.add(this);
			return result;
		}
		DateRange newDateRange1 = new DateRange((fromDate.isBefore(dateRangeToSplitBy.getFromDate()) ? fromDate : dateRangeToSplitBy.getFromDate()), dateRangeToSplitBy.getFromDate());
		System.out.println("DR1: " + newDateRange1.getFromDate().getYear() + " " + newDateRange1.getToDate().getYear());
		DateRange newDateRange2 = new DateRange(dateRangeToSplitBy.getToDate(), toDate);
		System.out.println("DR2: " + newDateRange2.getFromDate().getYear() + " " + newDateRange2.getToDate().getYear());
		if(!newDateRange1.getFromDate().equals(newDateRange1.getToDate()))
			result.add(newDateRange1);
		if(!newDateRange2.getFromDate().equals(newDateRange2.getToDate()))
			result.add(newDateRange2);
		return result;
	}
	
	public List<DateRange> splitByDateRange(List<DateRange> dateRangesToSplitBy) throws Exception {
		List<DateRange> previousResult = new ArrayList<>();
		List<DateRange> newResult = new ArrayList<>();
		newResult.add(this);
		for(DateRange dateRangeToSplitBy : dateRangesToSplitBy) {
			previousResult = newResult;
			newResult = new ArrayList<>();
			for(DateRange dateRangeToSplit : previousResult) {
				List<DateRange> tmpResult = dateRangeToSplit.splitByDateRange(dateRangeToSplitBy);
				for(DateRange tr : tmpResult)
					newResult.add(tr);
			}
		}
		return newResult;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fromDate == null) ? 0 : fromDate.hashCode());
		result = prime * result + ((toDate == null) ? 0 : toDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DateRange other = (DateRange) obj;
		if (fromDate == null) {
			if (other.fromDate != null)
				return false;
		} else if (!fromDate.equals(other.fromDate))
			return false;
		if (toDate == null) {
			if (other.toDate != null)
				return false;
		} else if (!toDate.equals(other.toDate))
			return false;
		return true;
	}
	
	
}
