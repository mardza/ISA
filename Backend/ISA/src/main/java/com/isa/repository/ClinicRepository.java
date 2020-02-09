package com.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.entity.Clinic;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {

//	@Query("SELECT c " + 
//			"FROM Clinic c " + 
//			"WHERE 0 < ( " + 
//				"SELECT COUNT(e.id) " + 
//				"FROM User e " + 
//				"WHERE e.role.name = 'ROLE_DOCTOR' AND e.clinic.id = c.id AND e.specialisation.id = :appointmentTypeId AND 0 = (" + 
//					"SELECT MAX() " + 
//					"FROM Appointment a " + 
//					"WHERE (a.doctor.id = e.id) AND (a.time BETWE)" +
//				")" + 
//			")")
//	List<Clinic> findFilteredByEmployeeSpecialisation(@Param("appointmentTypeId") Integer id, @Param("dateParam") Date date);
}
