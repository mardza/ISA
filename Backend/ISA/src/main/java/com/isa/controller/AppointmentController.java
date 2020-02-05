package com.isa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isa.dto.AppointmentDTO;
import com.isa.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	
	@GetMapping()
	public ResponseEntity<List<AppointmentDTO>> getAll(
			@RequestParam(name = "doctorEmail", required = false) String doctorEmail,
			@RequestParam(name = "patientEmail", required = false) String patientEmail,
			@RequestParam(name = "approved", required = false) Boolean approved,
			@RequestParam(name = "clinicId", required = false) Integer clinicId,
			@RequestParam(name = "predefined", required = false) Boolean predefined
			){
		List<AppointmentDTO> appointmentDTOList = this.appointmentService.findFiltered(doctorEmail, patientEmail, approved, clinicId, predefined);
		return new ResponseEntity<List<AppointmentDTO>>(appointmentDTOList, HttpStatus.OK);
	}
}
