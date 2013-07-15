package api.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * @author Nick Freeman <nfreeman@linkedin.com>
 */
public class PeopleInIndustryResult implements AbstractResult
{

	private static final String PROTECTED_RESOURCE_URL = "http://api.linkedin.com/v1/people/~/connections:(formatted-name,headline,picture-url,industry)?format=json";
	private String industry;
	private List<HashMap<String, String>> finalResult;
	private Map<String,HashMap<String,String>> uniqueSet;	
	private JSONObject finalJSON;

	public PeopleInIndustryResult() {
		this.finalResult = new ArrayList<HashMap<String, String>>();
		this.uniqueSet = new HashMap<String,HashMap<String,String>>();
	}
	
	public PeopleInIndustryResult(String industry) {
		this.industry = industry;
		this.finalResult = new ArrayList<HashMap<String, String>>();
		this.uniqueSet = new HashMap<String,HashMap<String,String>>();
	}
	
	public void setQueryResource(String industry) { 
		this.industry = industry;
	}

	public void decode() {
		try {
			JSONObject json  = ScribeClient.getResponse(PROTECTED_RESOURCE_URL);

			JSONArray values = json.getJSONArray("values");
			System.out.println(values.toString() + "\n\n\n\n\n");

			int count = 0;
			for (int i = 0; i < values.length(); i++) {
				HashMap<String, String> toAdd = new HashMap<String, String>();
				JSONObject memberattr = values.getJSONObject(i);
				if (memberattr.has("formattedName")) {
					String formattedName = memberattr.getString("formattedName");
					if (memberattr.has("industry")) {
						String industryobj = memberattr.getString("industry");
						if (industryobj.toLowerCase().replaceAll("\\s","").contains(this.industry.toLowerCase().replaceAll("\\s",""))) {

							HashMap<String, String> resultToAdd = new HashMap<String, String>();
							resultToAdd.put("name", formattedName);
							String headLine = "";
							if (memberattr.has("headline")) {
								headLine = memberattr.getString("headline");
								resultToAdd.put("headline", memberattr.getString("headline"));
							}
							if (memberattr.has("pictureUrl")) {
								resultToAdd.put("pictureUrl", memberattr.getString("pictureUrl"));
							}
							this.finalResult.add(resultToAdd);
							this.uniqueSet.put(formattedName+headLine,resultToAdd); 
						}
					}
				}
			}

			//System.out.println(finalResult.toString());
			JSONObject jsontoreturn = new JSONObject();
			jsontoreturn.accumulate("values", this.finalResult);
			this.finalJSON = jsontoreturn;
			//System.out.println(this.finalJSON.toString());

			//System.out.println(values.toString());
		} catch (JSONException e) {
			System.out.println("\n\n\n" + e);
		}

	}
	
	public Map<String,HashMap<String,String>> getUniqueSet() { 
		return uniqueSet;
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
		return PROTECTED_RESOURCE_URL;
	}

	public JSONObject getFinalJSON()
	{
		return finalJSON;
	}

	public void setFinalJSON(JSONObject finalJSON)
	{
		this.finalJSON = finalJSON;
	}

	public String getIndustry()
	{
		return industry;
	}

	public void setIndustry(String industry)
	{
		this.industry = industry;
	}

	public static void main(String args[])
	{
		PeopleInIndustryResult result = new PeopleInIndustryResult("Computer Software");
		result.decode();
	}

}
