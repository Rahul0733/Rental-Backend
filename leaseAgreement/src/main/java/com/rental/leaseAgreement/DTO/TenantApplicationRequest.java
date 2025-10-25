package com.rental.leaseAgreement.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantApplicationRequest {
	private long applicationId;
    private String tenantName;
    private long tenantId;
    private long landlordId;
    private String tenantEmail;
    private long mobileNo;
    private long propertyId;
    private String propertyName;
    private Date submittedDate;
    private String status;
    
 
 
    // or String if preferred

}


