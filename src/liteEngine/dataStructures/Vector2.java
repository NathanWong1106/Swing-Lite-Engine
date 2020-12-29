package liteEngine.dataStructures;

public class Vector2 {
	
	public static final Vector2 zero = new Vector2(0,0);
	
	public int x, y;

	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean pointEquals(Vector2 other) {
		return (this.x == other.x && this.y == other.y);
	}
	
	public static Vector2 copy(Vector2 other) {
		return new Vector2(other.x, other.y);
	}
}
