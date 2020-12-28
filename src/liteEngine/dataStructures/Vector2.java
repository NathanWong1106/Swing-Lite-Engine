package liteEngine.dataStructures;

public class Vector2 {
	public int x, y;

	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean pointEquals(Vector2 other) {
		return (this.x == other.x && this.y == other.y);
	}
}
