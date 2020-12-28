package liteEngine.engine.entities;

import liteEngine.engine.events.EventSource;
import liteEngine.engine.events.UpdateEventSource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import liteEngine.components.Component;
import liteEngine.components.Transform;
import liteEngine.dataStructures.Vector2;

/**
 * Base class that all entities in your game should inherit from. Components can be added to extend functionality.
 * @author Nathan Wong
 */
public class GameObject implements IEntity {

	public Transform transform = null;
	public HashMap<Class<? extends Component>, Object> components = new HashMap<Class<? extends Component>, Object>();

	/**
	 * @apiNote DO NOT override the constructor - use Awake instead
	 */
	public GameObject() {
		this.transform = addComponent(Transform.class);
		UpdateEventSource.addUpdateEventListener(this);
		awake();
	}

	/**
	 * Invoked by the constructor immediately after instantiation
	 */
	public void awake() {

	}

	/**
	 * Called every fixed time step (your frame-to-frame logic should be put here)
	 * 
	 * @apiNote DO NOT call this method - use fixedUpdate instead
	 */
	public final void fixedUpdate() {
		update();
	}

	/**
	 * Called every fixed time step (your frame-to-frame logic should be put here)
	 */
	public void update() {

	}

	/**
	 * Called immediately before the object is removed
	 * 
	 * @apiNote DO NOT call this method - use onObjectDestroy instead
	 */
	public final void onObjectDestroy() {
		UpdateEventSource.removeUpdateEventListener(this);
		for (Class<? extends Component> key : components.keySet()) {
			getComponent(key).onObjectDestroy();
		}
		components.clear();
		onDestroy();
	}

	/**
	 * Called immediately before the object is removed
	 */
	public void onDestroy() {

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
			components.put(componentClass, componentClass.getConstructor().newInstance());

			T component = getComponent(componentClass);
			component.parent = this;
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
	 *                  liteEngine.components.component)
	 */
	public <T extends Component> void addComponent(T component) {
		if (component.parent != null) {
			System.err.println("~GameObject.AddComponent: a component cannot have multiple parents");
			return;
		}

		components.put(component.getClass(), component);
		component.parent = this;
		addDependencies(component);
	}

	/**
	 * Adds all dependencies stated in component.getDependencies()
	 * 
	 * @param component component that has been added
	 */
	private <T extends Component> void addDependencies(T component) {

		HashSet<Class<? extends Component>> dependencies = component.getDependencies();

		// when all a component's dependencies are added, broadcast a message to that
		// component
		if (dependencies.size() > 0) {
			EventSource dependencySource = new EventSource();
			dependencySource.addListener(component.onDependenciesAdded);

			for (Class<? extends Component> dependency : component.getDependencies()) {
				if (getComponent(dependency) == null) {
					addComponent(dependency);
				}
			}

			dependencySource.invoke();
			dependencySource.removeListener(component.onDependenciesAdded);
		}

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

	/**
	 * Instantiates a given GameObject
	 */
	protected <T extends GameObject> T instantiate(Class<T> gameObjectClass) {
		try {
			return gameObjectClass.getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Instantiates a given GameObject with the specified transform arguments
	 */
	protected <T extends GameObject> T instantiate(Class<T> gameObjectClass, Vector2 sizeDelta, Vector2 position) {
		T obj = instantiate(gameObjectClass);
		obj.transform.setSizeAndPosition(sizeDelta, position);
		return obj;
	}

	/**
	 * Destroys a given GameObject
	 */
	protected <T extends GameObject> void destroy(T gameObject) {
		gameObject.onObjectDestroy();
	}

}
