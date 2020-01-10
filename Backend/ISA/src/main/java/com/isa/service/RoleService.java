package com.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.entity.Role;
import com.isa.repository.RoleRepository;


@Service
public class RoleService implements RoleServiceInterface {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

	@Override
	public Role findById(Integer id) {
		return roleRepository.findById(id).orElse(null);
	}

	@Override
	public Role findByUserId(Integer userId) {
		return roleRepository.findByUsers_Id(userId);
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void remove(Role role) {
		roleRepository.delete(role);
	}

}
