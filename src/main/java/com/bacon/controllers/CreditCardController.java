package com.bacon.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bacon.models.CreditCardInfo;
import com.bacon.models.Inventory;
import com.bacon.services.CreditCardService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value = "/creditcard")
public class CreditCardController {
	
	private CreditCardService cardService;
	
	@Autowired
	public CreditCardController(CreditCardService cardService) {
		this.cardService = cardService;
	}

	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addCard(@RequestBody String newCardInfoJson) throws JsonParseException, JsonMappingException, IOException {

		// map the incoming Json to an array for quick reference
		String[] cardDetails = new ObjectMapper().readValue(newCardInfoJson, String[].class);

		String cardNumber = cardDetails[0];
		String fullName  = cardDetails[1];
		int securityCode = Integer.parseInt(cardDetails[2]);
		String expirationDate =cardDetails[3];
		int custId = Integer.parseInt(cardDetails[4]);
		
		if(cardService.addCard(cardNumber, fullName, securityCode, expirationDate, custId))
			return new ResponseEntity<>(HttpStatus.CREATED);
		
		//inValide Card number 
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@GetMapping(value = "/{id}",produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CreditCardInfo>> getCard(@PathVariable("id")int id){
		
		List<CreditCardInfo> record = cardService.getByUserId(id);
		
		if(record.size() == 0)
			return new ResponseEntity<List<CreditCardInfo>>(record, HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<List<CreditCardInfo>>(record, HttpStatus.OK);
		
	}
	
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<Boolean> deleteCard(@PathVariable int id) {
		
		boolean cardDeleted = cardService.deleteCard(id);
		
		if(!cardDeleted) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(cardDeleted, HttpStatus.OK);
		}
}
