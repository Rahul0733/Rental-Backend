package com.rental.leaseAgreement.service;

import java.util.Calendar;
import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rental.leaseAgreement.DTO.TenantApplicationRequest;
import com.rental.leaseAgreement.exception.LeaseNotFoundException;
import com.rental.leaseAgreement.exception.TenantNotFoundException;
import com.rental.leaseAgreement.feign.TenantApplicationClient;
import com.rental.leaseAgreement.model.LeaseAgreement;
import com.rental.leaseAgreement.repo.LeaseAgreementRepository;

@Service
public class LeaseService {

	@Autowired
	private LeaseAgreementRepository leaseAgreementRepository;
	
	@Autowired
	private TenantApplicationClient tenantApplicationClient;
	
	public LeaseAgreement sendLease(LeaseAgreement lease) {
		System.out.println(lease.getApplicationId());
	    if (leaseAgreementRepository.existsByApplicationId(lease.getApplicationId())) {
	        throw new IllegalStateException("Lease has already been sent for this application.");
	    }
	    lease.setStatus("SENT"); 
	    LeaseAgreement savedLease = leaseAgreementRepository.save(lease);
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
		lease.setStatus("ACTIVE");
		return leaseAgreementRepository.save(lease);
	}

	public LeaseAgreement rejectLease(long leaseId) {
		LeaseAgreement lease=leaseAgreementRepository.findByLeaseId(leaseId);
		lease.setStatus("REJECT");
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



}
