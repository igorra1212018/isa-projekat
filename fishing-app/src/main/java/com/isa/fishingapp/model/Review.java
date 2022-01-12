package com.isa.fishingapp.model;

import javax.persistence.*;

@Entity
@Table(name = "review")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Review {
    @Id
    @SequenceGenerator(name = "review_sequence_generator", sequenceName = "review_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_sequence_generator")
    private Integer id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "rating")
    private int rating;

    protected Review() {
    }

    protected Review(Integer id, User user, int rating) {
        this.id = id;
        this.user = user;
        this.rating = rating;
    }

    protected Review(User user, int rating) {
        this.user = user;
        this.rating = rating;
    }

	public long getId() {
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
}

