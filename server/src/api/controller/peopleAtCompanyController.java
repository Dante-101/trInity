package api.controller;


import api.model.peopleAtCompanyResult;
import org.json.JSONObject;


/**
 * @author Nick Freeman <nfreeman@linkedin.com>
 */
public class peopleAtCompanyController
{
  // Returns all people at companyName in a JSON String
  public String getPeopleAtCompany(String companyName) {

    peopleAtCompanyResult result = new peopleAtCompanyResult(companyName);
    result.decode();
    return result.getFinalJSON().toString();


  }

}
