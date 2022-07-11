package environment;

import agents.VacuumCleaner;

public class World implements Cloneable {
	private VacuumCleaner vc;
	private Square[][] grid;
	public final int dimensions = Direction.values().length / 2;

	private World(Square[][] data) {
		grid = data;
	}

	public World(double[][] config) {
		grid = new Square[config.length][config[0].length];

		for (int i = 0; i < config.length; i++) {
			for (int j = 0; j < config[i].length; j++) {
				if (config[i][j] >= 0) {
					grid[i][j] = new Square(config[i][j], i, j);
				} else {
					// TODO choisir si les cases vides doivent rester NULL ou bien si on crée une
					// classe pour les obstacles
				}
			}
		}
	}

	/**
	 * 
	 * @param moveDirection
	 * @return <code>True</code> if the move is not against a border or against an
	 *         obstacle, <code>False</code> otherwise
	 */
	public boolean isMoveAvailable(Direction moveDirection) {
		switch (moveDirection) {
		case down:
			return (vc.getLocation().getCoordinates().y < grid.length - 1
					&& grid[vc.getLocation().getCoordinates().y + 1][vc.getLocation().getCoordinates().x] != null);
		case up:
			return (vc.getLocation().getCoordinates().y > 0
					&& grid[vc.getLocation().getCoordinates().y - 1][vc.getLocation().getCoordinates().x] != null);
		case right:
			return (vc.getLocation().getCoordinates().x < grid.length - 1
					&& grid[vc.getLocation().getCoordinates().y][vc.getLocation().getCoordinates().x + 1] != null);
		case left:
			return (vc.getLocation().getCoordinates().x > 0
					&& grid[vc.getLocation().getCoordinates().y][vc.getLocation().getCoordinates().x - 1] != null);
		default:
			return false;
		}
	}

	public boolean move(Direction moveDirection) {
		if (!isMoveAvailable(moveDirection))
			return false;

		switch (moveDirection) {
		case down:
			vc.setLocation(grid[vc.getLocation().getCoordinates().y + 1][vc.getLocation().getCoordinates().x]);
			break;
		case up:
			vc.setLocation(grid[vc.getLocation().getCoordinates().y - 1][vc.getLocation().getCoordinates().x]);
			break;
		case right:
			vc.setLocation(grid[vc.getLocation().getCoordinates().y][vc.getLocation().getCoordinates().x + 1]);
			break;
		case left:
			vc.setLocation(grid[vc.getLocation().getCoordinates().y][vc.getLocation().getCoordinates().x - 1]);
			break;
		default:
			return false;
		}
		return true;
	}

	public void clean() {
		Square loc = vc.getLocation();
		loc.setDirtLevel(loc.getDirtLevel() - vc.getEffectiveness());
	}

	public void setVacuumCleaner(VacuumCleaner vc) {
		this.vc = vc;
	}

	public VacuumCleaner getVacuumCleaner() {
		return this.vc;
	}

	public Square getSquare(Coordinates c) {
		return grid[c.x][c.y];
	}

	public Square[][] getGrid() {
		return grid;
	}

	@Override
	public World clone() {
		Square[][] s = new Square[grid.length][];

		for (int i = 0; i < s.length; i++) {
			s[i] = new Square[grid[i].length];
			for (int j = 0; j < s[i].length; j++) {
				if (grid[i][j] != null)
					s[i][j] = new Square(grid[i][j].getDirtLevel(), i, j);
			}
		}

		World result = new World(s);

		result.vc = new VacuumCleaner(result);
		Coordinates c = vc.getLocation().getCoordinates();
		result.vc.setLocation(result.grid[c.y][c.x]);

		return result;
	}

	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (o.getClass() != this.getClass())
			return false;
		World w = ((World) o);

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] != null && w.grid[i][j] != null)
					if (!grid[i][j].equals(w.grid[i][j]))
						return false;
			}
		}
		return true;

	}

	@Override
	public String toString() {
		String result = "";
		if (grid == null) {
			result += "No world or no squares available in the world";
			return result;
		}

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == null) {
					result += ("    ");
				} else if (grid[i][j].equals(vc.getLocation())) {
//					System.out.printf(
					result += ("xxx ");
				} else {
					result += (grid[i][j].getDirtLevel() + " ");
				}
			}
			result += System.lineSeparator();
		}
		return result;
	}

	public static class WorldCreator {
		// 1 means dirty square
		// 0 means clean square
		// 0< means unaccessible square (wall)

		public static World createSimpleWorld() {
			double[][] config = { { 1, 1 } };
		
			World w = new World(config);
		
			return w;
		}

		public static World create33World() {
			double[][] config = { { 1, 1, 1 }, { 0, 0, 0 }, { 0, 0, 0 } };

			World w = new World(config);

			return w;
		}

		public static World create44World() {
			double[][] config = { { 1, 1, 1, 0}, { 0, 0, 0, 1}, { 0, 0, 0 ,0}, {1, 0, 0, 0} };

			World w = new World(config);

			return w;
		}
		
		public static World create55World() {
			double[][] config = { { 1, 1, 0, 0, 0 }, { 0, 0, 0, 1, 1 }, { 0, 1, 0, 1, 1 }, { 1, 1, 0, 1, 1 },
					{ 1, 1, 0, 1, 1 } };

			World w = new World(config);

			return w;
		}

		public static World create55ComplexWorld() {
			// 1 means dirty square
			// 0 means clean square
			// 0< means unaccessible square (wall)
			double[][] config = { { 1, 1, -1, -1, -1 }, { -1, 0, 0, 1, 1 }, { -1, 1, -1, 1, 1 }, { 1, 1, -1, 1, 1 },
					{ 1, 1, -1, 1, 1 } };

			World w = new World(config);

			return w;
		}
	}

}
