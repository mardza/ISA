package com.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.entity.ClinicRating;
import com.isa.repository.ClinicRatingRepository;

@Service
public class ClinicRatingService implements ClinicRatingServiceInterface {

	@Autowired
	private ClinicRatingRepository clinicRatingRepository;
	
	
	@Override
	public ClinicRating findByPatientIdAndClinicId(Integer patientId, Integer clinicId) {
		return this.clinicRatingRepository.findByPatientIdAndClinicId(patientId, clinicId);
	}
	
	@Override
	public Double findClinicRating(Integer clinicId) {
		List<ClinicRating> clinicRatingList = this.clinicRatingRepository.findByClinicId(clinicId);
		Double sum = 0.0;
		for(ClinicRating clinicRating: clinicRatingList) {
			sum += clinicRating.getValue();
		}
		if(clinicRatingList.size() == 0) {
			return 0.0;
		} else {
			return sum / clinicRatingList.size();
		}
	}
	
	@Override
	public ClinicRating create(ClinicRating clinicRating) {
		return this.clinicRatingRepository.save(clinicRating);
	}
	
	@Override
	public ClinicRating update(ClinicRating clinicRating) {
		return this.clinicRatingRepository.save(clinicRating);
	}
}
