package com.cts.property.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.property.DTO.LandlordResponse;
import com.cts.property.config.FeignClientConfig;

@FeignClient(name = "user-service", configuration = FeignClientConfig.class) 
public interface UserServiceClient {

    @GetMapping("api/users/landlord/{userId}")
    LandlordResponse getLandlordByUserId(@PathVariable long userId);
}
