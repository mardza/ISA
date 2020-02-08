package com.isa.service;

import com.isa.entity.DoctorRating;

public interface DoctorRatingServiceInterface {

	DoctorRating findByPatientIdAndClinicId(Integer patientId, Integer doctorId);
	
	Double findDoctorRating(Integer doctorId);
	
	DoctorRating create(DoctorRating doctorRating);
	
	DoctorRating update(DoctorRating doctorRating);
}
