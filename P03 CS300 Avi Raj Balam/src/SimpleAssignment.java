import java.util.Random;

/**
 * Represents a simple assignment with points, completion status, and bonus availability.
 */
public class SimpleAssignment {
    private boolean bonusAvailable;
    private boolean isComplete;
    public final int POINTS_POSSIBLE;
    private double pointsEarned;

    /**
     * Constructor for a SimpleAssignment with a given maximum points possible.
     *
     * @param points the maximum points possible for the assignment
     */
    public SimpleAssignment(int points) {
        if (points < 1) {
            POINTS_POSSIBLE = 1;
        } else {
            POINTS_POSSIBLE = points;
        }
        isComplete = false;
        bonusAvailable = false;
    }

    /**
     * Constructor for a SimpleAssignment with a given maximum points possible and bonus availability.
     *
     * @param points the maximum points possible for the assignment
     * @param bonus  true if bonus points are available, false otherwise
     */
    public SimpleAssignment(int points, boolean bonus) {
        if (points < 1) {
            POINTS_POSSIBLE = 1;
        } else {
            POINTS_POSSIBLE = points;
        }
        isComplete = false;
        bonusAvailable = bonus;
    }

    /**
     * Gets the points earned for the assignment.
     *
     * @return the points earned for the assignment if it is complete, otherwise 0
     */
    public double getPoints() {
        if (isComplete) {
            return pointsEarned;
        } else {
            return 0;
        }
    }

    /**
     * Checks if the assignment is complete.
     *
     * @return true if the assignment is complete, false otherwise
     */
    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Completes the assignment with a given score, considering bonus points if available.
     *
     * @param score the score to be assigned to the assignment
     */
    public void complete(double score) {
        if (!isComplete) {
            if (score < 0) {
                pointsEarned = 0;
            } else if (score > POINTS_POSSIBLE) {
                pointsEarned = POINTS_POSSIBLE;
            } else {
                pointsEarned = score;
            }

            if (bonusAvailable) {
                double bonusPoints = POINTS_POSSIBLE * 0.05;
                pointsEarned = Math.min(pointsEarned + bonusPoints, POINTS_POSSIBLE);
                bonusAvailable = false;
            }

            isComplete = true;
        }
    }

    /**
     * Awards bonus points to the assignment if it is complete and bonus points are available.
     */
    public void awardBonus() {
        if (isComplete && bonusAvailable) {
            double bonusPoints = POINTS_POSSIBLE * 0.05;
            pointsEarned = Math.min(pointsEarned + bonusPoints, POINTS_POSSIBLE);
            bonusAvailable = false;
        }
    }

    /**
     * Generates an appropriate string representation of the assignment.
     *
     * @return a string containing the earned points and maximum possible points
     */
    @Override
    public String toString() {
        if (isComplete) {
            return pointsEarned + "/" + POINTS_POSSIBLE;
        } else {
            return "*/" + POINTS_POSSIBLE;
        }
    }

    /**
     * Generates an array of random SimpleAssignments with specified properties.
     *
     * @param n        the number of assignments to create
     * @param maxScore the maximum possible score for each assignment
     * @return an array of SimpleAssignments with random scores and bonus availability
     */
    public static SimpleAssignment[] makeRandomAssignments(int n, int maxScore) {
        Random random = new Random();
        SimpleAssignment[] assignments = new SimpleAssignment[n];

        for (int i = 0; i < n; i++) {
            double mean = 0.8 * maxScore;
            double stdDev = 0.15 * maxScore;
            double score = Math.max(0, random.nextGaussian() * stdDev + mean);
            assignments[i] = new SimpleAssignment(maxScore, random.nextBoolean());
            assignments[i].complete(score);
        }

        return assignments;
    }
}
