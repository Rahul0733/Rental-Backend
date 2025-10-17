package com.cts.user_service.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TenantRequest {
    private Long userId;
    private String email;
    private String name;
    private long mobileNo;
    private String address;
}