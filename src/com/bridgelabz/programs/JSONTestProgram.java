package com.bridgelabz.programs;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
public class JSONTestProgram {

	public static void main(String[]args){
		jsonFilewrite();
	}
	
	public static void jsonFilewrite(){

		        JSONObject obj = new JSONObject();
		        obj.put("name", "mkyong.com");
		        obj.put("age", new Integer(100));

		        JSONArray list = new JSONArray();
		        list.add("msg 1");
		        list.add("msg 2");
		        list.add("msg 3");

		        obj.put("messages", list);

		        try (FileWriter file = new FileWriter("/home/bridgeit/Desktop/test.json")) {

		            file.write(obj.toJSONString());
		            file.flush();
		            System.out.println(obj);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }

		        

		    }		
	}
