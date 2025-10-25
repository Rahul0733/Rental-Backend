package com.rental.tenant.repo;

import com.rental.tenant.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
  

	boolean existsByTenantIdAndPropertyId(long tenantId, long propertyId);

	Application findByApplicationId(long applicationId);

	List<Application> findByTenantId(long tenantId);

	




}
