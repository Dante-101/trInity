package api.model;

import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.model.*;
import org.scribe.oauth.*;

public class ScribeClient {
	private static final String PROTECTED_RESOURCE_URL = "http://api.linkedin.com/v1/people/~:(first-name,last-name,headline,industry,positions,educations,num-recommenders,connections,skills)?format=json";
	private static final String API_KEY = "xy6upwkl4yrd";
	private static final String API_SECRET = "9ur6Www3i7ShnKv7";
	private static final Token ACCESS_TOKEN = new Token("e44a170a-d830-4a6d-b8a4-156dcc85ec2b" , "4a289fc3-8753-47a8-9c31-485266a2e900");

	public static Token getAccessToken(OAuthService service) {

		Scanner in = new Scanner(System.in);

		System.out.println("=== LinkedIn's OAuth Workflow ===");
		System.out.println();

		// Obtain the Request Token
		System.out.println("Fetching the Request Token...");
		Token requestToken = service.getRequestToken();
		System.out.println("Got the Request Token!");
		System.out.println();

		System.out.println("Now go and authorize Scribe here:");
		System.out.println(service.getAuthorizationUrl(requestToken));
		System.out.println("And paste the verifier here");
		System.out.print(">>");
		Verifier verifier = new Verifier(in.nextLine());
		Token tkn = service.getAccessToken(requestToken, verifier);
		System.out.println(tkn);

		return tkn;
	}
	
	public static JSONObject getResponse(String url)
	{
		JSONObject output=null;

		OAuthService service = new ServiceBuilder().provider(LinkedInApi.class)
		// CiEgwWDkA5BFpNrc0RfGyVuSlOh4tig5kOTZ9q97qcXNrFl7zqk-Ts7DqRGaKDCV
				.apiKey(API_KEY)
				// dhho4dfoCmiQXrkw4yslork5XWLFnPSuMR-8gscPVjY4jqFFHPYWJKgpFl4uLTM6
				.apiSecret(API_SECRET).build();
	   OAuthRequest request = new OAuthRequest(Verb.GET,
				url);
		service.signRequest(ACCESS_TOKEN, request);
		Response response = request.send();
	    try {
			output = new JSONObject(response.getBody());
		} catch (JSONException e) {
			 e.printStackTrace();
		}
	  return output;
	}

	
	public static void main(String args[])
	{
		System.out.println(getResponse(PROTECTED_RESOURCE_URL).toString());
	}
	
}