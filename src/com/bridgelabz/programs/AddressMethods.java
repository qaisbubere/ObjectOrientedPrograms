package com.bridgelabz.programs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.bridgelabz.util.Address;

public class AddressMethods {

	private Scanner scanner = new Scanner(System.in);
	private JSONArray addressBook;
	private JSONObject jsonObjectAddress;
	private String arrayName;
	private String fileName;
	public int flag = 0;
	/*
	 * parameterized constructor
	 */
	public AddressMethods(String filePath) {
		addressBook = new JSONArray();
		jsonObjectAddress = new JSONObject();
		this.arrayName = "AddressBook";
		this.fileName = filePath;
	}

	/*
	 * method for getting array name
	 */
	public String getArrayName() {
		return this.arrayName;
	}

	/*
	 * method for adding address
	 */
	public void addAddress() throws FileNotFoundException, IOException, ParseException {
		File file = new File(fileName);
		if (file.length() == 0) {
			getDetails(addressBook);
		} else {
			addressBook = getArray();
			getDetails(addressBook);
		}
	}

	/*
	 * method for retrieving array
		for
	 */
	private JSONArray getArray()throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(fileName));
		JSONArray array = (JSONArray) jsonObject.get(getArrayName());
		return array;
	}

	/*
	 * method for getting details
	 */
	public void getDetails(JSONArray addressBook) throws IOException {
		System.out.println("Enter First Name");
		String firstName = scanner.next();
		System.out.println("Enter Last Name");
		String lastName = scanner.next();
		System.out.println("Enter Flat Number");
		String flatNumber = scanner.next();
		System.out.println("Enter location");
		String location = scanner.next();
		String address = flatNumber + location;
		System.out.println("Enter city name");
		String city = scanner.next();
		System.out.println("Enter State");
		String state = scanner.next();
		System.out.println("Enter zip code");
		int zipCode = scanner.nextInt();
		System.out.println("Enter phone Number");
		long phoneNumber = scanner.nextLong();
		Address addressInformation = new Address(firstName, lastName, address, city, state, zipCode, phoneNumber);
		createJsonObject(addressInformation, addressBook);
	}

	@SuppressWarnings("unchecked")
	/*
	 * this method adds details to the object
	 */
	private void createJsonObject(Address addressInformation, JSONArray addressBook)throws IOException {
		JSONObject object = new JSONObject();
		object.put("FirstName", addressInformation.getFirstName());
		object.put("LastName", addressInformation.getLastName());
		object.put("Address", addressInformation.getAddress());
		object.put("City", addressInformation.getCity());
		object.put("State", addressInformation.getState());
		object.put("ZipCode", addressInformation.getZipCode());
		object.put("PhoneNumber", addressInformation.getPhoneNumber());
		addressBook.add(object);
		jsonObjectAddress.put(getArrayName(), addressBook);
		System.out.println(jsonObjectAddress);
		writeToFile(jsonObjectAddress);
	}

	@SuppressWarnings("resource")
	/*
	 * method for writing details into file
	 */
	private void writeToFile(JSONObject jsonObject) throws IOException {
		FileWriter fileWriter = new FileWriter(fileName);
		fileWriter.write(jsonObject.toJSONString());
		fileWriter.flush();
}

	/*
	 * method for deleting a particular address
	 */
	public void deleteAddress() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONObject obj = new JSONObject();
		JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(fileName));
		
		JSONArray array = (JSONArray) jsonObject.get(getArrayName());			
		//String number = (String) obj.get("PhoneNumber");
		System.out.println("enter zip code");
		long zipCode = scanner.nextInt();
		for(int i=0; i<=array.size();i++){
			obj = (JSONObject) array.get(i);
			long number = (long) obj.get("ZipCode");
			if(number==zipCode){
				array.remove(obj);
				jsonObjectAddress.put(getArrayName(),array);
				System.out.println("deleted");
				flag=1;
				break;
			}
		}
		if(flag==0){
			System.out.println("no such code in the address book");
		}
		writeToFile(jsonObjectAddress);
		
	}
}
