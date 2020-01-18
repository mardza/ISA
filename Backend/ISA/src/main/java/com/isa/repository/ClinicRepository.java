package com.isa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isa.entity.Clinic;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {

	@Query("SELECT c " + 
			"FROM Clinic c")
	List<Clinic> findFiltered(@Param("date") Date date);
}
