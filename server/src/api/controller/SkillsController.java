package api.controller;
import api.model.Skills;
import org.json.JSONObject;
public class SkillsController {
	public String getSkills(String skills) {

	    Skills result = new Skills(skills);
	    result.decode();
	    return result.getFinalJSON().toString();


	  }
}
