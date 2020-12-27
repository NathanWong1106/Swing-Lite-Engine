package liteEngine.UI;

import javax.swing.*;

import liteEngine.engine.entities.IEntity;
import liteEngine.engine.events.UpdateEventSource;
import liteEngine.engine.events.keyInput.InputManager;

@SuppressWarnings("serial")
public class MainView extends JFrame implements IEntity {
	private static MainView instance = null;

	private MainView() {
		UpdateEventSource.addUpdateEventListener(this);
	}

	public static MainView getInstance() {
		if (instance == null) {
			instance = new MainView();
		}
		return instance;
	}
	
	//TODO these settings should be able to be overwritten by the user
	public void init() {
		addKeyListener(InputManager.getInstance());
		setTitle("Hello World!");
		setSize(500, 500);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void update() {
		if (instance != null) {
			repaint();
		}
	}

	@Override
	public void onDestroy() {

	}
}
