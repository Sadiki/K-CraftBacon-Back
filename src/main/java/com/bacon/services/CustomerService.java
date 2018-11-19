package com.bacon.services;

import java.util.List;

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
	
	
	public boolean register(String[] registrationInfo) {
		System.out.println("Inside CustomerService: register method");
		
		List<Customers> existingCust = custRepo.getAll();
		
		//Assign registration array elements variables with proper data type, 
		String username = registrationInfo[0];
		String password = registrationInfo[1];
		String firstname = registrationInfo[2];
		String lastname = registrationInfo[3];
		String email = registrationInfo[4];
		String phoneNumber = registrationInfo[5];
		String streetAddress = registrationInfo[6];
		String city = registrationInfo[7];
		String state = registrationInfo[8];
		String zip = registrationInfo[9];
		
		
		
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
