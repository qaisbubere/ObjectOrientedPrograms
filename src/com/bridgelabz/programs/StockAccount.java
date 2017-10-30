/****************************************************************************** 
 *  Purpose: JSON programs for maintaining company shares.
 *
 *  @author  Qais Bubere
 *  @version 1.0
 *  @since   30-10-2017
 *
 ******************************************************************************/

package com.bridgelabz.programs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StockAccount {

		static int defaultShares=50,numberOfShares,remainingShares1,remainingShares2,remainingShares3;
		static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {

		File file = new File("/home/bridgeit/Desktop/Account.json");
		stockAccount();
	}
	public static void stockAccount() throws FileNotFoundException, IOException, ParseException{
		
		JSONObject object = new JSONObject();
		JSONObject object1 = new JSONObject();
		JSONObject object2 = new JSONObject();
		JSONObject finalObject = new JSONObject();

		JSONArray array = new JSONArray();
		object.put("company name","wipro");
		object.put("shares available",defaultShares);
		remainingShares1= defaultShares-numberOfShares;
		object.put("remaining_shares",remainingShares1 );
		array.add(object);
		
		object1.put("company name","tcs");
		object1.put("shares available",defaultShares);
		remainingShares2= defaultShares-numberOfShares;
		object1.put("remaining_shares",remainingShares2 );		
		array.add(object1);
		
		object2.put("company name","hcl");
		object2.put("shares available",defaultShares);
		remainingShares3= defaultShares-numberOfShares;
		object2.put("remaining_shares",remainingShares3 );
		array.add(object2);
		finalObject.put("Stock", array);
		System.out.println(array);
		buy(finalObject);
	}
	
	public static void buy(JSONObject finalObject) throws FileNotFoundException, IOException, ParseException{
		System.out.println("which banks shares do you wish to buy?");
		String companyName = scanner.next();
		JSONParser parser = new JSONParser();
		JSONObject fileData = (JSONObject) parser.parse(new FileReader("/home/bridgeit/Desktop/Account.json"));
		JSONArray fileReadingArray = (JSONArray) fileData.get("Stock");
		
		for(int a=0; a<fileReadingArray.size(); a++){
			JSONObject jsonObject = (JSONObject) fileReadingArray.get(a);
			if(jsonObject.get("company name").equals(companyName)){
				System.out.println("how man shares do u want to buy?");
				int sharesToBuy = scanner.nextInt();
				if(sharesToBuy<=remainingShares1){
					
				}
					}
				}   
	}

}
