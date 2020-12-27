package liteEngine.engine;

import liteEngine.UI.MainView;
import liteEngine.components.Animator;
import liteEngine.components.Renderer;
import liteEngine.engine.entities.GameObject;
import liteEngine.engine.events.UpdateEventSource;

public class Game {
	private Game() {
		
	}
	
	public static void main (String[] args) {
		GameObject obj = new GameObject();
		obj.addComponent(Animator.class);
		System.out.println(obj.getComponent(Renderer.class));
	}

	public static void init() {
		MainView.getInstance().init();
		UpdateEventSource.start();
	}
}
