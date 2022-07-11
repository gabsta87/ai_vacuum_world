package agents.sensors;

import actions.Cleaning;
import agents.VacuumCleaner;
import environment.World;

public class CleaningSensor extends Sensor {
	private double dirtLevelAutorized = 0.5;

	public CleaningSensor(VacuumCleaner vc,World w) {
		super(vc,w);
	}

	public boolean isDirty() {
		return Cleaning.isDirty(vc);
	}

	public double getDirtLevelAutorized() {
		return dirtLevelAutorized;
	}

}
