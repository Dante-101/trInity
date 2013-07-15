package api.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * @author Nick Freeman <nfreeman@linkedin.com>
 */
public class PeopleAtCompanyResult implements AbstractResult
{

	private static final String PROTECTED_RESOURCE_URL = "http://api.linkedin.com/v1/people/~/connections:(formatted-name,headline,picture-url,positions)?format=json";
	private String companyName;
	private List<HashMap<String, String>> finalResult;
	private Map<String,HashMap<String,String>> uniqueSet;
	private JSONObject finalJSON;

	public PeopleAtCompanyResult() {
		this.finalResult = new ArrayList<HashMap<String, String>>();
		this.uniqueSet = new HashMap<String,HashMap<String,String>>();
	}
	public PeopleAtCompanyResult(String companyName) {
		this.companyName = companyName;
		this.finalResult = new ArrayList<HashMap<String, String>>();
		this.uniqueSet = new HashMap<String,HashMap<String,String>>();
	}
	
	
	public void setQueryResource(String companyName) {
		this.companyName = companyName;
	}

	public void decode() {
		try {
			JSONObject json  = ScribeClient.getResponse(PROTECTED_RESOURCE_URL);
			System.out.println(json);
			JSONArray values = json.getJSONArray("values");
			//System.out.println(values.toString());
			for (int i = 0; i < values.length(); i++) {
				HashMap<String, String> toAdd = new HashMap<String, String>();
				JSONObject memberattr = values.getJSONObject(i);
				if (memberattr.has("formattedName")) {
					String formattedName = memberattr.getString("formattedName");
					if (memberattr.has("positions")) {
						JSONObject positions = memberattr.getJSONObject("positions");
						if (positions.has("values")) {

							JSONArray positionsvalues = positions.getJSONArray("values");
							for (int j = 0; j < positionsvalues.length(); j++) {
								if (positionsvalues.getJSONObject(j).has("company")) {
									JSONObject company = positionsvalues.getJSONObject(j).getJSONObject("company");
									if (company.has("name")) {
										String companyname = company.getString("name");
										if (companyname.toLowerCase().contains(this.companyName.toLowerCase())) {
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
						}

					}
				}
			}
			JSONObject jsontoreturn = new JSONObject();
			jsontoreturn.accumulate("values", this.finalResult);
			this.finalJSON = jsontoreturn;
		} catch (Exception e) {
			System.out.println("\n\n\n" + e);
		}

	}
	
	public Map<String,HashMap<String,String>> getUniqueSet() { 
		return uniqueSet;
	}

	public String getCompanyName()
	{
		return companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
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

	public static void main(String args[])
	{
		PeopleAtCompanyResult result = new PeopleAtCompanyResult("linkedin");
		result.decode();
	}

}
