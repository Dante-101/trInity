package api.controller;


import api.model.peopleInAreaResult;


/**
 * @author Nick Freeman <nfreeman@linkedin.com>
 */
public class peopleInAreaController implements AbstractController
{

  // Returns all people in an Area (San francisco, new york, illinois) in a JSON String
  private String getPeopleInArea(String area) {
    peopleInAreaResult result = new peopleInAreaResult(area);
    result.decode();
    return result.getFinalJSON().toString();
  }
  public String getIdentifier() { 
	  return "QUERYAREA";
  }
  public String handle(String query) { 
	  return getPeopleInArea(query);
  }
}
