package liteEngine.engine.events;

import java.util.HashSet;

/**
 * Broadcasts events to listener functions(Runnables).
 * @author Nathan Wong
 */
public final class EventSource {
	protected HashSet<Runnable> listeners = new HashSet<Runnable>();
	
	public void addListener(Runnable listener) {
		listeners.add(listener);
	}

	public void removeListener(Runnable listener) {
		listeners.remove(listener);
	}

	public void clearListeners() {
		listeners.clear();
	}
	
	public void invoke() {
		for(Runnable listener : listeners) {
			listener.run();
		}
	}
}












