package com.bacon.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bacon.models.Inventory;
import com.bacon.models.OrderItems;
import com.bacon.models.Orders;
import com.bacon.repos.OrdersRepo;

@Service
@Transactional
public class OrdersService {

	private OrdersRepo ordersRepo;
	
	public OrdersService (OrdersRepo ordersRepo) {
		this.ordersRepo = ordersRepo;
	}
	
	//get all orders
	public List<Orders> getAll(){
		return ordersRepo.getAll();
	}
	
	//get all orders by userId
	public List<Orders> getAllOrdersByCustId(int custId){
		List<Orders> orderRecords = ordersRepo.getAllOrdersByCustId(custId);
		System.out.println(orderRecords);
		return orderRecords;	
	}
	
	//create new order
	public boolean addNewOrder(Orders newOrder) {
		boolean newOrderCreated = ordersRepo.addNewOrder(newOrder);
		return newOrderCreated;
	}
	//update orderStatus
	
	//update shippingStatus
	
}
