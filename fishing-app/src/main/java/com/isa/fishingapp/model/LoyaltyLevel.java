package com.isa.fishingapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LoyaltyLevel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String levelName;
	private String levelThreshold;
	private float userDiscount;
	private float ownerDiscount;
	private int userPointsAdd;
	private int ownerPointsAdd;
	
	public LoyaltyLevel() {};
	
	public LoyaltyLevel(String levelName, String levelThreshold, 
			float userDiscount, float ownerDiscount, int userPointsAdd, int ownerPointsAdd) {
		super();
		this.levelName = levelName;
		this.levelThreshold = levelThreshold;
		this.userDiscount = userDiscount;
		this.ownerDiscount = ownerDiscount;
		this.userPointsAdd = userPointsAdd;
		this.ownerPointsAdd = ownerPointsAdd;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getLevelThreshold() {
		return levelThreshold;
	}
	public void setLevelThreshold(String levelThreshold) {
		this.levelThreshold = levelThreshold;
	}
	public int getUserPointsAdd() {
		return userPointsAdd;
	}
	public void setUserPointsAdd(int userPointAdd) {
		this.userPointsAdd = userPointAdd;
	}
	public int getOwnerPointsAdd() {
		return ownerPointsAdd;
	}
	public void setOwnerPointsAdd(int ownerPointAdd) {
		this.ownerPointsAdd = ownerPointAdd;
	}
	public float getUserDiscount() {
		return userDiscount;
	}
	public void setUserDiscount(float userDiscount) {
		this.userDiscount = userDiscount;
	}
	public float getOwnerDiscount() {
		return ownerDiscount;
	}
	public void setOwnerDiscount(float ownerDiscount) {
		this.ownerDiscount = ownerDiscount;
	}
	
	
}
