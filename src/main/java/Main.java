/*
Responsibilities:

- Launch the Space Invaders game by initializing the game controller and starting the game.
- Provide the entry point for the game's execution.

Data:

- None (The Main class primarily serves as an entry point and does not contain any data).
 */

package main.java;

import main.java.controller.GameController;

public class Main {
    public static void main(String[] args) {
        // Get the instance of the GameController (Singleton)
        GameController gameController = GameController.getInstance();

        // Start the game
        gameController.startGame();
    }
}
