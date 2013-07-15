package api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerFactory {
	private static int NUM_CONTROLLERS = 5;
	private AbstractController[] controllers = new AbstractController[NUM_CONTROLLERS];
	public ControllerFactory(){
		controllers[0] = new PeopleAtCompanyController(); 
		controllers[1] = new PeopleInAreaController(); 
		controllers[2] = new PeopleInIndustryController(); 
		controllers[3] = new PeopleStudyAtEducationController();
		controllers[4]  = new ChartsController();
	}
	
	public String getResultForQuery(String action,String input) { 
		AbstractController ctrl = controllers[0];
		for(int i=0;i<NUM_CONTROLLERS;i++) { 
			if(controllers[i].getIdentifier().equalsIgnoreCase(action)) {
				ctrl = controllers[i];
			}
		}
		return ctrl.handle(input);
	}
	public Map<String,HashMap<String,String>> getSetForQuery(String action,String input) { 
		AbstractController ctrl = controllers[0];
		for(int i=0;i<NUM_CONTROLLERS;i++) { 
			if(controllers[i].getIdentifier().equalsIgnoreCase(action)) {
				ctrl = controllers[i];
			}
		}
		return ctrl.handleSet(input); 
	}
}
