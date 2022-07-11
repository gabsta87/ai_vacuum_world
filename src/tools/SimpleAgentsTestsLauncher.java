package tools;

import agents.VacuumCleaner;
import agents.VacuumCleanerCreator;
import agents.AI.ModelBasedReflexAgent;
import environment.Square;
import environment.World;
import tools.view.DebugPrinter;

public class SimpleAgentsTestsLauncher {

	public static void main(String[] args) {

		DebugPrinter.getInstance().setDebugLevel(DebugPrinter.silent);
		
//		// Creating simple world with 2 squares
//		VacuumCleaner vc = VacuumCleanerCreator.createFullMovementSimpleVC();
//		vc.setAgentProgram(AgentProgramCreator.createSimpleAP(vc));
//		World w = World.WorldCreator.createSimpleWorld();
//		vc.setLocation(getFirstAvailableLocation(w));
//		// Try to clean this simple 2 squares world
//		for(int i = 0; i < 5; i++) {
//			vc.executeStep();
//		DebugPrinter.getInstance().printMessage(w.toString());
//		}
		
		// Creating a much complex 5x5 squares world and test reflex agent's efficiency
		World w55 = World.WorldCreator.create55ComplexWorld();
		VacuumCleaner vc = VacuumCleanerCreator.createFullMovementSimpleVC(w55);
//		vc.setAgentProgram(new SimpleReflexAgent(vc)); // This is the default AgentProgram
		vc.setLocation(getFirstAvailableLocation(w55));
		PerformanceMeasurement pm = new PerformanceMeasurement(w55);
		int timeCount = 0;
		// Try to clean this 5x5 world and compute necessary steps
		while(pm.computeDirtySquares() > 0) {
			vc.executeStep();
			DebugPrinter.getInstance().printMessage(w55.toString());
			timeCount++;
		}
		System.out.println("It took "+timeCount+" steps to clean with Simple Reflex Agent");
		
		// Creating a complex 5x5 squares world and test a model-based agent efficiency
		World w55Bis = World.WorldCreator.create55ComplexWorld();
		VacuumCleaner vc2 = VacuumCleanerCreator.createFullMovementSimpleVC(w55Bis);
		vc2.setAgentProgram(new ModelBasedReflexAgent(vc2));
		vc2.setLocation(getFirstAvailableLocation(w55Bis));
		PerformanceMeasurement pm2 = new PerformanceMeasurement(w55Bis);
		int timeCount2 = 0;
		// Try to clean this 5x5 world and compute necessary steps
		while(pm2.computeDirtySquares() > 0) {
			vc2.executeStep();
			DebugPrinter.getInstance().printMessage(w55Bis.toString());
			timeCount2++;
		}
		System.out.println("It took "+timeCount2+" steps to clean with Model Based Reflex Agent");
		
	}
	
	private static Square getFirstAvailableLocation(World w) {
		for(int i = 0; i < w.getGrid().length; i++) {
			for(int j = 0; j < w.getGrid()[i].length; j++) {
				if(w.getGrid()[i][j] != null)
					return w.getGrid()[i][j];
			}
		}
		return null;
	}

}


