package com.isa.fishingapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.isa.fishingapp.model.enums.ERequestApproval;

@Entity
@Table(name="user_deletion_request")
public class UserDeletionRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	private ERequestApproval requestApproval;
	
	@NotBlank
	String requestDescription;
	String rejectionResponse;
	
	public UserDeletionRequest() {}

	public ERequestApproval getRequestApproval() {
		return requestApproval;
	}

	public void setRequestApproval(ERequestApproval requestApproval) {
		this.requestApproval = requestApproval;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRequestDescription() {
		return requestDescription;
	}

	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
	}

	public String getRejectionResponse() {
		return rejectionResponse;
	}

	public void setRejectionResponse(String rejectionResponse) {
		this.rejectionResponse = rejectionResponse;
	}
	
	
}
