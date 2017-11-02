package com.bridgelabz.programs;

public class Doctor {

	private String doctorName;
	private String specialization;
	private String availability;

	public void setDoctorName(String doctorName){
		this.doctorName = doctorName;
	}
	
	public void setSpecialization(String specialization){
		this.specialization = specialization;
	}
	
	public void setAvailability(String availability){
		this.availability = availability;
	}
	
	public String getDoctorName(){
		return this.doctorName;
	}
	
	public String getSpecialization(){
		return this.specialization;
	}
	
	public String getAvailability(){
		return this.availability;
	}
}
