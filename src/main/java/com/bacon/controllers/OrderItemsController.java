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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bacon.models.OrderItems;
import com.bacon.services.OrderItemsService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@CrossOrigin
@RequestMapping(value = "/profile")
public class OrderItemsController {
	
	public OrderItemsController() {}
	
	private OrderItemsService orderItemsService;
	
	@Autowired
	public OrderItemsController (OrderItemsService orderItemsService) {
		this.orderItemsService = orderItemsService;
	}

	@PostMapping(value="/add", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addOrderItem(@RequestBody String newOrderItemJson) throws JsonParseException, JsonMappingException, IOException{
		Map<String, String> itemDetails = new HashMap<String, String>();
        itemDetails = new ObjectMapper().readValue(newOrderItemJson, new TypeReference<Map<String, String>>(){});
        
        int custId = Integer.parseInt(itemDetails.get("cust_id"));
        int quantity = Integer.parseInt(itemDetails.get("quantity"));
        int inventory = Integer.parseInt(itemDetails.get("item_id"));
        int status = Integer.parseInt(itemDetails.get("status"));
        orderItemsService.addOrderItem(custId, quantity, inventory,status);
		return new ResponseEntity<>(HttpStatus.CREATED); //Http status code = 201
	}
		
	//get items by custId and orderstatus 1 for cart items
	@PostMapping(value = "/cart" ,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderItems>> getAllCartItemsById(@RequestBody String userInfoJson) throws JsonParseException, JsonMappingException, IOException{
		Map<String, String> userInfo = new HashMap<String, String>();
        userInfo = new ObjectMapper().readValue(userInfoJson, new TypeReference<Map<String, String>>(){});
        
        //Represents shopping cart
        int statusId = 1;
        int custId = Integer.parseInt(userInfo.get("cust_id"));
        
        List<OrderItems> orderItems = orderItemsService.getAllOrderItemsById(custId, statusId);
        return new ResponseEntity<List<OrderItems>>(orderItems, HttpStatus.OK);
	}
	
	//get items by custId and orderstatus 2 for saved for later items
	@PostMapping(value = "/wishlist" ,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderItems>> getAllWishListItemsById(@RequestBody String userInfoJson) throws JsonParseException, JsonMappingException, IOException{
		Map<String, String> userInfo = new HashMap<String, String>();
        userInfo = new ObjectMapper().readValue(userInfoJson, new TypeReference<Map<String, String>>(){});
        
        //Represents shopping cart
        int statusId = 2;
        int custId = Integer.parseInt(userInfo.get("cust_id"));
        
        List<OrderItems> orderItems = orderItemsService.getAllOrderItemsById(custId, statusId);
        return new ResponseEntity<List<OrderItems>>(orderItems, HttpStatus.OK);
	}
		
	//get items by orderId -- will be called to show items for completed order on the order history
	@PostMapping(value = "/orders/order-id" ,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderItems>> getAlItemsByOrderId(@RequestBody String orderIdJson) throws JsonParseException, JsonMappingException, IOException{
		System.out.println("controller");
		Map<String, String> userInfo = new HashMap<String, String>();
        userInfo = new ObjectMapper().readValue(orderIdJson, new TypeReference<Map<String, String>>(){});
        
        //Represents shopping cart
        int orderId = Integer.parseInt(userInfo.get("order_id"));
        
        List<OrderItems> orderItems = orderItemsService.getAllOrderItemsByOrderId(orderId);
        return new ResponseEntity<List<OrderItems>>(orderItems, HttpStatus.OK);
	}
	
	//update items from orderstatus 1 to 2
	@PostMapping(value = "/orders/save-for-later" ,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderItems>> updateOrderStatusTo2(@RequestBody String orderIdJson) throws JsonParseException, JsonMappingException, IOException{
		System.out.println("controller");
		Map<String, String> userInfo = new HashMap<String, String>();
        userInfo = new ObjectMapper().readValue(orderIdJson, new TypeReference<Map<String, String>>(){});
        
        int custId = Integer.parseInt(userInfo.get("cust_id"));
        int itemId = Integer.parseInt(userInfo.get("item_id"));
        int statusId = 1;
        System.out.println(custId + " " + itemId);
        List<OrderItems> orderItems = orderItemsService.updateOrderStatusTo2(custId, itemId, statusId);
        return new ResponseEntity<List<OrderItems>>(orderItems, HttpStatus.OK);
	}
	
	//update items from orderstatus 2 to 1
	@PostMapping(value = "/orders/return-to-cart" ,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderItems>> updateOrderStatusTo1(@RequestBody String orderIdJson) throws JsonParseException, JsonMappingException, IOException{
		System.out.println("controller");
		Map<String, String> userInfo = new HashMap<String, String>();
        userInfo = new ObjectMapper().readValue(orderIdJson, new TypeReference<Map<String, String>>(){});
        
        int custId = Integer.parseInt(userInfo.get("cust_id"));
        int itemId = Integer.parseInt(userInfo.get("item_id"));
        int statusId = 2;
        System.out.println(custId + " " + itemId);
        List<OrderItems> orderItems = orderItemsService.updateOrderStatusTo1(custId, itemId, statusId);
        return new ResponseEntity<List<OrderItems>>(orderItems, HttpStatus.OK);
	}
	
	//delete item from cart or wishlist
	@PostMapping(value = "/orders/delete-item" ,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteItem(@RequestBody String orderIdJson) throws JsonParseException, JsonMappingException, IOException{
		Map<String, String> userInfo = new HashMap<String, String>();
        userInfo = new ObjectMapper().readValue(orderIdJson, new TypeReference<Map<String, String>>(){});
        
        int custId = Integer.parseInt(userInfo.get("cust_id"));
        int itemId = Integer.parseInt(userInfo.get("item_id"));
        int statusId = Integer.parseInt(userInfo.get("status_id"));
        
        boolean deleted = orderItemsService.deleteItem(custId, itemId, statusId);
        
        if (deleted == false) {
        	return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
	}
}
