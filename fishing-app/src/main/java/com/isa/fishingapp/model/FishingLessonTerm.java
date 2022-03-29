package com.isa.fishingapp.model;

import java.time.LocalDateTime;
import java.util.List;

public class FishingLessonTerm {
	private LocalDateTime dateTime;
	private int duration;
	private int capacity;
	private List<String> additionalServices;
	private int price;
	private boolean isCanceled;
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public List<String> getAdditionalServices() {
		return additionalServices;
	}
	public void setAdditionalServices(List<String> additionalServices) {
		this.additionalServices = additionalServices;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public boolean isCanceled() {
		return isCanceled;
	}
	public void setCanceled(boolean isCanceled) {
		this.isCanceled = isCanceled;
	}
}
