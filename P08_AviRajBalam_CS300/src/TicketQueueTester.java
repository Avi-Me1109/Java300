//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: TicketQueue Main Class
// Course: CS 300 Fall 2023
//
// Author: Avi Raj Balam
// Email: abalam@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: Instructors Answer via Piazza (Identifying a bug in null pointer exception where
//////////////// this.front is null)
// Online Sources: https://learn.zybooks.com/zybook/WISCCOMPSCI300Fall2023 (Identifying Queue
//////////////// functionality)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class containing test methods for the TicketQueue class, ensuring its functionality.
 */
public class TicketQueueTester {

  /**
   * Tests the enqueue operation of the TicketQueue class under various conditions.
   *
   * @return true if all tests pass, false otherwise.
   */
  public static boolean testEnqueue() {

    try {
      // Create a TicketQueue with a capacity of 2
      TicketQueue ticketQueue = new TicketQueue(3);

      // Create TicketSiteUser objects
      TicketSiteUser user1 = new TicketSiteUser("user1", "password1", "1234567890123456");
      user1.login("user1", "password1");
      TicketSiteUser user2 = new TicketSiteUser("user2", "password2", "9876543210123456");
      user2.login("user2", "password2");
      TicketSiteUser user3 = new TicketSiteUser("user3", "password3", "1234987340710348");
      user3.login("user3", "password3");

      // Test: Enqueue when the queue is not full and the user can buy a ticket
      ticketQueue.enqueue(user1);
      String expectedString = "user1: *\n";
      if (ticketQueue.size() != 1 || !ticketQueue.toString().equals(expectedString)) {
        return false;
      }

      ticketQueue.enqueue(user2);
      ticketQueue.enqueue(user3);
      expectedString = "user1: *\nuser2: *\nuser3: *\n";
      if (ticketQueue.size() != 3 || !ticketQueue.toString().equals(expectedString)) {
        return false;
      }

      // Test: Try to enqueue when the queue is full (expect an IllegalStateException)
      try {
        ticketQueue.enqueue(user3);
        // System.out.println("Test failed: Expected IllegalStateException, but no exception was
        // thrown.");
        return false;
      } catch (IllegalStateException e) {
        // System.out.println("Test passed: IllegalStateException was thrown as expected.");
      }

      ticketQueue.setCapacity(4);

      // Test: Try to enqueue when the user cannot buy a ticket (expect an IllegalArgumentException)
      try {
        ticketQueue.enqueue(new TicketSiteUser("user4", "password4", "1234"));
        // System.out.println("Test failed: Expected IllegalArgumentException, but no exception was
        // thrown.");
        return false;
      } catch (IllegalArgumentException e) {
        // System.out.println("Test passed: IllegalArgumentException was thrown as expected.");
      }


      return true;
    }

    catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  /**
   * Tests the dequeue operation of the TicketQueue class under various conditions.
   *
   * @return true if all tests pass, false otherwise.
   */
  public static boolean testDequeue() {

    try {
      // Create a TicketQueue with a capacity of 3
      TicketQueue ticketQueue = new TicketQueue(3);
      // boolean determiner = true;

      // Create TicketSiteUser objects
      TicketSiteUser user1 = new TicketSiteUser("user1", "password1", "1234567890123456");

      TicketSiteUser user2 = new TicketSiteUser("user2", "password2", "9876543210123456");

      TicketSiteUser user3 = new TicketSiteUser("user3", "password3", "1234987340710348");


      // Enqueue users to the queue
      user1.login("user1", "password1");
      user2.login("user2", "password2");
      user3.login("user3", "password3");
      ticketQueue.enqueue(user1);
      ticketQueue.enqueue(user2);
      ticketQueue.enqueue(user3);

      String expectedString1 = "user2: *\nuser3: *\n";
      String expectedString2 = "user2: *\n";
      String expectedString3 = "";

      if (!ticketQueue.dequeue().equals(user1) || ticketQueue.size() != 2
          || !expectedString1.toString().equals("user2: *\nuser3: *\n")) {
        System.out.println("Fail2");
        return false;
      }
      if (!ticketQueue.dequeue().equals(user2) || ticketQueue.size() != 1
          || !expectedString2.toString().equals("user2: *\n")) {
        System.out.println("Fail3");
        return false;
      }

      TicketSiteUser a = ticketQueue.dequeue();
      if (!a.equals(user3) || ticketQueue.size() != 0 || !expectedString3.toString().equals("")) {
        System.out.println("Fail4");
        return false;
      }

      try {
        ticketQueue.dequeue();
        return false;
      }

      catch (NoSuchElementException e) {
        // System.out.println("Correct exception thrown test passed.");

        return true;
      }
    }

    catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }



  }

