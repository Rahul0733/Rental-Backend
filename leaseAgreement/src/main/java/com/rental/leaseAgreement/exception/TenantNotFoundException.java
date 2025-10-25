package com.rental.leaseAgreement.exception;

public class TenantNotFoundException extends RuntimeException{
	
	public TenantNotFoundException(String msg) {
		super(msg);
	}

}
