package com.isa.fishingapp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "boat")
@DiscriminatorValue("BOAT")
public class Boat extends Reservable {
	
}
