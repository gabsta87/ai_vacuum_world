package agents.actuators;

import environment.World;

public abstract class Actuator {
	protected World w;
	
	public Actuator(World w) {
		this.w = w;
	}
	
	public abstract boolean execute();

}