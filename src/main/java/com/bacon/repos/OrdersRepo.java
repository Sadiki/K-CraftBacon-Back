
package com.bacon.repos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bacon.models.Orders;

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
	
	//add new order
	public Orders add(Orders newOrder) {
		Session s = sessionFactory.getCurrentSession();
		s.save(newOrder);
		return newOrder;
	}
	
}
