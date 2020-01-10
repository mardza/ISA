package com.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);

	Role findByUsers_Id(Integer userId);
}
