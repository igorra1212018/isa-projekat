package com.isa.fishingapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reservable_amenity")
public class ReservableAmenity {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	private String amenityName;
	private String amenityIcon;
	private float price;
	public Integer getId() {
		return id;
	}
	public String getAmenityName() {
		return amenityName;
	}
	public float getPrice() {
		return price;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAmenityIcon() {
		return amenityIcon;
	}
	public void setAmenityIcon(String amenityIcon) {
		this.amenityIcon = amenityIcon;
	}
	public void setAmenityName(String amenityName) {
		this.amenityName = amenityName;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}
