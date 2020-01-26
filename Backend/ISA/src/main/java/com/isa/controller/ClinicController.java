package com.isa.controller;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isa.dto.AppointmentDTO;
import com.isa.dto.ClinicDTO;
import com.isa.entity.Appointment;
import com.isa.entity.Clinic;
import com.isa.service.AppointmentService;
import com.isa.service.ClinicService;

@RestController
@RequestMapping("/clinics")
public class ClinicController {

	@Autowired
	private ClinicService clinicService;
	
	@Autowired
	private AppointmentService appointmentService;
	

	@GetMapping(path = "/{id}")
	public ResponseEntity<ClinicDTO> getById(@PathVariable("id") Integer id) {
		Clinic clinic = this.clinicService.findById(id);
		return new ResponseEntity<ClinicDTO>(new ClinicDTO(clinic), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<ClinicDTO>> getAllFiltered(
			@RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
			@RequestParam(name = "appointmentType", required = false) String appointmentType,
			@RequestParam(name = "address", required = false) String address) {
		List<Clinic> clinicList = this.clinicService.findFiltered(date, appointmentType, address);
		return new ResponseEntity<List<ClinicDTO>>(ClinicDTO.toList(clinicList), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ClinicDTO> createClinic(@RequestBody @Valid ClinicDTO clinicDTO) {
		Clinic clinic = this.clinicService.create(clinicDTO);
		return new ResponseEntity<ClinicDTO>(new ClinicDTO(clinic), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClinicDTO> updateClinic(@PathVariable("id") Integer id, @RequestBody @Valid ClinicDTO clinicDTO) {
		Clinic clinic = this.clinicService.update(id, clinicDTO);
		return new ResponseEntity<ClinicDTO>(new ClinicDTO(clinic), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/predefined-appointments")
	public ResponseEntity<List<AppointmentDTO>> getPredefinedAppointments(@PathVariable("id") Integer id) {
		List<Appointment> appointmentList = this.appointmentService.findPredefinedByClinicId(id);
		return new ResponseEntity<List<AppointmentDTO>>(AppointmentDTO.toList(appointmentList), HttpStatus.OK);
	}
}
