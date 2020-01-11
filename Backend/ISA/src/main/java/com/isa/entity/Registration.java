package com.isa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "registrations")
public class Registration {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	private String id;
	
	@OneToOne(mappedBy = "registration")
	private User user;
	
	@Column(name = "approved", unique = false, nullable = false)
	private Boolean approved;
	
	@Column(name = "activated", unique = false, nullable = false)
	private Boolean activated;
	
	
	public Registration() {
		this.approved = false;
		this.activated = false;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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


	@Override
	public String toString() {
		return "Registration [id=" + id + ", user=" + user + ", approved=" + approved + ", activated=" + activated
				+ "]";
	}
}
