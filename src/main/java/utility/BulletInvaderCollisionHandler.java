/*
Responsibilities:

- Handle collisions specifically between bullets and invaders within the game.

Data:

- Inherits the core collision handling structure from the CollisionHandler class, providing extensibility for handling various types of collisions.
 */

package main.java.utility;

import main.java.model.GameObject;
import main.java.model.Invader;
import main.java.model.Bullet;

public class BulletInvaderCollisionHandler extends CollisionHandler {

    @Override
    public void handleCollision(GameObject object1, GameObject object2) {
        // Check if object1 is a Bullet and object2 is an Invader
        if (object1 instanceof Bullet && object2 instanceof Invader) {
            // Handle collision logic between Bullet and Invader
            Bullet bullet = (Bullet) object1;
            Invader invader = (Invader) object2;

            System.out.println("Bullet collided with Invader at (" + invader.getX() + ", " + invader.getY() + ")");
        }

        // Pass to the next handler if not handled by this handler
        if (nextHandler != null) {
            nextHandler.handleCollision(object1, object2);
        }
    }
}
