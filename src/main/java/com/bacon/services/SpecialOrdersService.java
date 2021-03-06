package com.bacon.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bacon.models.Inventory;
import com.bacon.models.Orders;
import com.bacon.models.SpecialOrders;
import com.bacon.repos.SpecialOrdersRepo;

@Service
@Transactional
public class SpecialOrdersService {

	private SpecialOrdersRepo specialOrdersRepo;
	
	@Autowired
	public SpecialOrdersService (SpecialOrdersRepo specialOrdersRepo) {
		this.specialOrdersRepo = specialOrdersRepo;
	}
	
	//get all orders
	public List<SpecialOrders> getAll(){
		return specialOrdersRepo.getAll();
	}
	
	//add new order
	public SpecialOrders addSpecialOrder(SpecialOrders newOrder) {
		return specialOrdersRepo.add(newOrder);
	}
	
//	//add item to cart
//	public void addOrderItem(int specialOrderId) {
//		orderItemsRepo.addOrderItem(custId, quantity, inventoryItem);
//	}
	
	
}
