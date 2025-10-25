package com.rental.tenant.DTO;

import lombok.Data;

@Data
public class PropertyDTO {
    private long propertyId;        
    private long landlordId; 
    private String name;
    private String address;
    private long rentAmount;
    private String availabilityStatus;
    private String image; 
}
