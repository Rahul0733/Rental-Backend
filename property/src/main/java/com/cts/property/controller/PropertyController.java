package com.cts.property.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.property.DTO.PropertyDto;
import com.cts.property.model.Property;
import com.cts.property.service.PropertyService;

@RestController
@RequestMapping("/property")
@CrossOrigin(origins = "http://localhost:4200")
public class PropertyController {

    @Autowired
    private PropertyService service;
   
    @PostMapping("/add/by-user/{userId}")
    public ResponseEntity<Property> addProperty(@PathVariable int userId, @RequestBody PropertyDto dto) {
        return ResponseEntity.ok(service.addPropertyByUserId(userId, dto));
    }

    @GetMapping("/owner/by-user/{userId}")
    public ResponseEntity<List<Property>> getOwnerProperties(@PathVariable int userId) {
        return ResponseEntity.ok(service.getOwnerPropertiesByUserId(userId));
    }

    @PutMapping("/update/{propertyId}")
    public ResponseEntity<Property> updateProperty(@PathVariable int propertyId, @RequestBody PropertyDto dto) {
    	System.out.println("Reached");
        return ResponseEntity.ok(service.updateProperty(propertyId, dto));
    }

    @DeleteMapping("/delete/{propertyId}")
    public ResponseEntity<Void> deleteProperty(@PathVariable int propertyId) {
        service.deleteProperty(propertyId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available")
    public ResponseEntity<List<Property>> getAvailableProperties() {
        return ResponseEntity.ok(service.getAvailableProperties());
    }
}