package liteEngine.engine;

import liteEngine.UI.MainView;
import liteEngine.UI.ViewSettings;
import liteEngine.engine.events.UpdateEventSource;

/**
 * After initialization of custom components use this class to start the game
 * @author Nathan Wong
 */
public final class Game {
	public static void init(ViewSettings viewSettings) {
		MainView.getInstance().init(viewSettings);
		UpdateEventSource.start();
	}
}
