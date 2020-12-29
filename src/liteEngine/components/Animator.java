package liteEngine.components;

import java.util.Arrays;
import java.util.HashSet;

import javax.swing.ImageIcon;

/**
 * Extensible abstract component used to provide animation states to a Renderer component.
 * 
 * <h1>In order to use an animator you must:</h1>
 * <ul>
 * <li>Create a class that extends the Animator Component</li>
 * <li>Create a HashMap of String and ImageIcon KVP's</li>
 * <li>Override <code>getState</code> - return the ImageIcon of a provided String Key</li>
 * <li>Implement <code>animStateProvider</code> - return a String key or null if no condition is satisfied</li>
 * </ul>
 * @author Nathan Wong
 */
public abstract class Animator extends Component {
	
	private String lastState = "";
	
	public Animator() {
		super();
	}
	
	@Override
	public final void update() {
		updateAnimations();
	}

	@Override
	public final HashSet<Class<? extends Component>> getDependencies() {
		return new HashSet<Class<? extends Component>>(Arrays.asList(Renderer.class));
	}

	protected final void updateAnimations() {
		String nextState = animStateProvider();
		if(nextState == null) {
			return;
		}
		
		if(!this.lastState.equals(nextState)) {
			this.lastState = nextState;
			this.parent.getComponent(Renderer.class).setIcon(this.getState(nextState));
		}
	}
	
	/**
	 * @return the value(ImageIcon) of the key in the anim HashMap
	 */
	protected ImageIcon getState(String key) {
		return null;
	}
	
	/**
	 * In a prefab animator, place animation logic here
	 * 
	 * @example if(speed > 20) return "run"
	 * @return animation key or null if no condition is satisfied
	 */
	protected abstract String animStateProvider();
}
