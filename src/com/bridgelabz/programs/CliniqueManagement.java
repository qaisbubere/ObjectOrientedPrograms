package com.bridgelabz.programs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import com.bridgelabz.util.Clinic;

public class CliniqueManagement {

	private static Scanner scanner = new Scanner(System.in);
	private static int answer;

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException {
		String doctorListFile = "/home/bridgeit/Desktop/Doctor.json";
		String patientListFile = "/home/bridgeit/Desktop/Patient.json";
		String appointmentListFile = "/home/bridgeit/Desktop/Appointment.json";
		Clinic clinic = new Clinic(doctorListFile, patientListFile,appointmentListFile);
		do {
			//System.out.println("Enter your choice..!!");
			System.out.println("1.Add Doctor\n2.Add Patient\n3.Appointment");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				clinic.addDoctor(doctorListFile);
				break;
			case 2:
				clinic.addPatient(patientListFile);
				break;
			case 3:
				clinic.appointment(appointmentListFile);
				break;
			default:
				System.out.println("wrong choice");
				break;
			}
			System.out.println("Do you wish to continue? \n 1:yes \n 2:no");
			answer = scanner.nextInt();
		} while (answer == 1);
}
}
