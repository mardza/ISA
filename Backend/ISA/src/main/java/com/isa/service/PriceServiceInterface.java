package com.isa.service;

import com.isa.entity.Price;

public interface PriceServiceInterface {

	
	Price findByClinicAndAppointmentType(Integer clinicId, Integer appointmentTypeId);
}
