package com.rental.leaseAgreement.exception;

public class LeaseNotFoundException extends RuntimeException{
	public LeaseNotFoundException(String msg) {
		super(msg);
	}
	
}
