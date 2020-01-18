package com.isa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.controller.exception.custom.EntityNotFoundException;
import com.isa.dto.ClinicDTO;
import com.isa.entity.Clinic;
import com.isa.repository.ClinicRepository;

@Service
public class ClinicService implements ClinicServiceInterface {

	@Autowired
	private ClinicRepository clinicRepository;
	
	
	@Override
	public List<Clinic> findAll() {
		return this.clinicRepository.findAll();
	}

	@Override
	public Clinic findById(Integer id) {
		return this.clinicRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Clinic with id '" + id + "' not found"));
	}

	@Override
	public List<Clinic> findFiltered(Date date, String appointmentType, String address) {
		List<Clinic> clinicList = this.clinicRepository.findFiltered(date);
		return clinicList;
	}
	
	@Override
	public Clinic create(ClinicDTO clinicDTO) {
		Clinic clinic = new Clinic(clinicDTO);
		clinic = this.save(clinic);
		return clinic;
	}
	
	@Override
	public Clinic update(Integer id, ClinicDTO clinicDTO) {
		Clinic clinic = this.findById(id);
		clinic.setName(clinicDTO.getName());
		clinic.setAddress(clinicDTO.getAddress());
		clinic.setDescription(clinicDTO.getDescription());
		clinic = this.save(clinic);
		return clinic;
	}

	@Override
	public Clinic save(Clinic clinic) {
		return this.clinicRepository.save(clinic);
	}

	@Override
	public void remove(Clinic clinic) {
		this.clinicRepository.delete(clinic);
	}	
}
