package com.isa.fishingapp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lodging")
@DiscriminatorValue("LODGING")
public class Lodging extends Reservable {
	
}
