package com.isa.service;

import java.util.Date;
import java.util.List;

import com.isa.dto.ClinicDTO;
import com.isa.dto.DoctorAvailableDTO;
import com.isa.entity.Clinic;

public interface ClinicServiceInterface {

	List<Clinic> findAll();
	
	Clinic findById(Integer id);
	
	List<Clinic> findFiltered(Date date, Integer appointmentTypeId);
	
	List<DoctorAvailableDTO> findAvailableDoctorsByClinic(Integer clinicId, Integer appointmentTypeId, Date date, String firstName, String lastName, Integer rating);
	
	Clinic create(ClinicDTO clinicDTO);
	
	Clinic update(Integer id, ClinicDTO clinicDTO);
	
	Clinic save(Clinic clinic);
	
	void remove(Clinic clinic);
}
