package com.cts.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.auth.model.Landlord;
import com.cts.auth.repository.LandlordRepository;

@RestController
@RequestMapping("/owner")
@CrossOrigin(origins = "http://localhost:4200")
public class LandlordController {

    @Autowired
    private LandlordRepository landlordRepository;

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<Landlord> getLandlordByUserId(@PathVariable int userId) {
        Landlord landlord = landlordRepository.findByUserUserId(userId)
                .orElseThrow(() -> new RuntimeException("Landlord not found for userId: " + userId));
        return ResponseEntity.ok(landlord);
    }
}