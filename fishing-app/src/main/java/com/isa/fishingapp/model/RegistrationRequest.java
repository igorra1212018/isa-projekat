package com.isa.fishingapp.model;

import javax.persistence.*;

@Entity
@Table(name = "registration_request")
public class RegistrationRequest {
	@Id
    @SequenceGenerator(name = "registration_request_sequence_generator", sequenceName = "registration_request_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "registration_request_sequence_generator")
	private Integer id;
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	private String description;
	private String rejectionReason;

    public RegistrationRequest() {
    }
    
    public RegistrationRequest(User user, String description) {
    	this.user = user;
    	this.description = description;
    }

	public User getUser() {
		return user;
	}

	public String getDescription() {
		return description;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
    
    
}
