package liteEngine.components;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.ImageIcon;

/**
 * Extensible component used to provide animation states to a Renderer component.
 * This component must be extended in order to be used. Provide your logic in animStateProvider and states in getStates.
 * @author Nathan Wong
 */
public abstract class Animator extends Component {
	@Override
	public final void update() {
		updateAnimations();
	}

	@Override
	public final HashSet<Class<? extends Component>> getDependencies() {
		return new HashSet<Class<? extends Component>>(Arrays.asList(Renderer.class));
	}

	protected final void updateAnimations() {
		this.parent.getComponent(Renderer.class).icon = animStateProvider();
	}
	
	/**
	 * 
	 * @return a map of state names and animations
	 */
	protected abstract HashMap<String, ImageIcon> getStates();
	
	/**
	 * In a prefab animator, place animation logic here
	 * 
	 * @example if(speed > 20) return states.get("run")
	 * @return animation state
	 */
	protected abstract ImageIcon animStateProvider();
}
