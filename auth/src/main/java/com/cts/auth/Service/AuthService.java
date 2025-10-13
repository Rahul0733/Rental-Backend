package com.cts.auth.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.auth.DTO.LandlordSignup;
import com.cts.auth.DTO.Login;
import com.cts.auth.DTO.LoginResponse;
import com.cts.auth.DTO.TenantSignup;
import com.cts.auth.model.Landlord;
import com.cts.auth.model.Tenant;
import com.cts.auth.model.User;
import com.cts.auth.model.UserType;
import com.cts.auth.repository.LandlordRepository;
import com.cts.auth.repository.TenantRepository;
import com.cts.auth.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
	
	private LandlordRepository landlordRepo;
	
	private UserRepository userRepo;
	
	private TenantRepository tenantRepo;
	
	public boolean landlordSignup(LandlordSignup request) {
		User user = new User();
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setUserType("Landlord");
		userRepo.save(user);
		
		Landlord land = new Landlord();
		land.setUser(user);
		land.setName(request.getName());
		land.setEmail(request.getEmail());
		land.setMobile(request.getMobile());
		land.setAddress(request.getAddress());
		landlordRepo.save(land);
		
		return true;
	}
	
	public boolean tenantSignup(TenantSignup request) {
		User user = new User();
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setUserType("Tenant");
		userRepo.save(user);
		
		Tenant tenant = new Tenant();
		tenant.setUser(user);
		tenant.setName(request.getName());
		tenant.setMobile(request.getMobile());
		tenant.setRentalHistory(request.getRentalHistory());
		tenantRepo.save(tenant);
		
		return true;
	}
	
	public LoginResponse login(Login request) {
	    List<User> users = userRepo.findAll();

	    for (User u : users) {
	        if (u.getEmail().equals(request.getEmail()) &&
	            u.getPassword().equals(request.getPassword())) {

	            if (!u.getUserType().equals(request.getUserType())) {
	                return new LoginResponse(0, null, "Invalid user type");
	            }

	            return new LoginResponse(u.getUserId(), u.getUserType(), "Login successful");
	        }
	    }

	    return new LoginResponse(0, null, "Invalid credentials");
	}
}
