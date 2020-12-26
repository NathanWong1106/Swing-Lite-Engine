package liteEngine.engine.events.keyInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class InputManager implements KeyListener {
	
	private HashMap<Integer, Boolean> keysDown = new HashMap<Integer, Boolean>();

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
	
	public boolean getKeyDown(int keyCode) {
		return keysDown.getOrDefault(keyCode, false);
	}

}
