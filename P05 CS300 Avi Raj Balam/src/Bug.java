//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////

//
// Title:    Bug Class
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
 * This class is the main bug class tthat holds the points and image as well as crucial bug components
 * 
 * @author Avi Raj Balam
 */
public class Bug extends GameActor {
    private static final String IMG_PATH = "images/bug.png"; // Path to the bug's image
    private int points; // The points awarded when the bug is eaten

    /**
     * Constructor for creating a Bug instance.
     * @param x The initial X-coordinate of the bug.
     * @param y The initial Y-coordinate of the bug.
     * @param points The number of points awarded when the bug is eaten.
     */
    public Bug(float x, float y, int points) {
        super(x, y, IMG_PATH); // Call the superclass constructor
        this.points = points;
    }

    /**
     * Get the number of points awarded when the bug is eaten.
     * @return The points value of the bug.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Check if the bug is eaten by a Frog.
     * @param f The Frog object to check for collision with the bug.
     * @return True if the bug is eaten by the Frog, false otherwise.
     */
    public boolean isEatenBy(Frog f) {
        // Check if this Bug's Hitbox overlaps with the Frog's Tongue's Hitbox
        if (f.getTongueHitbox().doesCollide(getHitbox())) {
            return true;
        } else {
            return false;
        }
    }
}
