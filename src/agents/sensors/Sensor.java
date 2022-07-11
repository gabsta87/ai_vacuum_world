package agents.sensors;

import agents.VacuumCleaner;
import environment.World;

public abstract class Sensor {
	protected VacuumCleaner vc;
	protected World w;
	
	public Sensor(VacuumCleaner vc, World w) {
		this.vc = vc;
		this.w = w;
	}
	
}