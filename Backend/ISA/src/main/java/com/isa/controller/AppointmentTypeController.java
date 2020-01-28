package com.isa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.dto.AppointmentTypeDTO;
import com.isa.entity.AppointmentType;
import com.isa.service.AppointmentTypeService;

@RestController
@RequestMapping("/appointment-types")
public class AppointmentTypeController {

	@Autowired
	private AppointmentTypeService appointmentTypeService;
	
	
	@GetMapping
	public ResponseEntity<List<AppointmentTypeDTO>> getAll() {
		List<AppointmentType> appointmentTypeList = this.appointmentTypeService.findAll();
		return new ResponseEntity<List<AppointmentTypeDTO>>(AppointmentTypeDTO.toList(appointmentTypeList), HttpStatus.OK);
	}
}
