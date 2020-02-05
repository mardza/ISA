package com.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.controller.exception.custom.EntityNotFoundException;
import com.isa.dto.AppointmentDTO;
import com.isa.entity.Appointment;
import com.isa.repository.AppointmentRepository;

@Service
public class AppointmentService implements AppointmentServiceInterface {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	
	@Override
	public Appointment findById(Integer id) {
		return this.appointmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Appointment with id '" + id + "' not found"));
	}
	
	@Override
	public List<Appointment> findPredefinedByClinicId(Integer id) {
		return this.appointmentRepository.findPredefinedByClinicId(id);
	}
	
	@Override
	public List<AppointmentDTO> findFiltered(String doctorEmail, String patientEmail, Boolean approved, Integer clinicId, Boolean predefined) {
		List<Appointment> appointmentList = this.appointmentRepository.findAll();
		
		if(doctorEmail != null && doctorEmail.length() > 0) {
			appointmentList.removeIf(appointment -> {
				return !appointment.getDoctor().getEmail().equals(doctorEmail);
			});
		}
		
		if(patientEmail != null && patientEmail.length() > 0) {
			appointmentList.removeIf(appointment -> {
				return !appointment.getPatient().getEmail().equals(patientEmail);
			});
		}
		
		if(approved != null) {
			appointmentList.removeIf(appointment -> {
				return appointment.getApproved() != approved;
			});
		}
		
		if(clinicId != null) {
			appointmentList.removeIf(appointment -> {
				return appointment.getClinic().getId() != clinicId;
			});
		}
		
		if(predefined != null) {
			appointmentList.removeIf(appointment -> {
				return (appointment.getPatient() == null) != predefined;
			});
		}
		
		return AppointmentDTO.toList(appointmentList);
	}
}
