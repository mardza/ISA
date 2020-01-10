package com.isa.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;


// TODO: single role per user

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

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles;
	
	private Double ratingAverage;
	private Integer ratingWeight;
	
	private Boolean passwordSet;

	
	public User() {
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
	@Override
	public String getUsername() {
		return this.email;
	}
	
	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
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
		return true;
	}
	
	
	@Override
	public String toString() {
		return "User ["
				+ "id=" + id + ", "
				+ "firstName=" + firstName + ", "
				+ "lastname=" + lastName + ", "
				+ "email=" + email + ", "
				+ "password=" + password + ", "
				+ "address=" + address + ", "
				+ "city=" + city + ", "
				+ "country=" + country + ", "
				+ "phone=" + phone + ", "
				+ "insuranceNumber=" + insuranceNumber
				+ "]";
	}
}
