package agents.AI.searchStrategy.adapters;

import agents.AI.searchStrategy.DustProblem;
import agents.AI.searchStrategy.Problem;

public class Clean extends Action {
	
	public Clean(Problem p) {
		this.problem = (DustProblem) p;
	}
	
	@Override
	public boolean execute() {
		if(problem == null)
			return false;
//		c.cleanSquare(problem.getVacuumCleaner());
		problem.getVacuumCleaner().clean();
		return true;
	}

	@Override
	public boolean setEnvironment(Problem p) {
		if(p.getClass() == DustProblem.class) {
			this.problem = (DustProblem) p;
			return true;
		}else {
			return false;
		}
	}

	@Override
	public double getCost() {
		return -1.5;
	}

	@Override
	public String toString() {
		return "Clean";
	}

}
