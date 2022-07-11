package agents.AI.searchStrategy.algorithms;

import java.util.LinkedList;
import java.util.List;

import agents.AI.searchStrategy.Node;

public class UniformCostS {

	public static Answer getSolution(Node root){
		if(root.isSolved())
			return new Success(root);
		
		Node actualNode = root;
		List<Node> frontier = new LinkedList<Node>();
		for(Node n: root.exploreNode()) {
			insertFrontier(n, frontier);
		}
		List<Node> explored = new LinkedList<Node>();
		
		while(true) {
			if(frontier.isEmpty())
				return new Failure();

			if(actualNode.isSolved())
				return new Success(actualNode);
			
			actualNode = frontier.remove(0);
			
//			if(actualNode.isSolved())
//				return new Success(actualNode);
			
			if(!explored.contains(actualNode))
				explored.add(actualNode);
			
			for (int i = 0; i < actualNode.exploreNode().length; i++) {
				Node child = actualNode.exploreNode()[i];
				if(!explored.contains(child) || !frontier.contains(child)) {
					insertFrontier(child,frontier);
				}else if(frontier.contains(child)) {
					int j = frontier.indexOf(child);
					if (child.getCost() < frontier.get(j).getCost()) {
						frontier.remove(j);
						frontier.add(j,child);
					}
				}
			}
		}
	}
	

	/**
	 * Inserts the node at the right place so the frontier stays sorted
	 * @param node
	 */
	private static void insertFrontier(Node node, List<Node> frontier) {
		if(frontier.contains(node)) {
			Node oldOne = frontier.get(frontier.indexOf(node));
			if(oldOne.compareTo(node) > 0)
				System.out.println("Old one cheaper");
			return;
		}
		frontier.add(node);
		frontier.sort(null);
	}


	public static String getName() {
		return "Uniform Cost Search";
	}
}
