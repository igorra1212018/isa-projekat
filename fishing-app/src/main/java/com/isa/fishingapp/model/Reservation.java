package com.isa.fishingapp.model;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.DiscriminatorType;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="reservation_type")
@Table(name="reservation")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private User user;
	@Embedded
	private DateRange dateRange;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "reserved_entity_id")
    private Reservable reservedEntity;
	
	private boolean cancelled;

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public Integer getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public DateRange getDateRange() {
		return dateRange;
	}

	public Reservable getReservedEntity() {
		return reservedEntity;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}

	public void setReservedEntity(Reservable reservedEntity) {
		this.reservedEntity = reservedEntity;
	}
	
	public Reservation() {
	}
	
	public Reservation(User user, DateRange dateRange, Reservable reservedEntity) {
		this.user = user;
		this.dateRange = dateRange;
		this.reservedEntity = reservedEntity;
		this.cancelled = false;
	}
}
