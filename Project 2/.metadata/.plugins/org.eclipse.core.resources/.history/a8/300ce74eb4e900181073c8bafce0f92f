package com.bacon.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="Customers")
public class Customers {

	@Id
	@Column(name="cust_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust_seq")
	@SequenceGenerator(name = "cust_seq", sequenceName ="customers_seq", allocationSize = 1)
	private int cust_id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;

	@Column(name = "username", unique = true)
	private String username;

	@Column(name = "password")
	private String password;
	
	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "street_address")
	private String streetAddress;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "zip")
	private String zip;
	
	@Column(name = "newsletter_option")
	private boolean newsletter;

	@OneToMany(mappedBy = "customers" , cascade= CascadeType.ALL)
	private List<CreditCardInfo> creditCardInfo;
	
	@OneToMany(mappedBy="customers", cascade= CascadeType.ALL)
	private List<Orders> orders;
	
	public Customers () {}


	public Customers(String firstName, String lastName, String username, String password, String email,
			String phoneNumber, String streetAddress, String city, String state, String zip) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}


	public int getCust_id() {
		return cust_id;
	}


	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getStreetAddress() {
		return streetAddress;
	}


	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getZip() {
		return zip;
	}


	public void setZip(String zip) {
		this.zip = zip;
	}


	public boolean isNewsletter() {
		return newsletter;
	}


	public void setNewsletter(boolean newsletter) {
		this.newsletter = newsletter;
	}


	@Override
	public String toString() {
		return "Customers [cust_id=" + cust_id + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", password=" + password + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", streetAddress=" + streetAddress + ", city=" + city + ", state=" + state + ", zip=" + zip + "]";
	}
	
	
	
}