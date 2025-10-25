package com.cts.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantProfileRequest {
	private long userId;
	private String email;
    private String name;
    private long mobileNo;
    private String address;
}
