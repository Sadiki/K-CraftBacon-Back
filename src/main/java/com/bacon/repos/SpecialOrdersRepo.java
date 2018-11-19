package com.bacon.repos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bacon.models.Orders;
import com.bacon.models.SpecialOrders;

@Repository
public class SpecialOrdersRepo {
	private SessionFactory sessionFactory;
	
	@Autowired
	public void OrdersRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		System.out.println(this.sessionFactory);
	}
	
	//get all orders
	public List<SpecialOrders> getAll(){
		System.out.println("repo...");
		Session s = sessionFactory.getCurrentSession();
		return s.createQuery("from Special_Orders", SpecialOrders.class).getResultList();
	}
	
	//add a new special order
	public SpecialOrders add(SpecialOrders newOrder) {
		System.out.println("repo");
		Session s = sessionFactory.getCurrentSession();
		s.save(newOrder);
		return newOrder;
	}
}
