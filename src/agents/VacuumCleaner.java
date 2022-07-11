package agents;

import java.util.List;

import agents.AI.AgentProgram;
import agents.actuators.CleaningTool;
import agents.sensors.CleaningSensor;
import agents.sensors.MovingSensor;
import environment.Direction;
import environment.Square;
import environment.World;

public class VacuumCleaner implements Cloneable{
	private World world;
	private AgentProgram program;
	private Square location;
	
	private CleaningTool ct;
	private CleaningSensor cs;
	private MovingSensor ms;
	
	public VacuumCleaner(World w) {
		this.world = w;
		this.ct = new CleaningTool(w, 1);
		this.cs = new CleaningSensor(this, w);
		this.ms = new MovingSensor(this, w);
	}
	
	public void setAgentProgram(AgentProgram ap) {
		this.program = ap;
	}
	
	public AgentProgram getProgram() {
		return program;
	}
	
	public boolean move(Direction d){
		return world.move(d);
	}

	public boolean isDirty() {
		return cs.isDirty();
	}
	
	public void setCleaningTool(CleaningTool ct) {
		this.ct = ct;
	}

	public List<Direction> getAvailableDirections() {
		return ms.getAvailableDirections();
	}

	public void setMovingSensor(MovingSensor ms) {
		this.ms = ms;
	}

	public void setCleaningSensor(CleaningSensor cs) {
		this.cs = cs;
	}

	public Square getLocation() {
		return location;
	}

	public void setLocation(Square location) {
		if(this.location != null)
			this.location.removeVC(this);
		this.location = location;
		this.location.addVC(this);
	}
	
	public void executeStep() {
		this.program.executeStep();
	}
	
	public double getEffectiveness() {
		return ct.getEffectiveness();
	}

	public double getDirtLevelAutorized() {
		return cs.getDirtLevelAutorized();
	}

	public void clean() {
		world.clean();
	}
	
	public boolean equals(Object o) {
		if(o == this)
			return true;
		if(o.getClass() != this.getClass())
			return false;
		VacuumCleaner v = ((VacuumCleaner)o);
		
		return this.location.equals(v.location);
	}
}
