package com.rental.leaseAgreement.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rental.leaseAgreement.DTO.ApplicationActionDTO;
import com.rental.leaseAgreement.DTO.TenantApplicationRequest;


@FeignClient(name = "tenant")
public interface TenantApplicationClient {
    @GetMapping("applications/user/landlord")
    List<TenantApplicationRequest> getPendingApplicationsByOwner();
    
    @PutMapping("applications/{applicationId}/status")
    ApplicationActionDTO updateStatus(@PathVariable long applicationId,@RequestParam String status);
}

