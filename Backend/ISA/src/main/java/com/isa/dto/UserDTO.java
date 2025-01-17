package com.isa.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

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
	
	private RegistrationDTO registration;
	
	private String role;
	
	private String specialisation;
	
	private Integer workStart;
	
	private Integer workEnd;

	private Double ratingAverage;
	
	private Integer patientRating;
	
	
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
		this.role = user.getRole().getName();
		if(user.getSpecialisation() != null) {
			this.specialisation = user.getSpecialisation().getName();
		}
		this.workStart = user.getWorkStart();
		this.workEnd = user.getWorkEnd();
		this.ratingAverage = user.getRatingAverage();
	}
	
	public UserDTO(User user, Boolean fillRegistration) {
		this(user);
		if(fillRegistration) {
			this.registration = new RegistrationDTO(user.getRegistration());
		}
	}
	
	
	public static List<UserDTO> toList(List<User> userList) {
		return userList.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
	}
	
	public static List<UserDTO> toList(List<User> userList, Boolean fillRegistration) {
		return userList.stream().map(user -> new UserDTO(user, fillRegistration)).collect(Collectors.toList());
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

	//@JsonIgnore // if this is set then there is error 'password must not be empty'
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

	public RegistrationDTO getRegistration() {
		return registration;
	}

	public void setRegistration(RegistrationDTO registration) {
		this.registration = registration;
	}
	
	public String getRole() {
		return role;
	}

	public String getSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}

	public Integer getWorkStart() {
		return workStart;
	}

	public void setWorkStart(Integer workStart) {
		this.workStart = workStart;
	}

	public Integer getWorkEnd() {
		return workEnd;
	}

	public void setWorkEnd(Integer workEnd) {
		this.workEnd = workEnd;
	}

	public Double getRatingAverage() {
		return ratingAverage;
	}

	public void setRatingAverage(Double ratingAverage) {
		this.ratingAverage = ratingAverage;
	}

	public Integer getPatientRating() {
		return patientRating;
	}

	public void setPatientRating(Integer patientRating) {
		this.patientRating = patientRating;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", address=" + address + ", city=" + city + ", country=" + country
				+ ", phone=" + phone + ", insuranceNumber=" + insuranceNumber + "]";
	}
	
}
