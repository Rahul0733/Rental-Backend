package com.cts.user_service.repository;

import com.cts.user_service.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
    Optional<Tenant> findByTenantId(long tenantId);
    Optional<Tenant> findByEmail(String email);
    Optional<Tenant> findByUserId(long userId);
}