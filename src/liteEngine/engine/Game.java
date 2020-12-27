package liteEngine.engine;

import liteEngine.UI.MainView;
import liteEngine.engine.events.UpdateEventSource;

public class Game {
	private Game() {
		
	}

	public static void init() {
		MainView.getInstance().init();
		UpdateEventSource.start();
	}
}
