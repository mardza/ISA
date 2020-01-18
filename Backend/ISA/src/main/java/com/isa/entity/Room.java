package com.isa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.isa.dto.RoomDTO;

@Entity
@Table(name = "rooms")
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "number", unique = true, nullable = false, length = 65)
	private String number;
	
	@Column(name = "name", unique = false, nullable = false, length = 65)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "clinic_id", referencedColumnName = "id", nullable = false)
	private Clinic clinic;
	
	
	public Room() {}
	
	public Room(RoomDTO roomDTO) {
		this.id = roomDTO.getId();
		this.number = roomDTO.getNumber();
		this.name = roomDTO.getName();
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", number=" + number + ", name=" + name + "]";
	}
}
