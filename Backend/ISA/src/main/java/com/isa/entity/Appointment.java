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
	
	@ManyToOne
	@JoinColumn(name = "type_id", referencedColumnName = "id", unique = false, nullable = false)
	private AppointmentType type;
	
	@ManyToOne
	@JoinColumn(name = "price_id", referencedColumnName = "id", unique = false, nullable = true)
	private Price price;
	
	@ManyToOne
	@JoinColumn(name = "clinic_id", referencedColumnName = "id", unique = false, nullable = false)
	private Clinic clinic;
	
	@ManyToOne
	@JoinColumn(name = "room_id", referencedColumnName = "id", unique = false, nullable = true)
	private Room room;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id", referencedColumnName = "id", unique = false, nullable = false)
	private User doctor;
	
	@ManyToOne
	@JoinColumn(name = "patient_id", referencedColumnName = "id", unique = false, nullable = true)
	private User patient;
	
	@Column(name = "approved", unique = false, nullable = false)
	private Boolean approved;
	
//	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.MERGE}, fetch = FetchType.EAGER)
//	@JoinTable(name = "appointments_work_calendars", joinColumns = @JoinColumn(name = "appointment_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "work_calendar_id", referencedColumnName = "id"))
//	private List<WorkCalendar> workCalendars;
	
	
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

	public AppointmentType getType() {
		return type;
	}

	public void setType(AppointmentType type) {
		this.type = type;
	}
	
	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public Clinic getClinic() {
		return clinic;
	}
	
	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
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

	public User getPatient() {
		return patient;
	}

	public void setPatient(User patient) {
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
		return "Appointment [id=" + id + ", time=" + time + ", type=" + type.getName() + ", clinic=" + clinic.getId() + ", price=" + price.getPrice() + ", room=" + (room!=null?room.getName():"null")
				+ "]";
	}

	
}
