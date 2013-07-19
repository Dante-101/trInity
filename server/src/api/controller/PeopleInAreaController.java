package api.controller;


import java.util.HashMap;
import java.util.List;

import api.model.PeopleAtCompanyResult;
import api.model.PeopleInAreaResult;
import api.model.PeopleStudyAtEducationResult;


/**
 * @author Nick Freeman <nfreeman@linkedin.com>
 */
public class PeopleInAreaController extends AbstractController
{
	PeopleInAreaController() { 
		result_ = new PeopleStudyAtEducationResult(); 
	}
	private String getPeopleInArea(String area) {
		result_.setQueryResource(area);
		result_.decode();
		return result_.getFinalJSON().toString();
	}
	public String handle(String query) { 
		return getPeopleInArea(query);
	}
	public String getIdentifier() { 
		return "QUERYAREA";
	}
}
