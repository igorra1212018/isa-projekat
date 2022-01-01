package com.isa.fishingapp.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Lodgings")
public class Lodging {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	@Embedded
	private Location residence;
	
	@ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Location getResidence() {
		return residence;
	}

	public User getOwner() {
		return owner;
	}
}
