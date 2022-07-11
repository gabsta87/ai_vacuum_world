package agents.AI;

import java.util.List;

import agents.VacuumCleaner;
import environment.Direction;
import tools.view.DebugPrinter;

/**
 * Simple reflex agent
 * @author Formation
 *
 */
public class SimpleReflexAgent extends AgentProgram{

	private VacuumCleaner vc;
	private List<Direction> availableDirections;
	
	public SimpleReflexAgent(VacuumCleaner vc) {
		this.vc = vc;
	}
	
	@Override
	public void executeStep() {
		// TODO vérifier le fonctionnement
		if(vc.isDirty()) {
			vc.clean();
			DebugPrinter.getInstance().printMessage("Cleaning");
		}else {
			availableDirections = vc.getAvailableDirections();
			if(availableDirections.size() == 1) {
				vc.move(availableDirections.get(0));
				DebugPrinter.getInstance().printMessage("Moving "+availableDirections.get(0));
			}else {
				// search for dirt in nearby places here if possible
				int choice = (int) (Math.random()*availableDirections.size());
				vc.move(availableDirections.get(choice));
				DebugPrinter.getInstance().printMessage("Moving "+availableDirections.get(choice));
			}
		}
	}
}
