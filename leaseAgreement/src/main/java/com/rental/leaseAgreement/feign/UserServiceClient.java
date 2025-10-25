package com.rental.leaseAgreement.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rental.leaseAgreement.DTO.LandLordRequest;
import com.rental.leaseAgreement.DTO.TenantRequest;

@FeignClient(name="user-service")
public interface UserServiceClient {
	
	@GetMapping("api/users/tenant/{userId}")
	public ResponseEntity<TenantRequest> getTenantDetail(@PathVariable long userId);

	 @GetMapping("api/users/landlord/{userId}")
	 public ResponseEntity<LandLordRequest> getLandlordDetail(@PathVariable long userId);

}
