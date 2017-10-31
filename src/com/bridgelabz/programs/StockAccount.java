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
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StockAccount {

		
		static int defaultShares=50,numberOfShares,remainingShares1,remainingShares2,remainingShares3,choiceAgain,myTotalStocks,wishAgain;
		static long remainingshares,myTotalShares;
		static Scanner scanner = new Scanner(System.in);
		static DateTimeFormatter itsObject = DateTimeFormatter.ofPattern("yyyy/dd/MM HH:mm:ss");
		static JSONArray myshares= new JSONArray(); 
		static JSONObject stockHolder = new JSONObject();
		static JSONObject myShares = new JSONObject();

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {

		File file = new File("/home/bridgeit/Desktop/Account.json");
		if(file.exists()){
			System.out.println("file exist");
		}
		else
		{
			System.out.println("file created");
		}
		
		File shareHolder = new File("/home/bridgeit/Desktop/shareholder.json");

		JSONObject object = new JSONObject();
		JSONObject object1 = new JSONObject();
		JSONObject object2 = new JSONObject();
		JSONObject finalObject = new JSONObject();

		JSONArray array = new JSONArray();
		object.put("company name","wipro");
		object.put("shares available",defaultShares);
		remainingShares1= defaultShares-numberOfShares;
		object.put("remaining_shares",remainingShares1 );
		object.put("price per share", 3000);
		array.add(object);
		
		object1.put("company name","tcs");
		object1.put("shares available",defaultShares);
		remainingShares2= defaultShares-numberOfShares;
		object1.put("remaining_shares",remainingShares2 );	
		object1.put("price per share", 3500);
		array.add(object1);
		
		object2.put("company name","hcl");
		object2.put("shares available",defaultShares);
		remainingShares3= defaultShares-numberOfShares;
		object2.put("remaining_shares",remainingShares3 );
		object2.put("price per share", 4000);
		array.add(object2);
		finalObject.put("Stock", array);
		FileWriter filewriter = new FileWriter("/home/bridgeit/Desktop/Account.json");
		filewriter.write(finalObject.toJSONString());
        filewriter.flush();
		
		System.out.println(finalObject);
		choice(finalObject);
	}
	
	
	/*
	 * method to buy shares
	 */
	public static void buy(JSONObject finalObject) throws FileNotFoundException, IOException, ParseException{
		
		JSONParser parser = new JSONParser();
		JSONObject temp = new JSONObject();
		JSONObject fileData = (JSONObject) parser.parse(new FileReader("/home/bridgeit/Desktop/Account.json"));
		JSONArray fileReadingArray = (JSONArray) fileData.get("Stock");
		
		/*
		 * do-while loop for buying shares again and again
		 */
		do{
		System.out.println("which company's shares do you wish to buy?");
		String companyName = scanner.next();
		
		/*
		 * for-loop for getting data of appropriate company
		 */
		for(int a=0; a<fileReadingArray.size(); a++){
			JSONObject jsonObject = (JSONObject) fileReadingArray.get(a);
			
			if(jsonObject.get("company name").equals(companyName)){
				System.out.println("how man shares do u want to buy?");
				int sharesToBuy = scanner.nextInt();
				long remainingshares=(long) jsonObject.get("remaining_shares");
				
				/*
				 * if-else loop for purchasing share
				 */
				if(sharesToBuy<=remainingshares){
					long priceOfShare=(long) jsonObject.get("price per share");
					long currentValue = priceOfShare*sharesToBuy; 	
					long totalValue =+ currentValue;
					System.out.println("you bought "+sharesToBuy+" shares, and your total amount is "+currentValue);
					LocalDateTime now = LocalDateTime.now();
					System.out.println("date and time of transactionis "+itsObject.format(now)); 
					remainingshares=remainingshares-sharesToBuy;
					myTotalStocks=myTotalStocks+sharesToBuy;
					jsonObject.put("remaining_shares", remainingshares);
					stockHolder.put("total share", myTotalStocks);
					stockPurchasedAndSold(stockHolder);

					System.out.println("do you wish to buy again? \n 1:yes \n 2:no");
					choiceAgain = scanner.nextInt();
				}
				else{
					System.out.println("sorry!! ,"+remainingshares+" shares are available. would you like to buy ? \n 1:yes\n 2:no");
					int answer= scanner.nextInt();
					
					if(answer==1){
					System.out.println("how man shares do u want to buy?");
					int SharesToBuy = scanner.nextInt();
					remainingshares=(long) jsonObject.get("remaining_shares");
					
					if(SharesToBuy<=remainingshares){
						long priceOfShare=(long) jsonObject.get("price per share");
						long currentValue = priceOfShare*SharesToBuy; 	
						long totalValue =+ currentValue;
						System.out.println("you bought "+SharesToBuy+" shares, and your total amount is "+currentValue);
						LocalDateTime now = LocalDateTime.now();
						System.out.println("dmyTotalSharesate and time of transactionis "+itsObject.format(now)); 
						remainingshares=remainingshares-SharesToBuy;
						jsonObject.put("remaining_shares", remainingshares);
						myTotalStocks=myTotalStocks+SharesToBuy;
						stockHolder.put("total share", myTotalStocks);
						stockPurchasedAndSold(stockHolder);
						System.out.println("do you wish to buy again? \n 1:yes \n 2:no");
						choiceAgain = scanner.nextInt();
					}
					}
					else
					{
						break;
					}
				}
			}
		}
		/*
		 * for-loop ends
		 */
		}
		while(choiceAgain==1);
		/*
		 * do-while loop ends
		 */
	}
	
	
	/*
	 * method to write data on file
	 */
	public static void stockPurchasedAndSold(JSONObject stockHolder) throws IOException{
		myshares.add(stockHolder);//this is array
		myShares.put("myshares", myshares);//this is object
		FileWriter filewriter = new FileWriter("/home/bridgeit/Desktop/shareholder.json");
		filewriter.write(myShares.toJSONString());
        filewriter.flush();
	}
	
	/*
	 * method to sell the shares 
	 */
	public static void sell() throws FileNotFoundException, IOException, ParseException{
		
		JSONParser parser = new JSONParser();
		JSONObject temp = new JSONObject();
		JSONObject fileData = (JSONObject) parser.parse(new FileReader("/home/bridgeit/Desktop/shareholder.json"));
		JSONArray array = (JSONArray) fileData.get("myshares");
		JSONObject jsonObject = (JSONObject) array.get(0);
		myTotalShares = (long) jsonObject.get("total share");
		
		/*
		 * do-while loop for performing selling again and again
		 */
		do{
		System.out.println("you have "+myTotalShares+" shares");
		System.out.println("how many shares you want to sell?");
		int selling=scanner.nextInt();
		if(selling<=myTotalShares){
			myTotalShares=myTotalShares-selling;
			System.out.println("you sold "+selling+" shares. "+myTotalShares+" are remaining with u");
			//stockHolder.put("total share", myTotalShares);
			stockHolder.put("total share", myTotalShares);
			stockPurchasedAndSold(stockHolder);
			System.out.println("do you wish to sell again? \n 1:yes \n 2:no");
			wishAgain = scanner.nextInt();
			
		}
		else{
			System.out.println(+myTotalShares+" are available. please enter figure among that");
			int Selling = scanner.nextInt(); 
			
			if(Selling<=myTotalShares){
				myTotalShares=myTotalShares-Selling;
				System.out.println("you sold "+Selling+" shares. "+myTotalShares+" are remaining with u");
				stockHolder.put("total share", myTotalShares);

				stockPurchasedAndSold(stockHolder);
				System.out.println("do you wish to sell again? \n 1:yes \n 2:no");
				wishAgain = scanner.nextInt();
			}
		}
		}
		while(wishAgain==1);
		/*
		 * do-while loop ends
		 */
	}
	
	/*
	 * method for users choice to buy or sell
	 */
	public static void choice(JSONObject finalObject) throws FileNotFoundException, IOException, ParseException{
		System.out.println("1: buy shares \n 2:sell shares");
		int choice=scanner.nextInt();
		if(choice==1)
			buy(finalObject);
		else if(choice==2)
			sell();
		else
			System.out.println("wrong choice");
	}
		
}
