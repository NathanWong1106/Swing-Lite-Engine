package liteEngine.components;

import liteEngine.engine.entities.IEntity;
import liteEngine.engine.events.EngineEvents;
import liteEngine.engine.events.ICallable;

import java.util.HashSet;

import liteEngine.engine.entities.GameObject;

/**
 * Parent class for all Component entities in Lite Engine
 */
public class Component implements IEntity, ICallable {
	/**
	 * Parent GameObject of this component
	 */
	public GameObject parent;

	/**
	 * Constructor used for generic components
	 * 
	 * @param parent GameObject parent of this component
	 */
	public Component(GameObject parent) {
		this.parent = parent;
	}

	/**
	 * Constructor used for prefab components
	 */
	public Component() {
	}

	public void onDestroy() {

	}

	@Override
	public void update() {

	}

	@Override
	public void receive(String event) {
		switch (event) {
			case EngineEvents.DEPENDENCIES_ADDED:
				onDependenciesAdded();
				break;
		}
	}

	/**
	 * Components that must be added alongside this component
	 */
	public HashSet<Class<? extends Component>> getDependencies() {
		return new HashSet<Class<? extends Component>>();
	}

	public void onDependenciesAdded() {

	}
}
