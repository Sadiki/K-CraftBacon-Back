package com.bacon.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component	
@Table(name="order_items")

public class OrderItems {
	
	@Id	
	@Column(name="order_history_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ord_history_seq")
	@SequenceGenerator(name = "ord_history_seq", sequenceName ="order_history_seq", allocationSize = 1)
	private int orderHistoryId;
	
	@Column(name="order_id")
	private int orderId;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="item_id")
	private int itemId;
	
	@ManyToOne(cascade= CascadeType.ALL)
	private Orders orders;
	
	@ManyToOne(cascade= CascadeType.ALL)
	private Inventory inventory;
	
	OrderItems() {}

	public OrderItems( int quantity, int itemId, Orders orders, Inventory inventory) {
		super();
		this.orderId = orders.getOrderId();
		this.quantity = quantity;
		this.itemId = itemId;
		this.orders = orders;
		this.inventory = inventory;
	}

	public int getOrderHistoryId() {
		return orderHistoryId;
	}

	public void setOrderHistoryId(int orderHistoryId) {
		this.orderHistoryId = orderHistoryId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	@Override
	public String toString() {
		return "OrderItems [orderHistoryId=" + orderHistoryId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", itemId=" + itemId + ", orders=" + orders + ", inventory=" + inventory + "]";
	}
	
	
}