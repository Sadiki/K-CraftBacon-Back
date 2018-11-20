package com.bacon.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bacon.models.Customers;
import com.bacon.services.CustomerService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

	private CustomerService custService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.custService = customerService;
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Customers>> getAll(){
		System.out.println("controller...");
		List<Customers> customers =  custService.getAll();
		return new ResponseEntity<List<Customers>>(customers,HttpStatus.OK);
	}

	
	//LOGING IN 
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customers> login(@RequestBody String loginCredentialsJson) throws JsonParseException, JsonMappingException, IOException {

		System.out.println("CustomerController: Inside PostMapping/login ");
		
		//map the incoming Json to an array for quick reference
		Map<String, String> loginCredentials = new HashMap<String, String>();
		loginCredentials = new ObjectMapper().readValue(loginCredentialsJson, new TypeReference<Map<String, String>>(){});
		
		String username = loginCredentials.get("username");
		String password = loginCredentials.get("password");
		
		System.out.println("CustomerController username ->"+ username +" and password -> "+ password);
		
		Customers cust = custService.login(username, password);
		if(cust == null) {
			System.out.println("Invalid Credentials, Customer not found....");
			return new ResponseEntity<Customers>(cust, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Customers> (cust, HttpStatus.OK);
	}
	

	//CREATING A USER
	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity register(@RequestBody String registerInfoJson) throws JsonParseException, JsonMappingException, IOException {
		
		System.out.println("CustomerController: Inside PostMapping/register ");
		/* Assumed Json Structure for registering a User ->
		 * firstName 
		 * lastName
		 * username
		 * password
		 * email
		 * phoneNumber
		 * streetAddress
		 * city
		 * state
		 * zip
		 */
		
		//map the incoming Json to an array for quick reference
		Map<String, String> registrationInfo = new HashMap<String, String>();
		registrationInfo = new ObjectMapper().readValue(registerInfoJson, new TypeReference<Map<String, String>>(){});

		
		boolean created = custService.register(registrationInfo);
		
		if(!created )
			return new ResponseEntity<> (HttpStatus.NOT_ACCEPTABLE); //Status code 406 conveys already existing username or email
			
		
		return new ResponseEntity<>(HttpStatus.CREATED);
		
		
	}