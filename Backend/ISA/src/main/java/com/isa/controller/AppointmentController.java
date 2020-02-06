package com.isa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isa.dto.AppointmentCreateDTO;
import com.isa.dto.AppointmentDTO;
import com.isa.entity.User;
import com.isa.service.AppointmentService;
import com.isa.service.UserService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping()
	public ResponseEntity<List<AppointmentDTO>> getAll(
			@RequestParam(name = "doctorEmail", required = false) String doctorEmail,
			@RequestParam(name = "patientEmail", required = false) String patientEmail,
			@RequestParam(name = "approved", required = false) Boolean approved,
			@RequestParam(name = "clinicId", required = false) Integer clinicId,
			@RequestParam(name = "predefined", required = false) Boolean predefined,
			@RequestParam(name = "old", required = false) Boolean old
			){
		List<AppointmentDTO> appointmentDTOList = this.appointmentService.findFiltered(doctorEmail, patientEmail, approved, clinicId, predefined, old);
		return new ResponseEntity<List<AppointmentDTO>>(appointmentDTOList, HttpStatus.OK);
	}
	
	@GetMapping(path = "/current-user")
	public ResponseEntity<List<AppointmentDTO>> getAllOfCurrentUser(
			@RequestParam(name = "old", required = false) Boolean old
			){
		User patient = this.userService.getCurrentUser();
		List<AppointmentDTO> appointmentDTOList = this.appointmentService.findFiltered(null, patient.getEmail(), null, null, null, old);
		return new ResponseEntity<List<AppointmentDTO>>(appointmentDTOList, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<AppointmentDTO> getById(@PathVariable("id") Integer id){
		AppointmentDTO appointmentDTO = this.appointmentService.findById(id);
		return new ResponseEntity<AppointmentDTO>(appointmentDTO, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentCreateDTO appointmentCreateDTO){
		AppointmentDTO appointmentDTO = this.appointmentService.createAppointment(appointmentCreateDTO);
		return new ResponseEntity<AppointmentDTO>(appointmentDTO, HttpStatus.OK);
	}
	
	@PostMapping(path = "/activate")
	public ResponseEntity<AppointmentDTO> activateAppointment(){
		
		return new ResponseEntity<AppointmentDTO>(HttpStatus.OK);
	}
	
	@PostMapping(path = "/approve")
	public ResponseEntity<AppointmentDTO> approveAppointment(){
		
		return new ResponseEntity<AppointmentDTO>(HttpStatus.OK);
	}
}
