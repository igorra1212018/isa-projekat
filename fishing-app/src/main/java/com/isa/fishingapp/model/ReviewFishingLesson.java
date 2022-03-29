package com.isa.fishingapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "review_fishing_lesson")
public class ReviewFishingLesson extends Review {
	@ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "fishing_lesson_id")
    private FishingLesson fishingLesson;

    public ReviewFishingLesson() {
    }

    public ReviewFishingLesson(Integer id, User user, int rating, FishingLesson fishingLesson) {
        super(id, user, rating);
        this.fishingLesson = fishingLesson;
    }

    public ReviewFishingLesson(User user, int rating, FishingLesson fishingLesson) {
        super(user, rating);
        this.fishingLesson = fishingLesson;
    }

	public FishingLesson getFishingLesson() {
		return fishingLesson;
	}

	public void setFishingLesson(FishingLesson fishingLesson) {
		this.fishingLesson = fishingLesson;
	}

}
