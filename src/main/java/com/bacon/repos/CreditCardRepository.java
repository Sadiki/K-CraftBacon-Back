package com.bacon.repos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bacon.models.CreditCardInfo;
import com.bacon.models.Customers;

@Repository
public class CreditCardRepository {
	
	protected SessionFactory sessionFactory;

	@Autowired
	public CreditCardRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		System.out.println("Inside CreditCard Repository constructor");
	}
	
	public List<CreditCardInfo> getAll(){
		Session s = sessionFactory.getCurrentSession();
		return s.createQuery("from CreditCardInfo", CreditCardInfo.class).getResultList();
	}
	
	
	
	public void addCard(String cardNumber, String fullName, int securityCode, String expirationDate, Customers customer) {
		Session s = sessionFactory.getCurrentSession();
		
		s.save(new CreditCardInfo(cardNumber, fullName, securityCode, expirationDate, customer));
	}
	
	
	public List<CreditCardInfo> getByUserId(int userId) {
		
		Session s = sessionFactory.getCurrentSession();
		return  s.createQuery("from CreditCardInfo where custId Like ?0", CreditCardInfo.class).setParameter(0, userId).getResultList();
	}
	
	public boolean deleteCard(int cardNumber) {
		
		Session s = sessionFactory.getCurrentSession();
		CreditCardInfo card = s.get(CreditCardInfo.class, cardNumber);
		
		if(card == null)
			return false;
		
		s.delete(card);
		return true;
	}
	
	
	
	
	
	
	
	
	//Helper Method
	public Customers getById(int id) {
		
		Session s = sessionFactory.getCurrentSession();
		return (Customers) s.createQuery("from Customers where custId Like ?0", Customers.class).setParameter(0, id);
	}
	
	
	
	
	
	
	
	
	
	
	
	

	
}
