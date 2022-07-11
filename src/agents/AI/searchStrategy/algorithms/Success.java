package agents.AI.searchStrategy.algorithms;

import java.util.LinkedList;
import java.util.List;

import agents.AI.searchStrategy.Node;

public class Success extends Answer{
	private List<Node> solution;
	
	public Success(Node n) {
		solution = new LinkedList<Node>();
		
		Node currentNode = n;
		
		do {
			solution.add(0,currentNode);
			currentNode = currentNode.getParent();
		}while(!currentNode.isRoot());
		
	}
	
	public List<Node> getSolution() {
		return solution;
	}
	
	public double getCost() {
//		double cost = 0;
//		for(int i = 0; i < solution.size(); i++) {
//			cost += solution.get(i).getCost();
//		}
//		return cost;
		return solution.get(solution.size()-1).getCost();
	}

	@Override
	public boolean isSuccess() {
		return true;
	}
	
	@Override
	public String toString() {
		return ""+solution;
	}
}