package api.controller;


import api.model.peopleAtCompanyResult;
import org.json.JSONObject;


/**
 * @author Nick Freeman <nfreeman@linkedin.com>
 */
public class peopleAtCompanyController implements AbstractController
{
  // Returns all people at companyName in a JSON String
  private String getPeopleAtCompany(String companyName) {
	System.out.println(companyName);
    peopleAtCompanyResult result = new peopleAtCompanyResult(companyName);
    result.decode();
    return result.getFinalJSON().toString();
  }
  public String getIdentifier() { 
	  return "QUERYCOMPANY";
  }
  public String handle(String query) { 
	  return getPeopleAtCompany(query);
  }
}
