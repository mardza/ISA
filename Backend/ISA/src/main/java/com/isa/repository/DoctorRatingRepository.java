package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.entity.DoctorRating;

@Repository
public interface DoctorRatingRepository extends JpaRepository<DoctorRating, Integer> {
	
	DoctorRating findByPatientIdAndDoctorId(Integer patientId, Integer doctorId);
	
	List<DoctorRating>  findByDoctorId(Integer doctorId);
}
