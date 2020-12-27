package liteEngine.components;

import liteEngine.engine.entities.GameObject;
import liteEngine.engine.events.EngineEvents;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.ImageIcon;

public class Animator extends Component {
	// TODO dependency on Label Component
	protected Renderer label = null;

	// Java doesn't support field polymorphism - here we are simply hiding the
	// parent's dependency field
//	public HashSet<Class<? extends Component>> dependencies = new HashSet<Class<? extends Component>>(
//			Arrays.asList(Label.class));
	
	public HashMap<String, ImageIcon> states = new HashMap<String, ImageIcon>();
	
	public Animator() {
		super();
	}

	public Animator(GameObject parent) {
		super(parent);
	}

	@Override
	public void update() {
		determineAnimations();
	}

	@Override
	public void receive(String event) {
		switch (event) {
		case EngineEvents.DEPENDENCIES_ADDED:
			label = this.parent.getComponent(Renderer.class);
			break;
		}
	}
	
	@Override
	public HashSet<Class<? extends Component>> getDependencies() {
		return new HashSet<Class<? extends Component>>(Arrays.asList(Renderer.class));
	}

	/**
	 * In a prefab animator, place animation logic here
	 * 
	 * @example if(speed > 20) label.setIcon(states.get("run"))
	 */
	public void determineAnimations() {

	}
}
