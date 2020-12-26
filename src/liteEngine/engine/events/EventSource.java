package liteEngine.engine.events;

import java.util.HashSet;

public class EventSource {
	protected HashSet<ICallable> listeners = new HashSet<ICallable>();
	
	public void addListener(ICallable listener) {
		listeners.add(listener);
	}
	
	public void removeListener(ICallable listener) {
		listeners.remove(listener);
	}
	
	public void broadcast(String event) {
		for(ICallable listener : listeners) {
			listener.receive(event);
		}
	}
}
