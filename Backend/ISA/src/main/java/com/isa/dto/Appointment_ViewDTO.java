package com.isa.dto;

import java.util.Date;

public class Appointment_ViewDTO {

	private Integer id;
	private Date time;
	private Integer duration;
	private AppointmentTypeDTO type;
	private RoomDTO room;
	private UserDTO doctor;
	private Integer price;
	private Integer discount;
	
	
	private Appointment_ViewDTO() {}
	
	
	
}
