package com.bacon.repos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bacon.models.Customers;

@Repository
public class CustomerRepository {
	
	private SessionFactory sessionFactory;
	
	
	//Constructor that signals spring to create an instance within the bean container
	@Autowired
	public void CustomersRepository( SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
		System.out.println(this.sessionFactory);
		
	}
	
//	
//	public Customers login(String username, String password) {
//		
//		System.out.println(sessionFactory);
//		//Establish a session 
//		Session s = sessionFactory.getCurrentSession();
//		
//		//Return the user that corresponds with the input parameters
//		return s.createQuery(("from Customers C where C.username = " + username + " and C.password = " + password), Customers.class).getSingleResult();	
//		
//	}
	public List<Customers> getAll(){
		System.out.println("repo...");
		Session s = sessionFactory.getCurrentSession();
		return s.createQuery("from Customers", Customers.class).getResultList();
	}

	
	
	
	
	
}