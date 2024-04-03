/*
Responsibilities:

- Represent a generic game object in the game.

Data:

- Position (x, y) to determine the object's location on the game canvas.
- Size (width, height) to define the object's dimensions.
- Health (if applicable) to track the object's state and potential damage.
 */

package main.java.model;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int health;
    private final int canvasSize = 20;

    public GameObject(int x, int y, int width, int height, int health) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = health;
    }

    /**
     * Abstract method to be implemented by subclasses for rendering.
     */
    public abstract void draw();

    /**
     * Moves the object to the left, if possible.
     */
    public void moveLeft() {
        if (x > 0)
            x--;
    }

    /**
     * Moves the object to the right, if possible.
     */
    public void moveRight() {
        if (x < canvasSize - width)
            x++;
    }

    /**
     * Moves the object down, if possible.
     */
    public void moveDown() {
        if (y < canvasSize - height)
            y++;
    }

    /**
     * Moves the object up, if possible.
     */
    public void moveUp() {
        if (y > 0)
            y--;
    }

    /**
     * Checks if this object is colliding with another game object based on
     * coordinates.
     */
    public boolean isCollidingWith(GameObject other) {
        return this.x == other.x && this.y == other.y;
    }

    // Getters and Setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Sets the position of the object.
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Handles damage or impact on the object.
     */
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            onDestruction();
        }
    }

    /**
     * Handles the object's destruction (if needed).
     */
    protected void onDestruction() {
        // TODO: Implement this method in subclasses to handle destruction
    }
}
