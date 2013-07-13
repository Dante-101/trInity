package api.controller;


import api.model.peopleInIndustryResult;


/**
 * @author Nick Freeman <nfreeman@linkedin.com>
 */
public class peopleInIndustryController
{

  // Gets list of connections working in INDUSTRY as A JSON String
  public String getPeopleInIndustry(String industry) {
    peopleInIndustryResult result = new peopleInIndustryResult(industry);
    result.decode();
    return result.getFinalJSON().toString();
  }

}
