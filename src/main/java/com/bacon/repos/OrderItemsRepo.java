package com.bacon.repos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bacon.models.Inventory;
import com.bacon.models.OrderItems;
import com.bacon.models.Orders;

@Repository
@Transactional
public class OrderItemsRepo {

	private final SessionFactory sessionFactory;
	// Constructor that signals spring to create an instance within the bean container
	@Autowired
	public OrderItemsRepo(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	//add item to cart/status
	public void addOrderItem(int custId, int quantity, Inventory inventoryItem ,int status) {
		System.out.println("repo");
		Session s = sessionFactory.getCurrentSession();
		s.save(new OrderItems(custId, quantity ,status , inventoryItem));
	}
	
	//get items by custId and orderstatus 1 for cart items and wishlist
	public List<OrderItems> getAllOrderItemsById(int custId){
		System.out.println("orderItemsRepo custId: " + custId);
		Session s = sessionFactory.getCurrentSession();
		System.out.println(s);
		List<OrderItems> orderItems= s.createQuery("from OrderItems where cust_id Like ?0", OrderItems.class).setParameter(0, custId).getResultList();
		System.out.println(orderItems);
		return orderItems;
	}

	//get items by orderId
	public List<OrderItems> getAllOrderItemsByOrderId(int orderId){
		Session s = sessionFactory.getCurrentSession();
		return s.createQuery("from OrderItems where order_id Like ?0", OrderItems.class).setParameter(0, orderId).getResultList();
	}
	
	//update items from orderstatus 1 to 2
	public List<OrderItems> updateOrderStatusTo2 (int custId, int itemId, int statusId) {
		Session s = sessionFactory.getCurrentSession();
		Query orderItemsQuery = s.createQuery("from OrderItems where cust_id Like ?0 and status Like ?1", OrderItems.class);
		orderItemsQuery.setParameter(0, custId);
		orderItemsQuery.setParameter(1, statusId);
		List<OrderItems> orderItems = orderItemsQuery.getResultList();
		List<OrderItems> filteredItems = setStatus(orderItems, itemId, statusId);
		return filteredItems;
	}
	
	//update items from orderstatus 2 to 1 
	public List<OrderItems> updateOrderStatusTo1 (int custId, int itemId, int statusId) {
		Session s = sessionFactory.getCurrentSession();
		Query orderItemsQuery = s.createQuery("from OrderItems where cust_id Like ?0 and status Like ?1", OrderItems.class);
		orderItemsQuery.setParameter(0, custId);
		orderItemsQuery.setParameter(1, statusId);
		List<OrderItems> orderItems = orderItemsQuery.getResultList();
		List<OrderItems> filteredItems = setStatus(orderItems, itemId, statusId);
		return filteredItems;
	}
	
	//delete item from cart or wishlist
	public boolean deleteItem (int custId, int itemId, int statusId) {
		Session s = sessionFactory.getCurrentSession();
		Query orderItemsQuery = s.createQuery("from OrderItems where cust_id Like ?0 and status Like ?1 and item_id like ?2", OrderItems.class);
		orderItemsQuery.setParameter(0, custId);
		orderItemsQuery.setParameter(1, statusId);
		orderItemsQuery.setParameter(2, itemId);
		
		//if trying to delete item not in cart or wishlist return false
		if(statusId != 1 && statusId != 2) {
			System.out.println("Can't delete items that are already purchased!");
			return false;
		}

		List<OrderItems> orderItems = orderItemsQuery.getResultList();
		System.out.println(orderItems);
		
		//if no entries match, return false
		if (orderItems.isEmpty()) {
			System.out.println("item doesn't exist");
			return false;
		}
//		Query deleteItemsQuery = s.createQuery("from OrderItems where cust_id Like ?0 and status Like ?1 and item_id like ?2", OrderItems.class);
//		orderItemsQuery.setParameter(0, custId);
//		orderItemsQuery.setParameter(1, statusId);
//		orderItemsQuery.setParameter(2, itemId);

		OrderItems item = orderItems.get(0);
		System.out.println(item);
		s.delete(item);
		return true;
	}
	
	//Helper Method for creating inventory object
	public Inventory getById(int id) {
		Session s = sessionFactory.getCurrentSession();
		Inventory inventory = s.get(Inventory.class, id);
		System.out.println("getById helper " + inventory);
		return inventory;
	}
	
	//Helper method for set the status of order items
	public List<OrderItems> setStatus(List<OrderItems> orderItemsRecords, int itemId, int statusId){
		int newStatus = 0;
		if(statusId == 1) {
			newStatus = 2;
		}
		if (statusId == 2) {
			newStatus = 1;
		}
		List<OrderItems> orderItems = new ArrayList();
		for(OrderItems items: orderItemsRecords) { 
			if(items.getInventory().getItemId() == itemId) {
				items.setStatus(newStatus);
				
				orderItems.add(items);
			}
		}
		return orderItems;
	}
	
}
