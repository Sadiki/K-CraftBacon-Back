package com.bacon.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bacon.models.Customers;
import com.bacon.models.Inventory;
import com.bacon.services.InventoryService;

@CrossOrigin
@Controller
@RequestMapping(value = "/inventory")
public class InventoryController {

	private InventoryService invService;
	
	@Autowired
	public InventoryController(InventoryService inventoryService) {
		this.invService = inventoryService;
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Inventory>> getAll(){
		System.out.println("Inside Inventory Controller: getAll()");
		List<Inventory> inventory =  invService.getAll();
		return new ResponseEntity<List<Inventory>>(inventory,HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/{id}",produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Inventory> getItem(@PathVariable("id")int id){
		
		Inventory item = invService.getItem(id);
		if(item == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}

		return new ResponseEntity<Inventory> (item,  HttpStatus.OK);
		
	}

}
