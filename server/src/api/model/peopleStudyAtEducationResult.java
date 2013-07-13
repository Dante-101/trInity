package api.model;

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
  public static String returnConnections(String schoolName) {

    try {

      JSONObject array;
      String name = encodeName(schoolName);
      //System.out.println("Name="+name);

      String url = "http://api.linkedin.com/v1/people-search:(people:(formatted-name,picture-url,headline))?school-name="+name+"&format=json";

      JSONObject toreturn = ScribeClient.getResponse(url);
      if (toreturn.has("people")) {
        //System.out.println(toreturn.getJSONObject("people").toString());
        return toreturn.getJSONObject("people").toString();
      } else {
        return "\"values\":[]";
      }
    } catch (JSONException e) {
      System.out.println("\n\n\npeopleAtEducation error: " + e);
      return "\"values\":[]";
    }
  }

  public static void main(String[] args) throws JSONException {

    returnConnections("UC Berkeley");

  }

}
