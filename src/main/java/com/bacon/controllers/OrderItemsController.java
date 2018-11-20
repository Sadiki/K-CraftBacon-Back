package com.bacon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bacon.models.OrderItems;
import com.bacon.services.OrderItemsService;

@Controller
@RequestMapping(value = "/profile")
public class OrderItemsController {
	
	private OrderItemsService orderItemsService;
	
	@Autowired
	public OrderItemsController (OrderItemsService orderItemsService) {
		this.orderItemsService = orderItemsService;
	}
	
	//add item to cart
	@PostMapping(value = "/add", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderItems> addOrderItem(@RequestBody OrderItems newOrderItem){
		newOrderItem.setStatus(1);
		if (newOrderItem.getCustId() == 0 || newOrderItem.getInventory() == null || newOrderItem.getQuantity() == 0) {
			return new ResponseEntity<OrderItems>(newOrderItem, HttpStatus.NOT_ACCEPTABLE);
		}
		OrderItems newItem = orderItemsService.addOrderItem(newOrderItem);
		return new ResponseEntity<OrderItems>(newItem, HttpStatus.CREATED); //Http status code = 201
	}
	
	
	//get items by custId and orderstatus 1 for cart items
	
	//get items by custId and orderstatus 2 for saved for later items
	
	//get items by orderId
	
	//update items from orderstatus 1 to 2
	
	//update items from orderstatus 1 to 3 and get orderId
	
	
	
}
