package liteEngine.components;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.ImageIcon;

public class Animator extends Component {
	public HashMap<String, ImageIcon> states = new HashMap<String, ImageIcon>();

	@Override
	public final void update() {
		updateAnimations();
	}

	@Override
	public final HashSet<Class<? extends Component>> getDependencies() {
		return new HashSet<Class<? extends Component>>(Arrays.asList(Renderer.class));
	}

	protected final void updateAnimations() {
		this.parent.getComponent(Renderer.class).label.setIcon(animStateProvider());;
	}
	
	/**
	 * In a prefab animator, place animation logic here
	 * 
	 * @example if(speed > 20) return states.get("run")
	 */
	protected ImageIcon animStateProvider() {
		return null;
	}
}
