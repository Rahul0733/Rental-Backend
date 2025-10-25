package com.rental.tenant.service;

import com.rental.tenant.DTO.PropertyDTO;
import com.rental.tenant.feign.PropertyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantPropertyService {

    @Autowired
    private PropertyClient propertyClient;

    public List<PropertyDTO> fetchAvailableProperties() {
        return propertyClient.getAvailableProperties();
    }

    public String getPropertyNameById(long propertyId) {
        return fetchAvailableProperties().stream()
            .filter(p -> p.getPropertyId() == propertyId)
            .map(PropertyDTO::getName)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Property not found"));
    }

//	public long getLandlordIdById(long propertyId) {
//		
//		 return fetchAvailableProperties().stream()
//		            .filter(p -> p.getPropertyId() == propertyId)
//		            .map(PropertyDTO::getLandlordId)
//		            .findFirst()
//		            .orElseThrow(() -> new RuntimeException("Landord not found"));
//	}

}
