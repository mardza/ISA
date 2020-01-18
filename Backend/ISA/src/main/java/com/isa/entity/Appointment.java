package com.isa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "appointments")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "time", unique = false, nullable = false)
	private Date time;
	
	@Column(name = "duration", unique = false, nullable = false)
	private Integer duration;
	
	@ManyToOne
	@JoinColumn(name = "type_id", referencedColumnName = "id", unique = false, nullable = false)
	private AppointmentType type;
	
	@ManyToOne
	@JoinColumn(name = "room_id", referencedColumnName = "id", unique = false, nullable = false)
	private Room room;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id", referencedColumnName = "id", unique = false, nullable = false)
	private User doctor;
	
	@Column(name = "price", unique = false, nullable = false)
	private Integer price;
	
	@Column(name = "discount", unique = false, nullable = false)
	private Integer discount;
	
	
	public Appointment() {}


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

	public AppointmentType getType() {
		return type;
	}

	public void setType(AppointmentType type) {
		this.type = type;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
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
		return "Appointment [id=" + id + ", time=" + time + ", duration=" + duration + ", type=" + type + ", room="
				+ room + ", doctor=" + doctor + ", price=" + price + ", discount=" + discount + "]";
	}
}
