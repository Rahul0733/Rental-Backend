package com.cts.auth.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LandlordSignup {
	private String name;
	private String email;
	private long mobile;
	private String address;
	private String password;
}
