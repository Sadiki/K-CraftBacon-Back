package com.bacon.services;

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
}