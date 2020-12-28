package liteEngine.UI;

import java.awt.LayoutManager;

import liteEngine.dataStructures.Vector2;

/**
 * Use this object to initialize the game window
 * @author Nathan Wong
 */
public final class ViewSettings {
	public Vector2 screenDimension = new Vector2(500, 500);
	public LayoutManager layout = null;
	public String title = "Swing Lite Engine";
	
	public ViewSettings(Vector2 screenDimension, LayoutManager layout, String title) {
		this.screenDimension = screenDimension;
		this.layout = layout;
		this.title = title;
	}
}
