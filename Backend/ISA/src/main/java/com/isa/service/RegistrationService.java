package com.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.controller.exception.custom.EntityNotFoundException;
import com.isa.entity.Registration;
import com.isa.repository.RegistrationRepository;

@Service
public class RegistrationService implements RegistrationServiceInterface {

	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Override
	public Registration findById(String id) {
		return this.registrationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registration with id '"+id+"' not found"));
	}

	@Override
	public Registration save(Registration registration) {
		return this.registrationRepository.save(registration);
	}

	@Override
	public void remove(Registration registration) {
		this.registrationRepository.delete(registration);
	}
}
