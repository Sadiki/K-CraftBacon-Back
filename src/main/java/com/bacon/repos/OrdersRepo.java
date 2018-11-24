
package com.bacon.repos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Order;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bacon.models.Customers;
import com.bacon.models.OrderItems;
import com.bacon.models.Orders;
import com.bacon.services.OrdersService;

@Repository
@Transactional
public class OrdersRepo {

	private SessionFactory sessionFactory;
	
	@Autowired
	public void OrdersRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		System.out.println(this.sessionFactory);
	}
	
	//get all orders
	public List<Orders> getAll(){
		Session s = sessionFactory.getCurrentSession();
		return s.createQuery("from Orders", Orders.class).getResultList();
	}
	
	//get all orders by userId
	public List<Orders> getAllOrdersByCustId(int custId){
		Session s = sessionFactory.getCurrentSession();
		return s.createQuery("from Orders where cust_id Like ?0", Orders.class).setParameter(0, custId).getResultList();
	}
	
	//create new order
	public Orders addNewOrder(int custId, int orderStatusId, LocalDateTime time, int shippingStatus, 
							   int deliveryMethod, double shippingPrice, double orderPrice){
		Customers customer = getById(custId);
		Orders newOrder = new Orders(customer, orderStatusId, time, shippingStatus, deliveryMethod, shippingPrice, orderPrice);
		Session s = sessionFactory.getCurrentSession();
		s.save(newOrder);
		return newOrder;
	}
	//update orderStatus
	public Orders updateStatus(int orderId, LocalDateTime time, int orderStatus) {
		Session s = sessionFactory.getCurrentSession();
		Orders order = s.get(Orders.class, orderId);
		order.setOrderStatusId(orderStatus);
		order.setOrderUpdate(time);
		s = sessionFactory.getCurrentSession();
		s.save(order);
		return order;
	}
	
	//update shippingStatus
	public Orders updateShippingStatus(int orderId, LocalDateTime time, int shippingStatus) {
		Session s = sessionFactory.getCurrentSession();
		Orders order = s.get(Orders.class, orderId);
		order.setShippingStatus(shippingStatus);
		order.setOrderUpdate(time);
		s = sessionFactory.getCurrentSession();
		s.save(order);
		return order;
	}
	
	//Helper Method
	public Customers getById(int id) {
		System.out.println("in cardRepo getById.. id = " + id);
		Session s = sessionFactory.getCurrentSession();
		s.getTransaction();
		Customers customer = s.get(Customers.class, id);
		return customer;
	}
	
	public List<OrderItems> getAllOrderItemsById(int custId){
		System.out.println("orderItemsRepo custId: " + custId);
		Session s = sessionFactory.getCurrentSession();
		System.out.println(s);
		List<OrderItems> orderItems= s.createQuery("from OrderItems where cust_id Like ?0", OrderItems.class).setParameter(0, custId).getResultList();
		System.out.println(orderItems);
		return orderItems;
	}
	
	//update items from orderstatus 1 to 3 -- will be called from Orders class
	public boolean updateOrderStatusTo3 (List<OrderItems> purchasingItems, Orders newOrder) {
		for(OrderItems items: purchasingItems) {
			//if trying to purchase items that are not in cart
			if(items.getStatus() != 1 ) {
				return false;
			}
		}
		
		//make sure cart is not empty. if empty return false
		if (purchasingItems.isEmpty()) {
			return false;
		}
		
		List<OrderItems> purchasedItems = setStatusTo3(purchasingItems, newOrder);
		return true;
	}
	
	public List<OrderItems> setStatusTo3(List<OrderItems> purchasingItems, Orders newOrder){
		List<OrderItems> purchasedItems = new ArrayList();
		for(OrderItems items: purchasingItems) { 
			Session s = sessionFactory.getCurrentSession();
			items.setStatus(3);
			items.setOrders(newOrder);
			s.update(items);
			purchasedItems.add(items);
		}
		return purchasedItems;
	}
	
}
