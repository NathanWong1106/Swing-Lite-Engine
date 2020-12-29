package liteEngine.components;

import liteEngine.entities.GameObject;
import liteEngine.entities.IEntity;
import liteEngine.events.UpdateEventSource;

import java.util.HashSet;

/**
 * Parent class for all Component entities in Lite Engine
 */
public class Component implements IEntity {
	/**
	 * Parent GameObject of this component
	 */
	public GameObject parent;
	
	public Component() {
		//only execute if there are no dependencies
		if(getDependencies().size() == 0) {
			UpdateEventSource.addUpdateEventListener(this);
			awake();
		}
	}
	
	public void awake() {
		
	}
	
	public Runnable onDependenciesAdded = new Runnable() {
		@Override
		public void run() {
			onDependenciesAdded();
		}
	};
	
	protected void onDependenciesAdded() {
		awake();
		UpdateEventSource.addUpdateEventListener(this);
	}
	
	/**
	 * Components that must be added alongside this component
	 */
	public HashSet<Class<? extends Component>> getDependencies() {
		return new HashSet<Class<? extends Component>>();
	}
	
	public final void fixedUpdate() {
		update();
	}

	@Override
	public void update() {

	}
	
	public void onObjectDestroy() {
		UpdateEventSource.removeUpdateEventListener(this);
		onDestroy();
	}
	
	public void onDestroy() {

	}
}
