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
	public static Token ACCESS_TOKEN = new Token("1d5615a7-ce7e-4898-b179-1bd6571d634d", "5ee45661-c9a2-4564-9b6a-3309f8643e01");

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
				.apiKey(API_KEY)
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
	public static JSONObject getResponse(String url,Token token)
	{
		JSONObject output=null;
		OAuthService service = new ServiceBuilder().provider(LinkedInApi.class)
				.apiKey(API_KEY)
				.apiSecret(API_SECRET).build();
		OAuthRequest request = new OAuthRequest(Verb.GET,
				url);
		service.signRequest(token, request);
		Response response = request.send();
		try {
			System.out.println(response.getBody());
			output = new JSONObject(response.getBody());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return output;
	}



	public static void main(String args[])
	{
		//getResponse(PROTECTED_RESOURCE_URL);
		//System.out.println(getResponse(PROTECTED_RESOURCE_URL).toString());
		
	}

}