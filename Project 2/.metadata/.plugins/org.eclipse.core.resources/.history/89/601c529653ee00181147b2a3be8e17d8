package com.bacon.repos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bacon.models.Orders;

import oracle.sql.DATE;

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
	public boolean addNewOrder(Orders newOrder){
		Session s = sessionFactory.getCurrentSession();
		if(newOrder == null) {
			return false;
		}
		s.save(newOrder);
		return true;
	}
	//update orderStatus
	
	//update shippingStatus
	
}
