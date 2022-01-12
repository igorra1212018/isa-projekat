package com.isa.fishingapp.model;

import javax.persistence.*;

@Entity
@Table(name = "review_lodging")
public class ReviewLodging extends Review {
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "lodging_id")
    private Lodging lodging;

    public ReviewLodging() {
    }

    public ReviewLodging(Integer id, User user, int rating, Lodging lodging) {
        super(id, user, rating);
        this.lodging = lodging;
    }

    public ReviewLodging(User user, int rating, Lodging lodging) {
        super(user, rating);
        this.lodging = lodging;
    }

	public Lodging getLodging() {
		return lodging;
	}

	public void setLodging(Lodging lodging) {
		this.lodging = lodging;
	}

}