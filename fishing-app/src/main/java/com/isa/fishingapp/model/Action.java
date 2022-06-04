package com.isa.fishingapp.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.isa.fishingapp.dto.ActionDTO;

@Entity
@Table(name="action")
public class Action {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	
	private float discount;
	@ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "reservable_id")
    private Reservable reservable;
	private DateRange dateRange;
	private LocalDateTime validUntil;
	
	public Action(ActionDTO dto) {
		dto.transformToDate();
		discount = dto.getDiscount();
		try {
			dateRange = new DateRange(dto.getFromDate(), dto.getToDate());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		validUntil = dto.getValidUntilDate();
	}
	
	public Integer getId() {
		return id;
	}
	public float getDiscount() {
		return discount;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public Reservable getReservable() {
		return reservable;
	}
	public DateRange getDateRange() {
		return dateRange;
	}
	public void setReservable(Reservable reservable) {
		this.reservable = reservable;
	}
	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}
	public LocalDateTime getValidUntil() {
		return validUntil;
	}
	public void setValidUntil(LocalDateTime validUntil) {
		this.validUntil = validUntil;
	}
	
}
