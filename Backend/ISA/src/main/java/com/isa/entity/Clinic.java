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
	
	//@Column(name = "latitude", unique = true, nullable = false, length = 65)
	//private Double latitude;
	
	//@Column(name = "longitude", unique = true, nullable = false, length = 65)
	//private Double longitude;
	
	@Column(name = "description", unique = true, nullable = false, length = 65)
	private String description;
	
	//private Double ratingAverage;
	//private Integer ratingWeight;
	
	@OneToMany(mappedBy = "clinic")
	private List<User> users;
	
	@OneToMany(mappedBy = "clinic")
	private List<Room> rooms;
	
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return "Clinic [id=" + id + ", name=" + name + ", address=" + address + ", description=" + description + "]";
	}
}
