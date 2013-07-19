package api.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public interface AbstractResult {
	public Map<String,HashMap<String,String>> getUniqueSet();
	public List<HashMap<String,String>> getFinalResult(); 
	public void setQueryResource(String query);
	public void decode();
	public JSONObject getFinalJSON();

}
