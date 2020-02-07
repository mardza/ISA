package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isa.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

//	@Query("SELECT a " + 
//			"FROM Appointment a JOIN FETCH a.clinic c JOIN FETCH a.type JOIN FETCH a.price " + 
//			"WHERE c.id = :id AND a.predefined = true")
//	List<Appointment> findPredefinedByClinicId(@Param("id") Integer id);
}
