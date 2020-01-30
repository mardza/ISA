//package com.isa.entity;
//
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.Table;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//
//@Entity
//@Table(name = "work_calendars")
//public class WorkCalendar {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id", unique = true, nullable = false)
//	private Integer id;
//	
//	@Column(name = "start_time", unique = false, nullable = false)
//	private Date start;
//	
//	@Column(name = "end_time", unique = false, nullable = false)
//	private Date end;
//	
//	@ManyToMany(mappedBy = "workCalendars", cascade = {CascadeType.MERGE, CascadeType.MERGE})
//	private List<Appointment> appointments;
//	
//	@OneToMany(mappedBy = "workCalendar")
//	private List<Leave> leaves;
//	
//	@OneToOne(mappedBy = "workCalendar")
//	private User user;
//	
//	
//	public WorkCalendar() {}
//
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public Date getStart() {
//		return start;
//	}
//
//	public void setStart(Date start) {
//		this.start = start;
//	}
//
//	public Date getEnd() {
//		return end;
//	}
//
//	public void setEnd(Date end) {
//		this.end = end;
//	}
//
//	public List<Appointment> getAppointments() {
//		return appointments;
//	}
//
//	public void setAppointments(List<Appointment> appointments) {
//		this.appointments = appointments;
//	}
//
//	public List<Leave> getLeaves() {
//		return leaves;
//	}
//
//	public void setLeaves(List<Leave> leaves) {
//		this.leaves = leaves;
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	@Override
//	public String toString() {
//		return "WorkCalendar [id=" + id + ", start=" + start + ", end=" + end + ", appointments=" + appointments
//				+ ", leaves=" + leaves + ", user=" + user + "]";
//	}
//}
