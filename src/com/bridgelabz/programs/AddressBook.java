package com.bridgelabz.programs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class AddressBook {

	private static Scanner scanner = new Scanner(System.in);
	private static  String filePath = "/home/bridgeit/Desktop/AddressBook.json";
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException {
	     int reply;
	     do{
	    	 AddressMethods addressMethod = new AddressMethods(filePath);
	    	 System.out.println("1.Add Address \n 2.delete \n 3.edit \n 4.Sort");
	    	 int choice = scanner.nextInt();
	    	 switch(choice){
	    	 case 1:
	    		 addressMethod.addAddress();
	    		 break;
	    		 
	    	 case 2:
	    		addressMethod.deleteAddress();
	    		 break;
	    	 }
	    	 System.out.println("Do you wish to make more changes? \n 1:yes \n 2:no");
	    	 reply = scanner.nextInt();
	     }while(reply == 1);
}
}
