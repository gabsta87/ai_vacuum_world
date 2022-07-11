package tools;

import agents.AI.searchStrategy.DustProblem;
import agents.AI.searchStrategy.Node;
import agents.AI.searchStrategy.algorithms.Answer;
import agents.AI.searchStrategy.algorithms.Success;
import agents.AI.searchStrategy.algorithms.UniformCostS;
import environment.World.WorldCreator;

public class LauncherUCS {

	public static void main(String[] args) {
		DustProblem problem = new DustProblem(WorldCreator.create33World());
		Node root = new Node(problem);
		
		Answer a = UniformCostS.getSolution(root);
		
		examineSolution(problem,a,UniformCostS.getName());
		
		DustProblem problem2 = new DustProblem(WorldCreator.create44World());
		Node root2 = new Node(problem2);
		
		Answer a2 = UniformCostS.getSolution(root2);
		
		examineSolution(problem2, a2,UniformCostS.getName());
		
		DustProblem problem3 = new DustProblem(WorldCreator.create55World());
		Node root3 = new Node(problem3);
		
		Answer a3 = UniformCostS.getSolution(root3);
		
		examineSolution(problem3, a3,UniformCostS.getName());
	}

	private static void examineSolution(DustProblem p, Answer a, String algorithmName) {
		int worldSize = p.getWorld().getGrid().length;
		if(a.isSuccess()) {
			System.out.println(algorithmName);
			System.out.println("Solution for "+worldSize+"x"+worldSize+" world found in "+((Success)a).getSolution().size() +" steps");
			for(Node n : ((Success)a).getSolution()) {
				System.out.println(n);
			}
		}else {
			System.out.println("No solution found");
		}
	}

}
