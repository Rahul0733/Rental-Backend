package com.rental.tenant.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rental.tenant.DTO.TenantResponse;

@FeignClient(name = "user-service") 
public interface UserServiceClient {

	@GetMapping("/api/users/tenant/{userId}")
    TenantResponse getTenantDetail(@PathVariable long userId);
	
//	@GetMapping("/api/users/tenants/{tenantId}")
//	TenantResponse getTenantDetailByTenantId(@PathVariable long tenantId);
	
}



