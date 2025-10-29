package com.rental.leaseAgreement.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rental.leaseAgreement.DTO.RentInfo;
import com.rental.leaseAgreement.DTO.TenantRequest;
import com.rental.leaseAgreement.feign.UserServiceClient;
import com.rental.leaseAgreement.model.LeaseAgreement;
import com.rental.leaseAgreement.repo.LeaseAgreementRepository;
import com.rental.leaseAgreement.service.LeaseService;


@RequestMapping("api/lease")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LeaseAgreementController {
	
	@Autowired
	private LeaseService leaseService;
	
	@Autowired
	private UserServiceClient userServiceClient;
	
	@Autowired
	private LeaseAgreementRepository leaseAgreementRepository;

	
	@PostMapping("/send")
	public LeaseAgreement sendLease(@RequestBody LeaseAgreement lease) {
		System.out.println("----------------------------------"+lease.getApplicationId());
		return leaseService.sendLease(lease);
	}
	

	@GetMapping("/user/{userId}")
	public List<LeaseAgreement> getLeaseForUser(@PathVariable Long userId) {
		ResponseEntity<TenantRequest> response = userServiceClient.getTenantDetail(userId);

	    Long tenantId = response.getBody().getTenantId();
	    return leaseService.getLeaseByTenantId(tenantId);
	}
	
	@PostMapping("/accept/{leaseId}")
	public LeaseAgreement accpetLease(@PathVariable long leaseId) {
		return leaseService.acceptLease(leaseId);
	}
	
	@PostMapping("/reject/{leaseId}")
	public LeaseAgreement rejectLease(@PathVariable long leaseId) {
		return leaseService.rejectLease(leaseId);
	}
	
	  
	
	@PostMapping("/upload-signature/{leaseId}")
	public LeaseAgreement uploadSignature(@PathVariable long leaseId, @RequestParam("file") MultipartFile file) throws IOException {
		return leaseService.uploadSignature(leaseId,file); 
	}
	
	@PutMapping("/{leaseId}/renew")
	public LeaseAgreement renewLease(@PathVariable long leaseId, @RequestParam("newEndDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date newEndDate) {
	    return leaseService.renewLease(leaseId, newEndDate);
	}

	@PutMapping("/{leaseId}/terminate")
	public LeaseAgreement terminateLease(@PathVariable long leaseId) {
	    return leaseService.terminateLease(leaseId);
	}
	
	
	
//	@GetMapping("/rent-info/{tenantId}")
//	public ResponseEntity<RentInfo> getRentInfoByTenantId(@PathVariable long tenantId) {
//	    LeaseAgreement lease = leaseAgreementRepository.findByTenantId(tenantId);
//	    if (lease != null) {
//	        RentInfo rentInfo = new RentInfo(lease.getTenantId(), lease.getRentAmount());
//	        return ResponseEntity.ok(rentInfo);
//	    } else {
//	        return ResponseEntity.notFound().build();
//	    }
//	}
	
	@GetMapping("/rent-info/{tenantId}")
	public ResponseEntity<List<RentInfo>> getRentInfoByTenantId(@PathVariable long tenantId) {
	    List<LeaseAgreement> leases = leaseAgreementRepository.findByTenantId(tenantId);
	    if (!leases.isEmpty()) {
	        List<RentInfo> rentInfos = leases.stream()
	            .map(lease -> new RentInfo(lease.getTenantId(), lease.getRentAmount()))
	            .collect(Collectors.toList());
	        return ResponseEntity.ok(rentInfos);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}



}
