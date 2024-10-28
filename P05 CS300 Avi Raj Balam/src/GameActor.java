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
 * The main class to hold a game actor with its crucial components
 * 
 * @author Avi Raj Balam
 */


import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a game actor in the Froggie Feeding Frenzie game.
 * 
 */
public class GameActor {
    private float[] coordinates; // The position coordinates of the game actor
    private Hitbox hitbox; // The hitbox for collision detection
    protected PImage image; // The image representing the game actor
    protected static PApplet processing; // The Processing environment

    /**
     * Constructor for creating a game actor at the specified position with an image.
     * @param x The x-coordinate of the game actor
     * @param y The y-coordinate of the game actor
     * @param imgPath The path to the image file for the game actor
     */
    public GameActor(float x, float y, String imgPath) {
        this.coordinates = new float[] { x, y };
        this.image = processing.loadImage(imgPath); // Load the image
        this.hitbox = new Hitbox(x, y, image.width, image.height); // Create a hitbox
    }

    /**
     * Sets the Processing environment for the GameActor class.
     * @param processing The Processing environment to set.
     */
    public static void setProcessing(PApplet processing) {
        GameActor.processing = processing;
    }

    /**
     * Gets the x-coordinate of the game actor.
     * @return The x-coordinate.
     */
    public float getX() {
        return coordinates[0];
    }

    /**
     * Gets the y-coordinate of the game actor.
     * @return The y-coordinate.
     */
    public float getY() {
        return coordinates[1];
    }

    /**
     * Sets the x-coordinate of the game actor and updates the hitbox position.
     * @param newX The new x-coordinate to set.
     */
    public void setX(float newX) {
        coordinates[0] = newX;
        hitbox.setPosition(newX, coordinates[1]);
    }

    /**
     * Sets the y-coordinate of the game actor and updates the hitbox position.
     * @param newY The new y-coordinate to set.
     */
    public void setY(float newY) {
        coordinates[1] = newY;
        hitbox.setPosition(coordinates[0], newY);
    }

    /**
     * Gets the hitbox of the game actor.
     * @return The hitbox used for collision detection.
     */
    public Hitbox getHitbox() {
        return hitbox;
    }

    /**
     * Moves the hitbox to the specified coordinates.
     * @param x The new x-coordinate for the hitbox.
     * @param y The new y-coordinate for the hitbox.
     */
    public void moveHitbox(float x, float y) {
        hitbox.setPosition(x, y);
    }

    /**
     * Draws the game actor on the screen if the Processing environment is available.
     */
    public void draw() {
        if (processing != null) {
            // Draw the game actor's image at the specified coordinates
            processing.image(image, coordinates[0] - image.width / 2, coordinates[1] - image.height / 2);
            
            // DEBUG: Draw the hitbox for debugging purposes
            // hitbox.draw();
        }
    }
}
