package com.isa.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.controller.exception.custom.EntityNotFoundException;
import com.isa.dto.ClinicDTO;
import com.isa.entity.Clinic;
import com.isa.entity.User;
import com.isa.entity.Appointment;
import com.isa.entity.AppointmentType;
import com.isa.repository.ClinicRepository;

@Service
public class ClinicService implements ClinicServiceInterface {
	
	SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");

	@Autowired
	private ClinicRepository clinicRepository;
	
	@Autowired
	private AppointmentTypeService appointmentTypeService;
	
	
	@Override
	public List<Clinic> findAll() {
		return this.clinicRepository.findAll();
	}

	@Override
	public Clinic findById(Integer id) {
		return this.clinicRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Clinic with id '" + id + "' not found"));
	}

	@Override
	public List<Clinic> findFiltered(Date date, Integer appointmentTypeId) {
//		List<Clinic> clinicList = this.clinicRepository.findFilteredByEmployeeSpecialisation(appointmentTypeId);
//		
//		clinicList.removeIf(clinic -> {
//			
//			
//			return true;
//		});
//		
//		return clinicList;
		
		List<Clinic> clinicList = this.clinicRepository.findAll();
		AppointmentType appointmentType = this.appointmentTypeService.findById(appointmentTypeId);
		clinicList.removeIf(clinic -> {
			
			List<User> doctors = clinic.getEmployees();
			doctors.removeIf(employee -> {
				return !employee.getRole().getName().equals("ROLE_DOCTOR");
			});
			
			doctors.removeIf(doctor -> {
				return !(doctor.getSpecialisation().getId() == appointmentType.getId());
			});
			
			doctors.removeIf(doctor -> {
				List<Appointment> appointmentList = doctor.getDoctorAppointmentList();
				appointmentList.removeIf(appointment -> {
					return !checkIfSameDay(date, appointment.getTime());
				});
				
				appointmentList.sort(Comparator.comparing(appointment -> appointment.getTime()));
				
				Date doctorStartDate = new Date();
				doctorStartDate.setHours(doctor.getWorkStart());
				Date doctorEndDate = new Date();
				doctorEndDate.setHours(doctor.getWorkEnd());
				
				Long max = 0L;
//				max = 
				
				// uporediti pocetak radnog vremena za pocetkom prvog appointmenta, pa onda svaki apointment, pa poslednji sa krajem
				
				
				// ovo nije dobro, poredi svaki sa svakim
//				for(int i = 0; i < appointmentList.size() - 2; i++) {
//					for(int j = i + 1; j < appointmentList.size() - 1; j++) {
//						Appointment a1 = appointmentList.get(i);
//						Appointment a2 = appointmentList.get(j);
//						Long x1 = a1.getTime().getTime();
//						Long y1 = a1.getTime().getTime() + a1.getType().getDuration();
//						Long x2 = a2.getTime().getTime();
//						Long y2 = a2.getTime().getTime() + a2.getType().getDuration();
//						
//						Long diff1 = Math.abs(x1 - y2);
//						Long diff2 = Math.abs(x2 - y1);
//						
//						Long diff = (diff1 > diff2)?diff1:diff2;
//						if(diff>max) {
//							max = diff;
//						}
//					}
//				}
				// uzeti u obzir i udaljenost od pocetka i kraja radnog vremena
				return max<appointmentType.getDuration();
			});
			return doctors.size() > 0;
		});
		return clinicList;
	}
	
	@Override
	public Clinic create(ClinicDTO clinicDTO) {
		Clinic clinic = new Clinic(clinicDTO);
		clinic = this.save(clinic);
		return clinic;
	}
	
	@Override
	public Clinic update(Integer id, ClinicDTO clinicDTO) {
		Clinic clinic = this.findById(id);
		clinic.setName(clinicDTO.getName());
		clinic.setAddress(clinicDTO.getAddress());
		clinic.setDescription(clinicDTO.getDescription());
		clinic = this.save(clinic);
		return clinic;
	}

	@Override
	public Clinic save(Clinic clinic) {
		return this.clinicRepository.save(clinic);
	}

	@Override
	public void remove(Clinic clinic) {
		this.clinicRepository.delete(clinic);
	}
	
	
	private boolean checkIfSameDay(Date date1, Date date2) {
		return fmt.format(date1).equals(fmt.format(date2));
	}
}
