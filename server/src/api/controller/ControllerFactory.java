package api.controller;

public class ControllerFactory {
	private static int NUM_CONTROLLERS = 5;
	private static AbstractController[] controllers = new AbstractController[NUM_CONTROLLERS];
	static { 
		controllers[0] = new peopleAtCompanyController(); 
		controllers[1] = new peopleInAreaController(); 
		controllers[2] = new peopleInIndustryController(); 
		controllers[3] = new peopleStudyAtEducationController();
    controllers[4]  = new ChartsController();
	}
	public static String getResultForQuery(String action,String input) { 
		AbstractController ctrl = controllers[0];
		for(int i=0;i<NUM_CONTROLLERS;i++) { 
			if(controllers[i].getIdentifier().equalsIgnoreCase(action)) {
				ctrl = controllers[i];
			}
		}
		return ctrl.handle(input);
	}
}
