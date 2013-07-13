package webapp;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nlp.AskZiggyNLUEndpoint;

import org.json.JSONArray;
import org.json.JSONObject;

import api.controller.ControllerFactory;

public class HelloServlet extends HttpServlet
{
	private String greeting="Hello World";
	private String language = "en";
	final static AskZiggyNLUEndpoint nlpLib = new AskZiggyNLUEndpoint(); 
	public HelloServlet(){}
	public HelloServlet(String greeting)
	{
		this.greeting=greeting;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.addHeader("Access-Control-Allow-Origin", "http://localhost");
		response.addHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type");
		//response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		String req = request.getParameter("query"); 
		try{
			if(req == null) { 
				//WTF Exception
			}else if(req.contains("charts"))
      {
         ControllerFactory.getResultForQuery("Charts",req);
      }
      else {
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
				String result = ControllerFactory.getResultForQuery(action, input);
				response.getWriter().println(result);
			}
		}catch (Exception e) { 
			System.out.println(e); 
		}
	}
}