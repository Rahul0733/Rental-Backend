package com.rental.leaseAgreement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lease_agreement")
public class LeaseAgreement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long leaseId;

    private long propertyId;

    private long tenantId;
    
    private long applicationId;
    
    private Date startDate;

    private Date endDate;

    private long landlordId;
    
    private String terms;

    private long rentAmount;

    private String status;

    private String signatureFileName;

	
}
