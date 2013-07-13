package api.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.json.*;
import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.model.*;
import org.scribe.oauth.*;
import java.util.LinkedList;

public class peopleStudyAtEducationResult {
  private static final String EDUCATION_URL =
      "http://api.linkedin.com/v1/people/~:(connections)?format=json";
  private String education;

  public List<HashMap<String, String>> getFinalResult()
  {
    return finalResult;
  }

  public void setFinalResult(List<HashMap<String, String>> finalResult)
  {
    this.finalResult = finalResult;
  }

  private List<HashMap<String, String>> finalResult;

  public JSONObject getFinalJSON()
  {
    return finalJSON;
  }

  public void setFinalJSON(JSONObject finalJSON)
  {
    this.finalJSON = finalJSON;
  }

  private JSONObject finalJSON;

  public peopleStudyAtEducationResult(String education) {
    this.education = education;
    this.finalResult = new ArrayList<HashMap<String, String>>();
  }

  public static List<String> getURLList() throws JSONException
  {
    List<String> ID = new LinkedList<String>();
    JSONObject jsonObj = ScribeClient.getResponse(EDUCATION_URL);
    JSONObject connections = jsonObj.getJSONObject("connections");
    System.out.println("Number of connections parsed ="+connections.getInt("_total"));
    JSONArray values = connections.getJSONArray("values");
    //System.out.println("Values="+values.length());

    for(int i=0;i<values.length();i++) {
      String id = values.getJSONObject(i).getString("id");//.getJSONObject("apiStandardProfileRequest");//.getString("url");
      //System.out.println(id.toString());
      ID.add(id);

    }
    return ID;
  }

  private static String encodeName(String url) {

    String new_str = url.replaceAll(" ", "%20");
    return new_str;
  }
  public void decode() {

    try {

      JSONObject array;
      String name = encodeName(this.education);
      //System.out.println("Name="+name);

      String url = "http://api.linkedin.com/v1/people-search:(people:(formatted-name,picture-url,headline))?school-name="+name+"&format=json";

      JSONObject toreturn = ScribeClient.getResponse(url);
      if (toreturn.has("people")) {
        //System.out.println(toreturn.getJSONObject("people").toString());
        JSONObject json = toreturn.getJSONObject("people");
        if (json.has("values")) {
          JSONArray values = json.getJSONArray("values");

          for (int i = 0; i < values.length(); i++) {
            HashMap<String, String> toAdd = new HashMap<String, String>();
            JSONObject memberattr = values.getJSONObject(i);
            if (memberattr.has("formattedName")) {
              String formattedName = memberattr.getString("formattedName");
              toAdd.put("name", formattedName);
            }
            if (memberattr.has("pictureUrl")) {
              String pictureUrl = memberattr.getString("pictureUrl");
              toAdd.put("pictureUrl", pictureUrl);
            }
            if (memberattr.has("headline")) {
              toAdd.put("headline", memberattr.getString("headline"));
            }
            this.finalResult.add(toAdd);
          }

          //System.out.println(finalResult.toString());
          JSONObject jsontoreturn = new JSONObject();
          jsontoreturn.accumulate("values", this.finalResult);
          this.finalJSON = jsontoreturn;
          //System.out.println(this.finalJSON.toString());


        }
        //return toreturn.getJSONObject("people").toString().replaceAll("formattedName", "name");
      }
    } catch (JSONException e) {
      System.out.println("\n\n\npeopleAtEducation error: " + e);
    }
  }

  public static void main(String[] args) throws JSONException {

    peopleStudyAtEducationResult result = new peopleStudyAtEducationResult("UC Berkeley");
    result.decode();
    System.out.println(result.getFinalJSON().toString());

  }

}
