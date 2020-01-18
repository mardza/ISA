package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.isa.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {

	@Query("SELECT r " + 
			"FROM Room r")
	List<Room> findFiltered();
}
