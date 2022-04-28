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
import com.isa.fishingapp.model.enums.ERequestType;

@Entity
@Table(name="user_creation_request")
public class UserCreationRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	private ERequestType requestType;
	private ERequestApproval requestApproval;
	
	@NotBlank
	String requestDescription;
	String rejectionResponse;
	
	public UserCreationRequest(User user, ERequestType requestType, String requestDescription) {
		this.user = user;
		this.setRequestType(requestType);
		this.setRequestApproval(ERequestApproval.PENDING);
		this.requestDescription = requestDescription;
	}

	public ERequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(ERequestType requestType) {
		this.requestType = requestType;
	}

	public ERequestApproval getRequestApproval() {
		return requestApproval;
	}

	public void setRequestApproval(ERequestApproval requestApproval) {
		this.requestApproval = requestApproval;
	}
}
