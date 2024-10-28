/**
 * The SimpleAssignmentTester class is responsible for testing various functionalities
 * of the SimpleAssignment class, including its accessor methods, bonus-related features,
 * and error-handling behavior. It provides a suite of test methods to ensure that
 * SimpleAssignment functions as expected in different scenarios.
 */

public class SimpleAssignmentTester {

    /**
     * Test the accessor methods of the SimpleAssignment class.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testAccessors() {
        SimpleAssignment assignment1 = new SimpleAssignment(100, false);
        SimpleAssignment assignment2 = new SimpleAssignment(80, false);

        // Test the accessor methods before completing the assignment
        if (assignment1.isComplete() || assignment2.isComplete()) {
            return false;
        }

        if (assignment1.getPoints() != 0.0 || assignment2.getPoints() != 0.0) {
            return false;
        }

        // Complete the assignments
        assignment1.complete(90.0);
        assignment2.complete(75.0);

        // Test the accessor methods after completing the assignment
        if (!assignment1.isComplete() || !assignment2.isComplete()) {
            return false;
        }

        if (assignment1.getPoints() != 90.0 || assignment2.getPoints() != 75.0) {
            return false;
        }

        // Test the value of the public constant
        if (assignment1.POINTS_POSSIBLE != 100 || assignment2.POINTS_POSSIBLE != 80) {
            return false;
        }

        return true;
    }

    /**
     * Test various cases for a SimpleAssignment with the bonus option available.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testSimpleBonus() {
        boolean result = testAwardBonus();
        result &= testBonusTwice();
        result &= testNoBonus();
        result &= testBonus105();

        return result;
    }

    /**
     * Test that a completed assignment that scores less than 95% has the correct bonus value
     * added to it when awardBonus() is called.
     *
     * @return true if the test passes, false otherwise
     */
    private static boolean testAwardBonus() {
        SimpleAssignment assignment = new SimpleAssignment(100, true);
        assignment.complete(85.0);
        assignment.awardBonus();
        return assignment.getPoints() == 90.0;
    }

    /**
     * Verify that calling the awardBonus() method a second time does not modify the earned
     * points result.
     *
     * @return true if the test passes, false otherwise
     */
    private static boolean testBonusTwice() {
        SimpleAssignment assignment = new SimpleAssignment(100, true);
        assignment.complete(85.0);
        assignment.awardBonus();
        double firstAwardedPoints = assignment.getPoints();
        assignment.awardBonus();
        double secondAwardedPoints = assignment.getPoints();
        return firstAwardedPoints == secondAwardedPoints;
    }

    /**
     * Verify that calling the awardBonus() method on an assignment with NO bonus available
     * does NOT result in a bonus being applied to the earned points result.
     *
     * @return true if the test passes, false otherwise
     */
    private static boolean testNoBonus() {
        SimpleAssignment assignment = new SimpleAssignment(100, false);
        assignment.complete(85.0);
        assignment.awardBonus();
        return assignment.getPoints() == 85.0;
    }

    /**
     * Verify that calling the awardBonus() method on an assignment whose earned points are > 95%
     * of possible points does NOT result in a score that exceeds the total possible points.
     *
     * @return true if the test passes, false otherwise
     */
    private static boolean testBonus105() {
        SimpleAssignment assignment = new SimpleAssignment(100, true);
        assignment.complete(98.0); // Earned points are 98% of possible points
        assignment.awardBonus();
        return assignment.getPoints() == 100.0; // Should not exceed possible points
    }

    /**
     * Test various error cases in the SimpleAssignment class.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testSimpleErrorCases() {
        boolean result = testBadConstructorInput();
        result &= testBonusIncomplete();
        result &= testBadPointsEarned();
        result &= testCompleteAssignmentTwice();

        return result;
    }

    /**
     * Test the SimpleAssignment constructor with bad input.
     *
     * @return true if the test passes, false otherwise
     */
    private static boolean testBadConstructorInput() {
        try {
            SimpleAssignment assignment = new SimpleAssignment(-10, true); // Negative points

            if (assignment.toString().equals("*/1"))
                return true; // Should throw an exception

            return false;
        } catch (IllegalArgumentException e) {
            return true; // Exception was thrown, test passed for bad input
        }
    }

    /**
     * Test the awardBonus() method on an assignment that has bonus available but is not
     * yet completed.
     *
     * @return true if the test passes, false otherwise
     */
    private static boolean testBonusIncomplete() {
        SimpleAssignment assignment = new SimpleAssignment(100, true);
        assignment.awardBonus(); // Try to award bonus without completing
        return assignment.getPoints() == 0.0; // Should not earn any points
    }

    /**
     * Test the complete() method with input values outside of the allowed range and make
     * sure that the points returned are what you expect for the given error case.
     *
     * @return true if the test passes, false otherwise
     */
    private static boolean testBadPointsEarned() {
        try {
            SimpleAssignment assignment = new SimpleAssignment(100, false);

            // Test with negative points
            assignment.complete(-10.0);
            if (assignment.getPoints() != 0.0) {
                return false; // Should be 0.0
            }

            // Test with points greater than possible points
            assignment = new SimpleAssignment(100, true);
            assignment.complete(110.0);
            if (assignment.getPoints() != 100.0) {
                return false; // Should be capped at possible points
            }

            return true;
        } catch (IllegalArgumentException e) {
            return true; // Exception was thrown, test passed for bad input
        }
    }

    /**
     * Test calling complete() twice with different values, and make sure that the earned point
     * value is NOT updated after the assignment has been completed.
     *
     * @return true if the test passes, false otherwise
     */
    private static boolean testCompleteAssignmentTwice() {
        SimpleAssignment assignment = new SimpleAssignment(100, false);
        assignment.complete(80.0); // Complete with 80 points
        assignment.complete(90.0); // Complete again with 90 points
        return assignment.getPoints() == 80.0; // Should not change after the first completion
    }

    /**
     * Run all the tests in the SimpleAssignmentTester class.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean runAllTests() {
        return testAccessors() && testSimpleBonus() && testSimpleErrorCases();
    }

    public static void main(String[] args) {
        System.out.println("basic: " + (testAccessors() ? "PASS" : "fail"));
        System.out.println("bonus: " + (testSimpleBonus() ? "PASS" : "fail"));
        System.out.println("edge cases: " + (testSimpleErrorCases() ? "PASS" : "fail"));
    }
}
