package agents.AI;

import java.util.LinkedList;
import java.util.List;

import actions.Action;
import agents.VacuumCleaner;
import environment.Direction;
import tools.view.DebugPrinter;

/**
 * This agent doesn't come back on its last location when possible
 * @author Formation
 *
 */
public class ModelBasedReflexAgent extends AgentProgram {

	private VacuumCleaner vc;
	private List<Direction> availableDirections;
	private List<Action> actionsHistory;

	public ModelBasedReflexAgent(VacuumCleaner vc) {
		this.vc = vc;
		this.actionsHistory = new LinkedList<Action>();
	}

	@Override
	public void executeStep() {
		if (vc.isDirty()) {
			vc.clean();
			actionsHistory.add(0, Action.clean);
			DebugPrinter.getInstance().printMessage("Cleaning");
		} else {
			availableDirections = vc.getAvailableDirections();
			if (actionsHistory.size() > 1 && availableDirections.size() > 1) {
				
				Action lastAction = actionsHistory.get(0);
				
				if(lastAction == Action.clean && actionsHistory.size() > 2) {
					lastAction = actionsHistory.get(1);
				}
				
				switch (lastAction) {
				case moveDown:
					availableDirections.remove(Direction.up);
					break;
				case moveUp:
					availableDirections.remove(Direction.down);
					break;
				case moveRight:
					availableDirections.remove(Direction.left);
					break;
				case moveLeft:
					availableDirections.remove(Direction.right);
					break;
				default:
					break;
				}
			}

			int choice = 0;
			if (availableDirections.size() == 1) {
				choice = 0;
			} else {
				// search for dirt in nearby places here if possible
				choice = (int) (Math.random() * availableDirections.size());
			}
			vc.move(availableDirections.get(choice));
			setHistory(availableDirections.get(choice));
			DebugPrinter.getInstance().printMessage("Moving " + availableDirections.get(choice));
		}
	}

	private void setHistory(Direction direction) {
		switch (direction) {
		case down:
			actionsHistory.add(0, Action.moveDown);
			break;
		case up:
			actionsHistory.add(0, Action.moveUp);
			break;
		case right:
			actionsHistory.add(0, Action.moveRight);
			break;
		case left:
			actionsHistory.add(0, Action.moveLeft);
			break;
		}
	}
}
