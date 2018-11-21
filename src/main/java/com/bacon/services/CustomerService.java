package com.bacon.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bacon.models.Customers;
import com.bacon.repos.CustomerRepository;

@Service
@Transactional
public class CustomerService {
	
	private CustomerRepository custRepo;
	
	@Autowired
	public CustomerService(CustomerRepository custRepo) {
		this.custRepo = custRepo;
	}
	
	public Customers login(String username, String password) {
		return custRepo.login(username, password);
	}
	
	
	public boolean register(Map<String, String> registrationInfo) {
		System.out.println("Inside CustomerService: register method");
		
		List<Customers> existingCust = custRepo.getAll();
		
		//Assign registration array elements variables with proper data type, 
		String firstname = registrationInfo.get("firstName");
		String lastname = registrationInfo.get("lastName");		
		String username = registrationInfo.get("username");
		String password = registrationInfo.get("password");
		String email = registrationInfo.get("email");
		String phoneNumber = registrationInfo.get("phoneNumber");
		String streetAddress = registrationInfo.get("streetAddress");
		String city = registrationInfo.get("city");
		String state = registrationInfo.get("state");
		String zip = registrationInfo.get("zip");
		
		
		
		//validate inputs that require validation{email and username }
		for(Customers c: existingCust) 
			if(c.getUsername().equals(username) || c.getEmail().equals(email)) 
				 return false;
				
		
		//make a call to the repository layer to construct and insert a new user
		custRepo.register( firstname, lastname, username, password, email, phoneNumber, streetAddress, city, state, zip);
		return true;
		
	}
	
	public List<Customers> getAll(){
		System.out.println("Inside CustomerService: getAll()");
		return custRepo.getAll();
	}
	
}
