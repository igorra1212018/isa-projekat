package com.isa.fishingapp.model;

import javax.persistence.*;

@Entity
@Table(name = "image_lodging")
public class ImageLodging extends Image {
	@ManyToOne
    @JoinColumn(name = "lodging_id")
    private Lodging lodging;

	public ImageLodging() {
		
	}
	
    public ImageLodging(Integer id, String imageName, byte[] data, Lodging lodging) {
        super(id, imageName, data);
        this.lodging = lodging;
    }

    public ImageLodging(String imageName, byte[] data, Lodging lodging) {
    	super(imageName, data);
        this.lodging = lodging;
    }

	public Lodging getLodging() {
		return lodging;
	}

	public void setLodging(Lodging lodging) {
		this.lodging = lodging;
	}
}
