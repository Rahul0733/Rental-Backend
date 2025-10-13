package com.cts.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.auth.DTO.LandlordSignup;
import com.cts.auth.DTO.Login;
import com.cts.auth.DTO.LoginResponse;
import com.cts.auth.DTO.TenantSignup;
import com.cts.auth.Service.AuthService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin(origins="http://localhost:4200/")
public class AuthController {
	
	private AuthService service;
	
	@PostMapping("/owner/signup")
	public String landlordSignup(@RequestBody LandlordSignup request) {
		return this.service.landlordSignup(request)?"Registration successful":"Failed";
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody Login request) {
		System.out.println(request);
		System.out.println(this.service.login(request));
		LoginResponse response = service.login(request);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/tenant/signup")
	public String tenantSignup(@RequestBody TenantSignup request) {
		System.out.println(request);
		return this.service.tenantSignup(request)?"Registration successful":"Failed";
	}
	
}
