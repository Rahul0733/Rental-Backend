package com.rental.payment.controller;



import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rental.payment.DTO.PaymentRequest;
import com.rental.payment.DTO.PaymentResponse;
import com.rental.payment.DTO.RentInfo;
import com.rental.payment.model.Payment;
import com.rental.payment.service.PaymentService;

import java.util.List;



@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class PaymentController {

    private PaymentService paymentService;

    @GetMapping("/rent/{tenantId}")
    public List<RentInfo> getRentInfoByTenantId(@PathVariable long tenantId) {
        return paymentService.fetchRentInfo(tenantId);
    }

    @PostMapping("/make")
    public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest payment) {
        try {
        	
            Payment saved = paymentService.processPayment(payment);
            System.out.println("------reached------------"+payment.getRentalAmount());
            return ResponseEntity.ok(new PaymentResponse("Payment successful. Transaction ID: " + saved.getTransactionId()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new PaymentResponse("Payment failed: " + e.getMessage()));
        }
    }
    
    @GetMapping("/history")
	public ResponseEntity<List<Payment>> getPaymentHistory() {
	      List<Payment> history = paymentService.getAllPayments();
	      return ResponseEntity.ok(history);
	  }
    
//    @GetMapping("/tenant/{tenantId}")
//	public ResponseEntity<List<Payment>> getPaymentsByTenant(@PathVariable int tenantId) {
//	      List<Payment> payments = paymentService.getPaymentsByTenantId(tenantId);
//	      return ResponseEntity.ok(payments);
//	  }

//    @GetMapping("/success/{tenantId}")
//    public ResponseEntity<String> paymentSuccessMessage(@PathVariable String tenantId) {
//        return ResponseEntity.ok("Payment successful for tenant ID: " + tenantId);
//    }
}

