package com.isa.fishingapp.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "fishing_lesson")
public class FishingLesson {
	
	@Id
    @SequenceGenerator(name = "fishing_lesson_sequence_generator", sequenceName = "fishing_lesson_sequence", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fishing_lesson_sequence_generator")
    @Column(name = "id", unique = true)
	private Integer id;
	
	private String name;
	private String description;
	
	@Embedded
	private Location address;
	
	private int capacity; // I termin i lesson imaju capacity?
	private String rules;
	private String availableEquipment;
	private String pricelist;
	private String cancelCondition;
	
	@ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner; //User for now

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

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Location getAddress() {
		return address;
	}

	public void setAddress(Location address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
