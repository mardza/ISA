package com.isa.service;

import java.util.List;

import com.isa.entity.Appointment;

public interface AppointmentServiceInterface {

	Appointment findById(Integer id);
	
	
	List<Appointment> findPredefinedByClinicId(Integer id);
}
