
package com.bacon.models;

import java.time.LocalDateTime;
import java.util.Date;

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
	
	@OneToOne
	@JoinColumn(name = "cust_id")
	private Customers customers;
	
	@Column(name="order_status_id") //1. received 2. preparing 3. ready 4. complete
	private int orderStatusId;
	
	@Column(name="created_date")
	private LocalDateTime createdDate;
	
	@Column(name="order_update")
	private LocalDateTime orderUpdate;
	
	@Column(name="shipping_status")  //1. preparing  2. ready for delivery 3. on its way 4. delivered
	private int shippingStatus;
	
	@Column(name="delivery_method_id") //1. pickup 2.delivery
	private int deliveryMethodId;
	
	@Column(name="shipping_price", columnDefinition = "FLOAT(5,2)")
	private double shippingPrice;
	
	@Column(name="order_price", columnDefinition = "FLOAT(5,2)")
	private double orderPrice;
	
	
	public Orders () {}

	

	public Orders(Customers customers, int orderStatusId, LocalDateTime createdDate, int shippingStatus,
			int deliveryMethodId, double shippingPrice, double orderPrice) {
		super();
		this.customers = customers;
		this.orderStatusId = orderStatusId;
		this.createdDate = createdDate;
		this.shippingStatus = shippingStatus;
		this.deliveryMethodId = deliveryMethodId;
		this.shippingPrice = shippingPrice;
		this.orderPrice = orderPrice;
	}
	public Orders(int orderStatusId, LocalDateTime createdDate, int shippingStatus,
			int deliveryMethodId, double shippingPrice, double orderPrice) {
		super();
		this.orderStatusId = orderStatusId;
		this.createdDate = createdDate;
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


	public Customers getCustomers() {
		return customers;
	}


	public void setCustomers(Customers customers) {
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


	public void setCreatedDate(LocalDateTime localDateTime) {
		this.createdDate = localDateTime;
	}


	public LocalDateTime getOrderUpdate() {
		return orderUpdate;
	}


	public void setOrderUpdate(LocalDateTime time) {
		this.orderUpdate = time;
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