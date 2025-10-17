package com.cts.user_service.controller;

import com.cts.user_service.dto.LandlordRequest;
import com.cts.user_service.dto.LandlordResponse;
import com.cts.user_service.dto.TenantRequest;
import com.cts.user_service.dto.TenantResponse;
import com.cts.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/tenant/{userId}")
    public ResponseEntity<TenantResponse> getTenantDetail(@PathVariable Long userId) {
        TenantResponse response = userService.getTenantProfile(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/landlord/{userId}")
    public ResponseEntity<LandlordResponse> getLandlordDetail(@PathVariable Long userId) {
        LandlordResponse response = userService.getLandlordProfile(userId);
        System.out.println("response to property service----------"+response);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/tenant")
    public ResponseEntity<Void> saveTenantProfile(@RequestBody TenantRequest request) {
        userService.saveTenantProfile(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/landlord")
    public ResponseEntity<Void> saveLandlordProfile(@RequestBody LandlordRequest request) {
        userService.saveLandlordProfile(request);
        return ResponseEntity.ok().build();
    }
}