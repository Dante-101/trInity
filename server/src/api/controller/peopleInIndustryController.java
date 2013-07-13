package api.controller;


import api.model.peopleInIndustryResult;


/**
 * @author Nick Freeman <nfreeman@linkedin.com>
 */
public class peopleInIndustryController implements AbstractController
{

  // Gets list of connections working in INDUSTRY as A JSON String
  private String getPeopleInIndustry(String industry) {
    peopleInIndustryResult result = new peopleInIndustryResult(industry);
    result.decode();
    return result.getFinalJSON().toString();
  }
  public String getIdentifier() { 
	  return "QUERYINDUSTRY";
  }
  
  public String handle(String query) { 
	  return getPeopleInIndustry(query);
  }

}
