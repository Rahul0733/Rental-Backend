package com.rental.leaseAgreement.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(TenantNotFoundException.class)
	public ResponseEntity<String> handleTenantNotFound(TenantNotFoundException e){
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(LeaseNotFoundException.class)
	public ResponseEntity<String> handleLeaseNotFound(LeaseNotFoundException e){
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<Map<String, String>> handleIllegalState(IllegalStateException ex) {
	    Map<String, String> error = new HashMap<>();
	    error.put("message", ex.getMessage());
	    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}

}
