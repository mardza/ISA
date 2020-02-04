package com.isa.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.isa.dto.ClinicDTO;

@Entity
@Table(name = "clinics")
public class Clinic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "name", unique = true, nullable = false, length = 65)
	private String name;
	
	@Column(name = "address", unique = false, nullable = false, length = 65)
	private String address;
	
	@Column(name = "city", unique = false, nullable = false, length = 65)
	private String city;
	
	@Column(name = "country", unique = false, nullable = false, length = 65)
	private String country;
	
	//@Column(name = "latitude", unique = true, nullable = false, length = 65)
	//private Double latitude;
	
	//@Column(name = "longitude", unique = true, nullable = false, length = 65)
	//private Double longitude;
	
	@Column(name = "description", unique = true, nullable = false, length = 65)
	private String description;
	
	//private Double ratingAverage;
	//private Integer ratingWeight;
	
	@OneToMany(mappedBy = "clinic")
	private List<User> employees;
	
	@OneToMany(mappedBy = "clinic")
	private List<Room> rooms;
	
	@OneToMany(mappedBy = "clinic")
	private List<Price> priceList;
	
	@OneToMany(mappedBy = "clinic")
	private List<Appointment> appointmentList;
	
	@Column(name = "rating_average", unique = false, nullable = true)
	private Double ratingAverage;
	
	@Column(name = "rating_weight", unique = false, nullable = true)
	private Integer ratingWeight;
	
	
	public Clinic() {}
	
	public Clinic(ClinicDTO clinicDTO) {
		this.id = clinicDTO.getId();
		this.name = clinicDTO.getName();
		this.address = clinicDTO.getAddress();
		this.description = clinicDTO.getDescription();
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getEmployees() {
		return employees;
	}

	public void setEmployees(List<User> employees) {
		this.employees = employees;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public List<Price> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<Price> priceList) {
		this.priceList = priceList;
	}

	public List<Appointment> getAppointmentList() {
		return appointmentList;
	}

	public void setAppointmentList(List<Appointment> appointmentList) {
		this.appointmentList = appointmentList;
	}

	public Double getRatingAverage() {
		return ratingAverage;
	}

	public void setRatingAverage(Double ratingAverage) {
		this.ratingAverage = ratingAverage;
	}

	public Integer getRatingWeight() {
		return ratingWeight;
	}

	public void setRatingWeight(Integer ratingWeight) {
		this.ratingWeight = ratingWeight;
	}

	@Override
	public String toString() {
		return "Clinic [id=" + id + ", name=" + name + ", address=" + address + ", description=" + description
				+ ", employees=" + employees + ", rooms=" + rooms + ", priceList=" + priceList + "]";
	}
}
