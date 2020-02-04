package com.isa.dto;

import java.util.List;

public class DoctorAvailableDTO {

	private UserDTO doctor;
	private List<PeriodDTO> periodList;
	private Long appointmentTypeDuration;
	
	
	public DoctorAvailableDTO() {}


	public UserDTO getDoctor() {
		return doctor;
	}

	public void setDoctor(UserDTO doctor) {
		this.doctor = doctor;
	}

	public List<PeriodDTO> getPeriodList() {
		return periodList;
	}

	public void setPeriodList(List<PeriodDTO> periodList) {
		this.periodList = periodList;
	}

	public Long getAppointmentTypeDuration() {
		return appointmentTypeDuration;
	}

	public void setAppointmentTypeDuration(Long appointmentTypeDuration) {
		this.appointmentTypeDuration = appointmentTypeDuration;
	}
}
