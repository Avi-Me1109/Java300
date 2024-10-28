//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////

//
// Title:    Bouncing Bug Class
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
 * Represents a strong bug in the Froggie Feeding Frenzie game. Extends Bug and implements Moveable.
 * 
 * @author Avi Raj Balam
 */

import processing.core.PApplet;
import processing.core.PImage;

public class StrongBug extends Bug implements Moveable {
    private final int MAX_HEALTH; // The maximum health of the strong bug
    private int currentHealth; // The current health of the strong bug
    private static final int POINTS = 500; // The points awarded for eating a strong bug

    /**
     * Constructor for creating a strong bug with a specific initial position and health.
     * @param x The x-coordinate of the strong bug
     * @param y The y-coordinate of the strong bug
     * @param health The initial health of the strong bug
     * @throws IllegalArgumentException if health is less than 1
     */
    public StrongBug(float x, float y, int health) {
        super(x, y, POINTS); // Initialize as a Bug worth 500 points
        MAX_HEALTH = health;
        if (health < 1) {
            throw new IllegalArgumentException("Health must be at least 1");
        }
        currentHealth = health;
    }

    /**
     * Checks if the strong bug is dead (health is zero or less).
     * @return true if the strong bug is dead, false otherwise
     */
    public boolean isDead() {
        return currentHealth <= 0;
    }

    /**
     * Decreases the health of the strong bug and reduces its size if it's not dead.
     */
    public void loseHealth() {
        if (!isDead()) {
            currentHealth--;
            // Reduce the size of the image and adjust the hitbox dimensions
            float newWidth = (float) (image.width * 0.75);
            float newHeight = (float) (image.height * 0.75);
            image.resize((int) newWidth, (int) newHeight);
            getHitbox().changeDimensions(newWidth, newHeight);
        }
    }

    /**
     * Moves the strong bug horizontally on the screen if it's not dead.
     */
    public void move() {
        if (shouldMove()) {
            setX((getX() + 3) % processing.width); // Wrap around the screen
        }
    }

    /**
     * Checks if the strong bug should move (not dead).
     * @return true if the strong bug should move, false if it's dead
     */
    public boolean shouldMove() {
        return !isDead();
    }

    /**
     * Checks if the strong bug is eaten by a frog and reduces its health if there is a collision.
     * @param f The frog attempting to eat the strong bug
     * @return true if the strong bug is eaten, false otherwise
     */
    public boolean isEatenBy(Frog f) {
        if (getHitbox().doesCollide(f.getTongueHitbox())) {
            loseHealth();
            return true;
        }
        return false;
    }
}
