package com.isa.service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.controller.exception.custom.EntityNotFoundException;
import com.isa.dto.AppointmentCreateDTO;
import com.isa.dto.AppointmentDTO;
import com.isa.dto.PeriodDTO;
import com.isa.entity.Appointment;
import com.isa.entity.Clinic;
import com.isa.entity.Price;
import com.isa.entity.User;
import com.isa.repository.AppointmentRepository;
import com.isa.service.exception.AppointmentAlreadyExistsException;

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
	public List<AppointmentDTO> findFiltered(String doctorEmail, String patientEmail, String adminEmail, Boolean approved, Integer clinicId, Boolean predefined, Boolean requested, Boolean patientApproved, Boolean old, Date dayDate) {
		List<Appointment> appointmentList = this.appointmentRepository.findAll();
		
		if(doctorEmail != null && doctorEmail.length() > 0) {
			appointmentList.removeIf(appointment -> {
				return appointment.getDoctor() == null || !appointment.getDoctor().getEmail().equals(doctorEmail);
			});
		}
		
		if(patientEmail != null && patientEmail.length() > 0) {
			appointmentList.removeIf(appointment -> {
				return appointment.getPatient() == null || !appointment.getPatient().getEmail().equals(patientEmail);
			});
		}
		
		if(adminEmail != null && adminEmail.length() > 0) {
			appointmentList.removeIf(appointment -> {
				List<User> adminList = appointment.getClinic().getEmployees();
				adminList.removeIf(employee -> {
					return !employee.getRole().getName().equals("ROLE_ADMIN_CLINIC");
				});
				boolean toRemove = true;
				for(User admin: adminList) {
					if(admin.getEmail().equals(adminEmail)) {
						toRemove = false;
					}
				}
				return toRemove;
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
				return appointment.getPredefined() != predefined;
			});
		}
		
		if(requested != null) {
			appointmentList.removeIf(appointment -> {
				return appointment.getRequested() != requested;
			});
		}
		
		if(patientApproved != null) {
			appointmentList.removeIf(appointment -> {
				return appointment.getPatientApproved() != patientApproved;
			});
		}
		
		if(old != null) {
			Long time = new Date().getTime();
			appointmentList.removeIf(appointment -> {
				return (appointment.getTime().getTime() >= time) == old;
			});
		}
		
		if(dayDate != null) {
			Date start = dateAtHours(dayDate, 0);
			Date end = dateAtHours(dayDate, 23);
			appointmentList.removeIf(appointment -> {
				return !(dayDate.getTime() >= start.getTime() && dayDate.getTime() <= end.getTime());
			});
		}
		
		return AppointmentDTO.toList(appointmentList);
	}
	
	@Override
	public List<AppointmentDTO> findAdminClinicAppointmentRequests() {
		User admin = this.userService.getCurrentUser();
		List<AppointmentDTO> appointmentDTOList = this.findFiltered(null, null, admin.getEmail(), false, admin.getClinic().getId(), null, true, null, null, null);
		return appointmentDTOList;
	}
	
	@Override
	public AppointmentDTO createAppointment(AppointmentCreateDTO appointmentCreateDTO) {
		Appointment appointment = new Appointment();
		
		User doctor = this.userService.findByEmail(appointmentCreateDTO.getDoctorEmail());
		User patient = this.userService.findByEmail(appointmentCreateDTO.getPatientEmail());
		
		if(!isAppointmentVaild(doctor, patient, appointmentCreateDTO.getTime(), false)) {
			throw new AppointmentAlreadyExistsException("Failed to create appointment, eather doctor or patient are not available at that time.");
		}
		
		Clinic clinic = this.clinicService.findById(appointmentCreateDTO.getClinicId());
		Price price = this.priceService.findByClinicAndAppointmentType(appointmentCreateDTO.getClinicId(), doctor.getSpecialisation().getId());
		
		appointment.setRequested(true);
		appointment.setApproved(false);
		appointment.setPatientApproved(false);
		appointment.setPredefined(false);
		appointment.setClinic(clinic);
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		appointment.setTime(appointmentCreateDTO.getTime());
		appointment.setType(doctor.getSpecialisation());
		appointment.setPrice(price);
		appointment = this.save(appointment);
		
		List<User> adminList = this.userService.findByClinicAndRole(clinic.getId(), "ROLE_ADMIN_CLINIC");
		adminList.forEach(admin -> {
			this.mailService.sendMail(admin.getEmail(), "Request for appointment", "Patient " + patient.getFirstName() + " " + patient.getLastName() + " requested an appointment. Go to your dashboard and approve/disapprove it.");
		});
				
		return new AppointmentDTO(appointment);
	}
	
	private boolean isAppointmentVaild(User doctor, User patient, Date date, Boolean ignoreDoctorPredefined) {
		List<PeriodDTO> periodDTOList = this.clinicService.getDoctorPatientAvailablePeriodList(doctor, patient, date, ignoreDoctorPredefined);
		boolean valid = false;
		System.out.println("Appointment start: " + date);
		System.out.println("Appointment end: " + new Date(date.getTime() + doctor.getSpecialisation().getDuration()));
		for(PeriodDTO period: periodDTOList) {
			System.out.println(period);
			if(date.getTime() >= period.getStart() && (date.getTime() + doctor.getSpecialisation().getDuration()) <= period.getEnd()) {
				valid = true;
			}
		}
		return valid;
	}
	
	@Override
	public AppointmentDTO activateAppointment(Integer id) {
		Appointment appointment = this.appointmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Appointment with id '" + id + "' not found"));
		User patient = this.userService.getCurrentUser();
		
		if(!isAppointmentVaild(appointment.getDoctor(), patient, appointment.getTime(), true)) {
			throw new AppointmentAlreadyExistsException("Failed to create appointment, eather doctor or patient are not available at that time.");
		}
		
		appointment.setPatient(patient);
		appointment.setRequested(true);
		appointment = this.save(appointment);
		
		List<User> adminList = this.userService.findByClinicAndRole(appointment.getClinic().getId(), "ROLE_ADMIN_CLINIC");
		adminList.forEach(admin -> {
			this.mailService.sendMail(admin.getEmail(), "Request for appointment", "Patient " + patient.getFirstName() + " " + patient.getLastName() + " requested an appointment. Go to your dashboard and approve/disapprove it.");
		});
		
		return new AppointmentDTO(appointment);
	}
	
	@Override
	public AppointmentDTO approveAppointment(Integer id) {
		Appointment appointment = this.appointmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Appointment with id '" + id + "' not found"));
		appointment.setApproved(true);
		appointment = this.save(appointment);
		this.mailService.sendMail(appointment.getPatient().getEmail(), "Request for appointment approved", "Request for appointment was approved, check your approved appointments and confirm.");
		return new AppointmentDTO(appointment);
	}
	
	@Override
	public AppointmentDTO disapproveAppointment(Integer id) {
		Appointment appointment = this.appointmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Appointment with id '" + id + "' not found"));
		this.mailService.sendMail(appointment.getPatient().getEmail(), "Request for appointment disapproved", "Request for appointment was disapproved. Try again.");
		if(appointment.getPredefined()) {
			appointment.setRequested(false);
			appointment.setPatient(null);
			appointment = this.save(appointment);
			return new AppointmentDTO(appointment);
		} else {
			this.remove(appointment);
			return null;
		}
	}
	
	@Override
	public void cancelAppointment(Integer id) {
		Appointment appointment = this.appointmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Appointment with id '" + id + "' not found"));
		if(appointment.getTime().getTime() - new Date().getTime() >  86400000) {
			if(appointment.getPredefined()) {
				appointment.setRequested(false);
				appointment.setPatient(null);
				this.save(appointment);
			} else {
				this.remove(appointment);
			}
		}
	}
	
	@Override
	public AppointmentDTO patientApproveAppointment(Integer id) {
		Appointment appointment = this.appointmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Appointment with id '" + id + "' not found"));
		appointment.setPatientApproved(true);
		appointment = this.save(appointment);
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
	
	private Date dateAtHours(Date date, Integer hours) {
		return Date.from(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime().truncatedTo(ChronoUnit.HOURS).withHour(hours).atZone(ZoneId.systemDefault()).toInstant());
	}
}
