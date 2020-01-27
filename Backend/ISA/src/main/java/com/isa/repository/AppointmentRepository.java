package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isa.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	@Query("SELECT a " + 
			"FROM Appointment a JOIN FETCH a.clinic c " + 
			"WHERE c.id = :id AND a.patient IS NULL")
	List<Appointment> findPredefinedByClinicId(@Param("id") Integer id);
}
