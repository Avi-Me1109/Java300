import java.util.ArrayList;

/**
 * Represents a group of assignments with the ability to drop a specified number of lowest scores.
 */
public class DropAssignmentGroup {
    private ArrayList<SimpleAssignment> assignments;
    private int numDropped;
    public final double PERCENT_OF_TOTAL;

    /**
     * Constructor for a DropAssignmentGroup with a specified percentage of the total grade and number of dropped scores.
     *
     * @param percent the percentage of the total grade represented by this group (between 0 and 1)
     * @param drops   the number of lowest scores to be dropped (at least 1)
     * @throws IllegalArgumentException if the percentage is not within the valid range or drops is less than 1
     */
    public DropAssignmentGroup(double percent, int drops) {
        if (percent < 0 || percent > 1) {
            throw new IllegalArgumentException("Percentage must be between 0 and 1.");
        }
        PERCENT_OF_TOTAL = percent;
        numDropped = drops < 1 ? 1 : drops;
        assignments = new ArrayList<>();
    }

    /**
     * Calculate the total points earned in this group, dropping the lowest scores as specified.
     *
     * @return the total points earned in this group after dropping the lowest scores
     */
    public double getPoints() {
        ArrayList<SimpleAssignment> nonDroppedAssignments = dropLowestN(assignments, numDropped);
        double totalPoints = 0.0;
        for (SimpleAssignment assignment : nonDroppedAssignments) {
            if (assignment.isComplete()) {
                totalPoints += assignment.getPoints();
            }
        }
        return totalPoints;
    }

    /**
     * Get the total possible points in this group, considering only non-dropped assignments.
     *
     * @return the total possible points in this group after dropping the lowest scores
     */
    public int getTotalPossible() {
        ArrayList<SimpleAssignment> nonDroppedAssignments = dropLowestN(assignments, numDropped);
        int totalPossible = 0;
        for (SimpleAssignment assignment : nonDroppedAssignments) {
            totalPossible += assignment.POINTS_POSSIBLE;
        }
        return totalPossible;
    }

    /**
     * Check if all non-dropped assignments in this group are complete.
     *
     * @return true if all non-dropped assignments are complete, false otherwise
     */
    public boolean isComplete() {
        if (assignments.isEmpty()) {
            return false; // If there are no assignments, they cannot be complete.
        }
        ArrayList<SimpleAssignment> nonDroppedAssignments = dropLowestN(assignments, numDropped);
        boolean determiner = true;

        for (int i = 0; i < nonDroppedAssignments.size(); i++) {
            if (!nonDroppedAssignments.get(i).isComplete()) {
                determiner = false;
                break;
            }
        }

        return determiner;
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
     * Get an assignment at a specified index within this group.
     *
     * @param i the index of the assignment to access (0-based)
     * @return the assignment at the given index, or null if the index is out of bounds
     */
    public SimpleAssignment getAssignment(int i) {
        if (i < 0 || i >= assignments.size()) {
            return null;
        }
        return assignments.get(i);
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
     * Drops the lowest scores from a list of assignments.
     *
     * @param assignments the list of assignments
     * @param numDropped  the number of lowest scores to drop
     * @return a list of assignments with the lowest scores dropped
     */
    public static ArrayList<SimpleAssignment> dropLowestN(ArrayList<SimpleAssignment> assignments, int numDropped) {
        if (numDropped <= 0 || numDropped >= assignments.size()) {
            return new ArrayList<>(); // No scores to drop or all scores to be dropped
        }

        // Create a copy of the input list to avoid modifying it
        ArrayList<SimpleAssignment> copiedList = new ArrayList<>(assignments);

        for (int i = 0; i < numDropped; i++) {
            int lowestIndex = getLowestScoreIndex(copiedList);

            if (lowestIndex != -1) {
                copiedList.remove(lowestIndex);
            }
        }

        return copiedList;
    }

    /**
     * Get the index of the assignment with the lowest score in a list of assignments.
     *
     * @param assignments the list of assignments
     * @return the index of the assignment with the lowest score, or -1 if the list is empty
     */
    public static int getLowestScoreIndex(ArrayList<SimpleAssignment> assignments) {
        int lowestIndex = -1;
        double lowestScore = Double.MAX_VALUE;

        for (int i = 0; i < assignments.size(); i++) {
            SimpleAssignment assignment = assignments.get(i);
            if (assignment.getPoints() < lowestScore) {
                lowestScore = assignment.getPoints();
                lowestIndex = i;
            }
        }
        return lowestIndex;
    }

    /**
     * Generate a string representation of this drop assignment group, including details of non-dropped assignments.
     *
     * @return a string representation of the drop assignment group
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ArrayList<SimpleAssignment> nonDroppedAssignments = dropLowestN(assignments, numDropped);
        for (int i = 0; i < nonDroppedAssignments.size(); i++) {
            SimpleAssignment assignment = nonDroppedAssignments.get(i);
            sb.append(i + 1).append(": ").append(assignment.toString()).append("\n");
        }
        return sb.toString();
    }
}
