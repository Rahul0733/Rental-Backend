package com.cts.auth.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TenantSignup {
	
	private String name;
	private String email;
	private long mobile;
	private String password;
	private String rentalHistory;
	
}
