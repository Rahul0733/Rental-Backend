package com.rental.payment.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rental.payment.DTO.RentInfo;
import com.rental.payment.config.FeignClientConfig;

@FeignClient(name = "leaseAgreement")
public interface LeaseClient {

    @GetMapping("api/lease/rent-info/{tenantId}")
    List<RentInfo> getRentInfoByTenantId(@PathVariable long tenantId);
}
