package com.bacon.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bacon.models.CreditCardInfo;
import com.bacon.models.Customers;
import com.bacon.repos.CreditCardRepository;
import com.bacon.repos.CustomerRepository;

@Service
public class CreditCardService {
	
	private static CreditCardRepository cardRepo;
	
	@Autowired
	public CreditCardService(CreditCardRepository cardRepo) {
		this.cardRepo = cardRepo;			
	}
	
	public boolean addCard(String cardNumber, String fullName, int securityCode, String expirationDate,
			int custId) {
		//retrieve the customer which corresponds with the given custID
		Customers customer = cardRepo.getById(custId);
		if(isUnique(cardNumber)) {
			cardRepo.addCard(cardNumber, fullName, securityCode, expirationDate, customer);
			return true;
		}	
		return false;	
	}
	
	
	//Retrieve card information based on requesting User
	public List<CreditCardInfo> getByUserId(int userId) {
		return cardRepo.getByUserId(userId);
	}
	
	
	
	//Delete a card
	public boolean deleteCard(int cardNumber) {
		return cardRepo.deleteCard(cardNumber);
	}
	
	
	//update card info
	public boolean updateCard(String cardNumber, int securityCode, String expirationDate) {
		return cardRepo.updateCard(cardNumber, securityCode, expirationDate);
	}
	
	
	
	
	/*HELPER METHOD*/
	public static boolean isUnique(String cardNumber) {
		List<CreditCardInfo> cards = cardRepo.getAll();
	
		//Iterate through the records and check to see if the credit card number already exists
		for(CreditCardInfo card: cards) 		
			if(card.getCardNumber().equals(cardNumber)) 
				return false;
					
		return true;
	}
}
