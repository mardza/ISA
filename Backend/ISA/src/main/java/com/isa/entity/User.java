package com.isa.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.dto.UserDTO;

@SuppressWarnings("serial")
@Entity
@Table(name = "users")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "email", unique = true, nullable = false, length = 65)
	private String email;

	@Column(name = "password", unique = false, nullable = false, length = 65)
	private String password;

	@Column(name = "firstName", unique = false, nullable = false, length = 65)
	private String firstName;

	@Column(name = "lastName", unique = false, nullable = false, length = 65)
	private String lastName;

	@Column(name = "address", unique = false, nullable = false, length = 65)
	private String address;

	@Column(name = "city", unique = false, nullable = false, length = 65)
	private String city;

	@Column(name = "country", unique = false, nullable = false, length = 65)
	private String country;

	@Column(name = "phone", unique = false, nullable = false, length = 65)
	private String phone;

	@Column(name = "insuranceNumber", unique = false, nullable = true, length = 65)
	private String insuranceNumber;

	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id", unique = false, nullable = false)
	private Role role;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "registration_id", referencedColumnName = "id", unique = true, nullable = false)
	private Registration registration;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "work_calendar_id", referencedColumnName = "id", unique = true, nullable = true)
	private WorkCalendar workCalendar;
	
	@ManyToOne
	@JoinColumn(name = "clinic_id", referencedColumnName = "id", nullable = true)
	private Clinic clinic;
	
	@ManyToOne
	@JoinColumn(name = "appointmenttype_id", referencedColumnName = "id", nullable = true)
	private AppointmentType specialisation;
	
	@OneToMany(mappedBy = "doctor")
	private List<Appointment> doctorAppointmentList;
	
	
	@Column(name = "workStart", unique = false, nullable = true)
	private Integer workStart;
	
	@Column(name = "workEnd", unique = false, nullable = true)
	private Integer workEnd;

	private Double ratingAverage;
	private Integer ratingWeight;

	private Boolean passwordSet;

	
	public User() {
	}

	public User(UserDTO userDTO) {
		id = userDTO.getId();
		email = userDTO.getEmail();
		password = userDTO.getPassword();
		firstName = userDTO.getFirstName();
		lastName = userDTO.getLastName();
		address = userDTO.getAddress();
		city = userDTO.getCity();
		country = userDTO.getCountry();
		phone = userDTO.getPhone();
		insuranceNumber = userDTO.getInsuranceNumber();
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public WorkCalendar getWorkCalendar() {
		return workCalendar;
	}

	public void setWorkCalendar(WorkCalendar workCalendar) {
		this.workCalendar = workCalendar;
	}

	public AppointmentType getSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(AppointmentType specialisation) {
		this.specialisation = specialisation;
	}

	public List<Appointment> getDoctorAppointmentList() {
		return doctorAppointmentList;
	}

	public void setDoctorAppointmentList(List<Appointment> doctorAppointmentList) {
		this.doctorAppointmentList = doctorAppointmentList;
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

	@Override
	public String getUsername() {
		return this.email;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return java.util.Arrays.asList(role);
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.registration.getActivated();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.registration.getApproved();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", address=" + address + ", city=" + city + ", country=" + country
				+ ", phone=" + phone + ", insuranceNumber=" + insuranceNumber + ", role=" + role + ", registration="
				+ registration + ", WorkCalendar=" + workCalendar + ", clinic=" + clinic
				+ ", ratingAverage=" + ratingAverage + ", ratingWeight=" + ratingWeight + ", passwordSet=" + passwordSet
				+ "]";
	}
}
