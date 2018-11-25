package com.bacon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bacon.models.Customers;
import com.bacon.services.CustomerService;


@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

	private CustomerService custService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.custService = customerService;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customers> login(@RequestBody String username, @RequestBody String password){
		
		System.out.println("lskjfpwrhfnasf");
		Customers cust = custService.login(username, password);
		if(cust == null) {
			System.out.println("Customer not found it was null naull naulllll....");
		}
		
		return new ResponseEntity<Customers> (cust, HttpStatus.OK);
	}
	
}