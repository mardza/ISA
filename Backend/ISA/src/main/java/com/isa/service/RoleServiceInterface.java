package com.isa.service;

import java.util.List;

import com.isa.entity.Role;

public interface RoleServiceInterface {
	
	List<Role> findAll();
	
	Role findByName(String name);
	
	Role findById(Integer id);
	
	Role findByUserId(Integer userId);
	
	Role save(Role role);
	
	void remove(Role role);
}
