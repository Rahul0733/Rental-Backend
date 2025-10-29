package com.rental.leaseAgreement.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="property")
public interface PropertyClient {
	@PutMapping("/auth/users/property/update-status/{propertyId}")
	void updatePropertyStatus(@PathVariable("propertyId") long propertyId,
	                          @RequestParam("status") String status);



}
