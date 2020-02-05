package com.isa.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.isa.entity.Appointment;

public class AppointmentDTO {
	
	private Integer id;
	private Date time;
	private AppointmentTypeDTO type;
	private PriceDTO price;
	private ClinicDTO clinic;
	private RoomDTO room;
	private UserDTO doctor;
	private UserDTO patient;
	private Boolean approved;
	
	
	public AppointmentDTO() {}
	
	public AppointmentDTO(Appointment appointment) {
		this.id = appointment.getId();
		this.time = appointment.getTime();
		
		if(appointment.getType() != null) {
			this.type = new AppointmentTypeDTO(appointment.getType());
		}
		
		if(appointment.getPrice() != null) {
			this.price = new PriceDTO(appointment.getPrice());
		}
		
		if(appointment.getClinic() != null) {
			this.clinic = new ClinicDTO(appointment.getClinic());
		}
		
		if(appointment.getRoom() != null) {
			this.room = new RoomDTO(appointment.getRoom());
		}
		
		if(appointment.getDoctor() != null) {
			this.doctor = new UserDTO(appointment.getDoctor());
		}
		
		if(appointment.getPatient() != null) {
			this.patient = new UserDTO(appointment.getPatient());
		}
		
		this.approved = appointment.getApproved();
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
	
	public AppointmentTypeDTO getType() {
		return type;
	}

	public void setType(AppointmentTypeDTO type) {
		this.type = type;
	}

	public PriceDTO getPrice() {
		return price;
	}

	public void setPrice(PriceDTO price) {
		this.price = price;
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

	public UserDTO getPatient() {
		return patient;
	}

	public void setPatient(UserDTO patient) {
		this.patient = patient;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	@Override
	public String toString() {
		return "AppointmentDTO [id=" + id + ", time=" + time + ", type=" + type + ", price="
				+ price + ", clinic=" + clinic + ", room=" + room + ", doctor=" + doctor + ", patient=" + patient + "]";
	}
}
