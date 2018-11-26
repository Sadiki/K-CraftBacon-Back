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

import com.bacon.models.OrderItems;
import com.bacon.models.Orders;
import com.bacon.services.OrderItemsService;
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
        System.out.println(orderItems.get(0).getCreatedDate());
        return new ResponseEntity<List<Orders>>(orderItems, HttpStatus.OK);
	}
	
	//create new order
	@PostMapping(value="/add", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addOrderItem(@RequestBody String newOrderItemJson) throws JsonParseException, JsonMappingException, IOException{
		Map<String, String> itemDetails = new HashMap<String, String>();
        itemDetails = new ObjectMapper().readValue(newOrderItemJson, new TypeReference<Map<String, String>>(){});
        
        int custId = Integer.parseInt(itemDetails.get("cust_id"));
        int orderStatusId = 1;
        int shippingStatus = Integer.parseInt(itemDetails.get("shipping_status"));
        int deliveryMethod = Integer.parseInt(itemDetails.get("delivery_method"));
        double shippingPrice = Double.parseDouble(itemDetails.get("shipping_price"));
        double orderPrice = Double.parseDouble(itemDetails.get("order_price"));
        LocalDateTime time = LocalDateTime.now();
        Orders newOrder = ordersService.addNewOrder(custId, orderStatusId, time, shippingStatus, deliveryMethod, shippingPrice, orderPrice);
        //update cart items to purchased and attach new orderId
        OrderItemsController oic = new OrderItemsController();
        System.out.println("New Order: " + newOrder + " --- custId: " + custId); //*******************************
        boolean cartPurchased = updateOrderStatusTo3(custId, newOrder);
        System.out.println(cartPurchased);//*******************************
        return new ResponseEntity<>(HttpStatus.CREATED); //Http status code = 201
	}
	
	//helper method for create new order
	public boolean updateOrderStatusTo3(int custId, Orders newOrder) {
		List<OrderItems> orderItems = ordersService.getAllOrderItemsById(custId, 1);
		boolean purchasedItems = ordersService.updateOrderStatusTo3(orderItems, newOrder);
		if (purchasedItems == false) {
			return false;
		}
		return true;
	}
	
	//update orderStatus
	@PostMapping(value = "/update-order-status" ,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Orders> updateOrderStatus (@RequestBody String updateInfoJson) throws JsonParseException, JsonMappingException, IOException{
		Map<String, String> userInfo = new HashMap<String, String>();
        userInfo = new ObjectMapper().readValue(updateInfoJson, new TypeReference<Map<String, String>>(){});
        int orderId = Integer.parseInt(userInfo.get("order_id"));
        int orderStatus = Integer.parseInt(userInfo.get("order_status_id"));
        LocalDateTime time = LocalDateTime.now();
        Orders orderUpdated = ordersService.updateOrderStatus(orderId, time, orderStatus);
        return new ResponseEntity<Orders>(orderUpdated, HttpStatus.OK);
	}
	
	//update shippingStatus
	@PostMapping(value = "/update-shipping-status" ,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Orders> updateShippingStatus (@RequestBody String updateInfoJson) throws JsonParseException, JsonMappingException, IOException{
		Map<String, String> userInfo = new HashMap<String, String>();
        userInfo = new ObjectMapper().readValue(updateInfoJson, new TypeReference<Map<String, String>>(){});
        int orderId = Integer.parseInt(userInfo.get("order_id"));
        int shippingStatus = Integer.parseInt(userInfo.get("shipping_status_id"));
        LocalDateTime time = LocalDateTime.now();
        Orders orderUpdated = ordersService.updateShippingStatus(orderId, time, shippingStatus);
        return new ResponseEntity<Orders>(orderUpdated, HttpStatus.OK);
	}
	
}
