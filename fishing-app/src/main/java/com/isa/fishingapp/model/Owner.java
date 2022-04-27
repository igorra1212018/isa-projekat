package com.isa.fishingapp.model;

import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import com.isa.fishingapp.dto.OwnerDTO;
import com.isa.fishingapp.model.enums.Gender;
import com.isa.fishingapp.model.enums.ERole;

import net.bytebuddy.description.modifier.Ownership;

@Entity
@Table(name="\"owner\"")
@AttributeOverride( name = "address", column = @Column(name = "residence_address"))
@AttributeOverride( name = "city", column = @Column(name = "residence_city"))
@AttributeOverride( name = "country", column = @Column(name = "residence_country"))
//@AttributeOverride( name = "from", column = @Column(name = "availability_from"))
//@AttributeOverride( name = "to", column = @Column(name = "availability_to"))
public class Owner extends User{
	@Embedded
	private DateRange availability;
	private String applicationDetails;
	
	public DateRange getAvailability() {
		return availability;
	}
	public void setAvailability(DateRange availability) {
		this.availability = availability;
	}
	
	public Owner() { }

	public Owner(OwnerDTO owner) throws Exception {
		if(owner.getId() != null)
			this.id = owner.getId();
		this.email = owner.getEmail();
		this.password = owner.getPassword();
		this.firstName = owner.getFirstName();
		this.lastName = owner.getLastName();
		this.residence = new Location(owner.getAddress(), owner.getCity(), owner.getCountry(), 0, 0);
		this.contactPhone = owner.getContactPhone();
		this.setApplicationDetails(owner.getApplicationDetails());
		//this.getRoles().add(new Role());
		this.gender = Gender.valueOf(owner.getGender());
		this.availability = new DateRange(LocalDateTime.now(), LocalDateTime.now().plusYears(3)); //Plus 3 years
	}
	public String getApplicationDetails() {
		return applicationDetails;
	}
	public void setApplicationDetails(String applicationDetails) {
		this.applicationDetails = applicationDetails;
	}
	
}
