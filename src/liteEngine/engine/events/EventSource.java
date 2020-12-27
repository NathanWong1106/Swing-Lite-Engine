package liteEngine.engine.events;

import java.util.HashSet;

public class EventSource {
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
	
	public void broadcast() {
		for(Runnable listener : listeners) {
			listener.run();
		}
	}
}












