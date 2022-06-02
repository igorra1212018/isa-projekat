package com.isa.fishingapp.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.fishingapp.dto.UserDTO;
import com.isa.fishingapp.dto.UserProfileChangeDTO;


@Entity
@Table(name="\"user\"")
@AttributeOverride( name = "address", column = @Column(name = "residence_address"))
@AttributeOverride( name = "city", column = @Column(name = "residence_city"))
@AttributeOverride( name = "country", column = @Column(name = "residence_country"))
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@NotBlank
	@Size(max = 50)
	@Email
	String email;
	
	@NotBlank
	@Size(max = 120)
	String password;
	String firstName;
	String lastName;
	@Embedded
	Location residence;
	String contactPhone;
	
	Integer penaltyPoints = 0;
	LocalDateTime lastPenaltyPointDate;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	boolean activated = false;
	
	public User() { }

	public User(UserDTO user) {
		if(user.getId() != null)
			this.id = user.getId();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		//this.residence = new Location(user.getAddress(), user.getCity(), user.getCountry(), 0, 0);
		this.contactPhone = user.getContactPhone();
	}
	
	public User(UserProfileChangeDTO user) {
		if(user.getId() != null)
			this.id = user.getId();
		this.email = user.getEmail();
		if(user.getNewPassword() != null && !user.getNewPassword().isBlank())
			this.password = user.getNewPassword();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.residence = new Location(user.getAddress(), user.getCity(), user.getCountry(), 0, 0);
		this.contactPhone = user.getContactPhone();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Location getResidence() {
		return residence;
	}

	public void setResidence(Location residence) {
		this.residence = residence;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> userRoles) {
		this.roles = userRoles;
	}

	public Integer getPenaltyPoints() {
		return penaltyPoints;
	}

	public void setPenaltyPoints(Integer penaltyPoints) {
		this.penaltyPoints = penaltyPoints;
	}

	public LocalDateTime getLastPenaltyPointDate() {
		return lastPenaltyPointDate;
	}

	public void setLastPenaltyPointDate(LocalDateTime lastPenaltyPointDate) {
		this.lastPenaltyPointDate = lastPenaltyPointDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (activated ? 1231 : 1237);
		result = prime * result + ((contactPhone == null) ? 0 : contactPhone.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((residence == null) ? 0 : residence.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (activated != other.activated)
			return false;
		if (contactPhone == null) {
			if (other.contactPhone != null)
				return false;
		} else if (!contactPhone.equals(other.contactPhone))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (residence == null) {
			if (other.residence != null)
				return false;
		} else if (!residence.equals(other.residence))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", residence=" + residence + ", contactPhone=" + contactPhone + ", activated=" + activated + "]";
	}
	
}
