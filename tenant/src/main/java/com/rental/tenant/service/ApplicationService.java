package com.rental.tenant.service;

import com.rental.tenant.DTO.ApplicationRequest;
import com.rental.tenant.DTO.ApplicationResponse;
import com.rental.tenant.DTO.PropertyDTO;
import com.rental.tenant.DTO.TenantResponse;
import com.rental.tenant.feign.UserServiceClient;
import com.rental.tenant.model.Application;
import com.rental.tenant.repo.ApplicationRepository;

import feign.FeignException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;
    
    @Autowired
    private UserServiceClient userServiceClient;
    
    @Autowired
    private TenantPropertyService tenantPropertyService;

    
    public List<Application> getApplicationsByUserId(long userId) {
        try {
            TenantResponse tenant = userServiceClient.getTenantDetail(userId);

            if (tenant.getTenantId() == 0) {
                throw new IllegalArgumentException("Invalid tenantId for userId: " + userId);
            }

            return applicationRepository.findByTenantId(tenant.getTenantId());

        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException("Tenant not found for userId: " + userId);
        }
    }

    public Application processApplicationSubmission(long userId, ApplicationRequest request) {
        if(userId == 0) {
            throw new IllegalArgumentException("Invalid userId");	
        }
        System.out.println("reached------------------"+userId);
        TenantResponse tenant = userServiceClient.getTenantDetail(userId);
        if (tenant.getTenantId() == 0) {
            throw new IllegalArgumentException("Invalid tenantId for userId: " + userId);
        }
        
        boolean alreadyApplied = applicationRepository.existsByTenantIdAndPropertyId(tenant.getTenantId(), request.getPropertyId());
        if (alreadyApplied) {
            throw new IllegalArgumentException("Application already submitted for this property.");
        }
        System.out.println(tenant);
        Application application = new Application();
        application.setTenantId(tenant.getTenantId());
        application.setTenantName(tenant.getTenantName());
        application.setTenantEmail(tenant.getTenantEmail());
        application.setMobileNo(tenant.getMobileNo());
        application.setPropertyId(request.getPropertyId());
        application.setLandlordId(request.getLandlordId());
        application.setStatus("Pending");
        application.setSubmittedDate(new java.sql.Date(System.currentTimeMillis()));
//        application.setLandlordId(tenantPropertyService.getLandlordIdById(request.getPropertyId()));
        application.setPropertyName(tenantPropertyService.getPropertyNameById(request.getPropertyId()));

        return applicationRepository.save(application);
    }

    public List<Application> getAllApplications(){
    	List<Application> applications = applicationRepository.findAll();
    	return applications;
    }
    
    public Application updateApplicationStatus(long applicationId, String status) {
        System.out.println("Updating status to: " + status);
        Application application = applicationRepository.findByApplicationId(applicationId);
        if (application == null) {
            throw new IllegalArgumentException("Application ID not found: " + applicationId);
        }
        application.setStatus(status);
        System.out.println("Updated application: " + application);
        return applicationRepository.save(application);
    }
}
