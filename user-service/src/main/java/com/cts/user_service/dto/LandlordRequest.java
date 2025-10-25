package com.cts.user_service.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LandlordRequest {
    private long userId;
    private String email;
    private String name;
    private long mobileNo;
    private String address;
}
