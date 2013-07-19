package api.controller;


import java.util.HashMap;
import java.util.List;

import api.model.PeopleInAreaResult;
import api.model.PeopleInIndustryResult;
import api.model.PeopleStudyAtEducationResult;


/**
 * @author Nick Freeman <nfreeman@linkedin.com>
 */
public class PeopleInIndustryController extends AbstractController
{
	PeopleInIndustryController() { 
		result_ = new PeopleStudyAtEducationResult(); 
	}
	// Gets list of connections working in INDUSTRY as A JSON String
	private String getPeopleInIndustry(String industry) {
		result_.setQueryResource(industry);
		result_.decode();
		return result_.getFinalJSON().toString();
	}
	public String handle(String query) { 
		return getPeopleInIndustry(query);
	}

	public String getIdentifier() { 
		return "QUERYINDUSTRY";
	}
}
