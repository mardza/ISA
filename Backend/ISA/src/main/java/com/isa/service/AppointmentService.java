package com.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.controller.exception.custom.EntityNotFoundException;
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
}
