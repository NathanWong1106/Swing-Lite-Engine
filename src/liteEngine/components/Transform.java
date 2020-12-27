package liteEngine.components;

import liteEngine.dataStructures.Point2D;

public class Transform extends Component {
	public Point2D sizeDelta = new Point2D(0, 0);
	public Point2D position = new Point2D(0, 0);

	public void setSize(int width, int height) {
		sizeDelta = new Point2D(width, height);
	}

	public void setPosition(int x, int y) {
		position = new Point2D(x, y);
	}

	public void setSizeAndPosition(int width, int height, int x, int y) {
		setSize(width, height);
		setPosition(x, y);
	}

	public void translate(int right, int up) {
		position = new Point2D(position.x + right, position.y - up);
	}
}
