package api.controller;


import api.model.peopleInAreaResult;


/**
 * @author Nick Freeman <nfreeman@linkedin.com>
 */
public class peopleInAreaController
{

  // Returns all people in an Area (San francisco, new york, illinois) in a JSON String
  public String getPeopleInArea(String area) {

    peopleInAreaResult result = new peopleInAreaResult(area);
    result.decode();
    return result.getFinalJSON().toString();


  }

}
