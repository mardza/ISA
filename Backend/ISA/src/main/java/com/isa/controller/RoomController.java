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

import com.isa.dto.RoomDTO;
import com.isa.entity.Room;
import com.isa.service.RoomService;

@RestController
@RequestMapping("/rooms")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<RoomDTO> getById(@PathVariable("id") Integer id) {
		Room room = this.roomService.findById(id);
		return new ResponseEntity<RoomDTO>(new RoomDTO(room), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<RoomDTO>> getAllFiltered(
			@RequestParam(name = "clinicId", required = false) Integer clinicId,
			@RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
			@RequestParam(name = "number", required = false) String number,
			@RequestParam(name = "name", required = false) String name) {
		List<Room> roomList = this.roomService.findFiltered(clinicId, date, number, name);
		return new ResponseEntity<List<RoomDTO>>(RoomDTO.toList(roomList), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<RoomDTO> createRoom(@RequestBody @Valid RoomDTO roomDTO) {
		Room room = this.roomService.create(roomDTO);
		return new ResponseEntity<RoomDTO>(new RoomDTO(room), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<RoomDTO> updateRoom(@PathVariable("id") Integer id, @RequestBody @Valid RoomDTO roomDTO) {
		Room room = this.roomService.update(id, roomDTO);
		return new ResponseEntity<RoomDTO>(new RoomDTO(room), HttpStatus.OK);
	}
}
