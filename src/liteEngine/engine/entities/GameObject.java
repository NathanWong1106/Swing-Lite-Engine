package liteEngine.engine.entities;

import liteEngine.engine.events.UpdateEventSource;
import java.util.HashSet;
import liteEngine.components.IComponent;

public class GameObject implements IEntity {
	
	public GameObject() {
		UpdateEventSource.addUpdateEventListener(this);
	}

	/**
	 * Called every fixed time step (your frame-to-frame logic should be put here)
	 */
	@Override
	public void update() {

	}

	public void onDestroy() {
		UpdateEventSource.removeUpdateEventListener(this);
	}
}
