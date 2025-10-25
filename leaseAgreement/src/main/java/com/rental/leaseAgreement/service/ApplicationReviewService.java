package com.rental.leaseAgreement.service;

import java.awt.desktop.UserSessionEvent;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rental.leaseAgreement.DTO.LandLordRequest;
import com.rental.leaseAgreement.DTO.TenantApplicationRequest;
import com.rental.leaseAgreement.feign.TenantApplicationClient;
import com.rental.leaseAgreement.feign.UserServiceClient;
import com.rental.leaseAgreement.model.LeaseAgreement;
import com.rental.leaseAgreement.repo.LeaseAgreementRepository;

@Service
public class ApplicationReviewService {
	
	@Autowired
	private TenantApplicationClient tenantApplicationClient;
	
	@Autowired
	private UserServiceClient userServiceClient;
	
	@Autowired
	private LeaseAgreementRepository leaseAgreementRepository;
	
	public List<TenantApplicationRequest> getPendingApplicationsByOwner(long userId) {
	    ResponseEntity<LandLordRequest> landlord = userServiceClient.getLandlordDetail(userId);
	    long landlordId = landlord.getBody().getLandlordId();

	    List<TenantApplicationRequest> allApplications = tenantApplicationClient.getPendingApplicationsByOwner();
	    List<TenantApplicationRequest> filtered = new ArrayList<>();

	    for (TenantApplicationRequest app : allApplications) {
	        if (app.getLandlordId() !=0 && app.getLandlordId() == landlordId) {
	            filtered.add(app);
	        }
	    }

	    return filtered;
	}

//	public LeaseAgreement acceptApplication(long applicationId) {
//	    LeaseAgreement app = leaseAgreementRepository.findById(applicationId)
//	        .orElseThrow(() -> new RuntimeException("Application not found"));
//	    app.setStatus("Accepted");
//	    return leaseAgreementRepository.save(app);
//	}

	public LeaseAgreement rejectApplication(long applicationId) {

        tenantApplicationClient.updateStatus(applicationId, "Rejected");

        return null;
	}

}
