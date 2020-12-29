package liteEngine.engine;

import liteEngine.UI.MainView;
import liteEngine.UI.ViewSettings;
import liteEngine.collision.CollisionDispatcher;
import liteEngine.dataStructures.Vector2;
import liteEngine.events.UpdateEventSource;

/**
 * After initialization of custom components use this class to start the game
 * @author Nathan Wong
 */
public final class Game {
	
	public static void main (String[] args) {
		init(new ViewSettings(new Vector2(600,600), null, "Hello World!"));
	}
	
	public static void init(ViewSettings viewSettings) {
		//Call update event first in case any other inits depend on the update loop
		UpdateEventSource.start();
		MainView.getInstance().init(viewSettings);
		CollisionDispatcher.init();
	}
}
