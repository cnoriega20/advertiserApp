package com.advertiser.rest.api.entity;

public class Advertiser {
 
	private Long id;
	private String firstName;
	private String lastName;
	private double creditLimit;
	
	
	
	public Advertiser(Long id, String firstName, String lastName, double creditLimit) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.creditLimit = creditLimit;
	}

	public Advertiser( String firstName, String lastName, double creditLimit) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.creditLimit = creditLimit;
	}
	
	public String getContactName(){
		return this.firstName + " " + this.lastName;
	}
	
	public void setContactName(String name){
		String[] parts = name.split(" ");
		this.firstName = parts [0];
		this.lastName = parts[1];
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	
	
	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	@Override
	public String toString() {
		return getId() + "," + getFirstName() + "," + getLastName();
	}
	
	
}
