package com.rental.tenant.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicationResponse {
	private long propertyId;
	private String propertyName;
	private String applicationStatus;
	private String tenantName;
	private String tenantEmail;
	private long mobileNo;
}
