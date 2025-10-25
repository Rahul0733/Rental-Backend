package com.cts.auth_service.service;

import com.cts.auth_service.client.UserServiceClient;
import com.cts.auth_service.dto.*;
import com.cts.auth_service.exception.InvalidCredentialsException;
import com.cts.auth_service.exception.UserAlreadyExistsException;
import com.cts.auth_service.model.User;
import com.cts.auth_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    
    private final UserServiceClient userServiceClient;

    public void signup(SignupRequest request) {
        if (!userRepository.findUserByEmail(request.getEmail()).isEmpty()) {
            throw new UserAlreadyExistsException("Email already registered");
        }

        User user = new User();
        
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
               

        userRepository.save(user);

        if ("TENANT".equalsIgnoreCase(request.getRole().name())) {
            TenantProfileRequest tenantProfile = new TenantProfileRequest();
            
            tenantProfile.setUserId(user.getUserId());
            tenantProfile.setEmail(request.getEmail());
            tenantProfile.setName(request.getName());
            tenantProfile.setMobileNo(request.getMobileNo());
            tenantProfile.setAddress(request.getAddress());

            System.out.println(tenantProfile);
            userServiceClient.saveTenantProfile(tenantProfile);

        } else if ("LANDLORD".equalsIgnoreCase(request.getRole().name())) {
            LandlordProfileRequest landlordProfile = new LandlordProfileRequest();
               
            landlordProfile.setUserId(user.getUserId());
            landlordProfile.setName(request.getName());
            landlordProfile.setEmail(request.getEmail());
            landlordProfile.setMobileNo(request.getMobileNo());
            landlordProfile.setAddress(request.getAddress());

            userServiceClient.saveLandlordProfile(landlordProfile);
        }

    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());
        System.out.println("Generated token------------------------------------------"+token);
        return new AuthResponse(token, user.getUserId(), user.getRole(), "Login Successfull");
    }
}