package com.bacon.repos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bacon.models.CreditCardInfo;
import com.bacon.models.Customers;

@Repository
@Transactional
public class CreditCardRepository {
	
	public CreditCardRepository() {}
	
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
	
	
	//Adding a card
	public void addCard(String cardNumber, String fullName, int securityCode, String expirationDate, Customers customer) {
		Session s = sessionFactory.getCurrentSession();
		s.save(new CreditCardInfo(cardNumber, fullName, securityCode, expirationDate, customer));
	}
	
	
	
	//Retrieving card(s) by UserId
	public List<CreditCardInfo> getByUserId(int userId) {
		
		Session s = sessionFactory.getCurrentSession();
		return  s.createQuery("from CreditCardInfo where cust_id Like ?0", CreditCardInfo.class).setParameter(0, userId).getResultList();
	}
	
	
	//UPDATES TO CARD INFORMATION
	public boolean updateCard(String cardNumber, int securityCode, String expirationDate) {
		Session s = sessionFactory.getCurrentSession();
		CreditCardInfo card = s.get(CreditCardInfo.class, cardNumber);
		
		//if the card does not exist, update is not possible. Otherwise update
		if(card == null) {
			return false;
		}else {
			card.setExpirationDate(expirationDate);
			card.setSecurityCode(securityCode);}
		
		return true;
	}
	
	//Delete a card
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
		System.out.println("in cardRepo getById.. id = " + id);
		Session s = sessionFactory.getCurrentSession();
		s.getTransaction();
		Customers customer = s.get(Customers.class, id);
		System.out.println(customer);
		return customer;
	}
	
}
