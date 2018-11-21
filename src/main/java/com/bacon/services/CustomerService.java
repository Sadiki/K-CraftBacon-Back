
package com.bacon.services;

import java.util.ArrayList;
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

	
	//LOGING IN
	public Customers login(String username, String password) {
		return custRepo.login(username, password);
	}

	//CREATING A CUSTOMER
	public boolean register(Map<String, String> registrationInfo) {
		System.out.println("Inside CustomerService: register method");

		List<Customers> existingCust = custRepo.getAll();

		// Assign registration array elements variables with proper data type,
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

		// validate inputs that require validation{email and username }
		for (Customers c : existingCust)
			if (c.getUsername().equals(username) || c.getEmail().equals(email))
				return false;

		// make a call to the repository layer to construct and insert a new user
		custRepo.register(firstname, lastname, username, password, email, phoneNumber, streetAddress, city, state, zip);
		return true;

	}

	// UPDATE A CUSTOMER
	public boolean customerUpdate(Map<String, String> customerUpdate) {
		System.out.println("Inside CustomerService: update method");
		List<Customers> existingCust = custRepo.getAll();

		int id = Integer.parseInt(customerUpdate.get("cust_id"));
		String firstname = customerUpdate.get("firstName");
		String lastname = customerUpdate.get("lastName");
		String username = customerUpdate.get("username");
		String password = customerUpdate.get("password");
		String email = customerUpdate.get("email");
		String phoneNumber = customerUpdate.get("phoneNumber");
		String streetAddress = customerUpdate.get("streetAddress");
		String city = customerUpdate.get("city");
		String state = customerUpdate.get("state");
		String zip = customerUpdate.get("zip");
		int newsletter = Integer.parseInt(customerUpdate.get("newsletter"));

		// validations checking to see if the username or email were changed{if they attempt to change there username or email the updated values should be unique to the table}
		for (Customers c : existingCust) {
			if ((c.getUsername().equals(username) || c.getEmail().equals(email)) && (!(c.getCust_id() == id)))
				return false;
		}

		// make a call to the repository layer to construct and insert a new user
		custRepo.updateCustomer(id, firstname, lastname, username, password, email, phoneNumber, streetAddress, city,
				state, zip, newsletter);
		return true;

	}

	//SIGNING UP FOR A NEWSLETTER
	public void newsletterSignup(int custId) {
		custRepo.newsletterSignup(custId);
	}

	//UNSUBSCRIBING TO A NEWSLETTER
	public void newsletterUnsubscribe(int custId) {
		custRepo.newsletterUnsubscribe(custId);
	}

	
	
	
	
	
	// Retrieving emails from all users who have are signed up for a newsletter
	public List<String> getAllNewsletterEmails() {
		List<String> newsletterEmails = new ArrayList<>();
		List<Customers> customers = custRepo.getAll();

		// iterate through the list of customers and select all the customers that are
		// signed up to receive the newsletter
		for (int i = 0; i < customers.size(); i++)
			if (customers.get(i).getNewsletter() == 1)
				newsletterEmails.add(customers.get(i).getEmail());

		return newsletterEmails;
	}

	// Retrieve all records from the customers table
	public List<Customers> getAll() {
		System.out.println("Inside CustomerService: getAll()");
		return custRepo.getAll();
	}

}
