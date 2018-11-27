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

import com.bacon.models.SpecialOrders;
import com.bacon.services.SpecialOrdersService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@CrossOrigin
@Controller
@RequestMapping(value="/bacon/craft")
public class SpecialOrdersController {
	
	private SpecialOrdersService specialOrdersService;
	
	@Autowired
	public SpecialOrdersController (SpecialOrdersService specialOrdersService) {
		this.specialOrdersService = specialOrdersService;
	}
	
	//gets all special orders. no practical use. probably going to delete
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SpecialOrders>> getAll(){
		List<SpecialOrders> orders =  specialOrdersService.getAll();
		return new ResponseEntity<List<SpecialOrders>>(orders,HttpStatus.OK);
	}
	
	//add new special order
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SpecialOrders> addOrder(@RequestBody SpecialOrders newOrder){
		if (newOrder.getMeatType() == null || newOrder.getSeasonings() == null || newOrder.getSmokeFlavor() == null || newOrder.getSpecialOrderPrice() == 0 || newOrder.getWeight() == 0) {
			return new ResponseEntity<SpecialOrders>(newOrder, HttpStatus.NOT_ACCEPTABLE);
		}
		SpecialOrders order = specialOrdersService.addSpecialOrder(newOrder);
		int specialOrderId = order.getSpecialOrderId();
//		int custId = or
		
		return new ResponseEntity<SpecialOrders>(order, HttpStatus.CREATED); //Http status code = 201
	}
	
//	//add new order to orderItems table
//	public ResponseEntity addOrderItem(int specialOrderId) throws JsonParseException, JsonMappingException, IOException{
//        int custId = Integer.parseInt(itemDetails.get("custId"));
//        specialOrdersService.addOrderItem(specialOrderId);
//		return new ResponseEntity<>(HttpStatus.CREATED); //Http status code = 201
//	}
	
}
