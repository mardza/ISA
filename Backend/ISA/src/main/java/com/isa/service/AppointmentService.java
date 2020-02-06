package com.isa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.controller.exception.custom.EntityNotFoundException;
import com.isa.dto.AppointmentCreateDTO;
import com.isa.dto.AppointmentDTO;
import com.isa.entity.Appointment;
import com.isa.entity.Clinic;
import com.isa.entity.Price;
import com.isa.entity.User;
import com.isa.repository.AppointmentRepository;

@Service
public class AppointmentService implements AppointmentServiceInterface {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ClinicService clinicService;
	
	@Autowired
	private PriceService priceService;
	
	@Autowired
	private MailService mailService;
	
	
	@Override
	public AppointmentDTO findById(Integer id) {
		Appointment appointment = this.appointmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Appointment with id '" + id + "' not found"));
		return new AppointmentDTO(appointment);
	}
	
	@Override
	public List<AppointmentDTO> findPredefinedByClinicId(Integer id) {
		List<Appointment> appointmentList = this.appointmentRepository.findPredefinedByClinicId(id);
		return AppointmentDTO.toList(appointmentList);
	}
	
	@Override
	public List<AppointmentDTO> findFiltered(String doctorEmail, String patientEmail, Boolean approved, Integer clinicId, Boolean predefined, Boolean old) {
		List<Appointment> appointmentList = this.appointmentRepository.findAll();
		
		if(doctorEmail != null && doctorEmail.length() > 0) {
			appointmentList.removeIf(appointment -> {
				return !appointment.getDoctor().getEmail().equals(doctorEmail);
			});
		}
		
		if(patientEmail != null && patientEmail.length() > 0) {
			appointmentList.removeIf(appointment -> {
				return appointment.getPatient() == null || !appointment.getPatient().getEmail().equals(patientEmail);
			});
		}
		
		if(approved != null) {
			appointmentList.removeIf(appointment -> {
				return appointment.getApproved() != approved;
			});
		}
		
		if(clinicId != null) {
			appointmentList.removeIf(appointment -> {
				return appointment.getClinic().getId() != clinicId;
			});
		}
		
		if(predefined != null) {
			appointmentList.removeIf(appointment -> {
				return (appointment.getPatient() == null) != predefined;
			});
		}
		
		if(old != null) {
			Long time = new Date().getTime();
			appointmentList.removeIf(appointment -> {
				return (appointment.getTime().getTime() >= time) == old;
			});
		}
		
		return AppointmentDTO.toList(appointmentList);
	}
	
	@Override
	public AppointmentDTO createAppointment(AppointmentCreateDTO appointmentCreateDTO) {
		Appointment appointment = new Appointment();
		
		User doctor = this.userService.findByEmail(appointmentCreateDTO.getDoctorEmail());
		User patient = this.userService.findByEmail(appointmentCreateDTO.getPatientEmail());
		Clinic clinic = this.clinicService.findById(appointmentCreateDTO.getClinicId());
		Price price = this.priceService.findByClinicAndAppointmentType(appointmentCreateDTO.getClinicId(), doctor.getSpecialisation().getId());
		
		appointment.setApproved(false);
		appointment.setClinic(clinic);
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		appointment.setTime(appointmentCreateDTO.getTime());
		appointment.setType(doctor.getSpecialisation());
		appointment.setPrice(price);
		appointment = this.save(appointment);
		
		List<User> adminList = this.userService.findByClinicAndRole(clinic.getId(), "ROLE_ADMIN_CLINIC");
		
		adminList.forEach(admin -> {
			this.mailService.sendMail(admin.getEmail(), "Request for appointment", "Patient " + patient.getFirstName() + " " + patient.getLastName() + " requested an appointment. Go to your dashboard and approve it.");
		});
				
		return new AppointmentDTO(appointment);
	}
	
	@Override
	public Appointment save(Appointment appointment) {
		return this.appointmentRepository.save(appointment);
	}
	
	@Override
	public void remove(Appointment appointment) {
		this.appointmentRepository.delete(appointment);
	}
}
