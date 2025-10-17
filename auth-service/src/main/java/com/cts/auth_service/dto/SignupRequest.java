package com.cts.auth_service.dto;

import com.cts.auth_service.model.Role;

import lombok.Data;

@Data
public class SignupRequest {
	
	private String name;
	private String email;
	private long mobileNo;
	private String address;
	private String password;
	
	private Role role;
}
