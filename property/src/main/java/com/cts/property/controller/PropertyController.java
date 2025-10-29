package com.cts.property.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.property.DTO.PropertyDto;
import com.cts.property.model.Property;
import com.cts.property.service.PropertyService;

@RestController
@RequestMapping("auth/users/property")
@CrossOrigin(origins = "http://localhost:4200")
public class PropertyController {

    @Autowired
    private PropertyService service;
   
    
    @PostMapping("/add/by-user/{userId}")
    public ResponseEntity<Property> addProperty(@PathVariable long userId, @RequestBody PropertyDto dto) {
        return ResponseEntity.ok(service.addPropertyByUserId(userId, dto));
    }

    @GetMapping("/landlord/by-user/{userId}")
    public ResponseEntity<List<Property>> getOwnerProperties(@PathVariable long userId) {
    	System.out.println("Reached");
        return ResponseEntity.ok(service.getOwnerPropertiesByUserId(userId));
    }

    @PutMapping("/update/{propertyId}")
    public ResponseEntity<Property> updateProperty(@PathVariable long propertyId, @RequestBody PropertyDto dto) {
    	System.out.println("Reached");
        return ResponseEntity.ok(service.updateProperty(propertyId, dto));
    }

    @DeleteMapping("/delete/{propertyId}")
    public ResponseEntity<?> deleteProperty(@PathVariable long propertyId) {
        try {
            service.deleteProperty(propertyId);
            return ResponseEntity.ok("Property deleted successfully.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property not available.");
        }
    }

    @GetMapping("/available")
    public ResponseEntity<List<Property>> getAvailableProperties() {
        return ResponseEntity.ok(service.getAvailableProperties());
    }
    
    @PutMapping("/update-status/{propertyId}")
    public ResponseEntity<String> updatePropertyStatus(@PathVariable Long propertyId,
                                                       @RequestParam String status) {
        service.updatePropertyStatus(propertyId, status);
        return ResponseEntity.ok("Property status updated to: " + status);
    }


}