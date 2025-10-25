package com.cts.user_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tenants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tenantId;

    private long userId; 
    
    private String email;

    private String name;

    private long mobileNo;
    
    private String address;

    private String rentalHistory; 
}
