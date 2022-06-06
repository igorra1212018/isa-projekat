package com.isa.fishingapp.dto;

public class EditFishingLessonDTO {

	private int id;
	private String name;
	private String description;
	private String biography;
	private String capacity;
	private String rules;
	private String price;
	private String cancelCondition;
	private String availableEquipment;
	
	private String image;
	
	private String userId;

	public EditFishingLessonDTO() {};
	
	public EditFishingLessonDTO(String name, String description, String biography, String capacity, String rules,
			String price, String cancelCondition, String userId, String availableEquipment, String image) {
		super();
		this.name = name;
		this.description = description;
		this.biography = biography;
		this.capacity = capacity;
		this.rules = rules;
		this.price = price;
		this.cancelCondition = cancelCondition;
		this.userId = userId;
		this.availableEquipment = availableEquipment;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCancelCondition() {
		return cancelCondition;
	}

	public void setCancelCondition(String cancelCondition) {
		this.cancelCondition = cancelCondition;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAvailableEquipment() {
		return availableEquipment;
	}

	public void setAvailableEquipment(String availableEquipment) {
		this.availableEquipment = availableEquipment;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
