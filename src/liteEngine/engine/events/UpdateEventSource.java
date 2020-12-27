package liteEngine.engine.events;

import javax.swing.Timer;

import liteEngine.engine.Time;
import liteEngine.engine.entities.IEntity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class UpdateEventSource {
	protected static Timer t;
	protected static HashSet<IEntity> listeners = new HashSet<IEntity>();
	protected static HashSet<IEntity> newListeners = new HashSet<IEntity>();
	protected static HashSet<IEntity> toRemove = new HashSet<IEntity>();

	public static void addUpdateEventListener(IEntity entity) {
		newListeners.add(entity);
	}

	public static void removeUpdateEventListener(IEntity entity) {
		toRemove.add(entity);
	}

	public static void start() {
		t = new Timer(Time.timeStep, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!Time.paused) {
					for (IEntity entity : listeners) {
						entity.fixedUpdate();
					}
					listeners.addAll(newListeners);
					listeners.removeAll(toRemove);
					
					newListeners.clear();
					toRemove.clear();
				}
			}
		});

		t.start();
	}

	public static void stop() {
		t.stop();
	}
}
