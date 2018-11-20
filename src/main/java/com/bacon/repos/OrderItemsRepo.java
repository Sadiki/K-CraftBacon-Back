package com.bacon.repos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bacon.models.OrderItems;

@Repository
public class OrderItemsRepo {

	private SessionFactory sessionFactory;
	// Constructor that signals spring to create an instance within the bean
	// container
	@Autowired
	public OrderItemsRepo(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	//add item to cart
	public OrderItems addOrderItem(OrderItems newOrderItem) {
		Session s = sessionFactory.getCurrentSession();
		s.save(newOrderItem);
		return newOrderItem;
	}
	
	
	//get items by custId and orderstatus 1 for cart items
	
	//get items by custId and orderstatus 2 for saved for later items
	
	//get items by orderId
	
	//update items from orderstatus 1 to 2
	
	//update items from orderstatus 1 to 3 and get orderId
	
	
	
}
