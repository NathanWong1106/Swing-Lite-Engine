package liteEngine.engine;

import liteEngine.UI.MainView;
import liteEngine.UI.ViewSettings;
import liteEngine.collision.CollisionDispatcher;
import liteEngine.events.UpdateEventSource;

/**
 * After initialization of custom components use this class to start the game
 * @author Nathan Wong
 */
public final class Game {
	public static void init(ViewSettings viewSettings) {
		//Call update event first in case any other inits depend on the update loop
		UpdateEventSource.start();
		MainView.getInstance().init(viewSettings);
		CollisionDispatcher.init();
	}
}
