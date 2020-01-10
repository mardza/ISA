package com.isa.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.isa.entity.User;

public class UserDTO {

	private Integer id;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@NotBlank
	private String address;
	
	@NotBlank
	private String city;
	
	@NotBlank
	private String country;
	
	@NotBlank
	private String phone;
	
	@NotBlank
	private String insuranceNumber;
	
	
	public UserDTO() {}
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.address = user.getAddress();
		this.city = user.getCity();
		this.country = user.getCountry();
		this.phone = user.getPhone();
		this.insuranceNumber = user.getInsuranceNumber();
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}


	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", address=" + address + ", city=" + city + ", country=" + country
				+ ", phone=" + phone + ", insuranceNumber=" + insuranceNumber + "]";
	}
	
}
