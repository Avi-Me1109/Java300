//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////

//
// Title:    Frog Class
// Course:   CS 300 Fall 2023
//
// Author:   Avi Raj Balam
// Email:    abalam@wisc.edu
// Lecturer: (Hobbes LeGault or Mark Mansi or Mouna Kacem)
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Online Sources: https://processing.org/reference#structure
//
///////////////////////////////////////////////////////////////////////////////
// Below is a javadoc class header to complete
/**
 * This class is the main Frog class that holds the player components and movements as well as attack patterns
 * @author Avi Raj Balam
 *

import processing.core.PApplet;

/**
 * The Frog class represents a game character that can be controlled by the player.
 * It extends the GameActor class and implements the Moveable interface.
 */
public class Frog extends GameActor implements Moveable {
    private int health; // The frog's health
    private static final String IMG_PATH = "images/frog.png"; // Path to the frog's image
    private boolean isDragging; // Indicates if the frog is being dragged by the player
    private float oldMouseX; // Previous mouse X position during dragging
    private float oldMouseY; // Previous mouse Y position during dragging
    private Tongue tongue; // The frog's tongue

    /**
     * Constructor for creating a Frog instance.
     * @param x The initial X-coordinate of the frog.
     * @param y The initial Y-coordinate of the frog.
     * @param health The initial health of the frog.
     * @throws IllegalArgumentException if health is less than 1.
     */
    public Frog(float x, float y, int health) {
        super(x, y, IMG_PATH); // Call the superclass constructor
        if (health < 1) {
            throw new IllegalArgumentException("Health must be at least 1");
        }
        this.health = health;
        this.isDragging = false;
        this.oldMouseX = x;
        this.oldMouseY = y;
        this.tongue = new Tongue(x, y); // Initialize the frog's tongue
    }

    /**
     * Get the frog's current health.
     * @return The frog's health.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Get the hitbox of the frog's tongue when it's active.
     * @return The hitbox of the active tongue.
     * @throws IllegalStateException if the tongue is inactive.
     */
    public Hitbox getTongueHitbox() {
        if (tongue.isActive()) {
            return tongue.getHitbox();
        }
        throw new IllegalStateException("The tongue is currently inactive");
    }

    /**
     * Check if the frog is dead (health is zero or less).
     * @return True if the frog is dead, false otherwise.
     */
    public boolean isDead() {
        return health <= 0;
    }

    /**
     * Check if the frog is hit by a Bug.
     * @param b The Bug to check for collision with the frog.
     * @return True if the frog is hit by the Bug, false otherwise.
     */
    public boolean isHitBy(Bug b) {
        return this.getHitbox().doesCollide(b.getHitbox());
    }

    /**
     * Decrease the frog's health by one.
     */
    public void loseHealth() {
        health--;
    }

    /**
     * Handle the mousePressed event for dragging the frog.
     */
    public void mousePressed() {
        if (isMouseOver()) {
            isDragging = true;
        }
    }

    /**
     * Handle the mouseReleased event for stopping frog dragging.
     */
    public void mouseReleased() {
        isDragging = false;
    }

    /**
     * Move the frog if it is currently being dragged.
     */
    public void move() {
        if (isDragging) {
            float mouseX = processing.mouseX;
            float mouseY = processing.mouseY;

            float dx = mouseX - oldMouseX;
            float dy = mouseY - oldMouseY;

            this.setX(this.getX() + dx);
            this.setY(this.getY() + dy);

            oldMouseX = mouseX;
            oldMouseY = mouseY;
        }
    }

    /**
     * Check if the frog should move (is being dragged).
     * @return True if the frog should move, false otherwise.
     */
    public boolean shouldMove() {
        return isDragging;
    }

    /**
     * Start the frog's attack by extending its tongue.
     */
    public void startAttack() {
        if (!tongue.isActive()) {
            tongue.reset();
            tongue.activate();
            tongue.extend(0, 2);
        }
    }

    /**
     * Stop the frog's attack and reset the tongue.
     */
    public void stopAttack() {
        tongue.deactivate();
        tongue.reset();
    }

    /**
     * Check if the frog's tongue hits the screen boundary.
     * @return True if the tongue hits the screen boundary, false otherwise.
     */
    public boolean tongueHitBoundary() {
        return tongue.hitScreenBoundary();
    }

    /**
     * Check if the mouse is over the frog's image.
     * @return True if the mouse is over the frog, false otherwise.
     */
    public boolean isMouseOver() {
        float frogLeft = this.getX() - this.image.width / 2;
        float frogRight = this.getX() + this.image.width / 2;
        float frogTop = this.getY() - this.image.height / 2;
        float frogBottom = this.getY() + this.image.height / 2;

        return (processing.mouseX >= frogLeft && processing.mouseX <= frogRight
                && processing.mouseY >= frogTop && processing.mouseY <= frogBottom);
    }

    /**
     * Draw the frog on the screen, including its tongue if active.
     */
    @Override
    public void draw() {
        super.draw(); // Draw the frog's image
        if (tongue.isActive()) {
            tongue.draw();
            tongue.extend(this.getX(), this.getY() - 2);
        }
    }
}
