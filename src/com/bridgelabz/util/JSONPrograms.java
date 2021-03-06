/****************************************************************************** 
 *  Purpose: JSON programs.
 *
 *  @author  Qais Bubere
 *  @version 1.0
 *  @since   29-10-2017
 *
 ******************************************************************************/

package com.bridgelabz.util;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.simple.JSONArray;
import com.bridgelabz.util.Expression;

public class JSONPrograms{

	static Scanner scanner = new Scanner(System.in);
	static String message= "Hello <<name>>, We have your full name as <<full name>> in our system. your contact number is 91-0000000000. Please,let us know incase of any clarification Thank you BridgeLabz 01/01/2016"; 
	static int i=1,numberOfShares,sharePrice,totalValue=0,reDirect=0,totalStock,repeat=0;
	static long price,weight,totalvalue,finalValue;
	static String shareName,name;
	static JSONArray array1 = new JSONArray();
	static JSONObject object = new JSONObject();
	private static QueueLinkedList<String> queue = new QueueLinkedList<String>();
	
	/*
	 * method for inserting objects to inventory
	 */
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
	
	/*
	 * method for calculating total value of a particular object
	 */
	public static void calculateValue(JSONObject riceObject) throws FileNotFoundException, IOException, ParseException{
		JSONParser parser = new JSONParser();
        JSONObject fileData = (JSONObject) parser.parse(new FileReader("/home/bridgeit/Desktop/DataInventory.json"));
		JSONArray array = (JSONArray) fileData.get("inventory");
		
		System.out.println("enter name");
		String name = scanner.next();
		/*
		 * for-loop for traversing through all elements
		 */
		for(int a=0; a<array.size(); a++){
			JSONObject object1 = (JSONObject) array.get(a);
			/*
			 * if-else loop for calculating total value
			 */
			if(object1.get("name").equals(name)){
				long price =  (long) object1.get("price");
				long weight = (long) object1.get("weight");
				System.out.println(object1);
				long totalValue = price*weight;
				System.out.println("total value of "+name+" is " +totalValue);
				break;
					}
					/*
					 * if-else loop ends
					 */
			}   
			/*
			 * for loop ends
			 */
			} 

/************************************************************************************************************************************/

	/*
	 *method for replacing words in a string using regex 
	 */
	public static void regularExpression() {
				
		  Scanner scanner = new Scanner(System.in);
		  String message = "Hello <name>,\nWe have your fullname as <full name> in our system. \n"
				  			+ "Your contact number is 91-xxxxxxxxxx. \n" + "Please,let us know in case of any clarification \n"
				  			+ "Thankyou BridgeLabz \n01/01/2016.";
		  String[] pattern = { "<full name>", "<name>", "xxxxxxxxxx", "01/01/2016" };
		  String[] replacePattern = new String[pattern.length];
		  
		  Expression expression = new Expression();
			int position = 0;
			System.out.println("Enter the FullName");
			expression.setFullName(scanner.nextLine());
			replacePattern[position++]= expression.getfullName() ;
			System.out.println("Enter the Name");
			expression.setName(scanner.next());
		    replacePattern[position++] = expression.getName();
		    System.out.println("Enter phone number");
		    expression.setPhoneNumber(scanner.next());
		    replacePattern[position++] = expression.getPhoneNumber() ;
		    Date date = new Date();
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    replacePattern[position++] = dateFormat.format(date);
		    
		    for(int i=0;i<pattern.length;i++){
		        	message = replaceContent(message, pattern[i], replacePattern[i]);
		    }
			
	}
	
	/*
	 * method to replace given content with user defined content
	 */
	public static String replaceContent(String replacedMessage, String stringToBeReplaced, String replacement) {
		String message = replacedMessage;
		Pattern pattern = Pattern.compile(stringToBeReplaced);
		Matcher matcher = pattern.matcher(message);
		while (matcher.find()) {
			message = matcher.replaceAll(Matcher.quoteReplacement(replacement));
		}
		return message;
	}

/************************************************************************************************************************************/
	/*
	 * method for stock report
	 */
	public static void stockReport() throws IOException {

		File file = new File("/home/bridgeit/Desktop/stockReport.json");
		JSONObject object = new JSONObject();
		FileWriter filewriter = new FileWriter("/home/bridgeit/Desktop/stockReport.json");

		System.out.println("number of stock?");
		totalStock = scanner.nextInt();
		
		/*
		 * while loop for calculating total value
		 */
		while(i<=totalStock){
			JSONArray array = new JSONArray();
			System.out.println("enter name of share");
			shareName = scanner.next();
			System.out.println("enter number of shares");
			numberOfShares = scanner.nextInt();
			System.out.println("enter share price");
			sharePrice = scanner.nextInt();
			
			object.put("nameOfShare",shareName);
			object.put("number_of_shares", numberOfShares);
			object.put("share price", sharePrice);
			array.add(object);
	
			stockEntries(array,filewriter);
			i++;
			int perStockValue = sharePrice*numberOfShares;
			System.out.println("total value of "+shareName+" is "+perStockValue);
			totalValue = totalValue+perStockValue;
			object.remove("nameOfShare");
			object.remove("number_of_shares");
			object.remove("share price");
			}	
		/*
		 * while loop ends
		 */
		System.out.println(" total value of all stock is "+totalValue);	
	}
	
