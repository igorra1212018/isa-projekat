package com.isa.fishingapp.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "image_fishing_lesson")
public class ImageFishingLesson extends Image {
	@ManyToOne
    @JoinColumn(name = "fishing_lesson_id")
    private FishingLesson fishingLesson;
	
	public ImageFishingLesson() {
		
	}

    public ImageFishingLesson(Integer id, String imageName, byte[] data, FishingLesson fishingLesson) {
        super(id, imageName, data);
        this.fishingLesson = fishingLesson;
    }

    public ImageFishingLesson(String imageName, byte[] data, FishingLesson fishingLesson) {
    	super(imageName, data);
        this.fishingLesson = fishingLesson;
    }

	public FishingLesson getFishingLesson() {
		return fishingLesson;
	}

	public void setFishingLesson(FishingLesson fishingLesson) {
		this.fishingLesson = fishingLesson;
	}
}
