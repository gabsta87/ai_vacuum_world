package environment;

import java.util.LinkedList;
import java.util.List;

import agents.VacuumCleaner;

public class Square implements Cloneable{
	
	private List<VacuumCleaner> vcs;
	private Coordinates coordinates;
	/** Value between 0 and 1 to describe dirt level , 0 is clean and 1 dirty */
	private double dirtLevel;
	
	public Square(double dirt, int x, int y) {
		this.dirtLevel = dirt;
		this.setCoordinates(new Coordinates(x, y));
		this.vcs = new LinkedList<VacuumCleaner>();
	}
	
	/**
	 * Places the vacuum cleaner only if the square is currently without another vacuum cleaner
	 * @param vc
	 * @return <code>true</code> if the placement is successful, <code>false</code> otherwise
	 */
	public boolean addVC(VacuumCleaner vc) {
		return this.vcs.add(vc);
	}
	
	public boolean removeVC(VacuumCleaner vc) {
		return this.vcs.remove(vc);
	}
	
	public double getDirtLevel() {
		return dirtLevel;
	}
	
	public void setDirtLevel(double dirt) {
		this.dirtLevel = dirt;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	@Override
	public String toString() {
		if(this.vcs.size() == 0) {
			return ""+dirtLevel;
		}else {
			return "xxx";
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		if(o.getClass() != this.getClass())
			return false;
		Square s = ((Square)o);
		return coordinates.equals(s.coordinates) && dirtLevel == s.dirtLevel;
	}
}
