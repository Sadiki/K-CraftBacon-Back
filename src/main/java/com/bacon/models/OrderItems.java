package com.bacon.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonCreator;

@Entity
@Component	
@Table(name="order_items")
public class OrderItems {
	
	@Id	
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ord_history_seq")
	@SequenceGenerator(name = "ord_history_seq", sequenceName ="order_history_seq", allocationSize = 1)
	private int orderHistoryId;
	
	@Column(name="cust_id")
	private int custId;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="status")
	private int status;
	
	@OneToOne
	@JoinColumn(name="special_order_id")
	private SpecialOrders specialOrder;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="order_id")
	private Orders orders;
	
	@ManyToOne(cascade= {CascadeType.DETACH,
						CascadeType.MERGE,
						CascadeType.PERSIST,
						CascadeType.REFRESH})
	@JoinColumn(name="item_id")
	private Inventory inventory;

	public OrderItems() {}

	public OrderItems(int custId, int quantity, int status, SpecialOrders specialOrder, Orders orders,
			Inventory inventory) {
		super();
		this.custId = custId;
		this.quantity = quantity;
		this.status = status;
		this.specialOrder = specialOrder;
		this.orders = orders;
		this.inventory = inventory;
	}

	public OrderItems(int orderHistoryId, int custId, int quantity, int status, SpecialOrders specialOrder,
			Orders orders, Inventory inventory) {
		super();
		this.orderHistoryId = orderHistoryId;
		this.custId = custId;
		this.quantity = quantity;
		this.status = status;
		this.specialOrder = specialOrder;
		this.orders = orders;
		this.inventory = inventory;
	}
	
	

	public OrderItems(int custId, int quantity, int status, Inventory inventory) {
		super();
		this.custId = custId;
		this.quantity = quantity;
		this.status = status;
		this.inventory = inventory;
	}

	public int getOrderHistoryId() {
		return orderHistoryId;
	}

	public void setOrderHistoryId(int orderHistoryId) {
		this.orderHistoryId = orderHistoryId;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public SpecialOrders getSpecialOrder() {
		return specialOrder;
	}

	public void setSpecialOrder(SpecialOrders specialOrder) {
		this.specialOrder = specialOrder;
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
		return "OrderItems [orderHistoryId=" + orderHistoryId + ", custId=" + custId + ", quantity=" + quantity
				+ ", status=" + status + "]";
	}


}