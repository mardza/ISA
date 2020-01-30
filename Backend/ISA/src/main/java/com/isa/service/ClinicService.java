package com.isa.service;

import java.text.SimpleDateFormat;
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
		return this.clinicRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Clinic with id '" + id + "' not found"));
	}

	@Override
	public List<Clinic> findFiltered(Date date, Integer appointmentTypeId) {

		System.out.println("Searching clinics for ");
		System.out.println("\tdate: " + date);
		System.out.println("\ttypeId: " + appointmentTypeId);

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
			
			System.out.println("Clinic " + clinic.getId() + " has these doctors with choosen AppointmentType: ");
			doctors.forEach(doctor -> {
				System.out.println(doctor);
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

				System.out.println("Doctor start time: " + doctorStartDate);
				System.out.println("Doctor end time: " + doctorEndDate);

				Long max = 0L;

				// uporediti pocetak radnog vremena za pocetkom prvog appointmenta, pa onda
				// svaki apointment, pa poslednji sa krajem

				if (appointmentList.size() == 0) {
					max = doctorEndDate.getTime() - doctorStartDate.getTime();
				} else {
					max = appointmentList.get(0).getTime().getTime() - doctorStartDate.getTime();
					if (appointmentList.size() > 1) {
						for (int i = 0; i < appointmentList.size() - 2; i++) {
							Long a1End = appointmentList.get(i).getTime().getTime() + appointmentType.getDuration();
							Long a2Start = appointmentList.get(i + 1).getTime().getTime();
							if(a2Start - a1End > max) {
								max = a2Start - a1End;
							}
						}
					}
					if (doctorEndDate.getTime() - appointmentList.get(appointmentList.size() - 1).getTime().getTime() > max) {
						max = doctorEndDate.getTime() - appointmentList.get(appointmentList.size() - 1).getTime().getTime();
					}
				}

				appointmentList.forEach(appointment -> System.out.println(appointment));

				return max < appointmentType.getDuration();
			});
			return doctors.size() == 0;
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
