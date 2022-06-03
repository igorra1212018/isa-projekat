package com.isa.fishingapp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.isa.fishingapp.dto.FishingLessonDTO;

@Entity
@DiscriminatorValue("FISHING_LESSON")
public class FishingLesson extends Reservable {
	
	private String availableEquipment;
	private String cancelCondition;
	private String biography;
	
	public FishingLesson() {};
	
	public FishingLesson(FishingLessonDTO fishingLessonDTO) {
		this.name = fishingLessonDTO.getName();
		this.description = fishingLessonDTO.getDescription();
		this.biography = fishingLessonDTO.getBiography();
		this.capacity = Integer.parseInt(fishingLessonDTO.getCapacity());
		this.rules = fishingLessonDTO.getRules();
		this.price = Integer.parseInt(fishingLessonDTO.getPrice());
		this.cancelCondition = fishingLessonDTO.getCancelCondition();
		this.availableEquipment = fishingLessonDTO.getAvailableEquipment();
		this.setAddress(new Location());
	}

	public String getCancelCondition() {
		return cancelCondition;
	}

	public void setCancelCondition(String cancelCondition) {
		this.cancelCondition = cancelCondition;
	}

	public String getAvailableEquipment() {
		return availableEquipment;
	}

	public void setAvailableEquipment(String availableEquipment) {
		this.availableEquipment = availableEquipment;
	}
}
