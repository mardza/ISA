package com.isa.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

public class Appointment_EditDTO {

	private Integer id;
	
	@NotBlank
	private Date time;
	
	@NotBlank
	private Integer duration;
	
	@NotBlank
	private Integer appointmentTypeId;
	
	@NotBlank
	private Integer roomId;
	
	@NotBlank
	private Integer doctorId;
	
	@NotBlank
	private Integer price;
	
	@NotBlank
	private Integer discount;
	
	
	public Appointment_EditDTO() {}
	

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

	public Integer getAppointmentTypeId() {
		return appointmentTypeId;
	}

	public void setAppointmentTypeId(Integer appointmentTypeId) {
		this.appointmentTypeId = appointmentTypeId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "AppointmentDTO [id=" + id + ", time=" + time + ", duration=" + duration + ", appointmentTypeId="
				+ appointmentTypeId + ", roomId=" + roomId + ", doctorId=" + doctorId + ", price=" + price
				+ ", discount=" + discount + "]";
	}
}
