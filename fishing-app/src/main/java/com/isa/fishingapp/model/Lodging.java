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
@Table(name = "lodging")
public class Lodging {

	@Id
    @SequenceGenerator(name = "lodging_sequence_generator", sequenceName = "lodging_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lodging_sequence_generator")
    @Column(name = "id", unique = true)
	private Integer id;
	
	private String name;
	private String description;
	@Embedded
	private Location address;
	
	@ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Location getAddress() {
		return address;
	}

	public User getOwner() {
		return owner;
	}
}
