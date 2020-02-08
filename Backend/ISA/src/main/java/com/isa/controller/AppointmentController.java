package com.isa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
			@RequestParam(name = "adminEmail", required = false) String adminEmail,
			@RequestParam(name = "approved", required = false) Boolean approved,
			@RequestParam(name = "clinicId", required = false) Integer clinicId,
			@RequestParam(name = "predefined", required = false) Boolean predefined,
			@RequestParam(name = "requested", required = false) Boolean requested,
			@RequestParam(name = "patientApproved", required = false) Boolean patientApproved,
			@RequestParam(name = "old", required = false) Boolean old
			){
		List<AppointmentDTO> appointmentDTOList = this.appointmentService.findFiltered(doctorEmail, patientEmail, adminEmail, approved, clinicId, predefined, requested, patientApproved, old, null);
		return new ResponseEntity<List<AppointmentDTO>>(appointmentDTOList, HttpStatus.OK);
	}
	
	@GetMapping(path = "/current-user")
	public ResponseEntity<List<AppointmentDTO>> getAllOfCurrentUser(
			@RequestParam(name = "old", required = false) Boolean old,
			@RequestParam(name = "patientApproved", required = false) Boolean patientApproved
			){
		User patient = this.userService.getCurrentUser();
		List<AppointmentDTO> appointmentDTOList = this.appointmentService.findFiltered(null, patient.getEmail(), null, true, null, null, null, patientApproved, old, null);
		return new ResponseEntity<List<AppointmentDTO>>(appointmentDTOList, HttpStatus.OK);
	}
	
	@GetMapping(path = "/admin-clinic-requests")
	public ResponseEntity<List<AppointmentDTO>> getAllAdminClinic() {
		List<AppointmentDTO> appointmentDTOList = this.appointmentService.findAdminClinicAppointmentRequests();
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
	
	// when patient confirms predefined appointment
	@PostMapping(path = "/{id}/activate")
	public ResponseEntity<AppointmentDTO> activateAppointment(@PathVariable("id") Integer id){
		AppointmentDTO appointmentDTO = this.appointmentService.activateAppointment(id);
		return new ResponseEntity<AppointmentDTO>(appointmentDTO, HttpStatus.OK);
	}
	
	// when clinic admin approves appointment request
	@PostMapping(path = "/{id}/approve")
	public ResponseEntity<AppointmentDTO> approveAppointment(@PathVariable("id") Integer id){
		AppointmentDTO appointmentDTO = this.appointmentService.approveAppointment(id);
		return new ResponseEntity<AppointmentDTO>(appointmentDTO, HttpStatus.OK);
	}
	
	// when clinic admin disapproves appointment request
	@PostMapping(path = "/{id}/disapprove")
	public ResponseEntity<AppointmentDTO> disapproveAppointment(@PathVariable("id") Integer id){
		AppointmentDTO appointmentDTO = this.appointmentService.disapproveAppointment(id);
		return new ResponseEntity<AppointmentDTO>(appointmentDTO, HttpStatus.OK);
	}
	
	// when patient confirms approved appointment request
	@PostMapping(path = "/{id}/patient-approve")
	public ResponseEntity<AppointmentDTO> patientApprove(@PathVariable("id") Integer id){
		AppointmentDTO appointmentDTO = this.appointmentService.patientApproveAppointment(id);
		return new ResponseEntity<AppointmentDTO>(appointmentDTO, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> cancelAppointment(@PathVariable("id") Integer id) {
		this.appointmentService.cancelAppointment(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
