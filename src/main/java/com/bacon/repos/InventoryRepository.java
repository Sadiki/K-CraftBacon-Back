package com.bacon.repos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bacon.models.Inventory;

@Repository
@Transactional
public class InventoryRepository {
	
	private SessionFactory sessionFactory;

	@Autowired
	public InventoryRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		System.out.println("Inside Inventory Repository constructor");
	}
	
	
	
	public List<Inventory> getAll(){
		System.out.println("Inside Inventory Repository: getAll()");
		Session s = sessionFactory.getCurrentSession();
		
		String hql = "from Inventory";
		return s.createQuery(hql, Inventory.class).getResultList();
				
	}
	
	public Inventory getItem(int id) {
		
		Session s = sessionFactory.getCurrentSession();
		return s.get(Inventory.class, id);
	}
	
}
