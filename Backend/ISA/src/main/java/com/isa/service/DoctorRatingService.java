package com.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.entity.DoctorRating;
import com.isa.repository.DoctorRatingRepository;

@Service
public class DoctorRatingService implements DoctorRatingServiceInterface {

	@Autowired
	private DoctorRatingRepository  doctorRatingRepository;
	
	
	@Override
	public DoctorRating findByPatientIdAndClinicId(Integer patientId, Integer doctorId) {
		return this.doctorRatingRepository.findByPatientIdAndDoctorId(patientId, doctorId);
	}
	
	@Override
	public Double findDoctorRating(Integer doctorId) {
		List<DoctorRating> doctorRatingList = this.doctorRatingRepository.findByDoctorId(doctorId);
		Double sum = 0.0;
		for(DoctorRating doctorRating: doctorRatingList) {
			sum += doctorRating.getValue();
		}
		if(doctorRatingList.size() == 0) {
			return 0.0;
		} else {
			return sum / doctorRatingList.size();
		}
	}
	
	@Override
	public DoctorRating create(DoctorRating doctorRating) {
		return this.doctorRatingRepository.save(doctorRating);
	}
	
	@Override
	public DoctorRating update(DoctorRating doctorRating) {
		return this.doctorRatingRepository.save(doctorRating);
	}
}
