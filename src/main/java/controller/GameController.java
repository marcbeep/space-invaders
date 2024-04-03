/*
Responsibilities:

- Manage the overall flow of the game.
- Initialize game components, including players, invaders, and bullets.
- Simulate player and invader actions during the game.
- Handle game over conditions.
- Control game level progression.
- Handle collisions between game objects.
- Keep track of the player's score.
- Play game-related sounds.

Data:

- Game state (running, paused, game over).
- List of invaders.
- List of bullets.
- Player information, including position and score.
- GameObjectFactory instance for creating game objects.
- CollisionHandler instance for handling collisions.
- SoundManager instance for managing game sounds.
 */

package main.java.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Iterator;

import main.java.factory.GameObjectFactory;
import main.java.model.Invader;
import main.java.model.Player;
import main.java.model.Bullet;
import main.java.utility.CollisionHandler;
import main.java.utility.PlayerInvaderCollisionHandler;
import main.java.utility.SoundManager;

public class GameController {
    private static GameController instance;
    private boolean isRunning;
    private List<Invader> invaders;
    private List<Bullet> bullets;
    private Player player;
    private GameObjectFactory factory;
    private CollisionHandler collisionChain;
    private SoundManager soundManager = SoundManager.getInstance();

    public boolean isRunning() {
        return isRunning;
    }

    private GameController() {
        factory = new GameObjectFactory();
        invaders = new ArrayList<>();
        bullets = new ArrayList<>();
        collisionChain = initializeCollisionChain();
    }

    public static synchronized GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public void startGame() {
        isRunning = true;
        System.out.println("Game Started");
        System.out.println("------------");
        initializeGame();
        simulateGame();
    }

    private void initializeGame() {
        player = (Player) factory.createObject("player");
        player.setPosition(10, 19);
        initializeLevel(1);
    }

    private void initializeLevel(int level) {
        System.out.println("\nStarting Level " + level);
        System.out.println("--------------\n");
        initializeInvaders(1 * level);
    }

    private void initializeInvaders(int count) {
        invaders.clear();
        for (int i = 0; i < count; i++) {
            Invader invader = (Invader) factory.createObject("invader");
            int x = (int) (Math.random() * 20);
            invader.setPosition(x, 0);
            invaders.add(invader);
            System.out.println("Invader spawned at (" + x + ", 0)");
        }
    }

    private CollisionHandler initializeCollisionChain() {
        return new PlayerInvaderCollisionHandler();
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted during sleep: " + e.getMessage());
        }
    }

    private void simulatePlayerActions() {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            movePlayerAndFireBullet(rand);
            sleep(500);
        }
    }

    private void movePlayerAndFireBullet(Random rand) {
        if (rand.nextBoolean()) {
            player.moveLeft();
        } else {
            player.moveRight();
        }
        fireBullet();
    }

    private void fireBullet() {
        Bullet bullet = (Bullet) factory.createObject("bullet");
        if (bullet != null) {
            bullet.setPosition(player.getX(), player.getY() - 1);
            bullets.add(bullet);
            soundManager.playSound("Pew! Player shoots and moves from (" + player.getX() + "," + player.getY() + ")");
        }
    }

    private boolean runGameCycle() {
        simulatePlayerActions();

        if (simulateInvaderActions()) {
            return true;
        }

        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            if (checkBulletCollisions(bullet, bulletIterator)) {
                return true;
            }
        }

        return false;
    }

    private boolean simulateInvaderActions() {
        Iterator<Invader> invaderIterator = invaders.iterator();
        while (invaderIterator.hasNext()) {
            Invader invader = invaderIterator.next();
            invader.moveDown();

            if (invader.isCollidingWith(player)) {
                soundManager.playSound(
                        "[CRASH!] Player collided with an invader at (" + invader.getX() + "," + invader.getY() + ")");
                endGame();
                return true;
            } else if (invader.getY() >= 19) {
                invaderIterator.remove();
            }
        }
        return false;
    }

    private void processBullets() {
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            bullet.moveUp();
            if (bullet.getY() < 0) {
                bulletIterator.remove();
            } else {
                checkBulletCollisions(bullet, bulletIterator);
            }
        }
    }

    private boolean checkBulletCollisions(Bullet bullet, Iterator<Bullet> bulletIterator) {
        try {
            bullet.moveUp();
            if (bullet.getY() < 0) {
                bulletIterator.remove();
            } else {
                List<Invader> invadersToRemove = new ArrayList<>();
                for (Invader invader : invaders) {
                    if (bullet.isCollidingWith(invader)) {
                        bulletIterator.remove();
                        invadersToRemove.add(invader);
                        player.increaseScore(1);
                        System.out.println("**********************************");
                        soundManager.playSound("[BOOM!] " + player.getScore() + " Invader(s) destroyed thus far!");
                        System.out.println("**********************************");
                    }
                }
                invaders.removeAll(invadersToRemove);
                if (invaders.isEmpty()) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Whoops. There has been an error in the simulation. Try running it again.");
        }
        return false;
    }

    public void simulateGame() {
        for (int level = 1; level <= 5 && isRunning; level++) {
            initializeLevel(level);

            boolean levelCompleted = false;
            while (!levelCompleted && isRunning) {
                levelCompleted = runGameCycle();
                if (invaders.isEmpty()) {
                    levelCompleted = true;
                }
            }

            if (!isRunning) {
                break;
            }
        }

        if (isRunning) {
            System.out.println("Player completed all levels! Invaders killed: " + player.getScore());
        }
    }

    public void endGame() {
        isRunning = false;
        if (!invaders.isEmpty()) {
            System.out.println("Game Over. Player lost but destroyed " + player.getScore() + " Invaders.");
        }
    }
}
