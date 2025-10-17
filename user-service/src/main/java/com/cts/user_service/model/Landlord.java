package com.cts.user_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "landlords")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Landlord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long landlordId;
    
    private String email;

    private Long userId; 

    private String name;

    private long mobileNo;

    private String address; 
}
