package com.bacon.repos;

import java.util.List;

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
	
	//update items from orderstatus 1 to 3 and get orderId
	
	//Helper Method
	public Inventory getById(int id) {
		Session s = sessionFactory.getCurrentSession();
		Inventory inventory = s.get(Inventory.class, id);
		System.out.println("getById helper " + inventory);
		return inventory;
	}
	
}
