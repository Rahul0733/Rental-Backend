package com.cts.auth_service.dto;

import com.cts.auth_service.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
	private String token;
	private long userId;
	private Role role;
	private String message;
}
