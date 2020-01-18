package com.isa.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import com.isa.entity.Room;

public class RoomDTO {

	private Integer id;
	
	@NotBlank
	private String number;
	
	@NotBlank
	private String name;
	
	
	public RoomDTO() {}
	
	public RoomDTO(Room room) {
		this.id = room.getId();
		this.number = room.getNumber();
		this.name = room.getName();
	}
	
	
	public static List<RoomDTO> toList(List<Room> roomList) {
		return roomList.stream().map(room -> new RoomDTO(room)).collect(Collectors.toList());
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RoomDTO [id=" + id + ", number=" + number + ", name=" + name + "]";
	}
}
