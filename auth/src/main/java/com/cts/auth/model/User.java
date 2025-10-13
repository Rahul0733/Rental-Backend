package com.cts.auth.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", initialValue = 101, allocationSize = 1)
	private int userId;
	
	@Column( unique=true, nullable = false) 
	private String email;
	
	@Column(nullable = false)
	private String password;
	
//	@Enumerated(EnumType.STRING)
    private String userType;
    

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Landlord owner;
//
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Tenant tenant;


}
