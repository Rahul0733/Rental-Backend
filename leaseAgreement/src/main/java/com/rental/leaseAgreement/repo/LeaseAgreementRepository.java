package com.rental.leaseAgreement.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rental.leaseAgreement.model.LeaseAgreement;

@Repository
public interface LeaseAgreementRepository extends JpaRepository<LeaseAgreement, Long>{



	//List<LeaseAgreement> findByApplicationId(Long applicationId);


	 boolean existsByApplicationId(long applicationId);

	 LeaseAgreement findByApplicationId(long applicationId);

	 List<LeaseAgreement> findByTenantId(long tenantId);

	 LeaseAgreement findByLeaseId(long leaseId);


}
