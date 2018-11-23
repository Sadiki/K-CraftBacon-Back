package com.bacon.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
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

import com.bacon.models.Customers;
import com.bacon.models.Orders;
import com.bacon.repos.CreditCardRepository;
import com.bacon.services.OrdersService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	//get all orders by userId
	@PostMapping(value = "/past-orders" ,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Orders>> getAllOrdersByCustId(@RequestBody String userInfoJson) throws JsonParseException, JsonMappingException, IOException{
		Map<String, String> userInfo = new HashMap<String, String>();
        userInfo = new ObjectMapper().readValue(userInfoJson, new TypeReference<Map<String, String>>(){});
        
        int custId = Integer.parseInt(userInfo.get("cust_id"));
        
        List<Orders> orderItems = ordersService.getAllOrdersByCustId(custId);
        return new ResponseEntity<List<Orders>>(orderItems, HttpStatus.OK);
	}
	
	//create new order
	@PostMapping(value="/add", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addOrderItem(@RequestBody String newOrderItemJson) throws JsonParseException, JsonMappingException, IOException{
		Map<String, String> itemDetails = new HashMap<String, String>();
        itemDetails = new ObjectMapper().readValue(newOrderItemJson, new TypeReference<Map<String, String>>(){});
        
        int custId = Integer.parseInt(itemDetails.get("cust_id"));
        //build customer
        CreditCardRepository ccr = new CreditCardRepository();
        Customers customer = ccr.getById(custId);
        System.out.println(customer);
        int orderStatusId = 1;
        int shippingStatus = Integer.parseInt(itemDetails.get("shipping_status"));
        int deliveryMethod = Integer.parseInt(itemDetails.get("delivery_method"));
        double shippingPrice = Double.parseDouble(itemDetails.get("shipping_price"));
        double orderPrice = Double.parseDouble(itemDetails.get("order_price"));
        LocalDateTime time = LocalDateTime.now();
        Orders newOrder = new Orders(customer, orderStatusId, time, shippingStatus, deliveryMethod, shippingPrice, orderPrice);
//        ordersService.addNewOrder(newOrder);
		return new ResponseEntity<>(HttpStatus.CREATED); //Http status code = 201
	}
	
	//update orderStatus
	
	//update shippingStatus
	
	
	
	
}
