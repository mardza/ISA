package com.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.controller.exception.custom.EntityNotFoundException;
import com.isa.dto.AppointmentTypeDTO;
import com.isa.entity.AppointmentType;
import com.isa.repository.AppointmentTypeRepository;

@Service
public class AppointmentTypeService implements AppointmentTypeServiceInterface {

	@Autowired
	private AppointmentTypeRepository appointmentTypeRepository;
	
	
	@Override
	public List<AppointmentType> findAll() {
		return this.appointmentTypeRepository.findAll();
	}

	@Override
	public AppointmentType findById(Integer id) {
		return this.appointmentTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AppointmentType with id '" + id + "' not found"));
	}

	@Override
	public AppointmentType create(AppointmentTypeDTO appointmentTypeDTO) {
		AppointmentType appointmentType = new AppointmentType(appointmentTypeDTO);
		appointmentType = this.appointmentTypeRepository.save(appointmentType);
		return appointmentType;
	}

	@Override
	public AppointmentType update(Integer id, AppointmentTypeDTO appointmentTypeDTO) {
		AppointmentType appointmentType = this.findById(id);
		appointmentType.setName(appointmentTypeDTO.getName());
		appointmentType = this.appointmentTypeRepository.save(appointmentType);
		return appointmentType;
	}

	@Override
	public void remove(AppointmentType appointmentType) {
		this.appointmentTypeRepository.delete(appointmentType);
	}
}
