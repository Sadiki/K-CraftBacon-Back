package com.bacon.models;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="inventory")
public class Inventory {

	@Id
	@Column(name="item_id")
	private int itemId;
	
	@Column(name="item_name")
	private String itemName;
	
	@Column(name="item_description")
	private String itemDescription;
	
	@Column(name="item_price", columnDefinition = "FLOAT(5,2)")
	private double itemPrice;
	
	@Column(name="on_hand_quantity")
	private int onHandQuantity;
	
	@Column(name="item_picture")
	private Blob itemPicture;
	
	Inventory () {}

	public Inventory(int itemId, String itemName, String itemDescription, double itemPrice, int onHandQuantity,
			Blob itemPicture) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
		this.onHandQuantity = onHandQuantity;
		this.itemPicture = itemPicture;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getOnHandQuantity() {
		return onHandQuantity;
	}

	public void setOnHandQuantity(int onHandQuantity) {
		this.onHandQuantity = onHandQuantity;
	}

	public Blob getItemPicture() {
		return itemPicture;
	}

	public void setItemPicture(Blob itemPicture) {
		this.itemPicture = itemPicture;
	}

	@Override
	public String toString() {
		return "Inventory [itemId=" + itemId + ", itemName=" + itemName + ", itemDescription=" + itemDescription
				+ ", itemPrice=" + itemPrice + ", onHandQuantity=" + onHandQuantity + ", itemPicture=" + itemPicture
				+ "]";
	}
	
	
}