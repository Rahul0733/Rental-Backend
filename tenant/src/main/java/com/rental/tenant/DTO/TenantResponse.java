package com.rental.tenant.DTO;

import lombok.Data;

@Data
public class TenantResponse {
	private long userId;
    private long tenantId;
    private String tenantName;
    private String tenantEmail;
    private long mobileNo;
    // Add any other fields returned by the UserService if needed
}
