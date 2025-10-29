package com.rental.tenant.service;

import com.rental.tenant.DTO.ApplicationRequest;
import com.rental.tenant.DTO.ApplicationResponse;
import com.rental.tenant.DTO.PropertyDTO;
import com.rental.tenant.DTO.TenantResponse;
import com.rental.tenant.feign.PropertyClient;
import com.rental.tenant.feign.UserServiceClient;
import com.rental.tenant.model.Application;
import com.rental.tenant.repo.ApplicationRepository;

import feign.FeignException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;
    
    @Autowired
    private UserServiceClient userServiceClient;
    
    @Autowired 
    private PropertyClient propertyClient;
    
    @Autowired
    private TenantPropertyService tenantPropertyService;
    
    public List<PropertyDTO> fetchAvailableProperties() {
        return propertyClient.getAvailableProperties();
    }
    
    public List<Application> getApplicationsByUserId(long userId) {
           TenantResponse tenant = userServiceClient.getTenantDetail(userId);
            if (tenant.getTenantId() == 0) {
                throw new IllegalArgumentException("Invalid tenantId for userId: " + userId);
            }

            return applicationRepository.findByTenantId(tenant.getTenantId());
    }

    public Application processApplicationSubmission(long userId, ApplicationRequest request) {
        if (userId == 0) {
            throw new IllegalArgumentException("Invalid userId");
        }

        TenantResponse tenant = userServiceClient.getTenantDetail(userId);
        if (tenant.getTenantId() == 0) {
            throw new IllegalArgumentException("Invalid tenantId for userId: " + userId);
        }

        boolean alreadyApplied = applicationRepository.existsByTenantIdAndPropertyId(
            tenant.getTenantId(), request.getPropertyId());
        if (alreadyApplied) {
            throw new IllegalArgumentException("Application already submitted for this property.");
        }

        List<PropertyDTO> availableProperties = fetchAvailableProperties();
        String propertyName = availableProperties.stream()
            .filter(p -> p.getPropertyId() == request.getPropertyId())
            .map(PropertyDTO::getName)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Property not found in available list"));

        Application application = new Application();
        application.setTenantId(tenant.getTenantId());
        application.setTenantName(tenant.getTenantName());
        application.setTenantEmail(tenant.getTenantEmail());
        application.setMobileNo(tenant.getMobileNo());
        application.setPropertyId(request.getPropertyId());
        application.setLandlordId(request.getLandlordId());
        application.setPropertyName(propertyName);
        application.setStatus("Pending");
        application.setSubmittedDate(new java.sql.Date(System.currentTimeMillis()));

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

//    List<Application>list=new ArrayList<>();
//	public List<Application> getappData() {
//		list=applicationRepository.findAll();
//		return list;
//	}

//	public Application getData(Application app) {
//		return applicationRepository.save(app);
//	}
    
    
    
    
}
