package com.bacon.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "Special_Orders")

public class SpecialOrders {
	
	@Id
	@Column(name = "special_order_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="Special_ord_seq")
	@SequenceGenerator(name = "Special_ord_seq" , sequenceName="Special_orders_seq")
	private int specialOrderId;
	
	@Column(name ="meat_type")
	private String meatType;

	@Column(name ="smoke_flavor")
	private String smokeFlavor;

	@Column(name ="seasonings")
	private String seasonings;
	
	@Column(name ="measured_weight", columnDefinition = "FLOAT(5,2)")
	private double weight;	
	
	@Column(name = "special_order_price", columnDefinition = "FLOAT(5,2)")
	private double specialOrderPrice;
	
	
	//Constructors 
	public SpecialOrders () {}


	
}