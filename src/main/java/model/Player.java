/*
Responsibilities:

- Represent the player's spaceship within the game.
- Handle player input, including movement and shooting.

Data:

- Inherits properties (position, size, health) from the parent class GameObject.
- Player's score, which tracks the player's in-game achievements.
 */

package main.java.model;

public class Player extends GameObject {

    private int score;

    public Player(int x, int y, int width, int height, int health) {
        super(x, y, width, height, health);
        this.score = 0;
    }

    @Override
    public void draw() {
        // TODO: Add drawing logic
    }

    /**
     * Handles player input for movement and shooting.
     * 
     * @param input The input command, e.g., "moveLeft", "moveRight", "shoot".
     */
    public void handleInput(String input) {
        switch (input) {
            case "moveLeft":
                moveLeft();
                System.out.println("Player moved left to (" + getX() + ", " + getY() + ")");
                break;
            case "moveRight":
                moveRight();
                System.out.println("Player moved right to (" + getX() + ", " + getY() + ")");
                break;
            case "shoot":
                // TODO: Add logic for shooting here
                System.out.println("Player shoots a bullet");
                break;
            default:
                System.out.println("Unknown command");
                break;
        }
    }

    /**
     * Increases the player's score.
     * 
     * @param points The points to increase the score by.
     */
    public void increaseScore(int points) {
        score += points;
    }

    /**
     * Gets the player's current score.
     * 
     * @return The player's score.
     */
    public int getScore() {
        return score;
    }
}
