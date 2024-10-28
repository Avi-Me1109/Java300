//////////////// FILE HEADER //////////////////////////
//
// Title:    WorkoutBuilderTester
// Course:   CS 300 Fall 2023
//
// Author:   Avi Raj Balam
// Email:    abalam@wisc.edu
// Lecturer: (Hobbes LeGault or Mark Mansi or Mouna Kacem)
//
///////////////////////////////////////////////////////////////////////////////
/**
 * This class serves as a tester for the WorkoutBuilder class, containing methods to check
 * the correctness of various functionalities such as clear, add, remove, and get exercises.
 * It also includes a test suite method to run all the test methods and a demo method to showcase
 * the usage of WorkoutBuilder in a simulated scenario.
 * 
 * @author Avi Raj Balam
 */
import java.util.NoSuchElementException;

public class WorkoutBuilderTester {
  
  /**
   * Checks the correctness of the WorkoutBuilder.clear() method.
   * 
   * @return True if the clear method behaves as expected, false otherwise.
   */
  public static boolean testClear() {
    Exercise.resetIDNumbers();
    WorkoutBuilder list = new WorkoutBuilder();
    list.add(new Exercise(WorkoutType.WARMUP, "stretch"));
    list.add(new Exercise(WorkoutType.PRIMARY, "bench press"));
    list.add(new Exercise(WorkoutType.COOLDOWN, "sit-ups"));

    list.clear();

    return list.isEmpty() && list.size() == 0 && list.getWarmupCount() == 0
            && list.getPrimaryCount() == 0 && list.getCooldownCount() == 0;
  }

  /**
   * Checks the correctness of the WorkoutBuilder.add() method.
   * 
   * @return True if the add method behaves as expected, false otherwise.
   */
  public static boolean testAddExercises() {
    Exercise.resetIDNumbers();
    WorkoutBuilder list = new WorkoutBuilder();
    Exercise warmupExercise = new Exercise(WorkoutType.WARMUP, "stretch");
    Exercise primaryExercise = new Exercise(WorkoutType.PRIMARY, "bench press");
    Exercise cooldownExercise = new Exercise(WorkoutType.COOLDOWN, "sit-ups");

    list.add(primaryExercise);
    list.add(warmupExercise);
    list.add(cooldownExercise);
    
    Exercise cooldownExercise1 = new Exercise(WorkoutType.COOLDOWN, "test1");
    Exercise cooldownExercise2 = new Exercise(WorkoutType.COOLDOWN, "test2");
    
    list.add(cooldownExercise1);
    list.add(cooldownExercise2);
    

    return list.size() == 5 && list.getWarmupCount() == 1
            && list.getPrimaryCount() == 1 && list.getCooldownCount() == 3
            && list.get(0).equals(warmupExercise)
            && list.get(1).equals(primaryExercise)
            && list.get(2).equals(cooldownExercise)
            && list.get(3).equals(cooldownExercise1)
            && list.get(4).equals(cooldownExercise2);
  }

  /**
   * Checks the correctness of BOTH of the WorkoutBuilder.removeExercise() methods.
   * 
   * @return True if the removeExercise methods behave as expected, false otherwise.
   */
  public static boolean testRemoveExercises() {
    Exercise.resetIDNumbers();
    WorkoutBuilder list = new WorkoutBuilder();
    Exercise warmupExercise1 = new Exercise(WorkoutType.WARMUP, "stretch");
    Exercise warmupExercise2 = new Exercise(WorkoutType.WARMUP, "jumping jacks");
    Exercise primaryExercise = new Exercise(WorkoutType.PRIMARY, "bench press");
    Exercise cooldownExercise = new Exercise(WorkoutType.COOLDOWN, "sit-ups");

    list.add(warmupExercise1);
    list.add(warmupExercise2);
    list.add(primaryExercise);
    list.add(cooldownExercise);

    
    list.removeExercise(WorkoutType.COOLDOWN);
    list.removeExercise(primaryExercise.getExerciseID());
    

    return list.size() == 2 && list.getWarmupCount() == 2
            && list.getPrimaryCount() == 0 && list.getCooldownCount() == 0
            && list.get(0).equals(warmupExercise2)
            && list.get(1).equals(warmupExercise1);
  }

