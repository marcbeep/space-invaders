/*
Responsibilities:

- Create different types of game objects based on the specified type (invaders, player, bullets).
- Initialize and configure the created game objects with appropriate initial positions and attributes.

Data:

- None (The GameObjectFactory class primarily serves as a factory for creating game objects and does not contain specific data).
 */

package main.java.factory;

import main.java.model.GameObject;
import main.java.model.Invader;
import main.java.model.Player;
import main.java.model.Bullet;

public class GameObjectFactory {

    public GameObject createObject(String type) {
        switch (type) {
            case "invader":
                // Create and return an Invader object with initial position and attributes
                int invaderX = (int) (Math.random() * 20);
                return new Invader(invaderX, 0, 1, 1, 1); // Size as 1x1 for a point

            case "player":
                // Create and return a Player object with initial position and attributes
                return new Player(10, 19, 1, 1, 100); // Size as 1x1 for a point

            case "bullet":
                // Create and return a Bullet object with a default initial position
                return new Bullet(0, 0); // Initial position to be adjusted later

            default:
                return null;
        }
    }
}
