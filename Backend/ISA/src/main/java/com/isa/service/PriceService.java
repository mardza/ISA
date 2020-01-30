package com.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.entity.Price;
import com.isa.repository.PriceRepository;

@Service
public class PriceService implements PriceServiceInterface {
	
	@Autowired
	private PriceRepository priceRepository;
	

	@Override
	public Price findByClinicAndAppointmentType(Integer clinicId, Integer appointmentTypeId) {
		return this.priceRepository.findByClinicIdAndAppointmentTypeId(clinicId, appointmentTypeId);
	}
}
