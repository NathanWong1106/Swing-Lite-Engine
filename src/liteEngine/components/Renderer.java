package liteEngine.components;

import java.util.Arrays;
import java.util.HashSet;

import javax.swing.*;

import liteEngine.UI.MainView;

/**
 * Contains a JLabel and an ImageIcon tracking the GameObject transform.
 * @author Nathan Wong
 */
public final class Renderer extends Component {
	public JLabel label = new JLabel();
	private ImageIcon icon;
	
	public Renderer() {
		super();
	}

	public void init(ImageIcon icon, String text) {
		this.label.setText(text);

		setIcon(icon);
		this.label.setIcon(this.icon);
		
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
	
	public void setIcon(ImageIcon icon) {
		Transform t = this.parent.transform;
		this.icon = new ImageIcon(icon.getImage().getScaledInstance(t.sizeDelta.x, t.sizeDelta.y, 0));
		this.label.setIcon(this.icon);
	}
}
