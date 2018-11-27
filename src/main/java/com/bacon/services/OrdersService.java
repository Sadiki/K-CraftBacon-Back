package com.bacon.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bacon.models.OrderItems;
import com.bacon.models.Orders;
import com.bacon.repos.OrdersRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
		public ArrayNode getAllOrdersByCustId(int custId){
			List<Orders> orderRecords = ordersRepo.getAllOrdersByCustId(custId);
			
			// create a map object to be returned as well as other helper variables(if needed)
			ArrayNode ordersList = new ObjectMapper().createArrayNode();
			
			for (Orders ord : orderRecords) {
				ObjectNode convertedOrders = new ObjectMapper().createObjectNode();
				// populate the JSON relevant information
				convertedOrders.put("orderId", ord.getOrderId());
				convertedOrders.put("customers", ord.getCustomers().getCust_id());
				convertedOrders.put("orderStatusId",ord.getOrderStatusId());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				convertedOrders.put("createdDate", ord.getCreatedDate().format(formatter).toString());
				
				// if there exist an order update convert it as well
				if(ord.getOrderUpdate()!= null) convertedOrders.put("orderUpdate", ord.getOrderUpdate().format(formatter).toString());
				convertedOrders.put("shippingStatus", ord.getShippingStatus());
				convertedOrders.put("deliveryMethodId", ord.getDeliveryMethodId());
				convertedOrders.put("shippingPrice", ord.getShippingPrice());
				convertedOrders.put("orderPrice", ord.getOrderPrice());
				
				//add the converted object to the orders list to be returned
				ordersList.add(convertedOrders);

				
			}
			System.out.println(ordersList);
			return ordersList;	
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
