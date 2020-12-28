package liteEngine.UI;

import javax.swing.*;

import liteEngine.engine.entities.IEntity;
import liteEngine.engine.events.UpdateEventSource;
import liteEngine.engine.events.keyInput.InputManager;

@SuppressWarnings("serial")
public final class MainView extends JFrame implements IEntity {
	private static MainView instance = null;
	
	private MainView() {
		UpdateEventSource.addUpdateEventListener(this);
		awake();
	}
	
	public void awake() {
		
	}

	public static MainView getInstance() {
		if (instance == null) {
			instance = new MainView();
		}
		return instance;
	}
	
	//TODO these settings should be able to be overwritten by the user
	public void init(ViewSettings settings) {
		addKeyListener(InputManager.getInstance());
		setTitle(settings.title);
		setSize(settings.screenDimension.x, settings.screenDimension.y);
		setLayout(settings.layout);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public final void fixedUpdate() {
		update();
	}
	
	public void update() {
		if (instance != null) {
			repaint();
		}
	}
	
	public final void onObjectDestroy() {
		onDestroy();
	}
	
	@Override
	public void onDestroy() {

	}
}
