package api.controller;


import java.util.HashMap;
import java.util.List;

import api.model.PeopleInIndustryResult;
import api.model.PeopleStudyAtEducationResult;


/**
 * @author Nick Freeman <nfreeman@linkedin.com>
 */
public class PeopleStudyAtEducationController extends AbstractController
{
	// Gets list of connections studying at schoolname as A JSON String
	PeopleStudyAtEducationController() { 
		result_ = new PeopleStudyAtEducationResult(); 
	}
	private String getPeopleStudyAtEducation(String schoolname) {
		result_.setQueryResource(schoolname);
		result_.decode();
		return result_.getFinalJSON().toString();
	}

	public String handle(String query) { 
		return getPeopleStudyAtEducation(query);
	}
	
	public String getIdentifier() { 
		return "QUERYEDUCATION";
	}


}
