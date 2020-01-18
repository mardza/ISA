package com.isa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.isa.dto.AppointmentTypeDTO;

@Entity
@Table(name = "appointmenttypes")
public class AppointmentType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "name", unique = true, nullable = false, length = 65)
	private String name;
	
	
	public AppointmentType() {}
	
	public AppointmentType(AppointmentTypeDTO appointmentTypeDTO) {
		this.id = appointmentTypeDTO.getId();
		this.name = appointmentTypeDTO.getName();
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

	@Override
	public String toString() {
		return "AppointmentType [id=" + id + ", name=" + name + "]";
	}
}
