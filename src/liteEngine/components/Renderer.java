package liteEngine.components;

import java.util.Arrays;
import java.util.HashSet;

import javax.swing.*;

import liteEngine.UI.MainView;
import liteEngine.engine.entities.GameObject;

public class Renderer extends Component {
	public JLabel label = new JLabel();
	public ImageIcon icon;

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
		Transform t = this.parent.transform;
		this.label.setBounds(t.position.x, t.position.y, t.sizeDelta.x, t.sizeDelta.y);
	}
	
	@Override
	public HashSet<Class<? extends Component>> getDependencies() {
		return new HashSet<Class<? extends Component>>(Arrays.asList(Transform.class));
	}
}
