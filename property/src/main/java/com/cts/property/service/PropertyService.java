package com.cts.property.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.cts.property.DTO.LandlordResponse;
import com.cts.property.DTO.PropertyDto;
import com.cts.property.feign.UserServiceClient;
import com.cts.property.model.Property;
import com.cts.property.repository.PropertyRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PropertyService {

    private final PropertyRepository propRepo;
    private final UserServiceClient userServiceClient;

    public Property addPropertyByUserId(long userId, PropertyDto dto) {
        LandlordResponse landlord = userServiceClient.getLandlordByUserId(userId);
        if(propRepo.existsPropertyByName(dto.getName())) {
        	return null;
        }
        
        Property prop = new Property();
        prop.setLandlordId(landlord.getLandlordId());
        prop.setName(dto.getName());
        prop.setAddress(dto.getAddress());
        prop.setRentAmount(dto.getRentAmount());
        prop.setSize(dto.getSize());
        prop.setAvailabilityStatus(dto.getAvailabilityStatus());
        prop.setDescription(dto.getDescription());
        prop.setImage(dto.getImage());

        return propRepo.save(prop);
        
    }

    public List<Property> getAvailableProperties() {
        return propRepo.findByAvailabilityStatusIn(List.of("Available", "Rejected"));
    }


    public List<Property> getOwnerPropertiesByUserId(long userId) {
        LandlordResponse landlord = userServiceClient.getLandlordByUserId(userId);
        System.out.println("landLordID: "+landlord.getLandlordId());
        System.out.println(propRepo.findPropertiesByLandlordId(landlord.getLandlordId()));
        return propRepo.findPropertiesByLandlordId(landlord.getLandlordId());
    }

    public Property updateProperty(long propertyId, PropertyDto dto) {
        Property existing = propRepo.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        existing.setName(dto.getName());
        existing.setAddress(dto.getAddress());
        existing.setRentAmount(dto.getRentAmount());
        existing.setSize(dto.getSize());
        existing.setAvailabilityStatus(dto.getAvailabilityStatus());
        existing.setDescription(dto.getDescription());
        existing.setImage(dto.getImage());

        return propRepo.save(existing);
    }

    public void deleteProperty(long propertyId) {
        if (!propRepo.existsById(propertyId)) {
            throw new NoSuchElementException("Property not available");
        }
        propRepo.deleteById(propertyId);
    }
    
    public void updatePropertyStatus(Long propertyId, String status) {
        Property property = propRepo.findById(propertyId)
            .orElseThrow(() -> new NoSuchElementException("Property not found with ID: " + propertyId));
        if (status.equalsIgnoreCase("ACTIVE")) {
            property.setAvailabilityStatus("Leased");
        } else if (status.equalsIgnoreCase("REJECT") || status.equalsIgnoreCase("TERMINATED")) {
            property.setAvailabilityStatus("Available");
        }
        propRepo.save(property);
    }

}