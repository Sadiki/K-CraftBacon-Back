package com.bacon.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bacon.models.Inventory;
import com.bacon.models.OrderItems;
import com.bacon.models.Orders;
import com.bacon.repos.OrderItemsRepo;

@Service
public class OrderItemsService {

	private OrderItemsRepo orderItemsRepo;
	
	@Autowired
	public OrderItemsService (OrderItemsRepo orderItemsRepo) {
		this.orderItemsRepo = orderItemsRepo;
	}
	
	public OrderItemsService() {}

	//add item to cart
	public void addOrderItem(int custId, int quantity, int inventory,int status) {
		Inventory inventoryItem = orderItemsRepo.getById(inventory);
		orderItemsRepo.addOrderItem(custId, quantity, inventoryItem ,status);
	}
	
	//get items by custId and orderstatus 1 for cart items
	public List<OrderItems> getAllOrderItemsById(int custId, int statusId){
		System.out.println("orderitemsservice getallorderitemsbyid");//****************
		List<OrderItems> orderItemsRecords = orderItemsRepo.getAllOrderItemsById(custId);
		System.out.println("order items records: " + orderItemsRecords);
		List<OrderItems> orderItems= new ArrayList();
		for(OrderItems items: orderItemsRecords) 
			if(items.getStatus() == statusId)
				orderItems.add(items);		
		return orderItems;	
	}
	
	//get items by orderId
	public List<OrderItems> getAllOrderItemsByOrderId(int orderId){
		System.out.println("service");
		List<OrderItems> orderItemsRecords = orderItemsRepo.getAllOrderItemsByOrderId(orderId);
		List<OrderItems> orderItems= new ArrayList();
		for(OrderItems items: orderItemsRecords) 
			if(items.getOrders().getOrderId() == orderId)
				orderItems.add(items);		
		System.out.println(orderItems);
		return orderItems;	
	}
	
	//update items from orderstatus 1 to 2
	public List<OrderItems> updateOrderStatusTo2(int custId, int itemId, int statusId){
		List<OrderItems> orderItemsRecords = orderItemsRepo.updateOrderStatusTo2(custId, itemId, statusId);	
		return orderItemsRecords;
	}
	
	//update items from orderstatus 2 to 1
	public List<OrderItems> updateOrderStatusTo1(int custId, int itemId, int statusId){
		List<OrderItems> orderItemsRecords = orderItemsRepo.updateOrderStatusTo1(custId, itemId, statusId);	
		return orderItemsRecords;
	}
	

	//delete item from cart or wishlist
	public boolean deleteItem (int custId, int itemId, int statusId){
		boolean deleted = orderItemsRepo.deleteItem(custId, itemId, statusId);	
		return deleted;
	}
	
}
