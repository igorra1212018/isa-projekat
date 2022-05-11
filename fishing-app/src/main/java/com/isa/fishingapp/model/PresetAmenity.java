package com.isa.fishingapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="amenity")
public class PresetAmenity {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	
	private String name;
	private String amenityIcon;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmenityIcon() {
		return amenityIcon;
	}

	public void setAmenityIcon(String amenityIcon) {
		this.amenityIcon = amenityIcon;
	}
}
