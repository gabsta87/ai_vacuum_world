package agents.AI.searchStrategy.adapters;

import agents.AI.searchStrategy.DustProblem;
import agents.AI.searchStrategy.Problem;
import environment.Direction;

public class Move extends Action {
	private Direction d;

	public Move(Problem p,Direction d) {
		this.problem = (DustProblem) p; 
		this.d = d;
	}
	
	@Override
	public boolean execute() {
		return problem.getVacuumCleaner().move(d);
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
		return 1;
	}

	@Override
	public String toString() {
		return "Move "+d;
	}

}
