package liteEngine.components;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.HashSet;

import liteEngine.collision.CollisionRegistry;

/**
 * Collider that detects collisions with the parent's Transform component. When
 * a collision is detected, onCollision will be called on the parent GameObject 
 * 
 * <h1>Important</h1>
 * <ul>
 * <li>Currently only supports Rectangle colliders</li>
 * <li>Currently collision is not very well optimized - expect lag when using a large amount of colliders</li>
 * </ul>
 * @author Nathan Wong
 *
 */
public class Collider extends Component {
	
	public Collider() {
		super();
		CollisionRegistry.registerCollider(this);
	}
	
	@Override
	public void awake() {
		
	}

	public Rectangle getTransformRect() {
		return this.parent.transform.getBounds();
	}

	/**
	 * Called when the CollisionDispatcher detects a collision with another collider
	 * 
	 * @param other the intersecting collider
	 */
	public void onCollisionWith(Collider other) {
		this.parent.onCollision(other);
	}

	@Override
	public final void onObjectDestroy() {
		CollisionRegistry.removeCollider(this);
		this.onDestroy();
	}
	
	public HashSet<Class<? extends Component>> getDependencies() {
		return new HashSet<Class<? extends Component>>(Arrays.asList(Transform.class));
	}
}
