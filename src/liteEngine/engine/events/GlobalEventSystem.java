package liteEngine.engine.events;

import java.util.HashMap;

/**
 * A static mapping of event names and EventSources available in a global scope.
 * @author Nathan Wong
 */
public final class GlobalEventSystem {
	private static HashMap<String, EventSource> events = new HashMap<String, EventSource>();
	
	public static EventSource addEvent(String name) {
		if(events.containsKey(name)) {
			System.err.println("~GlobalEventSystem.addEvent - event key already exists");
			return null;
		}
		
		EventSource newEvent = new EventSource();
		events.put(name, newEvent);
		return newEvent;
	}
	
	public static void removeEvent(String name) {
		if(!events.containsKey(name)) {
			return;
		}
		
		events.get(name).clearListeners();
		events.remove(name);
	}
	
	public static void addListenerToEvent(String name, Runnable listener) {
		if(!events.containsKey(name) || listener == null) {
			System.err.println("~GlobalEventSystem.addListenerToEvent - null key or Runnable");
			return;
		}
		
		events.get(name).addListener(listener);
	}
	
	public static void removeListenerFromEvent(String name, Runnable listener) {
		if(!events.containsKey(name) || listener == null) {
			System.err.println("~GlobalEventSystem.removeListenerFromEvent - null key or Runnable");
			return;
		}
		
		events.get(name).removeListener(listener);
	}
	
	public static void invokeEvent(String name) {
		if(!events.containsKey(name)) {
			System.err.println("~GlobalEventSystem.invokeEvent - event key does not exist");
		}
		
		events.get(name).invoke();
	}
}
