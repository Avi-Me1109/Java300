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
 * This class extends the bug class to create a windows dvd style bug movement across the screen
 * 
 * @author Avi Raj Balam
 *
 */

import java.util.Random;
import processing.core.PApplet;

/**
 * The BouncingBug class represents a bug that moves in a bouncing pattern.
 * It extends the Bug class and implements the Moveable interface.
 */
public class BouncingBug extends Bug implements Moveable {
    private boolean goDown; // Indicates the vertical movement direction (up or down)
    private boolean goLeft; // Indicates the horizontal movement direction (left or right)
    private static final int POINTS = 100; // Points awarded when this bug is eaten
    private int[] speedNums; // Speed values for horizontal and vertical movement
    private Random randGen; // Random number generator for initial movement direction

    /**
     * Constructor for creating a BouncingBug instance.
     * @param x The initial X-coordinate of the bug.
     * @param y The initial Y-coordinate of the bug.
     * @param dx The horizontal movement speed.
     * @param dy The vertical movement speed.
     */
    public BouncingBug(float x, float y, int dx, int dy) {
        super(x, y, POINTS); // Call the superclass constructor
        this.speedNums = new int[] { dx, dy };
        
        randGen = new Random();
        
        this.goLeft = randGen.nextBoolean(); // Randomly start moving left or right
        this.goDown = randGen.nextBoolean(); // Randomly start moving down or up
    }

    /**
     * Move the BouncingBug based on its direction and speed.
     */
    public void move() {
        if (!shouldMove()) {
            return; // If the bug shouldn't move, exit the method
        }

        // Calculate the new X and Y coordinates based on the current direction
        float newX = getX() + (goLeft ? -speedNums[0] : speedNums[0]);
        float newY = getY() + (goDown ? speedNums[1] : -speedNums[1]);

        // Check screen boundaries and change direction if needed
        if (newX + image.width / 2 >= processing.width || newX - image.width / 2 <= 0) {
            goLeft = !goLeft; // Reverse the horizontal direction
        }

        if (newY + image.height / 2 >= processing.height || newY - image.height / 2 <= 0) {
            goDown = !goDown; // Reverse the vertical direction
        }

        // Update the bug's position
        setX(newX);
        setY(newY);
    }

    /**
     * Check if the BouncingBug should move.
     * @return True, as this bug should always move.
     */
    public boolean shouldMove() {
        return true;
    }
}
