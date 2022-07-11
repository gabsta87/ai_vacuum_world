package agents.actuators;

import environment.World;

public class CleaningTool extends Actuator {
	private double effectiveness;
	
	public CleaningTool(World w,double effectiveness) {
		super(w);
		this.effectiveness = effectiveness;
	}
	
	public double getEffectiveness() {
		return effectiveness;
	}

	@Override
	public boolean execute() {
		w.getVacuumCleaner().clean();
		return true;
	}

}
