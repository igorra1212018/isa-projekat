package com.isa.fishingapp.model;

import javax.persistence.CascadeType;
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

import com.isa.fishingapp.dto.ReserveLodgingDTO;

@Entity
@Table(name = "reservation_lodging")
public class ReservationLodging {
	@Id
    @SequenceGenerator(name = "reservation_lodging_sequence_generator", sequenceName = "reservation_lodging_sequence", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_lodging_sequence_generator")
    @Column(name = "id", unique = true)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "lodging_id")
    private Lodging lodging;
	@ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private User user;
	@Embedded
	private DateRange dateRange;
	private boolean cancelled;
	public Integer getId() {
		return id;
	}
	public Lodging getLodging() {
		return lodging;
	}
	public DateRange getDateRange() {
		return dateRange;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setLodging(Lodging lodging) {
		this.lodging = lodging;
	}
	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}
	public boolean isCancelled() {
		return cancelled;
	}
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
