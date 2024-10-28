import java.util.ArrayList;

/**
 * Represents a group of assignments with a specified percentage of the total grade.
 */
public class AssignmentGroup {
    private ArrayList<SimpleAssignment> assignments;
    public final double PERCENT_OF_TOTAL;

    /**
     * Constructor for an AssignmentGroup with a specified percentage of the total grade.
     *
     * @param percent the percentage of the total grade represented by this group (between 0 and 1)
     * @throws IllegalArgumentException if the percentage is not within the valid range
     */
    public AssignmentGroup(double percent) {
        if (percent < 0 || percent > 1) {
            throw new IllegalArgumentException("Percentage must be between 0 and 1");
        }
        this.PERCENT_OF_TOTAL = percent;
        this.assignments = new ArrayList<>();
    }

    /**
     * Get the number of assignments in this group.
     *
     * @return the number of assignments in the group
     */
    public int getNumAssignments() {
        return assignments.size();
    }

    /**
     * Calculate the total points earned in this group by summing up points earned in all assignments.
     *
     * @return the total points earned in this group
     */
    public double getPoints() {
        double totalPoints = 0.0;
        for (SimpleAssignment assignment : assignments) {
            totalPoints += assignment.getPoints();
        }
        return totalPoints;
    }

    /**
     * Get the total possible points in this group by summing up the possible points in all assignments.
     *
     * @return the total possible points in this group
     */
    public int getTotalPossible() {
        int totalPossible = 0;
        for (SimpleAssignment assignment : assignments) {
            totalPossible += assignment.POINTS_POSSIBLE;
        }
        return totalPossible;
    }

    /**
     * Check if all assignments in this group are complete.
     *
     * @return true if all assignments are complete, false otherwise
     */
    public boolean isComplete() {
        for (SimpleAssignment assignment : assignments) {
            if (!assignment.isComplete()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get an assignment at a specified index within this group.
     *
     * @param i the index of the assignment to access (0-based)
     * @return the assignment at the given index, or null if the index is out of bounds
     */
    public SimpleAssignment getAssignment(int i) {
        if (i >= 0 && i < assignments.size()) {
            return assignments.get(i);
        } else {
            return null;
        }
    }

    /**
     * Add a single assignment to this group.
     *
     * @param assignment the assignment to add to the group
     */
    public void addAssignment(SimpleAssignment assignment) {
        assignments.add(assignment);
    }

    /**
     * Add an array of assignments to this group.
     *
     * @param assignmentBatch the array of assignments to add to the group
     */
    public void addAssignments(SimpleAssignment[] assignmentBatch) {
        for (SimpleAssignment assignment : assignmentBatch) {
            assignments.add(assignment);
        }
    }

    /**
     * Generate a string representation of this assignment group, including details of each assignment.
     *
     * @return a string representation of the assignment group
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < assignments.size(); i++) {
            sb.append("Assignment ").append(i + 1).append(": ").append(assignments.get(i).toString());
            if (i < assignments.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
