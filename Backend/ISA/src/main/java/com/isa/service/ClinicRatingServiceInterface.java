package com.isa.service;

import com.isa.entity.ClinicRating;

public interface ClinicRatingServiceInterface {

	ClinicRating findByPatientIdAndClinicId(Integer patientId, Integer clinicId);
	
	Double findClinicRating(Integer clinicId);
	
	ClinicRating create(ClinicRating clinicRating);
	
	ClinicRating update(ClinicRating clinicRating);
}
