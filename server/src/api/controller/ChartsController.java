package api.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import api.model.SkillsHistogram;


/**
 * @author Nick Freeman <nfreeman@linkedin.com>
 */
public class ChartsController extends AbstractController
{

	private String getCharts(String req) throws Exception {
		String arr[] = req.split(" ");
		SkillsHistogram result = new SkillsHistogram(arr[1],arr[2],arr[3]);
		result.decode();
		return result.getAJSON().toString();
	}
	
	private List<HashMap<String,String>> getChartsSet(String req) throws Exception {
		String arr[] = req.split(" ");
		SkillsHistogram result = new SkillsHistogram(arr[1],arr[2],arr[3]);
		result.decode();
		return new ArrayList<HashMap<String,String>>();
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
