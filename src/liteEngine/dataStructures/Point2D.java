package liteEngine.dataStructures;

public class Point2D {
	public int x, y;

	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean pointEquals(Point2D other) {
		return (this.x == other.x && this.y == other.y);
	}
}
