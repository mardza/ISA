package com.isa.service;

import java.util.List;

import com.isa.dto.AppointmentCreateDTO;
import com.isa.dto.AppointmentDTO;
import com.isa.entity.Appointment;

public interface AppointmentServiceInterface {

	AppointmentDTO findById(Integer id);
	
	List<AppointmentDTO> findPredefinedByClinicId(Integer id);
	
	List<AppointmentDTO> findFiltered(String doctorEmail, String patientEmail, Boolean approved, Integer clinicId, Boolean predefined, Boolean old);
	
	AppointmentDTO createAppointment(AppointmentCreateDTO appointmentCreateDTO);
	
	
	
	
	Appointment save(Appointment appointment);
	
	void remove(Appointment appointment);
}
