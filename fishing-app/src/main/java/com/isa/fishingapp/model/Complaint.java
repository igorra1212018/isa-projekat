package com.isa.fishingapp.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Complaint {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "reservable_id")
    private Reservable reservable;
    
    @Column(length=4095)
	private String description;
    
    @Column(length=4095)
   	private String response;
    
    private boolean approved = false;

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

	@JsonIgnore
	public Reservable getReservable() {
		return reservable;
	}

	public String getDescription() {
		return description;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setReservable(Reservable reservable) {
		this.reservable = reservable;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
