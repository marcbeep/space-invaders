/*
Responsibilities:

- Handle collisions specifically between the player and an invader within the game.
- Implement collision resolution logic for the interaction between the player and an invader.
  Optionally, perform additional collision handling logic as needed for this specific collision type.

Data:

- Inherits the core collision handling structure from the CollisionHandler class, providing extensibility for handling various types of collisions.
- Utilizes the object1 and object2 parameters to identify and interact with the player and invader involved in the collision.
- Can access specific methods and properties of the player and invader objects for more advanced collision handling, although this is optional and depends on the specific game requirements.
 */

package main.java.utility;

import main.java.model.GameObject;
import main.java.model.Player;
import main.java.model.Invader;

public class PlayerInvaderCollisionHandler extends CollisionHandler {
    @Override
    public void handleCollision(GameObject object1, GameObject object2) {
        // Check if object1 is a Player and object2 is an Invader
        if (object1 instanceof Player && object2 instanceof Invader) {
            // Handle collision between Player and Invader
            Player player = (Player) object1;
            Invader invader = (Invader) object2;

            System.out.println("Player collided with Invader at (" + invader.getX() + ", " + invader.getY() + ")");

            // TODO: Additional collision logic
        }

        // Pass to the next handler if not handled by this handler
        if (nextHandler != null) {
            nextHandler.handleCollision(object1, object2);
        }
    }
}
