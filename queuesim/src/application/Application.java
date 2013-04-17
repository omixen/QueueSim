package application;

import domain.Simulation;
/**
 *
 */
public class Application {
	
	
	
	public static void main(String[] args) {
		 String config = "interpreter? / xml?";
                 
                 domain.Simulation sim = new Simulation(config);
                 sim.build();
                 sim.run();
                 
		
		
		
	}
	
	
	
}
