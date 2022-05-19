package com.isa.fishingapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="country")
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String iso;
	private String name;
	private String shortName;
	private String iso3;
	@Column(nullable = true)
	private Integer numCode;
	@Column(nullable = true)
	private Integer phoneCode;
	
	public Integer getId() {
		return id;
	}
	public String getIso() {
		return iso;
	}
	public String getName() {
		return name;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setIso(String iso) {
		this.iso = iso;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public String getIso3() {
		return iso3;
	}
	public Integer getNumCode() {
		return numCode;
	}
	public Integer getPhoneCode() {
		return phoneCode;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}
	public void setNumCode(Integer numCode) {
		this.numCode = numCode;
	}
	public void setPhoneCode(Integer phoneCode) {
		this.phoneCode = phoneCode;
	}
}
