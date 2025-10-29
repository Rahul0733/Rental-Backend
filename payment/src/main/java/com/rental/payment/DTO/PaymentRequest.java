package com.rental.payment.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentRequest {
	private Long paymentId;

    private int tenantId; 
    
    private String payerName;

    private int propertyId; 

    private long rentalAmount; 

    private String paymentMethod; 

    private String status; 

    private String transactionId; 

    private String paymentDate;
}
