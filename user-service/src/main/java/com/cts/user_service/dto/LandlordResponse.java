package com.cts.user_service.dto;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LandlordResponse {
	private long landlordId;
    private String fullName;
    private String email;
    private long mobileNo;
    private String address;
    
}