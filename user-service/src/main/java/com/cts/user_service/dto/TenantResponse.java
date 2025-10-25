package com.cts.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantResponse {
    private long tenantId;
    private String tenantName;
    private String tenantEmail;
    private long mobileNo;
}
