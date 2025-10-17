package com.cts.auth_service.controller;

import com.cts.auth_service.dto.AuthResponse;
import com.cts.auth_service.dto.LoginRequest;
import com.cts.auth_service.dto.SignupRequest;
import com.cts.auth_service.service.AuthService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin(origins="http://localhost:4200/")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup/tenant")
    public ResponseEntity<String> signupTenant(@RequestBody SignupRequest request) {
        request.setRole(com.cts.auth_service.model.Role.TENANT);
        authService.signup(request);
        return ResponseEntity.ok("Tenant Signup Successful");
    }

    @PostMapping("/signup/landlord")
    public ResponseEntity<String> signupLandlord(@RequestBody SignupRequest request) {
        request.setRole(com.cts.auth_service.model.Role.LANDLORD);
        authService.signup(request);
        return ResponseEntity.ok("Landlord Signup Successfull");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        System.out.println(response);
        return ResponseEntity.ok(response);
    }
}