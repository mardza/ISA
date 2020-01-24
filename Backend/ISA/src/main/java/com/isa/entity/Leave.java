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
@Table(name = "leaves")
public class Leave {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "start_date", unique = false, nullable = false)
	private Date start;
	
	@Column(name = "end_date", unique = false, nullable = false)
	private Date end;
	
	@ManyToOne
	@JoinColumn(name = "work_calendar_id", referencedColumnName = "id", unique = false, nullable = false)
	private WorkCalendar workCalendar;
	
	
	public Leave() {}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public WorkCalendar getWorkCalendar() {
		return workCalendar;
	}

	public void setWorkCalendar(WorkCalendar workCalendar) {
		this.workCalendar = workCalendar;
	}

	@Override
	public String toString() {
		return "Leave [id=" + id + ", start=" + start + ", end=" + end + ", workCalendar=" + workCalendar + "]";
	}
}
