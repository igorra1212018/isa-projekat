package com.isa.fishingapp.model;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class FishingLesson {
	private String name;
	private Location location;
	private String description;
	private List<Image> images;
	private int capacity; // I termin i lesson imaju capacity?
	private List<FishingLessonTerm> promotionTerms;
	private String rules;
	private String availableEquipment;
	private String pricelist;
	private String cancelCondition;
	private List<Integer> ratings;
	
	public FishingLesson() {}

	public String getCancelCondition() {
		return cancelCondition;
	}

	public void setCancelCondition(String cancelCondition) {
		this.cancelCondition = cancelCondition;
	}

	public String getPricelist() {
		return pricelist;
	}

	public void setPricelist(String pricelist) {
		this.pricelist = pricelist;
	}

	public String getAvailableEquipment() {
		return availableEquipment;
	}

	public void setAvailableEquipment(String availableEquipment) {
		this.availableEquipment = availableEquipment;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public List<FishingLessonTerm> getPromotionTerms() {
		return promotionTerms;
	}

	public void setPromotionTerms(List<FishingLessonTerm> promotionTerms) {
		this.promotionTerms = promotionTerms;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
