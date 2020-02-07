package com.isa.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.isa.dto.ClinicSearchDTO;
import com.isa.dto.DoctorAvailableDTO;
import com.isa.dto.PriceDTO;
import com.isa.entity.Clinic;
import com.isa.entity.Price;
import com.isa.service.AppointmentService;
import com.isa.service.ClinicService;
import com.isa.service.PriceService;

@RestController
@RequestMapping("/clinics")
public class ClinicController {

	@Autowired
	private ClinicService clinicService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private PriceService priceService;
	

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
		//List<Clinic> clinicList = this.clinicService.findFiltered(date, appointmentType, address);
		List<Clinic> clinicList = this.clinicService.findAll();
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
		//List<AppointmentDTO> appointmentListDTO = this.appointmentService.findPredefinedByClinicId(id);
		List<AppointmentDTO> appointmentListDTO = this.appointmentService.findFiltered(null, null, null, null, id, true, false, null);
		return new ResponseEntity<List<AppointmentDTO>>(appointmentListDTO, HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<ClinicSearchDTO>> searchClinics (
			@RequestParam(name = "appointmentTypeId", required = true) Integer appointmentTypeId,
			@RequestParam(name = "date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
		List<Clinic> clinicList = this.clinicService.findFiltered(date, appointmentTypeId);
		
		List<ClinicSearchDTO> clinicSearchDTOList = new ArrayList<ClinicSearchDTO>();
		clinicList.forEach(clinic -> {
			ClinicSearchDTO clinicSearchDTO = new ClinicSearchDTO();
			clinicSearchDTO.setClinic(new ClinicDTO(clinic));
			Price price = this.priceService.findByClinicAndAppointmentType(clinic.getId(), appointmentTypeId);
			clinicSearchDTO.setPrice(new PriceDTO(price));
			clinicSearchDTOList.add(clinicSearchDTO);
		});
		
		return new ResponseEntity<List<ClinicSearchDTO>>(clinicSearchDTOList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}/search-doctors")
	public ResponseEntity<List<DoctorAvailableDTO>> searchClinicDoctors(
			@PathVariable("id") Integer clinicId,
			@RequestParam(name = "appointmentTypeId", required = true) Integer appointmentTypeId,
			@RequestParam(name = "date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
			@RequestParam(name = "firstName", required = false) String firstName,
			@RequestParam(name = "lastName", required = false) String lastName,
			@RequestParam(name = "rating", required = false) Integer rating
			){
		List<DoctorAvailableDTO> doctorAvailableDTOList = this.clinicService.findAvailableDoctorsByClinic(clinicId, appointmentTypeId, date, firstName, lastName, rating);
		return new ResponseEntity<List<DoctorAvailableDTO>>(doctorAvailableDTOList, HttpStatus.OK);
	}
}
