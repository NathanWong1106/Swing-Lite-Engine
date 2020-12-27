package liteEngine.components;

import javax.swing.*;

import liteEngine.UI.MainView;
import liteEngine.engine.entities.GameObject;

public class Renderer extends Component {
	public JLabel label = new JLabel();
	public ImageIcon icon;

	public Renderer() {
		super();
	}

	public Renderer(GameObject parent) {
		super(parent);
	}

	public void init(int posX, int posY, int sizeX, int sizeY, ImageIcon icon, String text) {
		this.label.setBounds(posX, posY, sizeX, sizeY);
		this.label.setText(text);
		this.label.setIcon(icon);
		MainView.getInstance().add(this.label);
	}

	@Override
	public void onDestroy() {
		MainView.getInstance().remove(this.label);
	}

	@Override
	public void update() {

	}
}
