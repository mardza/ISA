package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.entity.ClinicRating;

@Repository
public interface ClinicRatingRepository extends JpaRepository<ClinicRating, Integer> {

	ClinicRating findByPatientIdAndClinicId(Integer patientId, Integer clinicId);
	
	List<ClinicRating> findByClinicId(Integer clinicId);
}
