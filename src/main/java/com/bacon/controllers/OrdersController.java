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

import com.bacon.models.Orders;
import com.bacon.services.OrdersService;

@CrossOrigin
@Controller
@RequestMapping(value="/profile/orders")
public class OrdersController {
	
	private OrdersService ordersService;
	
	@Autowired
	public OrdersController (OrdersService ordersService) {
		this.ordersService = ordersService;
	}
	
	//get all orders
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Orders>> getAll(){
		System.out.println("controller...");
		List<Orders> orders =  ordersService.getAll();
		return new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);
	}
	
//	//new order
//	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Orders> addOrder(@RequestBody Orders newOrder){
//		System.out.println("Controller...");
//		Orders order = ordersService.addCard(newOrder);
//		return new ResponseEntity<Orders>(order, HttpStatus.CREATED); //Http status code = 201
//	}
	
}
