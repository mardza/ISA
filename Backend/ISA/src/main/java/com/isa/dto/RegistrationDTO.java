package com.isa.dto;

import com.isa.entity.Registration;

public class RegistrationDTO {
	
	private String id;
	
	private Boolean approved;
	
	private Boolean activated;
	
	
	public RegistrationDTO() {}
	
	public RegistrationDTO(Registration registration) {
		this.id = registration.getId();
		this.approved = registration.getApproved();
		this.activated = registration.getActivated();
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}
}
