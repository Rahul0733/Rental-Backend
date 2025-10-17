package com.cts.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.user_service.dto.LandlordRequest;
import com.cts.user_service.dto.LandlordResponse;
import com.cts.user_service.dto.TenantRequest;
import com.cts.user_service.dto.TenantResponse;
import com.cts.user_service.exception.UserNotFoundException;
import com.cts.user_service.model.Landlord;
import com.cts.user_service.model.Tenant;
import com.cts.user_service.repository.LandlordRepository;
import com.cts.user_service.repository.TenantRepository;

@Service
public class UserService {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private LandlordRepository landlordRepository;

    public TenantResponse getTenantProfile(Long userId) {
        Tenant tenant = tenantRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("Tenant not found with userId: " + userId));
        
        TenantResponse response = new TenantResponse();
        response.setTenantId(tenant.getTenantId());
        response.setFullName(tenant.getName());
        response.setEmail(tenant.getEmail());
        response.setMobileNo(tenant.getMobileNo());
        
        
        return response;
    }

    public LandlordResponse getLandlordProfile(Long userId) {
        Landlord landlord = landlordRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("Landlord not found with userId: " + userId));
        
        LandlordResponse response = new LandlordResponse();
        response.setLandlordId(landlord.getLandlordId());
        response.setFullName(landlord.getName());
        response.setEmail(landlord.getEmail());
        response.setMobileNo(landlord.getMobileNo());
        response.setAddress(landlord.getAddress());
        
        
        return response;
    }
    
    public void saveTenantProfile(TenantRequest request) {
        Tenant tenant = new Tenant();
            tenant.setUserId(request.getUserId());
            tenant.setEmail(request.getEmail());
            tenant.setName(request.getName());
            tenant.setMobileNo(request.getMobileNo());
            tenant.setAddress(request.getAddress());
            
        tenantRepository.save(tenant);
    }

    public void saveLandlordProfile(LandlordRequest request) {
        Landlord landlord = new Landlord();
            landlord.setUserId(request.getUserId());
            landlord.setEmail(request.getEmail());
            landlord.setName(request.getName());
            landlord.setMobileNo(request.getMobileNo());
            landlord.setAddress(request.getAddress());

        landlordRepository.save(landlord);
    }
    

	public Landlord getLandlordByUserId(Long userId) {
	    return landlordRepository.findByUserId(userId)
	        .orElseThrow(() -> new UserNotFoundException("Landlord not found for userId: " + userId));
	}

}