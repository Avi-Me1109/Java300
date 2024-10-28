/**
 * AssignmentGroupTester is a class for testing the functionality of AssignmentGroup and its subclasses.
 * It contains a suite of test methods to ensure that these classes perform as expected in different scenarios.
 */
public class AssignmentGroupTester {

  /**
   * Tests the addition of a single assignment to an AssignmentGroup.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testAddOneAssignment() {
    
    AssignmentGroup group = new AssignmentGroup(0.2);
    SimpleAssignment assignment = new SimpleAssignment(100, true);

    group.addAssignment(assignment);

    return group.getNumAssignments() == 1 && group.getAssignment(0) == assignment;
    
  }
  
  /**
   * Tests the addition of multiple assignments to an AssignmentGroup.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testAddManyAssignments() {
    
    AssignmentGroup group = new AssignmentGroup(0.2);
    SimpleAssignment[] assignments = new SimpleAssignment[]{
      new SimpleAssignment(100, true),
      new SimpleAssignment(80, false),
      new SimpleAssignment(90, true)
    };

    group.addAssignments(assignments);

    if (group.getNumAssignments() != 3) {
      return false;
    }

    for (int i = 0; i < 3; i++) {
      if (group.getAssignment(i) != assignments[i]) {
        return false;
      }
    }

    return true;
    
  }
  
  // PROVIDED
  // Verify that the getTotalPossible() method returns the expected value
  // in EACH of the classes which implements the method
  public static boolean testGetTotal() {
    boolean result = true;
    result &= testGroupTotal();
    result &= testDropTotal();
    result &= testScaledTotal();
    
    return result;
  }
  

  /**
   * Retrieves the total possible score for the assignments in this AssignmentGroup.
   *
   * @return the total possible score
   */
  private static boolean testGroupTotal() {
    
    AssignmentGroup group = new AssignmentGroup(0.2);
    SimpleAssignment[] assignments = new SimpleAssignment[]{
      new SimpleAssignment(100, true),
      new SimpleAssignment(80, false),
      new SimpleAssignment(90, true)
    };
    group.addAssignments(assignments);

    int expectedTotal = 100 + 80 + 90;
    return group.getTotalPossible() == expectedTotal;
    
  }
  

  /**
   * Retrieves the total possible score for the assignments in this DropAssignmentGroup.
   *
   * @return the total possible score
   */
  private static boolean testDropTotal() {
    
    DropAssignmentGroup dropGroup = new DropAssignmentGroup(0.2, 1);
    SimpleAssignment[] assignments = new SimpleAssignment[]{
      new SimpleAssignment(100, false),
      new SimpleAssignment(50, false),
      new SimpleAssignment(90, false)
    };
    
    assignments[0].complete(20);
    assignments[1].complete(30);
    assignments[2].complete(10);
    
    dropGroup.addAssignments(assignments);
    
    int expectedTotal = 100 + 50;
    return dropGroup.getTotalPossible() == expectedTotal;
    
  }
  

  /**
   * Retrieves the total possible score for the assignments in this ScalingAssignmentGroup.
   *
   * @return the total possible score
   */
  private static boolean testScaledTotal() {
    
    ScalingAssignmentGroup scaledGroup = new ScalingAssignmentGroup(0.2, 0.5);
    SimpleAssignment[] assignments = new SimpleAssignment[]{
      new SimpleAssignment(100, false),
      new SimpleAssignment(80, false),
      new SimpleAssignment(90, true)
    };
    scaledGroup.addAssignments(assignments);

    int expectedTotal = (int) (100 * 0.5 + 80 * 0.5 + 90 * 0.5);
    return scaledGroup.getTotalPossible() == expectedTotal;
    
  }
  
  // PROVIDED
  // Verify that the getPoints() method returns the expected value in EACH
  // of the classes which implements the method
  public static boolean testGetPoints() {
    boolean result = true;
    result &= testGroupPoints();
    result &= testDropPoints();
    result &= testScaledPoints();
    
    return result;
  }
  

