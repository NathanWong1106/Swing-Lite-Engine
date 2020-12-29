package liteEngine.events;

import java.util.HashSet;
import java.util.function.Consumer;

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
	
	/**
	 * Subclass of EventSource with Consumer listeners that receive an arg of type T when invoked
	 * @author Nathan Wong
	 * @example 
	 * 		Consumer<T> consumer = new Consumer<T>() {
			public void accept(T args) {
				// your method here
			}			
		};
	 */
	public static class WithArgs <T> {
		protected HashSet<Consumer<T>> listeners = new HashSet<Consumer<T>>();
		
		public void addListener(Consumer<T> listener) {
			listeners.add(listener);
		}

		public void removeListener(Consumer<T> listener) {
			listeners.remove(listener);
		}

		public void clearListeners() {
			listeners.clear();
		}
		
		public void invoke(T arg) {
			for(Consumer<T> listener : listeners) {
				listener.accept(arg);
			}
		}
	}
}











