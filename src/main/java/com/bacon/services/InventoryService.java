package com.bacon.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

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
