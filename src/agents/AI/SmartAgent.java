package agents.AI;

import java.util.LinkedList;
import java.util.List;

import agents.AI.searchStrategy.DustProblem;
import agents.AI.searchStrategy.Node;
import agents.AI.searchStrategy.adapters.Action;
import agents.AI.searchStrategy.algorithms.Answer;
import agents.AI.searchStrategy.algorithms.Success;
import agents.AI.searchStrategy.algorithms.UniformCostS;
import environment.World;

public class SmartAgent extends AgentProgram {
	private List<Action> solution;
	private World world;
	
	public SmartAgent(World w) {
		this.solution = new LinkedList<Action>();
		this.world = w;
	}
	
	private List<Action> findSolution(World world) {
		DustProblem p = new DustProblem(world);
		Answer a = UniformCostS.getSolution(new Node(p));
		
		if(a.isSuccess()) {
			Success s = (Success) a;
			List<Action> result = new LinkedList<Action>();
			
			for(Node n : s.getSolution()) {
				n.getAction().setEnvironment(p);
				result.add(n.getAction());
			}
			
			return result;
		}else {
			return null;
		}
	}

	@Override
	public void executeStep() {
		if(solution.size() == 0)
			solution = findSolution(world);
			
		solution.remove(0).execute();
	}

}
