package com.isa.fishingapp.model;

import java.beans.Transient;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="reservable_type")
@Table(name="reservable")
public abstract class Reservable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	
	@Column(name = "reservable_type", insertable=false, updatable=false, nullable = false)
	protected String reservableType;
	
	@ManyToOne
    @JoinColumn(name = "owner_id")
    protected User owner;
	
	protected String name;
	@Column(length=4095)
	protected String description;
	@Column(length=4095)
	protected String rules;
	@Embedded
	protected Location address;
	protected int price;
	protected int capacity;
	
	@OneToMany(cascade = CascadeType.DETACH)
	@JoinColumn(name = "reserable_id")
	private Set<AvailableDateRange> availableDateRanges;
	
	@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "primary_image_id")
	private Image primaryImage;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "reservable_id")
	private Set<Image> images;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "reservable_id")
	private Set<ReservableAmenity> amenities;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "reservable_id")
	private Set<Review> reviews;

	public String getName() {
		return name;
	}
	
	public Integer getId() {
		return id;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public Location getAddress() {
		return address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	public Image getPrimaryImage() {
		return primaryImage;
	}

	public void setPrimaryImage(Image primaryImage) {
		this.primaryImage = primaryImage;
	}

	public int getPrice() {
		return price;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(Location address) {
		this.address = address;
	}

	public void setPrice(int basePrice) {
		this.price = basePrice;
	}

	public Set<ReservableAmenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(Set<ReservableAmenity> amenities) {
		this.amenities = amenities;
	}
	
	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public Set<AvailableDateRange> getAvailableDateRanges() {
		return availableDateRanges;
	}

	public void setAvailableDateRanges(Set<AvailableDateRange> availableDateRanges) {
		this.availableDateRanges = availableDateRanges;
	}
	
	public String getReservableType() {
		return reservableType;
	}

	public void setReservableType(String reservableType) {
		this.reservableType = reservableType;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	@Transient
	public String getDiscriminatorValue(){
	    DiscriminatorValue val = this.getClass().getAnnotation( DiscriminatorValue.class );

	    return val == null ? null : val.value();
	}
}
