package com.bacon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bacon.models.OrderItems;
import com.bacon.repos.OrderItemsRepo;

@Service
public class OrderItemsService {

	private OrderItemsRepo orderItemsRepo;
	
	@Autowired
	public OrderItemsService (OrderItemsRepo orderItemsRepo) {
		this.orderItemsRepo = orderItemsRepo;
	}
	
	//add item to cart
	public OrderItems addOrderItem(OrderItems newOrderItem) {
		return orderItemsRepo.addOrderItem(newOrderItem);
	}
	//get items by custId and orderstatus 1 for cart items
	
	//get items by custId and orderstatus 2 for saved for later items
	
	//get items by orderId
	
	//update items from orderstatus 1 to 2
	
	//update items from orderstatus 1 to 3 and get orderId
	
	
}
