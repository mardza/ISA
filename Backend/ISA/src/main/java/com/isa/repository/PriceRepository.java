package com.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.entity.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {

	Price findByClinicIdAndAppointmentTypeId(Integer clinicId, Integer appointmentTypeId);
}
