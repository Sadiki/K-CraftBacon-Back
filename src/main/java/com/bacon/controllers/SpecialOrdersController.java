package com.bacon.controllers;

import java.util.List;

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
@CrossOrigin
@Controller
@RequestMapping(value="/bacon/craft")
public class SpecialOrdersController {
	
	private SpecialOrdersService specialOrdersService;
	
	@Autowired
	public SpecialOrdersController (SpecialOrdersService specialOrdersService) {
		System.out.println("Controller");
		this.specialOrdersService = specialOrdersService;
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SpecialOrders>> getAll(){
		System.out.println("controller...");	
		List<SpecialOrders> orders =  specialOrdersService.getAll();
		return new ResponseEntity<List<SpecialOrders>>(orders,HttpStatus.OK);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SpecialOrders> addOrder(@RequestBody SpecialOrders newOrder){
		System.out.println("Controller...");
		if (newOrder.getMeatType() == null || newOrder.getSeasonings() == null || newOrder.getSmokeFlavor() == null || newOrder.getSpecialOrderPrice() == 0 || newOrder.getWeight() == 0) {
			System.out.println("null...");
			return new ResponseEntity<SpecialOrders>(newOrder, HttpStatus.NOT_ACCEPTABLE);
		}
		SpecialOrders order = specialOrdersService.addSpecialOrder(newOrder);
		return new ResponseEntity<SpecialOrders>(order, HttpStatus.CREATED); //Http status code = 201
	}
	
	
}