package liteEngine.dataStructures;

/**
 * Stores two integer values (x,y)
 * @author Nathan Wong
 *
 */
public class Vector2 implements java.io.Serializable{
	private static final long serialVersionUID = -1309307253770238553L;

	/**
	 * Vector2 representation of (0,0)
	 */
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
