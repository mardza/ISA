package com.isa.service;

import java.util.Date;
import java.util.List;

import com.isa.dto.ClinicDTO;
import com.isa.dto.DoctorAvailableDTO;
import com.isa.dto.PeriodDTO;
import com.isa.entity.Clinic;
import com.isa.entity.User;

public interface ClinicServiceInterface {

	List<Clinic> findAll();
	
	Clinic findById(Integer id);
	
	List<Clinic> findFiltered(Date date, Integer appointmentTypeId);
	
	List<DoctorAvailableDTO> findAvailableDoctorsByClinic(Integer clinicId, Integer appointmentTypeId, Date date, String firstName, String lastName, Integer rating);
	
	List<DoctorAvailableDTO> findAllDoctorsByClinic(Integer clinicId);
	
	List<PeriodDTO> getDoctorPatientAvailablePeriodList(User doctor, User patient, Date date, Boolean ignoreDoctorPredefined);
	
	List<ClinicDTO> findPatientClinics();
	
	ClinicDTO rate(Integer clinicId, Integer rating);
			
	Clinic create(ClinicDTO clinicDTO);
	
	Clinic update(Integer id, ClinicDTO clinicDTO);
	
	Clinic save(Clinic clinic);
	
	void remove(Clinic clinic);
}
