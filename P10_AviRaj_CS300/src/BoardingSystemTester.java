//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: BoardingQueue Tester class
// Course: CS 300 Fall 2023
//
// Author: Avi Raj Balam
// Email: abalam@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * This is a Utility class which implements tester methods to ensure the correctness of the
 * implementation of the main operations defined in cs300 fall 2023 p10 Airplane Boarding System.
 *
 */
public class BoardingSystemTester {


  /**
   * Ensures the correctness of Passenger.compareTo() method when called to compare two Passenger
   * objects having different boarding groups.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPassengerCompareToDifferentGroup() {
    Passenger p1 = new Passenger("John", Group.A, true);
    Passenger p2 = new Passenger("Jane", Group.B, true);

    boolean determiner = true;

    determiner = p1.compareTo(p2) < 0;
    determiner = p2.compareTo(p1) > 0;


    Passenger p3 = new Passenger("Jake", Group.A, true);

    p3.resetPassengerOrder();

    Passenger p4 = new Passenger("Sam", Group.B, true);

    p4.resetPassengerOrder();

    determiner = p3.compareTo(p4) < 0;
    determiner = p4.compareTo(p3) > 0;

    return determiner;
  }

  /**
   * Ensures the correctness of Passenger.compareTo() method when called to compare two Passenger
   * objects having the same boarding group, and different arrival orders.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPassengerCompareToSameGroupDifferentArrival() {
    Passenger p1 = new Passenger("John", Group.A, true);
    Passenger p2 = new Passenger("Jane", Group.A, true);

    p1.resetPassengerOrder();
    p2.resetPassengerOrder();

    return p1.compareTo(p2) < 0 && p2.compareTo(p1) > 0;
  }

  /**
   * Ensures two passengers having the SAME boarding group and with the SAME order of arrival are
   * equal (compareTo should return 0).
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPassengerCompareToSameGroupSameArrival() {
    Passenger p1 = new Passenger("John", Group.A, true);


    p1.resetPassengerOrder();

    Passenger p2 = new Passenger("Jane", Group.A, true);

    p2.resetPassengerOrder();

    return p1.compareTo(p2) == 0 && p2.compareTo(p1) == 0;
  }

  /**
   * Ensures the correctness of the constructor for BoardingQueue class.
   * 
   * This tester should implement at least the following test cases:
   *
   * - Calling the constructor of the BoardingQueue class with an invalid capacity should throw an
   * IllegalArgumentException - Calling the constructor or the BoardingQueue class with a valid
   * capacity should not throw any errors, and should result in a new BoardingQueue object which is
   * empty, has size 0, a capacity equal to the capacity that was passed as a parameter, and the
   * heap array contains only null references.
   *
   * @return true if the constructor of the BoardingQueue functions properly, false otherwise
   */
  public static boolean testBoardingQueueConstructor() {
    try {
      // Testing with invalid capacity
      new BoardingQueue(-1);
      return false; // Should have thrown an IllegalArgumentException
    } catch (IllegalArgumentException e) {
      // This is expected, do nothing
    }

    // Testing with valid capacity
    int capacity = 10;
    BoardingQueue boardingQueue = new BoardingQueue(capacity);

    return boardingQueue.size() == 0 && boardingQueue.capacity() == capacity
        && Arrays.stream(boardingQueue.toArray()).allMatch(e -> e == null);
  }

  /**
   * Tests the functionality of BoardingQueue.peekBest() method by calling peekBest on an empty
   * queue and verifying it throws a NoSuchElementException.
   * 
   * @return true if BoardingQueue.peekBest() verifies a correct functionality, false otherwise
   */
  public static boolean testPeekBestEmptyQueue() {
    BoardingQueue boardingQueue = new BoardingQueue(10);
    try {
      boardingQueue.peekBest();
      return false; // Should have thrown a NoSuchElementException
    } catch (NoSuchElementException e) {
      // This is expected, do nothing
    }

    return true;
  }

