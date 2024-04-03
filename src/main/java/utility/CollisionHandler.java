/*
Responsibilities:

- Handle collisions between different game objects.
- Implement the Chain of Responsibility pattern, where a request is passed through a chain of handlers, each deciding whether to process the request or pass it to the next handler. This design pattern decouples the sender of a request from its receiver.

Data:

- Reference to the next collision handler in the chain, allowing for sequential processing of collision handling.
 */

package main.java.utility;

import main.java.model.GameObject;

public abstract class CollisionHandler {
    protected CollisionHandler nextHandler;

    /**
     * Sets the next handler in the chain.
     * 
     * @param nextHandler The next collision handler to be invoked in the chain.
     */
    public void setNextHandler(CollisionHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * Handles collisions between two game objects.
     * 
     * @param object1 The first game object involved in the collision.
     * @param object2 The second game object involved in the collision.
     */
    public abstract void handleCollision(GameObject object1, GameObject object2);
}