  /**
   * Retrieves the total points earned for the assignments in this AssignmentGroup.
   *
   * @return the total points earned
   */
  private static boolean testGroupPoints() {
    
    AssignmentGroup group = new AssignmentGroup(0.2);
    SimpleAssignment[] assignments = new SimpleAssignment[]{
      new SimpleAssignment(100, false),
      new SimpleAssignment(80, false),
      new SimpleAssignment(90, false)
    };
    
    group.addAssignments(assignments);
    
    assignments[0].complete(95.0);
    assignments[1].complete(75.0);
    assignments[2].complete(85.0);

    double expectedPoints = 95.0 + 75.0 + 85.0;
    return group.getPoints() == expectedPoints;
    
  }
  

  /**
   * Retrieves the total points earned for the assignments in this DropAssignmentGroup.
   *
   * @return the total points earned
   */
  private static boolean testDropPoints() {
    
    DropAssignmentGroup dropGroup = new DropAssignmentGroup(0.2, 1);
    SimpleAssignment[] assignments = new SimpleAssignment[]{
      new SimpleAssignment(100, false),
      new SimpleAssignment(80, false),
      new SimpleAssignment(90, false)
    };
    
    dropGroup.addAssignments(assignments);
    
    assignments[0].complete(95.0);
    assignments[1].complete(75.0);
    assignments[2].complete(85.0);

    // One lowest assignment should be dropped (75.0)
    double expectedPoints = 95.0 + 85.0;
    return dropGroup.getPoints() == expectedPoints;
    
  }
  

  /**
   * Retrieves the total points earned for the assignments in this ScalingAssignmentGroup.
   *
   * @return the total points earned
   */
  private static boolean testScaledPoints() {
    ScalingAssignmentGroup group = new ScalingAssignmentGroup(0.2, 0.1); // 20% of total grade, scale by 10%
    SimpleAssignment assignment1 = new SimpleAssignment(100, false);
    SimpleAssignment assignment2 = new SimpleAssignment(80, false);

    SimpleAssignment[] assignmentBatch = { assignment1, assignment2 };
    group.addAssignments(assignmentBatch);

    assignment1.complete(90.0);
    assignment2.complete(75.0);

    // Calculate the expected points after scaling
    double expectedPoints = (100 + 80) * 0.1; // Scaled by 10%, (100 + 80) * 0.1 = 18.0
     
    
    if(group.getPoints() == expectedPoints) {
      return true;
    }
    else {
      return false;
    }
}

  

  /**
   * Checks if all assignments in this AssignmentGroup are complete.
   *
   * @return true if all assignments are complete, false otherwise
   */
  public static boolean testComplete() {
    
    AssignmentGroup group = new AssignmentGroup(0.2); // 20% of total grade
    SimpleAssignment assignment1 = new SimpleAssignment(100, true);
    SimpleAssignment assignment2 = new SimpleAssignment(80, false);
    
    SimpleAssignment[] assignmentBatch = { assignment1, assignment2 };
    group.addAssignments(assignmentBatch);

    return !group.isComplete(); // None of the assignments are complete initially
    
  }
  
  /**
   * Run all the tests in the SimpleAssignmentTester class.
   *
   * @return true if all tests pass, false otherwise
   */
  
  public static void main(String[] args) {
    if (SimpleAssignmentTester.runAllTests()) {
      System.out.println("add one: "+(testAddOneAssignment()?"PASS":"fail"));
      System.out.println("add many: "+(testAddManyAssignments()?"PASS":"fail"));
      System.out.println("get total: "+(testGetTotal()?"PASS":"fail"));
      System.out.println("get points: "+(testGetPoints()?"PASS":"fail"));
      System.out.println("complete: "+(testComplete()?"PASS":"fail"));
    }
    else {
      System.out.println("Your SimpleAssignment implementation does NOT pass its tests!\n"
          + "Do NOT continue with AssignmentGroup until you have passed all SimpleAssignment tests.");
    }
  }
  
}
