package agents.AI.searchStrategy.adapters;

import agents.AI.searchStrategy.DustProblem;
import agents.AI.searchStrategy.Problem;

public abstract class Action {
	protected DustProblem problem;
	
	public abstract boolean execute();
	
	public abstract boolean setEnvironment(Problem p);
	
	public abstract double getCost();
	
	public abstract String toString();
}
