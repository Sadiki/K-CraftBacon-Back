package com.bacon.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public Orders addNewOrder(int custId, int orderStatusId, LocalDateTime time, int shippingStatus, int deliveryMethod, double shippingPrice, double orderPrice) {
		Orders newOrderCreated = ordersRepo.addNewOrder(custId, orderStatusId, time, shippingStatus, deliveryMethod, shippingPrice, orderPrice);
		return newOrderCreated;
	}
	
	//update orderStatus
	public Orders updateOrderStatus (int orderId, LocalDateTime time, int orderStatus) {
		Orders order = ordersRepo.updateStatus(orderId, time, orderStatus);
		return order;
	}
	
	//update shippingStatus
	public Orders updateShippingStatus (int orderId, LocalDateTime time, int shippingStatus) {
		Orders order = ordersRepo.updateShippingStatus(orderId, time, shippingStatus);
		return order;
	}
	
	public List<OrderItems> getAllOrderItemsById(int custId, int statusId){
		System.out.println("orderitemsservice getallorderitemsbyid");//****************
		List<OrderItems> orderItemsRecords = ordersRepo.getAllOrderItemsById(custId);
		System.out.println("order items records: " + orderItemsRecords);
		List<OrderItems> orderItems= new ArrayList();
		for(OrderItems items: orderItemsRecords) 
			if(items.getStatus() == statusId)
				orderItems.add(items);		
		return orderItems;	
	}
	
	//update items from orderstatus 1 to 3 -- will be called from Orders class
	public boolean updateOrderStatusTo3(List<OrderItems> purchasingItems, Orders newOrder){
		boolean purchasedItems = ordersRepo.updateOrderStatusTo3(purchasingItems, newOrder);	
		return purchasedItems;
	}
	
}
