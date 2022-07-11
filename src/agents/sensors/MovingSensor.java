package agents.sensors;

import java.util.LinkedList;
import java.util.List;

import agents.VacuumCleaner;
import environment.Direction;
import environment.World;

/**
 * This sensor has perfect information about the environment and perfectly knows if a 
 * movement is allowed
 * @author Formation
 *
 */
public class MovingSensor extends Sensor{
	
	public MovingSensor(VacuumCleaner vc, World w) {
		super(vc,w);
	}

	public List<Direction> getAvailableDirections() {
		List<Direction> result = new LinkedList<Direction>();
		
		for (Direction direction : Direction.values()) {
			if(w.isMoveAvailable(direction)) {
				result.add(direction);
			}
		}
		
		return result;
	}

}
