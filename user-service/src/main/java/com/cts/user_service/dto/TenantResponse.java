package com.cts.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantResponse {
    private Long tenantId;
    private String fullName;
    private String email;
    private long mobileNo;
}
