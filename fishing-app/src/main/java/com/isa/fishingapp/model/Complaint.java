package com.isa.fishingapp.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.isa.fishingapp.model.enums.ERequestApproval;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="complaint")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Integer.class)
public class Complaint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
    
    @Column(length=4095)
	private String description;
    
    @Column(length=4095)
   	private String response;
    
    private ERequestApproval approved = ERequestApproval.PENDING;

    public Complaint() {
    }

    public Complaint(Integer id, User user) {
        this.id = id;
        this.user = user;
    }

    public Complaint(User user) {
        this.user = user;
    }

	public Integer getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public String getDescription() {
		return description;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public ERequestApproval getApproved() {
		return approved;
	}

	public void setApproved(ERequestApproval approved) {
		this.approved = approved;
	}
	
}
