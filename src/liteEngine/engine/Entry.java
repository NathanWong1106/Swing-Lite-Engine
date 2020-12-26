package liteEngine.engine;

import liteEngine.engine.events.UpdateEventSource;

public class Entry {
	public static void init() {
		UpdateEventSource.start();
	}
}
