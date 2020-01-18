package com.isa.service;

import java.util.Date;
import java.util.List;

import com.isa.dto.RoomDTO;
import com.isa.entity.Room;

public interface RoomServiceInterface {
	
	List<Room> findAll();
	
	Room findById(Integer id);
	
	List<Room> findFiltered(Integer clinicId, Date date, String number, String name);
	
	Room create(RoomDTO roomDTO);
	
	Room update(Integer id, RoomDTO roomDTO);
	
	void remove(Room room);

}
