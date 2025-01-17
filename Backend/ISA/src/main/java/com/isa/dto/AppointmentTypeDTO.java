package com.isa.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import com.isa.entity.AppointmentType;

public class AppointmentTypeDTO {
	
	private Integer id;
	
	@NotBlank
	private String name;
	
	private Long duration;
	
	
	public AppointmentTypeDTO() {}
	
	public AppointmentTypeDTO(AppointmentType appointmentType) {
		this.id = appointmentType.getId();
		this.name = appointmentType.getName();
		this.duration = appointmentType.getDuration();
	}
	
	public static List<AppointmentTypeDTO> toList(List<AppointmentType> appointmentTypeList) {
		return appointmentTypeList.stream().map(appointmentType -> new AppointmentTypeDTO(appointmentType)).collect(Collectors.toList());
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

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "AppointmentTypeDTO [id=" + id + ", name=" + name + ", duration=" + duration + "]";
	}
}
