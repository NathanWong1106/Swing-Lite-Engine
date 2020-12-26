package liteEngine.dataStructures;

public class Point2D {
	public double x, y;

	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public boolean pointEquals(Point2D other) {
		return (this.x == other.x && this.y == other.y);
	}
}
