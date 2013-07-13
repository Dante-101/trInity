package webapp;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nlp.AskZiggyNLUEndpoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import api.controller.ControllerFactory;
import api.model.ScribeClient;


public class HelloHandler extends HttpServlet
{
	private final static String REQ_PARAMETER = "query";
	final static AskZiggyNLUEndpoint nlpLib = new AskZiggyNLUEndpoint(); 

	/* public void handle(String target,Request baseRequest,HttpServletRequest request,HttpServletResponse response) 
        throws IOException, ServletException
    {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        System.out.println(request.getParameter("name"));

        Cookie[] cookies = request.getCookies();

        System.out.println(cookies.length);
        Map<String,String> vals = request.getParameterMap();
        for (Map.Entry entry : vals.entrySet()) {
        	System.out.println(entry.getKey()+" "+entry.getValue());
        }
        response.getWriter().println("<h1>Hello World</h1>");
    }*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		Cookie[] cookies = request.getCookies();
		System.out.println(cookies.length);
		for(int i=0;i<cookies.length;i++) {
			System.out.println(cookies[i].getName());
		}

		if(cookies.length!=0){

			try {
				JSONObject cookie = new JSONObject(URLDecoder.decode(cookies[0].getValue(),"UTF-8"));

				String accessToken = cookie.getString("access_token");

				String access_token_url = "https://api.linkedin.com/uas/oauth/accessToken";

				String API_KEY = "xy6upwkl4yrd";
				String API_SECRET = "9ur6Www3i7ShnKv7";


				OAuthService service = new ServiceBuilder()
				.provider(LinkedInApi.class)
				.apiKey(API_KEY)
				.apiSecret(API_SECRET)
				.build();

				String AuthTokenFromJS = accessToken;

				Token token = new Token("","");

				OAuthRequest oRequest = new OAuthRequest(Verb.POST, access_token_url + "?xoauth_oauth2_access_token=" + AuthTokenFromJS);
				service.signRequest(token, oRequest);

				Response oResponse = oRequest.send();

				String oauth_token="";
				String oauth_token_secret="";

				String[] strTokens = oResponse.getBody().split("&");
				for(String str : strTokens){
					String[] oAuthToken = str.split("=");
					if(oAuthToken[0].equals("oauth_token")){
						oauth_token = oAuthToken[1];
					} else if(oAuthToken[0].equals("oauth_token_secret")){
						oauth_token_secret = oAuthToken[1];
					}
				}

				Token oauth1_0aToken = new Token(oauth_token,oauth_token_secret);
				//ScribeClient.ACCESS_TOKEN = oauth1_0aToken;
				String req = request.getParameter(REQ_PARAMETER); 
				if(req == null) { 
					//WTF Exception
				} else { 
					JSONObject nlpJson = new JSONObject(nlpLib.getZiggyResponse(req));
					System.out.println(nlpJson);
					JSONArray array  = (JSONArray)nlpJson.get("result");
					JSONObject subResult = array.getJSONObject(0);
					Iterator<?> keys = subResult.keys();
					String action = "";
					String input = "";
					while(keys.hasNext()) {
						String key = (String)keys.next();
						if(key.equals("ACTION")) { 
							action = ((JSONObject)subResult.get(key)).getString("value");
						} else { 
							input = ((JSONArray)subResult.get(key)).getJSONObject(0).getString("input");
						}
					}
					/*
					JSONArray retJsonArr = (JSONArray)nlpJson.get("results");
					String action = ((JSONObject)retJsonArr.getJSONObject(0).get("ACTION")).getString("value");
					String  = ((JSONObject)retJsonArr.getJSONObject(0).get("ACTION")).getString("value");
					 */
					String result = ControllerFactory.getResultForQuery(action, input);
					response.getWriter().println(result);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

	}
}