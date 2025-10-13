package com.cts.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.auth.model.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer>{

}
