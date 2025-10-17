package com.cts.property.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LandlordResponse {
	private int landlordId;
	private String name;
	private String email;
	private long mobile;
	private String address;
}
