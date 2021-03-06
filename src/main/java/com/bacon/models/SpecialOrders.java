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


	public SpecialOrders(String meatType, String smokeFlavor, String seasonings, double weight,
			double specialOrderPrice) {
		super();
		this.meatType = meatType;
		this.smokeFlavor = smokeFlavor;
		this.seasonings = seasonings;
		this.weight = weight;
		this.specialOrderPrice = specialOrderPrice;
	}


	public SpecialOrders(int specialOrderId, String meatType, String smokeFlavor, String seasonings, double weight,
			double specialOrderPrice) {
		super();
		this.specialOrderId = specialOrderId;
		this.meatType = meatType;
		this.smokeFlavor = smokeFlavor;
		this.seasonings = seasonings;
		this.weight = weight;
		this.specialOrderPrice = specialOrderPrice;
	}


	public int getSpecialOrderId() {
		return specialOrderId;
	}


	public void setSpecialOrderId(int specialOrderId) {
		this.specialOrderId = specialOrderId;
	}


	public String getMeatType() {
		return meatType;
	}


	public void setMeatType(String meatType) {
		this.meatType = meatType;
	}


	public String getSmokeFlavor() {
		return smokeFlavor;
	}


	public void setSmokeFlavor(String smokeFlavor) {
		this.smokeFlavor = smokeFlavor;
	}


	public String getSeasonings() {
		return seasonings;
	}


	public void setSeasonings(String seasonings) {
		this.seasonings = seasonings;
	}


	public double getWeight() {
		return weight;
	}


	public void setWeight(double weight) {
		this.weight = weight;
	}


	public double getSpecialOrderPrice() {
		return specialOrderPrice;
	}


	public void setSpecialOrderPrice(double specialOrderPrice) {
		this.specialOrderPrice = specialOrderPrice;
	}


	@Override
	public String toString() {
		return "SpecialOrders [specialOrderId=" + specialOrderId + ", meatType=" + meatType + ", smokeFlavor="
				+ smokeFlavor + ", seasonings=" + seasonings + ", weight=" + weight + ", specialOrderPrice="
				+ specialOrderPrice + "]";
	}
}