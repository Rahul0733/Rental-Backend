package com.rental.payment.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rental.payment.DTO.PaymentRequest;
import com.rental.payment.DTO.RentInfo;
import com.rental.payment.feign.LeaseClient;
import com.rental.payment.model.Payment;
import com.rental.payment.repository.PaymentRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;



@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private LeaseClient leaseClient;

    public List<RentInfo> fetchRentInfo(long tenantId) {
        return leaseClient.getRentInfoByTenantId(tenantId);
    }

    public Payment processPayment(PaymentRequest paymentrequest) {
    	Payment payment= new Payment();
    	payment.setPayerName(paymentrequest.getPayerName());
    	payment.setTenantId(paymentrequest.getTenantId());
    	payment.setPaymentMethod(paymentrequest.getPaymentMethod());
    	payment.setRentalAmount(paymentrequest.getRentalAmount());
        payment.setStatus("Success");
        payment.setTransactionId(generateTransactionId());
        payment.setPaymentDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm a")));
        return paymentRepository.save(payment);
//        return null;
    }

    private String generateTransactionId() {
        return "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

//    public List<Payment> getPaymentsByTenantId(int tenantId) {
//        return paymentRepository.findByTenantId(tenantId);
//    }

//    public Payment getPaymentByTransactionId(String transactionId) {
//        return paymentRepository.findByTransactionId(transactionId);
//    }
}

