package com.cts.property.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.property.DTO.LandlordDto;
import com.cts.property.DTO.PropertyDto;
import com.cts.property.feign.LandlordFeignClient;
import com.cts.property.model.Property;
import com.cts.property.repository.PropertyRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PropertyService {

    private final PropertyRepository propRepo;
    private final LandlordFeignClient landlordFeignClient;

    public Property addPropertyByUserId(int userId, PropertyDto dto) {
        LandlordDto landlord = landlordFeignClient.getLandlordByUserId(userId);

        Property prop = new Property();
        prop.setOwnerId(landlord.getOwnerId());
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
        return propRepo.findByAvailabilityStatus("Available");
    }

    public List<Property> getOwnerPropertiesByUserId(int userId) {
        LandlordDto landlord = landlordFeignClient.getLandlordByUserId(userId);
        System.out.println("ownerID: "+landlord.getOwnerId());
        System.out.println(propRepo.findPropertiesByOwnerId(landlord.getOwnerId()));
        return propRepo.findPropertiesByOwnerId(landlord.getOwnerId());
    }

    public Property updateProperty(int propertyId, PropertyDto dto) {
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

    public void deleteProperty(int propertyId) {
        propRepo.deleteById(propertyId);
    }
}