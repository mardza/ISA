package com.isa.service;

import java.util.List;

import com.isa.dto.AppointmentTypeDTO;
import com.isa.entity.AppointmentType;

public interface AppointmentTypeServiceInterface {

	List<AppointmentType> findAll();
	
	AppointmentType findById(Integer id);
	
	AppointmentType create(AppointmentTypeDTO appointmentTypeDTO);
	
	AppointmentType update(Integer id, AppointmentTypeDTO appointmentTypeDTO);
	
	void remove(AppointmentType appointmentType);
}
