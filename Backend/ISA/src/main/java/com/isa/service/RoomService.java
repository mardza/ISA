package com.isa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.controller.exception.custom.EntityNotFoundException;
import com.isa.dto.RoomDTO;
import com.isa.entity.Room;
import com.isa.repository.RoomRepository;

@Service
public class RoomService implements RoomServiceInterface {

	@Autowired
	private RoomRepository roomRepository;

	@Override
	public List<Room> findAll() {
		return this.roomRepository.findAll();
	}

	@Override
	public Room findById(Integer id) {
		return this.roomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Room with id '" + id + "' not found"));
	}

	@Override
	public List<Room> findFiltered(Integer clinicId, Date date, String number, String name) {
		return this.roomRepository.findFiltered();
	}

	@Override
	public Room create(RoomDTO roomDTO) {
		Room room = new Room();
		room.setNumber(roomDTO.getNumber());
		room.setName(roomDTO.getName());
		room = this.roomRepository.save(room);
		return room;
	}

	@Override
	public Room update(Integer id, RoomDTO roomDTO) {
		Room room = this.findById(id);
		room.setNumber(roomDTO.getNumber());
		room.setName(roomDTO.getName());
		room = this.roomRepository.save(room);
		return room;
	}

	@Override
	public void remove(Room room) {
		this.roomRepository.delete(room);
	}
}
