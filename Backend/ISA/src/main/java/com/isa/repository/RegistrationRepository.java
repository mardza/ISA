package com.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.entity.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, String> {
		
}
