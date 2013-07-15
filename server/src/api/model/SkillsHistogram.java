package api.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.*;
//import org.json.simple.*;
//import org.json.simple.JSONObject;
//import org.json.simple.JSONArray;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SkillsHistogram implements AbstractResult {
	private String skills;
	private static String URL = 
			"http://api.linkedin.com/v1/people-search:(facets:(code,buckets:(code,name,count)))?facets=";//location,network&start=0&count=25&format=json";

	private JSONArray aJSON;
	private JSONArray bJSON;
	private String a;
	private String b;

	//private static final int TOTAL = 2;

	private Integer aTotal;
	private Integer bTotal;
	public SkillsHistogram(String skills, String a, String b) {
		this.skills = skills;
		this.a = a;
		this.b = b;
	}

	public void decode() {
		try{
			URL = URL+a+","+b+"&start=0&count=25&format=json"+"&keywords="+this.skills;
			JSONObject finalJSON = /*readFile("C:\\Users\\naikb\\Desktop\\json.txt");*/ScribeClient.getResponse(URL);
			JSONObject temp = finalJSON.getJSONObject("facets");
			JSONArray values = temp.getJSONArray("values");
			temp = values.getJSONObject(0);
			JSONObject location = temp.getJSONObject("buckets");
			setATotal(location.getInt("_total"));
			temp = values.getJSONObject(1);
			JSONObject degree = temp.getJSONObject("buckets");
			setBTotal(degree.getInt("_total"));

			JSONArray tmp = location.getJSONArray("values");
			JSONArray resultA = new JSONArray();
			for(int i=0;i<getATotal();i++) {
				JSONObject tmpp = tmp.getJSONObject(i);
				JSONObject resTemp = new JSONObject();
				resTemp.put("value", tmpp.getInt("count"));
				resTemp.put("label", tmpp.getString("name"));
				resultA.put(resTemp);
			}
			tmp = degree.getJSONArray("values");
			JSONArray resultB = new JSONArray();
			for(int i=0;i<getBTotal();i++) {
				JSONObject tmpp = tmp.getJSONObject(i);
				JSONObject resTemp = new JSONObject();
				resTemp.put("value", tmpp.getInt("count"));
				resTemp.put("label", tmpp.getString("name"));
				resultB.put(resTemp);
			}
			setAJSON(resultA);
			setBJSON(resultB);


			temp = values.getJSONObject(0);
			setA((String)temp.get("code"));
			temp = values.getJSONObject(1);
			setB((String)temp.get("code"));
		} catch (Exception e) { 
			System.out.println(e);
		}

	}
	public void setA(String a) {
		this.a = a;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getA() {
		return this.a;
	}
	public String getB() {
		return this.b;
	}
	public void setAJSON(JSONArray json)
	{
		this.aJSON = json;
	}
	public void setBJSON(JSONArray json) {
		this.bJSON = json;
	}
	public void setATotal(Integer total) {
		this.aTotal = total;
	}
	public void setBTotal(Integer long1) {
		this.bTotal = long1;
	}
	public Integer getATotal() {
		return this.aTotal;
	}
	public Integer getBTotal() {
		return this.bTotal;
	}
	public JSONArray getAJSON() {
		return this.aJSON;
	}
	public JSONArray getBJSON() {
		return this.bJSON;
	}
	public static void main(String[] args) throws Exception {
		SkillsHistogram obj = new SkillsHistogram("java","location","network");
		obj.decode();
		System.out.println(obj.getAJSON().toString());
		System.out.println(obj.getBJSON().toString());
	}

	@Override
	public Map<String, HashMap<String,String>> getUniqueSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getFinalResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setQueryResource(String query) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JSONObject getFinalJSON() {
		// TODO Auto-generated method stub
		return null;
	}
}

