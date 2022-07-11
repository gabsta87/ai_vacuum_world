package tools;

import environment.Square;
import environment.World;

public class PerformanceMeasurement {
	private World w;
	private double toleratedDirtLevel = 0.5;
	
	public PerformanceMeasurement(World w) {
		this.w = w;
	}

	public int computeDirtySquares() {
		Square[][] grid = w.getGrid();
		int counter = 0;
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				if(grid[i][j]!=null && grid[i][j].getDirtLevel() > toleratedDirtLevel) {
					counter++;
				}
			}
		}
		return counter;
	}
}
