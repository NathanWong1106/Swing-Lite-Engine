package liteEngine.components;

import java.awt.Rectangle;

import liteEngine.dataStructures.Vector2;

/**
 * Contains the position and sizeDelta of an entity on screen.
 * @author Nathan Wong
 */
public final class Transform extends Component {
	public Vector2 sizeDelta = new Vector2(0, 0);
	public Vector2 position = new Vector2(0, 0);
	
	public Transform() {
		super();
	}

	public void setSize(int width, int height) {
		sizeDelta = new Vector2(width, height);
	}
	
	public void setSize(Vector2 sizeDelta) {
		this.sizeDelta = sizeDelta;
	}

	public void setPosition(int x, int y) {
		position = new Vector2(x, y);
	}
	
	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public void setSizeAndPosition(int width, int height, int x, int y) {
		setSize(width, height);
		setPosition(x, y);
	}
	
	public void setSizeAndPosition(Vector2 sizeDelta, Vector2 position) {
		this.sizeDelta = sizeDelta;
		this.position = position;
	}

	public void translate(int right, int up) {
		position = new Vector2(position.x + right, position.y - up);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(position.x, position.y, sizeDelta.x, sizeDelta.y);
	}
}
