/**
 * Represents a group of assignments with scaling applied to the total possible points.
 */
public class ScalingAssignmentGroup {
    private AssignmentGroup group;
    public final double PERCENT_OF_TOTAL;
    private double scalingFactor;

    /**
     * Constructor for a ScalingAssignmentGroup with a specified percentage of the total grade and scaling factor.
     *
     * @param percent the percentage of the total grade represented by this group (between 0 and 1)
     * @param scale   the scaling factor (between 0 and 1)
     * @throws IllegalArgumentException if percent or scale is not within the valid range
     */
    public ScalingAssignmentGroup(double percent, double scale) {
        if (percent < 0 || percent > 1 || scale < 0 || scale > 1) {
            throw new IllegalArgumentException("Percentage and scale must be between 0 and 1.");
        }
        PERCENT_OF_TOTAL = percent;
        scalingFactor = scale;
        group = new AssignmentGroup(percent);
    }

    /**
     * Calculate the total possible points for this group, taking into account the scaling factor.
     *
     * @return the total possible points after applying the scaling factor
     */
    public double getTotalPossible() {
        return group.getTotalPossible() * scalingFactor; // Apply the scaling factor to total possible
    }

    /**
     * Calculate the total points earned in this group, ensuring it does not exceed the scaled total possible.
     *
     * @return the total points earned in this group, capped by the scaled total possible points
     */
    public double getPoints() {
        double totalPossible = this.getTotalPossible();
        double pointsEarned = group.getPoints();

        if (pointsEarned > totalPossible) {
            return totalPossible;
        } else {
            return pointsEarned;
        }
    }

    /**
     * Check if all assignments in this group are complete.
     *
     * @return true if all assignments are complete, false otherwise
     */
    public boolean isComplete() {
        return group.isComplete();
    }

    /**
     * Get the number of assignments in this group.
     *
     * @return the number of assignments in the group
     */
    public int getNumAssignments() {
        return group.getNumAssignments();
    }

    /**
     * Get an assignment at a specified index within this group.
     *
     * @param i the index of the assignment to access (0-based)
     * @return the assignment at the given index, or null if the index is out of bounds
     */
    public SimpleAssignment getAssignment(int i) {
        return group.getAssignment(i);
    }

    /**
     * Add a single assignment to this group.
     *
     * @param assignment the assignment to add to the group
     */
    public void addAssignment(SimpleAssignment assignment) {
        group.addAssignment(assignment);
    }

    /**
     * Add an array of assignments to this group.
     *
     * @param assignmentBatch the array of assignments to add to the group
     */
    public void addAssignments(SimpleAssignment[] assignmentBatch) {
        group.addAssignments(assignmentBatch);
    }

    /**
     * Generate a string representation of the scaling assignment group, showing the percentage earned.
     *
     * @return a formatted string showing the percentage earned in this group
     */
    @Override
    public String toString() {
        double percentEarned = (getPoints() / getTotalPossible()) * 100;
        return String.format("%.2f%%", percentEarned);
    }
}
