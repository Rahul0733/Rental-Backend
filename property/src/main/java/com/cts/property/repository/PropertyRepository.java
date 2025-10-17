package com.cts.property.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.property.DTO.LandlordResponse;
import com.cts.property.model.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer>{
	
	 List<Property> findPropertiesByLandlordId(int landlordId);
	 
	 List<Property> findByAvailabilityStatus(String status);
	
}