  /**
   * Tests the functionality of BoardingQueue.peekBest() method by calling peekBest on a non-empty
   * queue and ensures it
   * 
   * 1) returns the Passenger having the highest priority (the minimum), and 2) does not remove that
   * Passenger from the boarding queue.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPeekBestNonEmptyQueue() {
    BoardingQueue boardingQueue = new BoardingQueue(10);
    Passenger p1 = new Passenger("John", Group.A, true);
    Passenger p2 = new Passenger("Jane", Group.B, true);

    boardingQueue.enqueue(p1);
    boardingQueue.enqueue(p2);

    Passenger peekedPassenger = boardingQueue.peekBest();

    return peekedPassenger.equals(p1) && boardingQueue.size() == 2;
  }

  /**
   * Tests the functionality of the BoardingQueue.enqueue() method by calling enqueue() on an empty
   * queue and ensuring the method 1) adds the Passenger and 2) increments the size.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testEnqueueToEmptyQueue() {
    BoardingQueue boardingQueue = new BoardingQueue(10);
    Passenger p1 = new Passenger("John", Group.A, true);

    boolean result = boardingQueue.enqueue(p1);


    return result && boardingQueue.size() == 1;
  }


  /**
   * Tests the functionality of the BoardingQueue.enqueue() method by calling enqueue() on a
   * non-empty queue and ensuring it
   * 
   * 1) inserts the Passenger at the proper position of the heap, increments the size by one, and
   * returns true, if the queue was not already full.
   * 
   * 2) returns false, without making any changes to the size of the queue or the array heap, if the
   * method is called on a full queue.
   * 
   * Try adding at least 5 Passengers.
   * 
   * @return true if tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testEnqueueToNonEmptyQueue() {
    BoardingQueue boardingQueue = new BoardingQueue(5);
    Passenger p1 = new Passenger("passenger1", Group.A, false);
    Passenger p2 = new Passenger("passenger2", Group.A, false);
    Passenger p3 = new Passenger("passenger3", Group.B, false);
    Passenger p4 = new Passenger("passenger4", Group.B, false);
    Passenger p5 = new Passenger("passenger5", Group.C, false);

    boardingQueue.enqueue(p2);
    boardingQueue.enqueue(p1);
    boardingQueue.enqueue(p3);
    boardingQueue.enqueue(p4);
    boardingQueue.enqueue(p5);


    if (boardingQueue.size() != 5) {
      return false;
    }

    Passenger[] boardingQueueArray = boardingQueue.toArray();
    Passenger[] expectedArray = new Passenger[] {p1, p2, p3, p4, p5};

    if (!Arrays.deepEquals(boardingQueueArray, expectedArray)) {
      System.out.println("Not Equals");
      return false;
    }

    Passenger p6 = new Passenger("passenger6", Group.A, true);
    boolean QueueFull = boardingQueue.enqueue(p6);
    boardingQueueArray = boardingQueue.toArray();

    if (!Arrays.deepEquals(boardingQueueArray, expectedArray)) {
      System.out.println("Not Equals 2");
      return false;
    }


    return !QueueFull && boardingQueue.size() == 5 && boardingQueueArray.length == 5;
  }

  /**
   * Tests the functionality of BoardingQueue.dequeue() method by calling dequeue() on an empty
   * queue and ensures a NoSuchElementException is thrown in that case.
   * 
   * @return true if tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testDequeueEmpty() {
    BoardingQueue boardingQueue = new BoardingQueue(10);
    try {
      boardingQueue.dequeue();
      return false; // Should have thrown a NoSuchElementException
    } catch (NoSuchElementException e) {
      // This is expected, do nothing
    }

    return true;
  }


  /**
   * Tests the functionality of BoardingQueue.dequeue() method by calling dequeue() on a queue of
   * size one and ensures the method call returns the correct Passenger, size is zero, and the array
   * heap contains null references, only.
   * 
   * @return true if tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testDequeueBoardingQueueSizeOne() {
    BoardingQueue boardingQueue = new BoardingQueue(10);
    Passenger p1 = new Passenger("John", Group.A, true);

    boardingQueue.enqueue(p1);
    Passenger dequeuedPassenger = boardingQueue.dequeue();

    return dequeuedPassenger.equals(p1) && boardingQueue.size() == 0
        && Arrays.stream(boardingQueue.toArray()).allMatch(e -> e == null);
  }

  /**
   * Tests the functionality of BoardingQueue.dequeue() when called on a non-empty boarding queue.
   * 
   * This tests ensures the dequeue() method removes, and returns the passenger with the highest
   * boarding priority in the queue, the size of the queue must be decremented by one, and the
   * contents of the heap array is as expected.
   * 
   * @return true if PriorityCareAdmissions.dequeue() returns the correct Passenger each time it is
   *         called and size is appropriately decremented, false otherwise
   */
  public static boolean testDequeueBoardingQueueNonEmpty() {
    BoardingQueue boardingQueue = new BoardingQueue(10);
    Passenger p1 = new Passenger("John", Group.A, true);
    Passenger p2 = new Passenger("Jane", Group.B, true);
    Passenger p3 = new Passenger("Doe", Group.C, true);

    boardingQueue.enqueue(p1);
    boardingQueue.enqueue(p2);
    boardingQueue.enqueue(p3);

    Passenger dequeuedPassenger = boardingQueue.dequeue();

    return dequeuedPassenger.equals(p1) && boardingQueue.size() == 2
        && Arrays.stream(boardingQueue.toArray()).noneMatch(e -> e == null);
  }

