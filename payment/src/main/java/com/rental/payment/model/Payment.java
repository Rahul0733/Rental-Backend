package com.rental.payment.model;





import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
