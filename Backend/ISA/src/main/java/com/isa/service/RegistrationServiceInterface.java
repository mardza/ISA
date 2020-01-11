package com.isa.service;

import com.isa.entity.Registration;

public interface RegistrationServiceInterface {

	Registration findById(String id);
		
	Registration save(Registration registration);
	
	void remove(Registration registration);
}
