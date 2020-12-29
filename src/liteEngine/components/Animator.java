package liteEngine.components;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.ImageIcon;

/**
 * Extensible component used to provide animation states to a Renderer component.
 * This component must be extended in order to be used. Provide your HashMap of String and ImageIcon KVP's.
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
