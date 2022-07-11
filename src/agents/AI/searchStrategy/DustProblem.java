package agents.AI.searchStrategy;

import java.util.LinkedList;
import java.util.List;

import agents.VacuumCleaner;
import agents.VacuumCleanerCreator;
import agents.AI.searchStrategy.adapters.Action;
import agents.AI.searchStrategy.adapters.Clean;
import agents.AI.searchStrategy.adapters.Move;
import environment.Coordinates;
import environment.Direction;
import environment.World;
import tools.PerformanceMeasurement;

public class DustProblem extends Problem {
	private World world;
	private VacuumCleaner vc;
	private PerformanceMeasurement pm;
	
	public DustProblem(World w) {
		this.world = w;
		this.vc = VacuumCleanerCreator.createFullMovementSmartVC(w);
		this.vc.setLocation(this.world.getSquare(new Coordinates(1, 1)));
		this.pm = new PerformanceMeasurement(world);
	}
	
	public VacuumCleaner getVacuumCleaner() {
		return this.vc;
	}
	
	public World getWorld() {
		return this.world;
	}
	
	@Override
	public boolean isSolved() {
		return pm.computeDirtySquares() == 0;
	}

	@Override
	public List<Action> getAvailableActions() {
		List<Action> result = new LinkedList<Action>();
		
		for(Direction d : vc.getAvailableDirections()) {
			result.add(new Move(this.clone(), d));
		}
		
		if(vc.isDirty()) {
			Clean c = new Clean(this.clone());
			result.add(c);
		}
		return result;
	}

	@Override
	public Problem clone() {
		DustProblem result = new DustProblem(world.clone());
		result.vc.setLocation(vc.getLocation());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		if(o.getClass() != this.getClass())
			return false;
		DustProblem p = ((DustProblem)o);
		
		return p.world.equals(this.world) && 
				p.vc.equals(this.vc);
	}
	
	@Override
	public String toString() {
		return world.toString();
	}
}
