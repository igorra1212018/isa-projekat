package com.isa.fishingapp.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "review")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "reservable_id")
    private Reservable reservable;
    
    @Min(1)
    @Max(5)
    private int rating;
    @Column(length=4095)
	private String description;
    
    private boolean approved = false;

    public Review() {
    }

    public Review(Integer id, User user, int rating) {
        this.id = id;
        this.user = user;
        this.rating = rating;
    }

    public Review(User user, int rating) {
        this.user = user;
        this.rating = rating;
    }

	public Integer getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public int getRating() {
		return rating;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

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
}

