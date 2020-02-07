package com.isa.service;

import java.util.List;

import com.isa.dto.AppointmentCreateDTO;
import com.isa.dto.AppointmentDTO;
import com.isa.entity.Appointment;

public interface AppointmentServiceInterface {

	AppointmentDTO findById(Integer id);
	
	//List<AppointmentDTO> findPredefinedByClinicId(Integer id);
	
	List<AppointmentDTO> findFiltered(String doctorEmail, String patientEmail, String adminEmail, Boolean approved, Integer clinicId, Boolean predefined, Boolean requested, Boolean old);
	
	List<AppointmentDTO> findAdminClinicAppointmentRequests();
	
	AppointmentDTO createAppointment(AppointmentCreateDTO appointmentCreateDTO);
	
	AppointmentDTO activateAppointment(Integer id);
	
	AppointmentDTO approveAppointment(Integer id);
	
	AppointmentDTO disapproveAppointment(Integer id);
	
	void cancelAppointment(Integer id);
		
	Appointment save(Appointment appointment);
	
	void remove(Appointment appointment);
}
