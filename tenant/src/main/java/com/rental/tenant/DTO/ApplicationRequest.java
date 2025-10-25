package com.rental.tenant.DTO;

import lombok.Data;

@Data
public class ApplicationRequest {
    private long propertyId;
    private long landlordId;
    private String status;
    private String submittedDate;
}
