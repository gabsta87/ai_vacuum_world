package environment;

public class Coordinates{
	public int x;
	public int y;
	public Coordinates(int x, int y) {
		this.x = y;
		this.y = x;
	}

	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		if(o.getClass() != this.getClass())
			return false;
		Coordinates c = ((Coordinates)o);
		return x == c.x && y == c.y;
	}
	
	public String toString() {
		return "["+x+","+y+"]";
	}
}
