package liteEngine.components;

import liteEngine.engine.entities.IEntity;
import liteEngine.engine.events.UpdateEventSource;

import java.util.HashSet;

import liteEngine.engine.entities.GameObject;

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
			Awake();
		}
	}
	
	public void Awake() {
		
	}
	
	public Runnable onDependenciesAdded = new Runnable() {
		@Override
		public void run() {
			onDependenciesAdded();
		}
	};
	
	protected void onDependenciesAdded() {
		Awake();
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
	
	public final void onObjectDestroy() {
		UpdateEventSource.removeUpdateEventListener(this);
		onDestroy();
	}
	
	public void onDestroy() {

	}
}
