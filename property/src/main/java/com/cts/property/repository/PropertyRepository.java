package com.cts.property.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.property.DTO.LandlordDto;
import com.cts.property.model.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer>{
	
	 List<Property> findPropertiesByOwnerId(int ownerId);
	 
	 List<Property> findByAvailabilityStatus(String status);
	
}
