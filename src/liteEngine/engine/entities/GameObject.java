package liteEngine.engine.entities;

import javax.swing.JLabel;
import liteEngine.engine.events.UpdateEventSource;

public class GameObject implements IEntity {
	
	JLabel label;

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
