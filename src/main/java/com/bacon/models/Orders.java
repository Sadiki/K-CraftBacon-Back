package com.bacon.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="orders")

public class Orders {

	@Id
	@Column(name="order_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ord_seq")
	@SequenceGenerator(name = "ord_seq", sequenceName ="order_seq", allocationSize = 1)
	private int orderId;
	
//	@OneToOne
//	@JoinColumn(name = "cust_id")
//	private Customers customers;

	@Column(name="cust_id")
	private int customers;
	
	@Column(name="order_status_id")
	private int orderStatusId;		//hard coded values 1.Order Received 2.Preparing
											//3. Order Ready For Pickup
											//4. Order Ready For Delivery
											//5. Order Out For Delivery 6. Order Complete
	
	@Column(name="created_date")
	private LocalDateTime createdDate;
	
	@Column(name="order_update")
	private LocalDateTime orderUpdate;
	
	@Column(name="shipping_status")
	private int shippingStatus;
	
	@Column(name="delivery_method_id")
	private int deliveryMethodId;
	
	@Column(name="shipping_price", columnDefinition = "FLOAT(5,2)")
	private double shippingPrice;
	
	@Column(name="order_price", columnDefinition = "FLOAT(5,2)")
	private double orderPrice;
	
	
	public Orders () {}

	
	public Orders(int customers, int orderStatusId, LocalDateTime orderUpdate, int shippingStatus, int deliveryMethodId,
			double shippingPrice, double orderPrice) {
		super();
		this.customers = customers;
		this.orderStatusId = orderStatusId;
		this.orderUpdate = orderUpdate;
		this.shippingStatus = shippingStatus;
		this.deliveryMethodId = deliveryMethodId;
		this.shippingPrice = shippingPrice;
		this.orderPrice = orderPrice;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getCustomers() {
		return customers;
	}


	public void setCustomers(int customers) {
		this.customers = customers;
	}


	public int getOrderStatusId() {
		return orderStatusId;
	}


	public void setOrderStatusId(int orderStatusId) {
		this.orderStatusId = orderStatusId;
	}


	public LocalDateTime getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDateTime createdDate) {
		createdDate = LocalDateTime.now();
		this.createdDate = createdDate;
	}


	public LocalDateTime getOrderUpdate() {
		return orderUpdate;
	}


	public void setOrderUpdate(LocalDateTime orderUpdate) {
		createdDate = LocalDateTime.now();
		this.orderUpdate = orderUpdate;
	}


	public int getShippingStatus() {
		return shippingStatus;
	}


	public void setShippingStatus(int shippingStatus) {
		this.shippingStatus = shippingStatus;
	}


	public int getDeliveryMethodId() {
		return deliveryMethodId;
	}


	public void setDeliveryMethodId(int deliveryMethodId) {
		this.deliveryMethodId = deliveryMethodId;
	}


	public double getShippingPrice() {
		return shippingPrice;
	}


	public void setShippingPrice(double shippingPrice) {
		this.shippingPrice = shippingPrice;
	}


	public double getOrderPrice() {
		return orderPrice;
	}


	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}


	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", orderStatusId=" + orderStatusId + ", createdDate=" + createdDate
				+ ", orderUpdate=" + orderUpdate + ", shippingStatus=" + shippingStatus + ", deliveryMethodId="
				+ deliveryMethodId + ", shippingPrice=" + shippingPrice + ", orderPrice=" + orderPrice + "]";
	}
		
	
}