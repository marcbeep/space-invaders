/*
Responsibilities:

- Represent a bullet in the game.
- Implement bullet movement logic.

Data:

- Inherits properties (position, size, health) from the parent class GameObject.
 */

package main.java.model;

public class Bullet extends GameObject {

    public Bullet(int x, int y) {
        super(x, y, 1, 1, 1); // Size and health are minimal since it's just a point
    }

    @Override
    public void draw() {
        // TODO: Drawing logic for the bullet
    }

    // Method for the bullet to move upward
    public void move() {
        moveUp();
        System.out.println("Bullet moved up to (" + getX() + ", " + getY() + ")");
    }
}
