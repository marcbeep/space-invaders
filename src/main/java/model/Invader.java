/*
Responsibilities:

- Represent an invader character within the game.
- Handle the movement of the invader.

Data:

- Inherits properties (position, size, health) from the parent class GameObject.
- The specific type of the invader, which may influence its behaviour (though not explicitly implemented in the provided code).
 */

package main.java.model;

public class Invader extends GameObject {

    public Invader(int x, int y, int width, int height, int health) {
        super(x, y, width, height, health);
    }

    @Override
    public void draw() {
        // TODO: Add drawing logic for the invader here
    }

    /**
     * Moves the invader downward.
     */
    public void move() {
        moveDown();
        System.out.println("Invader moved down to (" + getX() + ", " + getY() + ")");
    }
}
