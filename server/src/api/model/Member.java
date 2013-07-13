package api.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class Member {

	List<Skill> skills = new ArrayList<Skill>();
	List<Connection> connections = new ArrayList<Connection>();
	List<Position> positions = new ArrayList<Position>();
	List<Education> educations = new ArrayList<Education>();
	long memberId;
	String firstName;
	String lastName;
	String headLine;

	public Member(String url)
    {
    	JSONObject memberJson = ScribeClient.getResponse(url);
    	decode(memberJson);
    	
    	
    	
    	
    }

	private void decode(JSONObject json) {
		
		//JSONObject json ;
        try {
			 
			 this.firstName = json.getString("firstName");
			 this.lastName = json.getString("lastName");
			 this.headLine = json.getString("headLine");
			 
		} catch (Exception e) {
			
			e.printStackTrace();
		}
        
	}
	
	
	

}
