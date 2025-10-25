package com.rental.tenant.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.rental.tenant.DTO.PropertyDTO;
import com.rental.tenant.config.FeignClientConfig;

import java.util.List;

@FeignClient(name = "property")
public interface PropertyClient {

    @GetMapping("auth/users/property/available")
    List<PropertyDTO> getAvailableProperties();
}
