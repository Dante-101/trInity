package api.controller;

import java.util.HashMap;
import java.util.Map;

import api.model.AbstractResult;

public abstract class  AbstractController {
	public AbstractResult result_;
	public abstract String handle(String query);
	public Map<String,HashMap<String,String>> handleSet(String query) {
		result_.setQueryResource(query);
		result_.decode(); 
		return result_.getUniqueSet();
	}
	public abstract String getIdentifier();
}
