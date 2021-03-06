package com.bacon.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="credit_card_info")
public class CreditCardInfo {

	@Id
	@Column(name="card_number")
	private String cardNumber;
	
	@Column(name="card_full_name")
	private String fullName;
	
	@Column(name="card_security_code")
	private int securityCode;
	
	@Column(name="card_expiration")
	private String expirationDate;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="cust_id")
	private Customers customers;
	
	CreditCardInfo () {}

	public CreditCardInfo(String cardNumber, String fullName, int securityCode, String expirationDate,
			Customers customers) {
		super();
		this.cardNumber = cardNumber;
		this.fullName = fullName;
		this.securityCode = securityCode;
		this.expirationDate = expirationDate;
		this.customers = customers;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Customers getCustomers() {
		return customers;
	}

	public void setCustomers(Customers customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "CreditCardInfo [cardNumber=" + cardNumber + ", fullName=" + fullName + ", securityCode=" + securityCode
				+ ", expirationDate=" + expirationDate + ", customers=" + customers + "]";
	}
	
}