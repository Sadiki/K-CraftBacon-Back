package com.bacon.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bacon.models.Inventory;
import com.bacon.repos.InventoryRepository;

@Service
@Transactional
public class InventoryService {

	private InventoryRepository invRepo;
	
	@Autowired
	public InventoryService(InventoryRepository invRepo) {
		this.invRepo = invRepo;
			
	}
	
	public List<Inventory> getAll(){
		System.out.println("Inside Inventory Service: getAll()");
		return invRepo.getAll();
	}
	

	
	public Inventory getItem(int id) {
		return invRepo.getItem(id);
	}
	
	
	
}
