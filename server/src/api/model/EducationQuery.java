package api.model;
import java.util.List;
import java.util.Scanner;

import org.json.*;
import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.model.*;
import org.scribe.oauth.*;
import com.google.gson.*;
import java.util.LinkedList;

public class EducationQuery {
	private static final String EDUCATION_URL = 
			"http://api.linkedin.com/v1/people/~:(connections)?format=json";
	
	public static List<String> getURLList() throws JSONException
	{
		List<String> ID = new LinkedList<String>();
		JSONObject jsonObj = ScribeClient.getResponse(EDUCATION_URL);
		JSONObject connections = jsonObj.getJSONObject("connections");
		System.out.println("Number of connections parsed ="+connections.getInt("_total"));
		JSONArray values = connections.getJSONArray("values");
		//System.out.println("Values="+values.length());
		
		for(int i=0;i<values.length();i++) {
			String id = values.getJSONObject(i).getString("id");//.getJSONObject("apiStandardProfileRequest");//.getString("url");
			//System.out.println(id.toString());
			ID.add(id);
			
		}
		return ID;
	}
	
	private static String encodeName(String url) {
		
		String new_str = url.replaceAll(" ", "%20");
		return new_str;
	}
	public static void returnConnections(String schoolName) {
		
		JSONObject array;
		String name = encodeName(schoolName);
		//System.out.println("Name="+name);
		
		String url = "http://api.linkedin.com/v1/people-search:(people:(first-name,last-name,picture-url))?school-name="+name+"&format=json";
		
		
		System.out.println(ScribeClient.getResponse(url).toString());	
	}
	public static void main(String[] args) throws JSONException {
//		List<String> output = getURLList();
//		int i=0;
//		for(String id : output) {
//			String url = "http://api.linkedin.com/v1/people/id="+id+"?format=json";
//			//i++;
//			//System.out.println(ScribeClient.getResponse(url).toString());
//			
//			System.out.println(id);
//		}
		returnConnections("IIIT");
		
	}
	
}
