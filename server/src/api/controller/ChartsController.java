package api.controller;


import api.model.SkillsHistogram;
import api.model.peopleInAreaResult;


/**
 * @author Nick Freeman <nfreeman@linkedin.com>
 */
public class ChartsController    implements AbstractController
{

  private String getCharts(String req) throws Exception {
    String arr[] = req.split(" ");
    SkillsHistogram result = new SkillsHistogram(arr[1],arr[2],arr[3]);
    result.decode();
    return result.getAJSON().toString();
  }
  public String getIdentifier() {
    return "Charts";
  }
  public String handle(String query)  {


    try{


    return getCharts(query);
    }
    catch(Exception e)
    {
      e.printStackTrace();
  }
   return null;
  }


}
