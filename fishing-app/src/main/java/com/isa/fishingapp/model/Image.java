package com.isa.fishingapp.model;

import java.util.HashSet;
import java.util.Set;

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
    
    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "image_tags", 
				joinColumns = @JoinColumn(name = "image_id"), 
				inverseJoinColumns = @JoinColumn(name = "image_tag_id"))
	private Set<ImageTag> tags = new HashSet<>();
    
    public Image() {
    	
    }
    
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
	public Set<ImageTag> getTags() {
		return tags;
	}
	public void setTags(Set<ImageTag> tags) {
		this.tags = tags;
	}
	
}