  /**
   * Tests the constructor of the TicketQueue class and its associated methods.
   *
   * @return true if all tests pass, false otherwise.
   */
  public static boolean testConstructor() {
    try {
      TicketQueue test = new TicketQueue(2);


      if (test.capacity() == 2) {
        // System.out.println("Capacity Test Passed");
      } else {
        // System.out.println("Capacity Test Failed");
        return false;
      }

      try {
        TicketQueue test2 = new TicketQueue(0);
        // System.out.println("Test Failed! Expected IllegalArgumentException.");
        return false;
      }

      catch (IllegalArgumentException e) {
        // System.out.println("Test Passed. IllegalArgumentException caught sucessfully");
      }

      // Create TicketSiteUser objects
      TicketSiteUser user1 = new TicketSiteUser("user1", "password1", "1234567890123456");
      user1.login("user1", "password1");
      TicketSiteUser user2 = new TicketSiteUser("user2", "password2", "9876543210123456");
      user2.login("user2", "password2");
      TicketSiteUser user3 = new TicketSiteUser("user3", "password3", "1234987340710348");
      user3.login("user3", "password3");

      // In Beggining
      if (test.isFull() == false) {
        // System.out.println("Full(1) Test Passed.");
      }

      else {
        // System.out.println("Full(1) Test Failed.");
        return false;
      }

      if (test.isEmpty() == true) {
        // System.out.println("Empty(1) Test Passed.");
      }

      else {
        // System.out.println("Empty(1) Test Failed");
        return false;
      }

      if (test.size() == 0) {
        // System.out.println("Size(1) Test Passed");
      } else {
        // System.out.println("Size(1) Test Failed");
        return false;
      }

      // Test: Enqueue when the queue is not full and the user can buy a ticket
      test.enqueue(user1);

      // In Middle
      if (test.isFull() == false) {
        // System.out.println("Full(2) Test Passed.");
      }

      else {
        // System.out.println("Full(2) Test Failed.");
        return false;
      }

      if (test.isEmpty() == false) {
        // System.out.println("Empty(2) Test Passed.");
      }

      else {
        // System.out.println("Empty(2) Test Failed");
        return false;
      }

      if (test.size() == 1) {
        // System.out.println("Size(2) Test Passed");
      } else {
        // System.out.println("Size(2) Test Failed");
        return false;
      }

      test.enqueue(user2);

      // In End
      if (test.isFull() == true) {
        // System.out.println("Full(3) Test Passed.");
      }

      else {
        // System.out.println("Full(3) Test Failed.");
        return false;
      }

      if (test.isEmpty() == false) {
        // System.out.println("Empty(3) Test Passed.");
      }

      else {
        // System.out.println("Empty(3) Test Failed");
        return false;
      }

      if (test.size() == 2) {
        // System.out.println("Size(3) Test Passed");
      } else {
        // System.out.println("Size(3) Test Failed");
        return false;
      }

      // Create a TicketQueue with a capacity of 3
      TicketQueue ticketQueue = new TicketQueue(3);

      // Create TicketSiteUser objects
      TicketSiteUser a = new TicketSiteUser("user1", "password1", "1234567890123456");
      a.login("user1", "password1");
      TicketSiteUser b = new TicketSiteUser("user2", "password2", "9876543210123456");
      b.login("user2", "password2");

      // Enqueue users to the queue
      ticketQueue.enqueue(a);
      ticketQueue.enqueue(b);

      // Test: Check the string representation of the queue
      String expectedString = a + "\n" + b + "\n";
      String actualString = ticketQueue.toString();

      if (expectedString.equals(actualString)) {
        // System.out.println("Test passed: String representation is as expected.");
      } else {
        // System.out.println("Test failed: String representation is not as expected.");
        // System.out.println("Expected: " + expectedString);
        // System.out.println("Actual: " + actualString);
        return false;
      }

      return true;
    }

    catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  /**
   * Tests the iterator functionality of the TicketQueue class.
   *
   * @return true if all tests pass, false otherwise.
   */
  public static boolean testIterator() {

    try {
      // boolean determiner = true;
      // Create a TicketQueue and add TicketSiteUser objects
      TicketQueue ticketQueue = new TicketQueue(5);
      TicketSiteUser user1 = new TicketSiteUser("user1", "password1", "1234567890123456");

      TicketSiteUser user2 = new TicketSiteUser("user2", "password2", "9876543210123456");

      TicketSiteUser user3 = new TicketSiteUser("user3", "password3", "1234987340710348");


      user1.login("user1", "password1");
      user2.login("user2", "password2");
      user3.login("user3", "password3");
      ticketQueue.enqueue(user1);
      ticketQueue.enqueue(user2);
      ticketQueue.enqueue(user3);

      // Create an iterator for the TicketQueue
      TicketSiteUser[] expected = {user1, user2, user3};

      int count = 0;

      for (TicketSiteUser user : ticketQueue) {
        if (!user.equals(expected[count])) {
          return false;
        }
        count++;
      }

      if (count != ticketQueue.size()) {
        return false;
      }

      String expectedString = "user1: *\nuser2: *\nuser3: *\n";
      if (!ticketQueue.toString().equals(expectedString) || ticketQueue.size() != 3) {
        return false;
      }

      return true;
    }

    catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  /**
   * Tests the peek operation of the TicketQueue class under various conditions.
   *
   * @return true if all tests pass, false otherwise.
   */
  public static boolean testPeek() {

    try {
      TicketQueue queue = new TicketQueue(2);

      // Test peek on an empty queue, it should throw NoSuchElementException
      try {
        queue.peek();
        System.out.println("Test Peek: Fail");
        return false;
      } catch (NoSuchElementException e) {
        // This is expected
      }

      // Create TicketSiteUser objects
      TicketSiteUser a = new TicketSiteUser("user1", "password1", "1234567890123456");
      a.login("user1", "password1");
      TicketSiteUser b = new TicketSiteUser("user2", "password2", "9876543210123456");
      b.login("user2", "password2");

      // Enqueue users to the queue
      queue.enqueue(a);
      queue.enqueue(b);

      TicketSiteUser peekedTicket = queue.peek();

      // Check if the peeked ticket is the first one enqueued
      if (!peekedTicket.equals(a)) {
        System.out.println("Test Peek: Fail");
        return false;
      }

      // Check if the size of the queue remains unchanged
      if (queue.size() != 2) {
        System.out.println("Test Peek: Fail");
        return false;
      }

      // Check if the peeked ticket is still at the front of the queue
      TicketSiteUser dequeuedTicket = queue.dequeue();
      if (!dequeuedTicket.equals(a)) {
        System.out.println("Test Peek: Fail");
        return false;
      }

      System.out.println("Test Peek: Pass");
      return true;
    }

    catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  /**
   * Runs all test methods and prints the results.
   */
  private static void runAllTests() {
    boolean determiner;

    determiner = testEnqueue();
    System.out.println("Test Enqueue: " + (determiner ? "Pass" : "Fail"));
    System.out.println("------------------------------");

    determiner = testDequeue();
    System.out.println("Test Dequeue: " + (determiner ? "Pass" : "Fail"));
    System.out.println("------------------------------");

    determiner = testConstructor();
    System.out.println("Test Constructor: " + (determiner ? "Pass" : "Fail"));
    System.out.println("------------------------------");

    determiner = testIterator();
    System.out.println("Test Iterator: " + (determiner ? "Pass" : "Fail"));
    System.out.println("------------------------------");

    determiner = testPeek();

    System.out.println("------------------------------");
  }

  /**
   * The main method that executes all test methods.
   *
   * @param args Command-line arguments (not used in this context).
   */
  public static void main(String[] args) {

    runAllTests();

  }
}
