package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isa.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);
	
	User findByEmailAndPassword(String email, String password);
	
	List<User> findByRegistrationApproved(Boolean approved);
	
	List<User> findByRoleName(String name);
	
	@Query(	"SELECT u " +
			"FROM User u JOIN FETCH u.role role JOIN FETCH u.registration registration " + 
			"WHERE (registration.approved = :approved OR :approved IS NULL) AND " + 
				"(registration.activated = :activated OR :activated IS NULL) AND " + 
				"(role.name = :roleName OR :roleName IS NULL) AND " + 
				"(u.firstName LIKE CONCAT('%', :firstName, '%') OR :firstName IS NULL) AND " + 
				"(u.lastName LIKE CONCAT('%', :lastName, '%') OR :lastName IS NULL) AND " + 
				"(u.insuranceNumber LIKE CONCAT('%', :insuranceNumber, '%') OR :insuranceNumber IS NULL)")
	List<User> findFiltered(
			@Param("approved") Boolean approved, 
			@Param("activated") Boolean activated,
			@Param("roleName") String roleName,
			@Param("firstName") String firstName,
			@Param("lastName") String lastName,
			@Param("insuranceNumber") String insuranceNumber);
	
	
	User findByRegistrationId(String registrationId);
}
