package com.cts.auth_service.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.auth_service.model.Role;
import com.cts.auth_service.model.User;
import com.cts.auth_service.repository.UserRepository;

@Service
public class UserInfoService implements UserDetailsService {
	
	private UserRepository userrepository;
	
	private Role role;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userrepository.findUserByEmail(email).orElse(null);
		
		if(user!=null && user.getRole().equals("LANDLORD")) {
			return (UserDetails) new User(user.getEmail(), user.getPassword(), user.getRole());	
		}
		else if(user!=null && user.getRole().equals("TENANT")) {
			return (UserDetails) new User(user.getEmail(), user.getPassword(), user.getRole());	
		}
		
		throw new UsernameNotFoundException("User Not Found");
	}

}