	/*
	 * method for writing stock entries on file 
	 */
	public static void stockEntries(JSONArray array,FileWriter filewriter) throws IOException{
		System.out.println(array);
		filewriter.write(array.toJSONString());
		filewriter.flush();
	}

/*****************************************************************************************************************************************/
	/*
	 * method for extension of inventory management program
	 */
	public static void inventoryManagement(JSONArray newArray) {
		
		while(repeat!=newArray.size())
		{
		System.out.println("enter name");
		String name = scanner.next();
		for(int i=0; i<newArray.size(); i++){
			JSONObject object = (JSONObject) newArray.get(i);
			if(object.get("name").equals(name)){
				price =  (long) object.get("price");
				weight = (long) object.get("weight");
				System.out.println(object);
				totalvalue = price*weight;
				System.out.println("total value of "+name+" is " +totalvalue);
				finalValue = finalValue+totalvalue;
				break;
			}
		}   
		repeat++;
		}
		System.out.println("final value of all the object is :"+finalValue);

	}

/****************************************************************************************************************************************/
	/*
	 * method for assigning cards
	 */
	public static String[] assignDeckOfCards() {
		
		String[] suit = { "Club", "Diamond", "Heart", "Spade" };
		String[] rank = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
		String[] deckOfCards = new String[52];
		int position = 0;
		for (int i = 0; i < suit.length; i++) {
			for (int j = 0; j < rank.length; j++) {
				deckOfCards[position++] = suit[i] + " " + rank[j];
			}
		}
		return deckOfCards;	
	}

	
	/*
	 * method for shuffling cards
	 */
	public static String[] shuffle(String[] deckOfCards) {
		int length = deckOfCards.length;
		for (int i = 0; i < length; i++) {
			int random = (int) (Math.random() * (52));
			String temp = deckOfCards[i];
			deckOfCards[i] = deckOfCards[random];
			deckOfCards[random] = temp;
		}	
		return deckOfCards;
	}
	
	/*
	 * this method is for distributing cards to the players
	 */
	public static String[][] distribute(int noOfPlayers, int noOfCards,String[] deckOfShuffleCards) {
		int position = 0;
		String[][] distributedCards = new String[noOfPlayers][noOfCards];
		for (int i = 0; i < noOfPlayers; i++) {
			for (int j = 0; j < noOfCards; j++) {
				distributedCards[i][j] = deckOfShuffleCards[position++];
			}
		}
		return distributedCards;
	}

	
	/*
	 * method for printing distributed cards
	 */
	public static void printDistibutedCards(String[][] cardsOfPlayers, int noOfPlayers, int noOfCards) {
		for (int i = 0; i < noOfPlayers; i++) {
			System.out.println("Cards of player " + (i + 1) + " are");
			for (int j = 0; j < noOfCards; j++) {
				System.out.println(cardsOfPlayers[i][j]);
			}
			System.out.println();
		}
	}

	/*
	 * 
	 */
	public static void printSortedCards(String[][] playerCards, int noOfPlayers, int noOfCards) {
		String[] cards = new String[noOfCards];
		for (int i = 0; i < noOfPlayers; i++) {
			for (int j = 0; j < noOfCards; j++) {
				cards[j] = playerCards[i][j];
			}
			queue.enQueue("Cards Of Player " + (i + 1) + " are as follows..");
			sort(cards);
		}
		printSortedCardQueue();
	}
	
	/*
	 * method for sorting cards on the basis of ranks
	 */
	private static void sort(String[] cards) {
		char[] rank = { 'A', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'J', 'Q', 'K' };
		for (int i = 0; i < rank.length; i++) {
			for (int j = 0; j < cards.length; j++) {
				String card = cards[j];
				char cardRank = card.charAt(card.length() - 1);
				if (cardRank == rank[i])
					queue.enQueue(card);
			}
		}
	}

	
	/*
	 * method for printing sorted card queue
	 */
	private static void printSortedCardQueue() {
		for (int i = 0; i < queue.size(); i++) {
			if (i % 10 == 0)
				System.out.println();
			System.out.println(queue.deQueue());

		}
}
}
