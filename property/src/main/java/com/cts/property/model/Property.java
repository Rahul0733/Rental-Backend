package com.cts.property.model;

//import com.cts.property.DTO.Landlord;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Property {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int propId;
	
//	@ManyToOne
//	@JoinColumn(name="owner_id", nullable = false)
	private int ownerId;
	
	private String name;
	
	private String address;
	
	private long rentAmount;
	
	private float size;
	
	private String availabilityStatus;
	
	private String description;
	
	@Lob
	private String image;
}
