package com.bacon.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonCreator;

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
	private String itemPicture;
	
	@OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
	private List<OrderItems> orderItems;
	
	Inventory () {}

	//Constructor without primary key field
	public Inventory(String itemName, String itemDescription, double itemPrice, int onHandQuantity,
			String itemPicture) {
		super();
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
		this.onHandQuantity = onHandQuantity;
		this.itemPicture = itemPicture;
	}

	public Inventory(int itemId, String itemName, String itemDescription, double itemPrice, int onHandQuantity,
			String itemPicture) {
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

	public String getItemPicture() {
		return itemPicture;
	}

	public void setItemPicture(String itemPicture) {
		this.itemPicture = itemPicture;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemDescription == null) ? 0 : itemDescription.hashCode());
		result = prime * result + itemId;
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((itemPicture == null) ? 0 : itemPicture.hashCode());
		long temp;
		temp = Double.doubleToLongBits(itemPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + onHandQuantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		if (itemDescription == null) {
			if (other.itemDescription != null)
				return false;
		} else if (!itemDescription.equals(other.itemDescription))
			return false;
		if (itemId != other.itemId)
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (itemPicture == null) {
			if (other.itemPicture != null)
				return false;
		} else if (!itemPicture.equals(other.itemPicture))
			return false;
		if (Double.doubleToLongBits(itemPrice) != Double.doubleToLongBits(other.itemPrice))
			return false;
		if (onHandQuantity != other.onHandQuantity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Inventory [itemId=" + itemId + ", itemName=" + itemName + ", itemDescription=" + itemDescription
				+ ", itemPrice=" + itemPrice + ", onHandQuantity=" + onHandQuantity + ", itemPicture=" + itemPicture
				+ "]";
	}
}