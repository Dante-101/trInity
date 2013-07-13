package api.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class Skills {

	private String skills;
	private static String URL = "http://api.linkedin.com/v1/people-search:(people:(formatted-name,headline,picture-url))?format=json&start=0&count=25";
	private List<HashMap<String, String>> finalResult;
	private JSONObject finalJSON;
	
	public Skills(String skills) {
		this.skills = skills;
		this.finalResult = new ArrayList<HashMap<String, String>>();
	}
	
	public void decode() {
		URL = URL + "&keywords="+this.skills;
		setFinalJSON(ScribeClient.getResponse(URL));
		
	}
	
	public String getSkills()
	  {
	    return skills;
	  }

	  public void setSkills(String skills)
	  {
	    this.skills = skills;
	  }

	  public List<HashMap<String, String>> getFinalResult()
	  {
	    return finalResult;
	  }

	  public void setFinalResult(List<HashMap<String, String>> finalResult)
	  {
	    this.finalResult = finalResult;
	  }

	  public static String getProtectedResourceUrl()
	  {
	    return URL;
	  }

	  public JSONObject getFinalJSON()
	  {
	    return finalJSON;
	  }

	  public void setFinalJSON(JSONObject finalJSON)
	  {
	    this.finalJSON = finalJSON;
	  }

	  public static void main(String args[])
	  {
	    Skills result = new Skills("java");
	    result.decode();
	    System.out.println(result.getFinalJSON().toString());
	  }
	
}
