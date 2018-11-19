package com.bacon.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		System.out.println("service...");
		return ordersRepo.getAll();
	}
	
	//add new order
	public Orders addCard(Orders newOrder) {
		System.out.println("Service");
		return ordersRepo.add(newOrder);
	}
	
	
	
}