package com.cts.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.auth.model.Landlord;

@Repository
public interface LandlordRepository extends JpaRepository<Landlord, Integer>{
	Optional<Landlord> findByUserUserId(int userId);
}