  /**
   * Tests the functionality of the clear() method. Should implement at least the following
   * scenarios:
   * 
   * - clear can be called on an empty queue with no errors.
   * 
   * - clear can be called on a non-empty queue with no errors.
   * 
   * After calling clear(), this tester ensures that the queue is empty, meaning its size is zero
   * and its heap array contains only null references.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testBoardingQueueClear() {
    BoardingQueue boardingQueue = new BoardingQueue(10);
    Passenger p1 = new Passenger("John", Group.A, true);
    Passenger p2 = new Passenger("Jane", Group.B, true);

    boardingQueue.enqueue(p1);
    boardingQueue.enqueue(p2);

    boardingQueue.clear();

    return boardingQueue.size() == 0
        && Arrays.stream(boardingQueue.toArray()).allMatch(e -> e == null);

  }

  /**
   * Main method to run this tester class.
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testPassengerCompareToDifferentGroup: "
        + (testPassengerCompareToDifferentGroup() ? "Pass" : "Failed!"));
    System.out.println("testPassengerCompareToSameGroupDifferentArrival: "
        + (testPassengerCompareToSameGroupDifferentArrival() ? "Pass" : "Failed!"));
    System.out.println("testPassengerCompareToSameGroupSameArrival: "
        + (testPassengerCompareToSameGroupSameArrival() ? "Pass" : "Failed!"));
    System.out.println(
        "testBoardingQueueConstructor: " + (testBoardingQueueConstructor() ? "Pass" : "Failed!"));
    System.out
        .println("testPeekBestEmptyQueue: " + (testPeekBestEmptyQueue() ? "Pass" : "Failed!"));
    System.out.println(
        "testPeekBestNonEmptyQueue: " + (testPeekBestNonEmptyQueue() ? "Pass" : "Failed!"));
    System.out
        .println("testEnqueueToEmptyQueue: " + (testEnqueueToEmptyQueue() ? "Pass" : "Failed!"));
    System.out.println(
        "testEnqueueToNonEmptyQueue: " + (testEnqueueToNonEmptyQueue() ? "Pass" : "Failed!"));
    System.out.println("testDequeueEmpty: " + (testDequeueEmpty() ? "Pass" : "Failed!"));
    System.out.println("testDequeueBoardingQueueSizeOne: "
        + (testDequeueBoardingQueueSizeOne() ? "Pass" : "Failed!"));
    System.out.println("testDequeueBoardingQueueNonEmpty: "
        + (testDequeueBoardingQueueNonEmpty() ? "Pass" : "Failed!"));
    System.out
        .println("testBoardingQueueClear: " + (testBoardingQueueClear() ? "Pass" : "Failed!"));
  }

}
