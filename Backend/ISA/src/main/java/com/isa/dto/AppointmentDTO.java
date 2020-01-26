package com.isa.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.isa.entity.Appointment;

public class AppointmentDTO {
	
	private Integer id;
	private Date time;
	private Integer duration;
	private AppointmentTypeDTO type;
	private ClinicDTO clinic;
	private RoomDTO room;
	private UserDTO doctor;
	
	
	public AppointmentDTO() {}
	
	public AppointmentDTO(Appointment appointment) {
		this.id = appointment.getId();
		this.time = appointment.getTime();
		this.duration = appointment.getDuration();
		this.type = new AppointmentTypeDTO(appointment.getType());
		this.clinic = new ClinicDTO(appointment.getClinic());
		this.room = new RoomDTO(appointment.getRoom());
		this.doctor = new UserDTO(appointment.getDoctor());
	}
	
	public static List<AppointmentDTO> toList(List<Appointment> appointmentList) {
		return appointmentList.stream().map(appointment -> new AppointmentDTO(appointment)).collect(Collectors.toList());
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public AppointmentTypeDTO getType() {
		return type;
	}

	public void setType(AppointmentTypeDTO type) {
		this.type = type;
	}

	public ClinicDTO getClinic() {
		return clinic;
	}

	public void setClinic(ClinicDTO clinic) {
		this.clinic = clinic;
	}

	public RoomDTO getRoom() {
		return room;
	}

	public void setRoom(RoomDTO room) {
		this.room = room;
	}

	public UserDTO getDoctor() {
		return doctor;
	}

	public void setDoctor(UserDTO doctor) {
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "AppointmentDTO [id=" + id + ", time=" + time + ", duration=" + duration + ", type=" + type + ", clinic="
				+ clinic + ", room=" + room + ", doctor=" + doctor + "]";
	}
}
