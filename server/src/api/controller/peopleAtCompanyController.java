package api.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.model.AbstractResult;
import api.model.PeopleAtCompanyResult;


/**
 * @author Nick Freeman <nfreeman@linkedin.com>
 */
public class PeopleAtCompanyController extends AbstractController
{	
	
	PeopleAtCompanyController() { 
		result_ = new PeopleAtCompanyResult();
	}
	// Returns all people at companyName in a JSON String
	private String getPeopleAtCompany(String companyName) {
		//System.out.println(companyName);
		result_.setQueryResource(companyName);
		result_.decode();
		return result_.getFinalJSON().toString();
	}
	public String handle(String query) { 
		return getPeopleAtCompany(query);
	}
	
	public String getIdentifier() { 
		return "QUERYCOMPANY";
	}


}
