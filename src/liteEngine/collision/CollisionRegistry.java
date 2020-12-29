package liteEngine.collision;

import java.util.HashSet;

import liteEngine.components.Collider;

public final class CollisionRegistry {

	protected static HashSet<Collider> registeredColliders = new HashSet<Collider>();

	public static void registerCollider(Collider collider) {
		registeredColliders.add(collider);
	}

	public static void removeCollider(Collider collider) {
		registeredColliders.remove(collider);
	}

	public static Collider[] getRegistryAsArray() {
		return registeredColliders.toArray(new Collider[registeredColliders.size()]);
	}
}
