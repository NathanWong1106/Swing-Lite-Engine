# Swing-Lite-Engine

A small framework I built to help with basic game development using Java's javax.swing library. In no way should this ever be used for larger games (use OpenGL, Unity, etc for that). The syntax is Unity-like, and implements some of its core concepts.

I mainly made this to refresh my Java knowledge before I take ICS4U ¯\\ _ (ツ) _ /¯

## Current Features
- <b>Extensible Entity-Component system</b>
    - A `GameObject` is an entity that can be extended to make custom entities
        - `GameObjects` maintain a set of `Component` objects
    - Behaviours of a `GameObject` can be described by adding `Component` objects
        - Some standard components are `Renderer`, `Transform`, `Animator`, and `Collider`
        - Custom components can be made by extending the `Component` class
        - Allows for easy reusability of behaviours among numerous `GameObjects`
- <b>Observer pattern</b>
    - Using Java's `Runnable` and `Consumer`, listeners can be created for global/local events that are invoked.
    - Events can be created by instantiating a new `EventSource` or `EventSource.WithArgs<T>` and adding any listeners
        - Mitigates callback hell that I've experienced in other javax.swing projects
- <b>Object lifecycle</b>
    - For any classes implementing `IEntity` (including `GameObject` and `Component`):
        - The `awake()` method is called when an entity is first instantiated. Overriding `awake()` allows you to run a custom startup.
        - The `update()` method is called on a `fixedUpdate`. Overriding `update()` allows you to run custom frame-to-frame logic
        - The `onDestroy()` method is called when an entity is removed or destroyed. Overriding `onDestroy()` allows you to implement custom `onDestroy` behaviours
- <b>Serialization utilities</b>
    - Use the `Serializer` and `Deserializer` classes to save Java objects in your game (save games, playerprefs, etc) to a byte .txt file
    - Make sure your object implements `java.io.Serializable`
- <b>Collision?</b>
    - No physics are implemented but intersection detection between `Collider` components is supported
        - The `onCollision(Collider other)` method of a `GameObject` with a `Collider` component will be called on intersection
    
### Some Boilerplate to Get Started
 ```
public static void main(String[] args) {
     //Custom startup initialization goes here
     Game.init(new ViewSettings(new Vector2(900, 600), null, "Hello Game!")); //start the update loop and create window
}
 ```
