package com.rental.tenant.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long applicationId;

    private long tenantId;  
    
    
    private long landlordId;
    
    private String tenantName;
    
    private String tenantEmail;
    
    private long mobileNo;
    
    private long propertyId;    
    
    private String propertyName;
    private String status;      

    @Temporal(TemporalType.DATE)
    private Date submittedDate;
}
