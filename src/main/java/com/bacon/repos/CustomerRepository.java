package com.bacon.repos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bacon.models.Customers;

@Repository
@Transactional
public class CustomerRepository {

	private SessionFactory sessionFactory;

	// Constructor that signals spring to create an instance within the bean
	// container
	@Autowired
	public CustomerRepository(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
		System.out.println(
				"Inside Customer Repository Constructer....SessionFactory up and running: " + this.sessionFactory);
	}

	public void register(String firstName, String lastName, String username, String password, String email,
			String phoneNumber, String streetAddress, String city, String state, String zip) {

		System.out.println("Inside CustomerRepository: register method");

		Session s = sessionFactory.getCurrentSession();
//		s.beginTransaction();
		s.save(new Customers(firstName, lastName, username, password, email, phoneNumber, streetAddress, city, state,
				zip));
	}

	public Customers login(String username, String password) {
		System.out.println("Inside CustomerRepositorys' login method");

		// Establish a session
		Session s = sessionFactory.getCurrentSession();

		// Craft the appropriate HQL statement
		String hql = "from Customers C where C.username Like ?0 AND C.password  Like ?1";

		// execute the statement with the given input parameters and return the current
		// user
		List customer = s.createQuery(hql).setParameter(0, username).setParameter(1, password).getResultList();

		if (customer.size() > 0)
			return (Customers) customer.get(0);

		// if the query returns 0 results then the input parameters does not match any database records.
		return null;

	}

	public List<Customers> getAll() {
		System.out.println("Inside CustomerReposiroty: getAll method");
		Session s = sessionFactory.getCurrentSession();
		return s.createQuery("from Customers", Customers.class).getResultList();
	}
	
	
	//UPDATING USER FIELDS 

}
