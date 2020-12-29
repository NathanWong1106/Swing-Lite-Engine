package liteEngine.collision;

import liteEngine.components.Collider;
import liteEngine.events.GlobalEventSystem;
import liteEngine.events.UpdateEventSource;

public final class CollisionDispatcher {
	protected static Runnable updateCollisions = new Runnable() {
		@Override
		public void run() {
			updateCollisions();
		}
	};

	public static void init() {
		GlobalEventSystem.addListenerToEvent(UpdateEventSource.UPDATE_EVENT_KEY, updateCollisions);
	}
	
	/*
	 * Given the relative simplicity of a rectangle intersection check this should be fine for smaller games
	 * This has a complexity of [sumof](range(0,n)) 
	 * while quadtrees would be much faster(not sure if it's worth the work to try understanding it yet)
	 */
	protected static void updateCollisions() {
		Collider[] registeredColliders = CollisionRegistry.getRegistryAsArray();

		for (int current = 0, length = registeredColliders.length; current < length - 1; current++) {
			for (int other = current + 1; other < length; other++) {
				Collider currentCollider = registeredColliders[current];
				Collider otherCollider = registeredColliders[other];

				if (currentCollider.getTransformRect().intersects(otherCollider.getTransformRect())) {
					currentCollider.onCollisionWith(otherCollider);
					otherCollider.onCollisionWith(currentCollider);
				}
			}
		}
	}

}
