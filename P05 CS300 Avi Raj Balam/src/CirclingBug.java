//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////

//
// Title:    Circling Bug Class
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
 * This class extends the bug class to create a circling bug in a fixed position on a certain radius
 * 
 * @author Avi Raj Balam
 *

/**
 * The CirclingBug class represents a bug that moves in a circular pattern around a center point.
 * It extends the Bug class and implements the Moveable interface.
 */
public class CirclingBug extends Bug implements Moveable {
    private float[] circleCenter; // Coordinates of the center of the circular path
    private double radius; // Radius of the circular path
    private double ticks; // Current angle in radians along the circular path
    private int[] tintColor; // Color to tint the bug when drawing
    private static final int POINTS = 200; // Points awarded when this bug is eaten

    /**
     * Constructor for creating a CirclingBug instance.
     * @param circleX The X-coordinate of the center of the circular path.
     * @param circleY The Y-coordinate of the center of the circular path.
     * @param radius The radius of the circular path.
     * @param tintColor The color used to tint the bug when drawing.
     */
    public CirclingBug(float circleX, float circleY, double radius, int[] tintColor) {
        super(initialX(circleX, radius, 0.0), initialY(circleY, radius, 0.0), POINTS);
        this.circleCenter = new float[]{circleX, circleY};
        this.radius = radius;
        this.ticks = 0.0;
        this.tintColor = tintColor;
    }

    /**
     * Calculate the initial X-coordinate based on the center, radius, and angle (ticks).
     * @param centerX The X-coordinate of the center.
     * @param radius The radius of the circular path.
     * @param ticks The angle in radians.
     * @return The initial X-coordinate.
     */
    private static float initialX(float centerX, double radius, double ticks) {
        return (float) (centerX + radius * Math.cos(ticks));
    }

    /**
     * Calculate the initial Y-coordinate based on the center, radius, and angle (ticks).
     * @param centerY The Y-coordinate of the center.
     * @param radius The radius of the circular path.
     * @param ticks The angle in radians.
     * @return The initial Y-coordinate.
     */
    private static float initialY(float centerY, double radius, double ticks) {
        return (float) (centerY + radius * Math.sin(ticks));
    }

    /**
     * Move the CirclingBug along its circular path.
     */
    public void move() {
        if (shouldMove()) {
            ticks += 0.05; // Increment the angle
            float newX = initialX(circleCenter[0], radius, ticks);
            float newY = initialY(circleCenter[1], radius, ticks);
            setX(newX);
            setY(newY);
        }
    }

    /**
     * Draw the CirclingBug with the specified tint color.
     */
    public void draw() {
        // Apply the tint color and draw the image
        processing.tint(tintColor[0], tintColor[1], tintColor[2]);
        super.draw();
        // Reset the tint to white
        processing.tint(255, 255, 255);
    }

    /**
     * Check if the CirclingBug should move.
     * @return True, as this bug should always move.
     */
    public boolean shouldMove() {
        return true;
    }
}
