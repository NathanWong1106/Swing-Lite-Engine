package liteEngine.engine.events.keyInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class InputManager implements KeyListener {
	private static InputManager instance = null;
	
	private InputManager() {
		
	}
	
	public static InputManager getInstance() {
		if(instance == null) {
			instance = new InputManager();
		}
		return instance;
	}
	
	
	private static HashMap<Integer, Boolean> keysDown = new HashMap<Integer, Boolean>();

	@Override
	public void keyTyped(KeyEvent e) {
		return;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keysDown.put(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keysDown.put(e.getKeyCode(), false);
	}
	
	public static boolean getKeyDown(int keyCode) {
		return keysDown.getOrDefault(keyCode, false);
	}

}
