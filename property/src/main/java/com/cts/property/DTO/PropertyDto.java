package com.cts.property.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDto {
//	private int ownerId;
	private String name;
	private String address;
	private long rentAmount;
	private float size;
	private String availabilityStatus;
	private String description;
	private String image;
}
