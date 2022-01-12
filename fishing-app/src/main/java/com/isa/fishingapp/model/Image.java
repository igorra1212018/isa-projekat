package com.isa.fishingapp.model;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {
	@Id
    @SequenceGenerator(name = "image_sequence_generator", sequenceName = "image_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_sequence_generator")
    private Integer id;
    
    private String imageName;
    private byte[] data;
    
	public Image(Integer id, String imageName, byte[] data) {
		this.id = id;
		this.imageName = imageName;
		this.data = data;
	}
	
	public Image(String imageName, byte[] data) {
		this.imageName = imageName;
		this.data = data;
	}
	
	public Integer getId() {
		return id;
	}
	public String getImageName() {
		return imageName;
	}
	public byte[] getData() {
		return data;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
}
