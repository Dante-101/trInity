package webapp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nlp.AskZiggyNLUEndpoint;

import org.json.JSONArray;
import org.json.JSONObject;

import api.controller.AggregationUtility;
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

		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type");
		//response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		String req = request.getParameter("query"); 
		try{
			if(req == null) { 
				//WTF Exception
			}
			else {
				String result = (String)handleAggregations(req); 
				response.getWriter().println(result);
			}
		}catch (Exception e) { 
			System.out.println(e); 
		}
	}
	private Object handleSingleQuery(String req, boolean isAggregation) throws Exception {
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
		ControllerFactory factory = new ControllerFactory();
		if(isAggregation) { 
			return factory.getSetForQuery(action, input);
		}

		return factory.getResultForQuery(action, input);
	}


	private String handleAggregations(String query) throws Exception {
		if(!AggregationUtility.isAggregation(query)) {
			return (String)handleSingleQuery(query,false); 
		}
		System.out.println("IS aggregation");
		List<String> queries = new ArrayList<String>(); 
		List<String> ops = new ArrayList<String>();
		AggregationUtility.getQueriesAndOps(query, queries, ops);
		for(String Q : queries) { 
			System.out.println(Q); 
		}
		for(String op : ops) { 
			System.out.println(op);
		}
		Map<String,HashMap<String,String>> finalSet = new HashMap<String,HashMap<String,String>>();
		finalSet = (Map<String,HashMap<String,String>>)handleSingleQuery(queries.get(0),true);
		for(int i=1;i<queries.size();i++) { 
			String op = ops.get(i-1);
			Map<String,HashMap<String,String>> inputSet = new HashMap<String,HashMap<String,String>>();
			inputSet = (Map<String,HashMap<String,String>>)handleSingleQuery(queries.get(i),true);
			finalSet = aggregateSets(finalSet,inputSet,op);
		}
		
		
		List<HashMap<String,String>> finalResult = new ArrayList<HashMap<String,String>>(); 
		for(Map.Entry<String, HashMap<String,String>> entry : finalSet.entrySet()) { 
			finalResult.add(entry.getValue()); 
		}
		JSONObject jsontoreturn = new JSONObject();
		jsontoreturn.accumulate("values", finalResult);
		return jsontoreturn.toString();
		
	}
	private Map<String,HashMap<String,String>> aggregateSets(Map<String,HashMap<String,String>> set1,
			Map<String,HashMap<String,String>> set2,
			String op) {
		Map<String,HashMap<String,String>> finalSet = new HashMap<String,HashMap<String,String>>(); 
		if(op.equals("and")) {
			for(Map.Entry<String, HashMap<String,String>> entry : set1.entrySet()) {
				String key = entry.getKey(); 
				if(set2.containsKey(key)) {
					finalSet.put(key, entry.getValue()); 
				}
			}

		} else if(op.equals("or")) { 
			for(Map.Entry<String, HashMap<String,String>> entry : set1.entrySet()) {
				String key = entry.getKey(); 
				finalSet.put(key, entry.getValue()); 
			}
			for(Map.Entry<String, HashMap<String,String>> entry : set2.entrySet()) {
				String key = entry.getKey(); 
				finalSet.put(key, entry.getValue()); 
			}
		} else if(op.equals("minus")) {
			for(Map.Entry<String, HashMap<String,String>> entry : set1.entrySet()) {
				String key = entry.getKey(); 
				finalSet.put(key, entry.getValue()); 
			}
			for(Map.Entry<String, HashMap<String,String>> entry : set2.entrySet()) {
				String key = entry.getKey(); 
				if(finalSet.containsKey(key)) { 
					finalSet.remove(key);
				}
			}

		}
		return finalSet;
	}

}