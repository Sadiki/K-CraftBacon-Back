
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

@Repository
@Transactional
public class OrderItemsRepo {

	protected SessionFactory sessionFactory;
	// Constructor that signals spring to create an instance within the bean
	// container
	@Autowired
	public OrderItemsRepo(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	//add item to cart
	public void addOrderItem(int custId, int quantity, Inventory inventoryItem) {
		System.out.println("repo");
		//System.out.println("addOrderItem custId: " + custId + " quantity: " + quantity + " inventoryItem: " + inventoryItem);
		Session s = sessionFactory.getCurrentSession();
		s.save(new OrderItems(custId, quantity, 1, inventoryItem));
	}

	
	//get items by custId and orderstatus 1 for cart items and wishlist
	public List<OrderItems> getAllOrderItemsById(int custId){
		Session s = sessionFactory.getCurrentSession();
		return s.createQuery("from OrderItems where cust_id Like ?0", OrderItems.class).setParameter(0, custId).getResultList();
	}

	//get items by orderId
	public List<OrderItems> getAllOrderItemsByOrderId(int orderId){
		System.out.println("repo");
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
	
	//update items from orderstatus 1 to 3 and get orderId
	public List<OrderItems> updateOrderStatusTo1 (int custId, int itemId, int statusId) {
		Session s = sessionFactory.getCurrentSession();
		Query orderItemsQuery = s.createQuery("from OrderItems where cust_id Like ?0 and status Like ?1", OrderItems.class);
		orderItemsQuery.setParameter(0, custId);
		orderItemsQuery.setParameter(1, statusId);
		List<OrderItems> orderItems = orderItemsQuery.getResultList();
		List<OrderItems> filteredItems = setStatus(orderItems, itemId, statusId);
		return filteredItems;
	}
	//Helper Method
	public Inventory getById(int id) {
		Session s = sessionFactory.getCurrentSession();
		Inventory inventory = s.get(Inventory.class, id);
		System.out.println("getById helper " + inventory);
		return inventory;
	}
	
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
				System.out.println("before setStatus(): " + items);
				items.setStatus(newStatus);
				System.out.println("after setStatus(): " + items);
				orderItems.add(items);
			}
		}
		return orderItems;
	}
	
}

