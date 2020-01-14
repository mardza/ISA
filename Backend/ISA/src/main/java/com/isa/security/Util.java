package com.isa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.isa.entity.User;
import com.isa.security.exception.TokenNotValidException;
import com.isa.service.UserService;


@Component
public class Util {
	
	@Autowired
	private UserService userService;
	
	public User getCurrentUser() {
		String email;
		Authentication currentUserAuth;
		currentUserAuth = SecurityContextHolder.getContext().getAuthentication();
		if(currentUserAuth != null) {
			User user = (User)currentUserAuth.getPrincipal();
			if(user == null) {
				throw new TokenNotValidException("Could not get current user, Auth not valid");
			}
			return user;
			
//			email = currentUserAuth.getName();
//			if(email != null) {
//				if(userService != null) {
//					return userService.findByEmail(email);
//				}
//				System.out.println("USER SERVICE = NULL");
//			}
//			return null;
		}
		throw new TokenNotValidException("Could not get current user, Auth not valid");
	}
}
