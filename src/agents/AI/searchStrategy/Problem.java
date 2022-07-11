package agents.AI.searchStrategy;

import java.util.List;

import agents.AI.searchStrategy.adapters.Action;

public abstract class Problem implements Cloneable{
	
	public abstract boolean isSolved();

	public abstract List<Action> getAvailableActions();
	
	public abstract Problem clone();
	
	public abstract boolean equals(Object o);
	
}
