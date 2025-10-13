package com.cts.property.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.property.DTO.LandlordDto;

@FeignClient(name = "auth") 
public interface LandlordFeignClient {

    @GetMapping("/owner/by-user/{userId}")
    LandlordDto getLandlordByUserId(@PathVariable("userId") int userId);
}
