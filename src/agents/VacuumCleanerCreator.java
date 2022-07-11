package agents;

import agents.AI.SimpleReflexAgent;
import agents.AI.SmartAgent;
import environment.World;

public class VacuumCleanerCreator {
	
	public static VacuumCleaner createFullMovementSimpleVC(World w) {
		VacuumCleaner vc = new VacuumCleaner(w);
		w.setVacuumCleaner(vc);
		
		vc.setAgentProgram(new SimpleReflexAgent(vc));
		
		return vc;
	}
	
	public static VacuumCleaner createFullMovementSmartVC(World w) {
		VacuumCleaner vc = new VacuumCleaner(w);
		w.setVacuumCleaner(vc);

		vc.setAgentProgram(new SmartAgent(w));
		
		return vc;
	}
}
