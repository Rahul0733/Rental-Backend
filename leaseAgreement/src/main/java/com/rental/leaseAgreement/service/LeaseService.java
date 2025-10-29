package com.rental.leaseAgreement.service;

import java.util.Calendar;
import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rental.leaseAgreement.DTO.LandLordRequest;
import com.rental.leaseAgreement.DTO.TenantApplicationRequest;
import com.rental.leaseAgreement.exception.LeaseNotFoundException;
import com.rental.leaseAgreement.exception.TenantNotFoundException;
import com.rental.leaseAgreement.feign.PropertyClient;
import com.rental.leaseAgreement.feign.TenantApplicationClient;
import com.rental.leaseAgreement.feign.UserServiceClient;
import com.rental.leaseAgreement.model.LeaseAgreement;
import com.rental.leaseAgreement.repo.LeaseAgreementRepository;

@Service
public class LeaseService {

	@Autowired
	private LeaseAgreementRepository leaseAgreementRepository;
	
	@Autowired
	private TenantApplicationClient tenantApplicationClient;
	
	@Autowired
	private UserServiceClient userServiceClient;
	
	@Autowired
	private PropertyClient propertyClient;
	
	public LeaseAgreement sendLease(long userId,LeaseAgreement lease) {
		System.out.println(lease.getApplicationId());
	    if (leaseAgreementRepository.existsByApplicationId(lease.getApplicationId())) {
	        throw new IllegalStateException("Lease has already been sent for this application.");
	    }
	    ResponseEntity<LandLordRequest> landlordId=userServiceClient.getLandlordDetail(userId);
	    lease.setLandlordId(landlordId.getBody().getLandlordId());
	    lease.setStatus("SENT"); 
	    LeaseAgreement savedLease = leaseAgreementRepository.save(lease);
	    System.out.println("--------------------"+leaseAgreementRepository.save(lease));
        tenantApplicationClient.updateStatus(lease.getApplicationId(), "Accepted");

        return savedLease; 
	}

	public List<LeaseAgreement> getLeaseByTenantId(Long tenantId) {
	    List<LeaseAgreement> leases = leaseAgreementRepository.findByTenantId(tenantId);
	    if (leases.isEmpty()) {
	        throw new TenantNotFoundException("No lease found for tenant ID: " + tenantId);
	    }
	    return leases;
	}



	public LeaseAgreement acceptLease(long leaseId) {
		LeaseAgreement lease=leaseAgreementRepository.findByLeaseId(leaseId);
		System.out.println(lease.getApplicationId()+"----------");
		 if (lease.getStatus().equals("ACTIVE")) {
		        throw new IllegalStateException("This lease has already been accepted.");
		    }
		 
		Long propertyId=lease.getPropertyId();
		List<LeaseAgreement> activeLeaseProperty=leaseAgreementRepository.findByPropertyIdAndStatus(propertyId,"ACTIVE");
		if(!activeLeaseProperty.isEmpty()) {
			tenantApplicationClient.updateStatus(lease.getApplicationId(), "Rejected");
			throw new IllegalStateException("Already accepted by another tenant Sorry!!");
		}
		
		Long tenantId=lease.getTenantId();
		List<LeaseAgreement> activeLeases=leaseAgreementRepository.findByTenantIdAndStatus(tenantId,"ACTIVE");
		if(!activeLeases.isEmpty()) {
			tenantApplicationClient.updateStatus(lease.getApplicationId(), "Rejected");
			throw new IllegalStateException("Tenant already has an active lease and rent is unpaid. Please pay the rent or ignore this lease.");
		}
		
		lease.setStatus("ACTIVE");
		return leaseAgreementRepository.save(lease);
	}

	public LeaseAgreement rejectLease(long leaseId) {
		LeaseAgreement lease=leaseAgreementRepository.findByLeaseId(leaseId);
		lease.setStatus("REJECT");
		tenantApplicationClient.updateStatus(lease.getApplicationId(),"Rejected");
		return leaseAgreementRepository.save(lease);
	}

	public LeaseAgreement uploadSignature(long leaseId, MultipartFile file) {
		LeaseAgreement lease=leaseAgreementRepository.findByLeaseId(leaseId);
		String fileName=file.getOriginalFilename();
		lease.setSignatureFileName(fileName);
		return leaseAgreementRepository.save(lease);
	}

	
	public LeaseAgreement renewLease(long leaseId, Date newEndDate) {
	    LeaseAgreement lease=leaseAgreementRepository.findByLeaseId(leaseId);
   
	    Calendar cal=Calendar.getInstance();
	    cal.setTime(lease.getEndDate());
	    cal.add(Calendar.MONTH, 1);
	    Date minimumRenewalDate=cal.getTime();

	    if (!newEndDate.after(minimumRenewalDate)) {
	        throw new IllegalArgumentException("Renewal date must be at least one month after current end date.");
	    }

	    lease.setEndDate(newEndDate);
	    lease.setStatus("RENEWED");
	    return leaseAgreementRepository.save(lease);
	}


	public LeaseAgreement terminateLease(long leaseId) {
		LeaseAgreement lease=leaseAgreementRepository.findById(leaseId).orElseThrow(()->new LeaseNotFoundException("Lease not found with ID: "+leaseId));
		lease.setStatus("TERMINATED");
		return leaseAgreementRepository.save(lease);
	}

	public List<LeaseAgreement> getLeaseForLandlord(long landlordId) {
	    List<LeaseAgreement> leases = leaseAgreementRepository.findByLandlordId(landlordId);

	    for (LeaseAgreement lease : leases) {
	        propertyClient.updatePropertyStatus(lease.getPropertyId(), lease.getStatus());
	    }

	    return leases;
	}




}
