package agents.actuators;

import environment.Direction;
import environment.World;

public class MovingTool extends Actuator {
	private Direction direction;
	
	public MovingTool(World w) {
		super(w);
	}
	
	public void setOrientation(Direction direction) {
		this.direction = direction;
	}
	
	public Direction getOrientation() {
		return this.direction;
	}
	
	@Override
	public boolean execute() {
		return w.move(direction);
	}

}
