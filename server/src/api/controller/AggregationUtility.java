package api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AggregationUtility {
	public static Set<String> AggregationOps = new HashSet<String>(); 
	static { 
		AggregationOps.add("and"); 
		AggregationOps.add("or"); 
		AggregationOps.add("minus");
	}
	public static Map<String,String> SupportedAggregationMap = new HashMap<String,String>();
	static { 
		SupportedAggregationMap.put(" AND ","and");
		SupportedAggregationMap.put(" and ","and");
		SupportedAggregationMap.put(" OR ","or");
		SupportedAggregationMap.put(" or ","or");
		SupportedAggregationMap.put(" minus ","minus");
		SupportedAggregationMap.put(" MINUS ","minus");
	}
	
	public static boolean isAggregation(String query) {
		Set<Map.Entry<String, String>> entries = SupportedAggregationMap.entrySet();
		for(Map.Entry<String, String> entry : entries) { 
			if(query.contains(entry.getKey())) {
				return true;
			}
		}
		return false;
	}
	public static void getQueriesAndOps(String query,List<String> queries, List<String> ops) { 
		Map<Integer,String> posOpMap = new TreeMap<Integer,String>(); 
		for(Map.Entry<String, String> entry : SupportedAggregationMap.entrySet()) { 
			String op = entry.getKey(); 
			String Q = query; 
			int count = 0;
			while(Q.contains(op)) { 
				int index = Q.indexOf(op);
				count+=index;
				Q = Q.substring(index+op.length());
				posOpMap.put(count,op); 
				count+=op.length();
			}
		}
		int start = 0;
		for(Map.Entry<Integer,String> entry : posOpMap.entrySet()) {
			queries.add(query.substring(start, entry.getKey()));
			ops.add(SupportedAggregationMap.get(entry.getValue()));
			start=(entry.getKey()+entry.getValue().length());
		}
		queries.add(query.substring(start,query.length()));
	}
	public static void main(String args[]) { 
		List<String> queries = new ArrayList<String>(); 
		List<String> ops = new ArrayList<String>();  
		String query = "my friends who live in and go to or work at amazon and are male";
		AggregationUtility.getQueriesAndOps(query, queries, ops);
	}
}
