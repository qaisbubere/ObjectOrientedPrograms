package com.bridgelabz.programs;

public class Patient {

	private String patientName;
	private int patientAge;
	private long phoneNumber;
	
	public void setPatientName(String patientName){
		this.patientName = patientName;
	}
	
	public void setPatientAge(int patientAge){
		this.patientAge = patientAge;
	}
	
	public void setPhoneNumber(long phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	
	public String getPatientName(){
		return this.patientName;
	}
	
	public int getPatientAge(){
		return this.patientAge;
	}
	
	public long getPhoneNumber(){
		return this.phoneNumber;
}
}
