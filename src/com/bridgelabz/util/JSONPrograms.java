package com.bridgelabz.util;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;

public class JSONPrograms {

	static Scanner scanner = new Scanner(System.in);
	static String message= "Hello <<name>>, We have your full name as <<full name>> in our system. your contact number is 91-0000000000. Please,let us know incase of any clarification Thank you BridgeLabz 01/01/2016"; 

	public static void dataInventory() throws IOException, ParseException{
		JSONObject object1 = new JSONObject();
		JSONObject object2 = new JSONObject();
		JSONObject object3 = new JSONObject();

		JSONArray commonArray = new JSONArray();
		JSONObject riceObject = new JSONObject();
		JSONObject wheatObject = new JSONObject();
		JSONObject pulseObject = new JSONObject();
		JSONArray riceArray = new JSONArray();
		JSONArray wheatArray = new JSONArray();
		JSONArray pulseArray = new JSONArray();
		JSONArray inventory = new JSONArray();
		JSONObject Inventory = new JSONObject();

		object1.put("name","rice");
		object1.put("weight",new Integer(50));
		object1.put("price",new Integer(40));
		commonArray.add(object1);

		object2.put("name","wheat");
		object2.put("weight",new Integer(50));
		object2.put("price",new Integer(20));
		commonArray.add(object2);
	
		object3.put("name","pulse");
		object3.put("weight",new Integer(20));
		object3.put("price",new Integer(10));
		commonArray.add(object3);
		Inventory.put("inventory",commonArray);
	
		FileWriter file = new FileWriter("/home/bridgeit/Desktop/DataInventory.json");
		file.write(Inventory.toJSONString());
        file.flush();
		System.out.println(Inventory);
        calculateValue(riceObject);
	}
	
	public static void calculateValue(JSONObject riceObject) throws FileNotFoundException, IOException, ParseException{
		JSONParser parser = new JSONParser();
        JSONObject fileData = (JSONObject) parser.parse(new FileReader("/home/bridgeit/Desktop/DataInventory.json"));
		JSONArray array = (JSONArray) fileData.get("inventory");
		
		System.out.println("enter name");
		String name = scanner.next();
		for(int i=0; i<array.size(); i++){
			JSONObject object1 = (JSONObject) array.get(i);
			if(object1.get("name").equals(name)){
				long price =  (long) object1.get("price");
				long weight = (long) object1.get("weight");
				System.out.println(object1);
				long totalValue = price*weight;
				System.out.println("total value of "+name+" is " +totalValue);
				break;
			}
		}   
	}

	public static void regularExpression() {
		System.out.println("default message is "+message);
		System.out.println("enter your first name");
		String firstName= scanner.next();
		System.out.println("enter your full name");
		String fullName = scanner.next();
		System.out.println("enter your mobile number");
		long mobileNumber = scanner.nextLong();
		Pattern pattern = Pattern.compile("<<name>>");
		Matcher match = pattern.matcher(message);
		String result = match.replaceAll(firstName);
		System.out.println(result);
	}
	
	
	
}
