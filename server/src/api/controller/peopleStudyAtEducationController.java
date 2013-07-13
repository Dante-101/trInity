package api.controller;


import api.model.peopleStudyAtEducationResult;


/**
 * @author Nick Freeman <nfreeman@linkedin.com>
 */
public class peopleStudyAtEducationController implements AbstractController
{
  // Gets list of connections studying at schoolname as A JSON String
  private String getPeopleStudyAtEducation(String schoolname) {
    return peopleStudyAtEducationResult.returnConnections(schoolname);
  }
  
  public String getIdentifier() { 
	  return "QUERYEDUCATION";
  }
  
  public String handle(String query) { 
	  return getPeopleStudyAtEducation(query);
  }

}
