package com.isa.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import com.isa.entity.Clinic;

public class ClinicDTO {

	private Integer id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String address;
	
	@NotBlank
	private String description;
	
	
	public ClinicDTO() {}
	
	public ClinicDTO(Clinic clinic) {
		this.id = clinic.getId();
		this.name = clinic.getName();
		this.address = clinic.getAddress();
		this.description = clinic.getDescription();
	}
	
	public static List<ClinicDTO> toList(List<Clinic> clinicList) {
		return clinicList.stream().map(clinic -> new ClinicDTO(clinic)).collect(Collectors.toList());
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

	@Override
	public String toString() {
		return "ClinicDTO [id=" + id + ", name=" + name + ", address=" + address + ", description=" + description + "]";
	}
}
