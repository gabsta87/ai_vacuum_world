package agents.AI.searchStrategy;

import java.util.LinkedList;
import java.util.List;

import agents.AI.searchStrategy.adapters.Action;

public class Node implements Comparable<Node>{
	private Node parent;
	private Node[] children;
	private Action action;
	private Problem problem;
	private double cost;
	
	/**
	 * Creates a root node
	 * @param p
	 */
	public Node(Problem p) {
		this.parent = null;
		this.action = null;
		this.problem = p;
		this.cost = 0;
	}
	
	public Node(Node parent, Problem p, Action a) {
		this.parent = parent;
		this.problem = p;
		this.action = a;
		this.action.setEnvironment(p);
		this.cost = parent.getCost()+action.getCost();
		a.execute();
	}
	
	/**
	 * @return The children of this node, sorted by node's cost
	 */
	public Node[] exploreNode(){
		List<Action> actionsAllowed = problem.getAvailableActions();
		
		children = new Node[actionsAllowed.size()];
		
		for(int i = 0; i<actionsAllowed.size(); i++) {
			children[i] = new Node(this,problem.clone(),actionsAllowed.get(i));
		}
		return children;
	}
	
	public Action getAction() {
		return action;
	}
	
	/**
	 * @return The node's path that leads to this node
	 */
	public List<Node> getSolution(){
		Node currentNode = this;
		List<Node> solution = new LinkedList<Node>();
		
		solution.add(this);
		
		while(currentNode.parent != null) {
			currentNode = currentNode.parent;
			solution.add(currentNode);
		}
		
		return solution;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public boolean isRoot() {
		return parent == null;
	}
	
	public boolean isSolved() {
		return problem.isSolved();
	}
	
	public double getCost() {
		return cost;
	}
	
	public Problem getProblem() {
		return problem;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if(!this.getClass().isInstance(o))
			return false;
		
		return problem.equals(((Node)o).problem) && cost == ((Node)o).cost;
	}
	
	@Override
	public String toString() {
		String result = "";
		if(action != null)
			result += action.toString()+",";
		return result+"Cost:"+cost+System.lineSeparator()+problem.toString();
	}

	@Override
	public int compareTo(Node o) {
		return Double.compare(getCost(), o.getCost());
	}
	
}
