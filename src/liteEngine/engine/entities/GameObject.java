package liteEngine.engine.entities;

import liteEngine.engine.events.EngineEvents;
import liteEngine.engine.events.EventSource;
import liteEngine.engine.events.UpdateEventSource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import liteEngine.components.Component;

public class GameObject implements IEntity {

	public EventSource broadcaster = new EventSource();
	public HashMap<Class<? extends Component>, Object> components = new HashMap<Class<? extends Component>, Object>();

	public GameObject() {
		UpdateEventSource.addUpdateEventListener(this);
	}

	/**
	 * Called every fixed time step (your frame-to-frame logic should be put here)
	 * 
	 * @apiNote always call super.update() when overridden
	 */
	public void update() {

	}

	/**
	 * Called immediately before the object is removed
	 * 
	 * @apiNote always call super.onDestroy() when overridden
	 */
	public void onDestroy() {
		UpdateEventSource.removeUpdateEventListener(this);
		for (Class<? extends Component> key : components.keySet()) {
			getComponent(key).onDestroy();
		}
		components.clear();
	}

	/**
	 * Adds a generic component to the GameObject
	 * 
	 * @param componentClass component to add (must extend
	 *                       liteEngine.components.component)
	 * @return Component if exists, else null
	 * @throws ClassNotFoundException
	 */
	public <T extends Component> T addComponent(Class<T> componentClass) {
		try {
			components.put(componentClass, componentClass.getConstructor(GameObject.class).newInstance(this));
			T component = getComponent(componentClass);
			broadcaster.addListener(component);
			addDependencies(component);
			return component;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Adds a prefab component to the GameObject
	 * 
	 * @param component component to add (must extend
	 *                      liteEngine.components.component)
	 */
	public <T extends Component> void addComponent(T component) {
		components.put(component.getClass(), component);
		component.parent = this;
		broadcaster.addListener(component);
		addDependencies(component);
	}
	
	/**
	 * Adds all dependencies stated in component.getDependencies()
	 * @param component component that has been added
	 */
	private <T extends Component> void addDependencies(T component) {
		//when all a component's dependencies are added, broadcast a message to that component
		EventSource dependencySource = new EventSource();
		dependencySource.addListener(component);
		
		for(Class<? extends Component> dependency : component.getDependencies()) {
			if(getComponent(dependency) == null) {
				addComponent(dependency);
			}
		}
		
		dependencySource.broadcast(EngineEvents.DEPENDENCIES_ADDED);
		dependencySource.removeListener(component);
	}

	/**
	 * Removes a component from the GameObject
	 * 
	 * @param componentClass component to add (must extend
	 *                       liteEngine.components.component)
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public <T extends Component> void removeComponent(Class<T> componentClass) {
		T component = (T) components.getOrDefault(componentClass, null);
		component.onDestroy();
		broadcaster.removeListener(component);
		components.remove(componentClass);
	}

	/**
	 * Returns the specified component from the GameObject
	 * 
	 * @param componentClass component to get (must extend
	 *                       liteEngine.components.component)
	 * @return Component if exists, else null
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public <T extends Component> T getComponent(Class<T> componentClass) {
		return (T) components.getOrDefault(componentClass, null);
	}
}
