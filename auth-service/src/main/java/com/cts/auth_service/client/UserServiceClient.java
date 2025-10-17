package com.cts.auth_service.client;

import com.cts.auth_service.dto.TenantProfileRequest;
import com.cts.auth_service.dto.LandlordProfileRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @PostMapping("/api/users/tenant")
    void saveTenantProfile(@RequestBody TenantProfileRequest request);

    @PostMapping("/api/users/landlord")
    void saveLandlordProfile(@RequestBody LandlordProfileRequest request);
}