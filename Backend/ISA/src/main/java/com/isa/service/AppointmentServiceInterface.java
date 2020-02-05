package com.isa.service;

import java.util.List;

import com.isa.dto.AppointmentDTO;
import com.isa.entity.Appointment;

public interface AppointmentServiceInterface {

	Appointment findById(Integer id);
	
	List<Appointment> findPredefinedByClinicId(Integer id);
	
	List<AppointmentDTO> findFiltered(String doctorEmail, String patientEmail, Boolean approved, Integer clinicId, Boolean predefined);
}
