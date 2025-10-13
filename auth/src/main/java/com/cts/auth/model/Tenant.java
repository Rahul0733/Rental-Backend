package com.cts.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Tenant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tenantId;
	
	@OneToOne
	@JoinColumn(name="user_id", nullable = false)
	private User user;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private long mobile;
	
	private String rentalHistory;
}
