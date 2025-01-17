package com.isa.service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.controller.exception.custom.EntityNotFoundException;
import com.isa.dto.ClinicDTO;
import com.isa.dto.AppointmentDTO;
import com.isa.dto.DoctorAvailableDTO;
import com.isa.dto.UserDTO;
import com.isa.dto.PeriodDTO;
import com.isa.entity.Clinic;
import com.isa.entity.ClinicRating;
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

	@Autowired
	private UserService userService;

	@Autowired
	private ClinicRatingService clinicRatingService;

	@Autowired
	private DoctorRatingService doctorRatingService;

	@Autowired
	private AppointmentService appointmentService;

	
	@Override
	public List<Clinic> findAll() {
		return this.clinicRepository.findAll();
	}

	@Override
	public Clinic findById(Integer id) {
		Clinic clinic = this.clinicRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Clinic with id '" + id + "' not found"));
		clinic.setRatingAverage(this.clinicRatingService.findClinicRating(clinic.getId()));
		return clinic;
	}
	
	@Override
	public List<DoctorAvailableDTO> findAllDoctorsByClinic(Integer clinicId) {
		Clinic clinic = this.findById(clinicId);
		List<User> doctors = clinic.getEmployees();
		doctors.removeIf(employee -> {
			return !employee.getRole().getName().equals("ROLE_DOCTOR");
		});
		List<DoctorAvailableDTO> doctorAvailableDTOList = new ArrayList<DoctorAvailableDTO>();
		doctors.forEach(doctor -> {
			doctor.setRatingAverage(this.doctorRatingService.findDoctorRating(doctor.getId()));
			DoctorAvailableDTO doctorAvailableDTO = new DoctorAvailableDTO();
			doctorAvailableDTO.setDoctor(new UserDTO(doctor));
			doctorAvailableDTOList.add(doctorAvailableDTO);
		});
		return doctorAvailableDTOList;
	}

	@Override
	public List<Clinic> findFiltered(Date date, Integer appointmentTypeId) {
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
			
			List<DoctorAvailableDTO> doctorAvailableDTOList = this.findAvailableDoctorsByClinic(clinic.getId(), appointmentTypeId, date, "", "", 0);
			return doctorAvailableDTOList.size() == 0;
		});
		clinicList.forEach(clinic -> {
			clinic.setRatingAverage(this.clinicRatingService.findClinicRating(clinic.getId()));
		});
		return clinicList;
	}

	@Override
	public List<DoctorAvailableDTO> findAvailableDoctorsByClinic(Integer clinicId, Integer appointmentTypeId, Date date, String firstName, String lastName, Integer rating) {
		Clinic clinic = this.findById(clinicId);
		AppointmentType appointmentType = this.appointmentTypeService.findById(appointmentTypeId);

		List<User> doctors = clinic.getEmployees();
		doctors.removeIf(employee -> {
			return !employee.getRole().getName().equals("ROLE_DOCTOR");
		});
		doctors.removeIf(doctor -> {
			return !(doctor.getSpecialisation().getId() == appointmentType.getId());
		});
		doctors.removeIf(doctor -> {
			return !doctor.getFirstName().toLowerCase().contains(firstName.toLowerCase());
		});
		doctors.removeIf(doctor -> {
			return !doctor.getLastName().toLowerCase().contains(lastName.toLowerCase());
		});
		doctors.removeIf(doctor -> {
			Double r = this.doctorRatingService.findDoctorRating(doctor.getId());
			return r < rating && r >= 1;
		});

		User patient = this.userService.getCurrentUser();
		List<DoctorAvailableDTO> doctorAvailableDTOList = new ArrayList<DoctorAvailableDTO>();
		if (!checkIfSameDay(new Date(), date)) {
			doctors.forEach(doctor -> {
				List<PeriodDTO> availablePeriodList = getDoctorPatientAvailablePeriodList(doctor, patient, date, false);
				if (availablePeriodList.size() > 0) {
					DoctorAvailableDTO doctorAvailableDTO = new DoctorAvailableDTO();
					doctor.setRatingAverage(this.doctorRatingService.findDoctorRating(doctor.getId()));
					doctorAvailableDTO.setDoctor(new UserDTO(doctor));
					doctorAvailableDTO.setPeriodList(availablePeriodList);
					doctorAvailableDTO.setAppointmentTypeDuration(appointmentType.getDuration());
					doctorAvailableDTOList.add(doctorAvailableDTO);
				}
			});
		}
		return doctorAvailableDTOList;
	}
	
	@Override
	public List<PeriodDTO> getDoctorPatientAvailablePeriodList(User doctor, User patient, Date date, Boolean ignoreDoctorPredefined) {
		Long workStart = dateAtHours(date, doctor.getWorkStart()).getTime();
		Long workEnd = dateAtHours(date, doctor.getWorkEnd()).getTime();

		// In case that patient tries to request predefined appointment we need 
		// to skip unrequested predefined appointments for this check or 
		// else that predefined appointment would not be able to be requested
		// (better solution would be to ignore just that exact appointment)
		Boolean requested = null;
		if(ignoreDoctorPredefined == true) {
			requested = true;
		}
		
		List<AppointmentDTO> doctorAppointmentList = this.appointmentService.findFiltered(doctor.getEmail(), null, null, null, null, null, requested, null, null, date);
		List<Point> pointList = new ArrayList<>();
		doctorAppointmentList.forEach(appointment -> {
			pointList.add(new Point(appointment.getTime().getTime(), PointType.start));
			pointList.add(new Point(appointment.getTime().getTime() + appointment.getType().getDuration(), PointType.end));
		});
		List<AppointmentDTO> patientAppointmentList = this.appointmentService.findFiltered(null, patient.getEmail(), null, null, null, null, null, null, null, date);
		patientAppointmentList.forEach(appointment -> {
			pointList.add(new Point(appointment.getTime().getTime(), PointType.start));
			pointList.add(new Point(appointment.getTime().getTime() + appointment.getType().getDuration(), PointType.end));
		});

		// remove points that are not in doctor work period
		pointList.removeIf(point -> {
			return point.timestamp <= workStart || point.timestamp >= workEnd;
		});
		
		pointList.sort(Comparator.comparing(point -> point.timestamp));

		if (pointList.size() > 0) {
			if (pointList.get(0).type == PointType.end) {
				pointList.add(0, new Point(workStart, PointType.start));
			}
			if (pointList.get(pointList.size() - 1).type == PointType.start) {
				pointList.add(new Point(workEnd, PointType.end));
			}
		}

		List<Point> pointList2 = new ArrayList<>();
		int counter = 0;
		for (Point point : pointList) {
			if (point.type == PointType.start) {
				counter++;
				if (counter == 1) {
					pointList2.add(new Point(point.timestamp, PointType.start));
				}
			} else if (point.type == PointType.end) {
				counter--;
				if (counter == 0) {
					pointList2.add(new Point(point.timestamp, PointType.end));
				}
			}
		}

		pointList2.forEach(point -> {
			point.type = point.type == PointType.start ? PointType.end : PointType.start;
		});

		pointList2.add(0, new Point(workStart, PointType.start));
		pointList2.add(new Point(workEnd, PointType.end));

		List<PeriodDTO> availablePeriodList = new ArrayList<>();
		Point p = null;
		for (Point point : pointList2) {
			if (point.type == PointType.start) {
				p = point;
			}
			if (point.type == PointType.end) {
				if (point.timestamp - p.timestamp >= doctor.getSpecialisation().getDuration()) {
					if (p.timestamp % 10 == 9) {
						p.timestamp++;
					}
					if (point.timestamp % 10 == 0) {
						point.timestamp--;
					}
					availablePeriodList.add(new PeriodDTO(p.timestamp, point.timestamp));
				}
			}
		}
		return availablePeriodList;
	}

	@Override
	public List<ClinicDTO> findPatientClinics() {
		User patient = this.userService.getCurrentUser();
		List<Appointment> patientAppointments = patient.getPatientAppointmentList();
		Long time = new Date().getTime();
		patientAppointments.removeIf(appointment -> {
			return appointment.getTime().getTime() >= time;
		});
		Set<Clinic> clinicSet = new HashSet<Clinic>();
		patientAppointments.forEach(appointment -> {
			clinicSet.add(appointment.getClinic());
		});
		clinicSet.forEach(clinic -> {
			clinic.setRatingAverage(this.clinicRatingService.findClinicRating(clinic.getId()));
		});
		List<ClinicDTO> clinicDTOList = ClinicDTO.toList(new ArrayList<Clinic>(clinicSet));
		clinicDTOList.forEach(clinicDTO -> {
			ClinicRating clinicRating = this.clinicRatingService.findByPatientIdAndClinicId(patient.getId(), clinicDTO.getId());
			if (clinicRating != null) {
				clinicDTO.setPatientRating(clinicRating.getValue());
			}
		});
		return clinicDTOList;
	}

	@Override
	public ClinicDTO rate(Integer clinicId, Integer rating) {
		Clinic clinic = this.findById(clinicId);
		User patient = this.userService.getCurrentUser();
		ClinicRating clinicRating = this.clinicRatingService.findByPatientIdAndClinicId(patient.getId(), clinicId);
		if (clinicRating != null) {
			clinicRating.setValue(rating);
			clinicRating = this.clinicRatingService.update(clinicRating);
		} else {
			clinicRating = new ClinicRating();
			clinicRating.setPatient(patient);
			clinicRating.setClinic(clinic);
			clinicRating.setValue(rating);
			clinicRating = this.clinicRatingService.create(clinicRating);
		}
		ClinicDTO clinicDTO = new ClinicDTO(clinic);
		clinicDTO.setPatientRating(rating);
		return clinicDTO;
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

	private Date dateAtHours(Date date, Integer hours) {
		return Date.from(Instant.ofEpochMilli(date.getTime())
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime()
				.truncatedTo(ChronoUnit.HOURS)
				.withHour(hours)
				.atZone(ZoneId.systemDefault())
				.toInstant());
	}

	private enum PointType {
		start, end
	}

	private class Point {
		public Long timestamp;
		public PointType type;

		public Point(Long timestamp, PointType type) {
			this.timestamp = timestamp;
			this.type = type;
		}
	}
}
