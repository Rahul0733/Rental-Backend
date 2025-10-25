package com.rental.leaseAgreement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rental.leaseAgreement.DTO.TenantApplicationRequest;
import com.rental.leaseAgreement.model.LeaseAgreement;
import com.rental.leaseAgreement.service.ApplicationReviewService;

@RestController
@RequestMapping("/api/review")
@CrossOrigin(origins = "http://localhost:4200")
public class ApplicationReviewController {

    @Autowired
    private ApplicationReviewService applicationService;

    @GetMapping("/applications/{userId}")
    public List<TenantApplicationRequest> getApplications(@PathVariable long userId) {
    	System.out.println(applicationService.getPendingApplicationsByOwner(userId));
        return applicationService.getPendingApplicationsByOwner(userId);
    }


//    @PutMapping("/applications/accept/{applicationId}")
//    public ResponseEntity<LeaseAgreement> accept(@PathVariable long applicationId) {
//        LeaseAgreement updated = applicationService.acceptApplication(applicationId);
//        return ResponseEntity.ok(updated);
//    }

    @PostMapping("/applications/reject/{applicationId}")
    public ResponseEntity<?> rejectApplication(@PathVariable long applicationId) {
        try {
            applicationService.rejectApplication(applicationId);
            return ResponseEntity.ok(Map.of("status", "REJECTED", "message", "Application rejected"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }




}
