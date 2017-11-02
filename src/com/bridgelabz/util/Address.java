package com.bridgelabz.util;

public class Address {

	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private int zipCode;
	private long phoneNumber;
	
	public Address(String firstName,String lastName,String address,String city,String state,int zipCode,long phoneNumber){
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getAddress() {
		return this.address;
	}

	public String getCity() {
		return this.city;
	}

	public String getState() {
		return this.state;
	}

	public int getZipCode() {
	    return this.zipCode;
	}
	
	public long getPhoneNumber(){
		return this.phoneNumber;
}
}
