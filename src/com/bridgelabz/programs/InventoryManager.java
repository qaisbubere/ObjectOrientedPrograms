/****************************************************************************** 
 *  Purpose: JSON programs for inventory management .
 *
 *  @author  Qais Bubere
 *  @version 1.0
 *  @since   29-10-2017
 *
 ******************************************************************************/

package com.bridgelabz.programs;
import com.bridgelabz.util.JSONPrograms;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class InventoryManager {

	public static void main(String[] args) throws IOException, ParseException {
		
	JSONParser parser = new JSONParser();
    JSONObject fileData = (JSONObject) parser.parse(new FileReader("/home/bridgeit/Desktop/DataInventory.json"));
    JSONArray array = (JSONArray) fileData.get("inventory");
    System.out.println(array);
    
    JSONPrograms.inventoryManagement(array);
	}

}
