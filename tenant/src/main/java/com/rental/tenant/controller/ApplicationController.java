package com.rental.tenant.controller;

import com.rental.tenant.DTO.ApplicationRequest;
import com.rental.tenant.DTO.PropertyDTO;
import com.rental.tenant.model.Application;
import com.rental.tenant.service.ApplicationService;
import com.rental.tenant.service.TenantPropertyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/applications")
@CrossOrigin(origins = "http://localhost:4200")
public class ApplicationController {
	

    @Autowired
    private ApplicationService applicationService;
    
    @Autowired
    private TenantPropertyService tenantPropertyService;
    
    @GetMapping("/landlord/property/available")
    public List<PropertyDTO> getAvailableProperties() {
        return tenantPropertyService.fetchAvailableProperties();
    }

    @PostMapping("/submit/{userId}")
    public ResponseEntity<Application> submitApplication(@PathVariable long userId,
                                                         @RequestBody ApplicationRequest request) {
    	System.out.println("-------------"+userId);
        Application savedApp = applicationService.processApplicationSubmission(userId, request);
        return ResponseEntity.ok(savedApp);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getApplicationsByUserId(@PathVariable long userId) {
        try {
            List<Application> applications = applicationService.getApplicationsByUserId(userId);
            return ResponseEntity.ok(applications);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }



    @PutMapping("/{applicationId}/status")
    public ResponseEntity<?> updateStatus(@PathVariable long applicationId,
                                          @RequestParam String status) {
        try {
            Application updatedApp = applicationService.updateApplicationStatus(applicationId, status);
            return ResponseEntity.ok(updatedApp);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }


    
    @GetMapping("/user/landlord")
    public List<Application> getAllApplications(){
    	System.out.println(applicationService.getAllApplications());
    	return applicationService.getAllApplications();
    }
    
    
//    @PostMapping
//    Application upData(@RequestBody Application app) {
//    	return applicationService.getData(app);
//    }
    
//    @GetMapping
//    List<Application> appData(){
//    	return applicationService.getappData();
//    	
//    }
    
    
    
   
}