  /**
   * Checks the correctness of the WorkoutBuilder.get() method.
   * 
   * @return True if the get method behaves as expected, false otherwise.
   */
  public static boolean testGetExercises() {
    Exercise.resetIDNumbers();
    WorkoutBuilder list = new WorkoutBuilder();
    Exercise warmupExercise = new Exercise(WorkoutType.WARMUP, "stretch");
    Exercise primaryExercise = new Exercise(WorkoutType.PRIMARY, "bench press");
    Exercise cooldownExercise = new Exercise(WorkoutType.COOLDOWN, "sit-ups");

    list.add(warmupExercise);
    list.add(primaryExercise);
    list.add(cooldownExercise);

    return list.get(0).equals(warmupExercise)
            && list.get(1).equals(primaryExercise)
            && list.get(2).equals(cooldownExercise);
  }

  /**
   * A test suite method to run all test methods.
   * 
   * @return True if all test methods pass, false otherwise.
   */
 public static boolean runAllTests() {
   boolean clear = testClear(), 
       add = testAddExercises(), 
       remove = testRemoveExercises(),
       get = testGetExercises();
   
   System.out.println("test clear: "+(clear?"pass":"FAIL"));
   System.out.println("test add: "+(add?"pass":"FAIL"));
   System.out.println("test remove: "+(remove?"pass":"FAIL"));
   System.out.println("test get: "+(get?"pass":"FAIL"));
   
   if(clear == true && add == true && remove == true && get == true) {
     return true;
   }
   
   else {
     return false; 
   }
 }

 public static void main(String[] args) {
   runAllTests();
   Exercise.resetIDNumbers();
   demo();
 }

 /**
  * Helper method to display the size and the count of different boxes stored in a list of boxes
  * 
  * @param list a reference to an InventoryList object
  * @throws NullPointerException if list is null
  */
 private static void displaySizeCounts(WorkoutBuilder list) {
   System.out.println("  Size: " + list.size() + ", warmupCount: " + list.getWarmupCount()
       + ", primaryCount: " + list.getPrimaryCount() + ", cooldownCount: " + list.getCooldownCount());
 }

 /**
  * Demo method showing how to use the implemented classes in P07 Inventory Storage System
  * 
  * @param args input arguments
  */
 public static void demo() {
   // Create a new empty WorkoutBuilder object
   WorkoutBuilder list = new WorkoutBuilder();
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   // Add a primary exercise to an empty list
   list.add(new Exercise(WorkoutType.PRIMARY, "5k run")); // adds PRIMARY: 5k run (1)
   System.out.println(list); // prints list's content
   list.add(new Exercise(WorkoutType.WARMUP, "stretch")); // adds WARMUP: stretch (2) at the head of the list
   System.out.println(list); // prints list's content
   list.add(new Exercise(WorkoutType.PRIMARY, "bench press")); // adds PRIMARY: bench press (3)
   System.out.println(list); // prints list's content
   list.add(new Exercise(WorkoutType.WARMUP, "upright row")); // adds WARMUP: upright row (4) at the head of the list
   System.out.println(list); // prints list's content
   list.add(new Exercise(WorkoutType.WARMUP, "db bench")); // adds WARMUP: db bench (5) at the head of the list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   // Add more exercises to list and display its contents
   list.add(new Exercise(WorkoutType.COOLDOWN, "stretch")); // adds COOLDOWN: stretch (6) at the end of the list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   list.add(new Exercise(WorkoutType.COOLDOWN, "sit-ups")); // adds COOLDOWN: sit-ups (7) at the end of the list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   list.removeExercise(WorkoutType.COOLDOWN); // removes COOLDOWN: sit-ups (7) from the list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   list.add(new Exercise(WorkoutType.PRIMARY, "deadlift")); // adds PRIMARY: deadlift (8)
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   list.removeExercise(WorkoutType.COOLDOWN); // removes COOLDOWN: stretch (6) from the list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   list.removeExercise(WorkoutType.WARMUP); // removes WARMUP: db bench (5)
   System.out.println(list); // prints list's content
   list.removeExercise(3); // removes PRIMARY: bench press (3) from the list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   try {
     list.removeExercise(25); // tries to remove box #25
   } catch (NoSuchElementException e) {
     System.out.println(e.getMessage());
   }
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   // remove all warm-ups
   while (list.getWarmupCount() != 0) {
     list.removeExercise(WorkoutType.WARMUP);
   }
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   list.removeExercise(1); // removes PRIMARY: 5k run (1) from the list -> empty list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   list.add(new Exercise(WorkoutType.COOLDOWN, "walk")); // adds COOLDOWN: walk (9) to the list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   list.removeExercise(8); // removes PRIMARY: deadlift (8) from the list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   list.removeExercise(WorkoutType.COOLDOWN); // removes COOLDOWN: walk (9) from the list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   list.add(new Exercise(WorkoutType.WARMUP, "pull-up")); // adds WARMUP: pull-up (10) to the list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   list.removeExercise(10); // removes WARMUP: pull-up (10) from the list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
 }

}
