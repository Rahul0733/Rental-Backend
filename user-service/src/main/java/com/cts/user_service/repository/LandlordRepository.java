package com.cts.user_service.repository;

import com.cts.user_service.model.Landlord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LandlordRepository extends JpaRepository<Landlord, Long> {
    Optional<Landlord> findByLandlordId(Long landlordId);
    Optional<Landlord> findByUserId(Long userId);
}