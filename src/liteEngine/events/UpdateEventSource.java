package liteEngine.events;

import javax.swing.Timer;

import liteEngine.engine.Time;
import liteEngine.entities.IEntity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Event source of the game update loop. Any IEntity can register as a listener and receive updates through update().
 * The update loop also broadcasts to the GlobalEventSystem with the UPDATE_EVENT_KEY.
 * @author Nathan Wong
 */
public final class UpdateEventSource {
	public final static String UPDATE_EVENT_KEY = "UPDATE";
	
	protected static Timer t;
	protected static HashSet<IEntity> listeners = new HashSet<IEntity>();
	protected static HashSet<IEntity> addBuffer = new HashSet<IEntity>();
	protected static HashSet<IEntity> removalBuffer = new HashSet<IEntity>();

	public static void addUpdateEventListener(IEntity entity) {
		addBuffer.add(entity);
	}

	public static void removeUpdateEventListener(IEntity entity) {
		removalBuffer.add(entity);
	}

	public static void start() {
		GlobalEventSystem.addEvent(UPDATE_EVENT_KEY);

		// There has to be a better way to do this
		t = new Timer(Time.timeStep, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!Time.paused) {
					for (IEntity entity : listeners) {
						entity.fixedUpdate();
					}
					GlobalEventSystem.invokeEvent(UPDATE_EVENT_KEY);
					
					listeners.addAll(addBuffer);
					listeners.removeAll(removalBuffer);
					
					addBuffer.clear();
					removalBuffer.clear();
				}
			}
		});

		t.start();
	}

	public static void stop() {
		t.stop();
	}
}
