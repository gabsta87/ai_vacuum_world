package agents.AI;

import java.util.List;

import agents.actuators.Actuator;

public abstract class AgentProgram {

	protected List<Actuator> actuators;
	
	public abstract void executeStep();
	
}
