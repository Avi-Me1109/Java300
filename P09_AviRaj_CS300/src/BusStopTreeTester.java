//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Tester for the Bus Stop Tree
// Course: CS 300 Fall 2023
//
// Author: Avi Raj Balam
// Email: abalam@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////////////////////////////////////////////////////////////

import java.time.LocalTime;
import java.util.Iterator;

public class BusStopTreeTester {

  /**
   * Tests that compareTo returns the correct value when comparing a bus with a different arrival.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToDifferentArrivalTime() {
    
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"07:00", "09:00", "11:00", "13:00", "15:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    
    // compare bus1 to bus2 and vice versa
    boolean correctComparison1 = bus1.compareTo(bus2) < 0; // should be +
    boolean correctComparison2 = bus2.compareTo(bus1) > 0; // should be -
    
    //test passes if both comparisons are true
    if(correctComparison1 && correctComparison2 == true) {
      return true;
    }
    
    // test fails otherwise
    return false;
  }

  /**
   * For two buses with the same arrival time but different routes, test that compareTo returns the
   * correct value.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToSameArrivalTimeDifferentRoute() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    int[] stopIds2 = {1, 2, 4, 6, 7};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
        BusRoute.dummyRoute("A1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.OUTGOING, stopIds2, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    
    // compare bus1 to bus2 and vice versa
    boolean correctComparison1 = bus1.compareTo(bus2) < 0; // should be +
    boolean correctComparison2 = bus2.compareTo(bus1) > 0; // should be -
    
    //test passes if both comparisons are true
    if(correctComparison1 && correctComparison2 == true) {
      return true;
    }
    
    // test fails otherwise
    return false;
  }


  /**
   * For two buses with the same arrival time and route name, but different directions, test that
   * compareTo returns the correct value.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToSameArrivalTimeSameRouteDifferentDirection() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    
    // compare bus1 to bus2 and vice versa
    boolean correctComparison1 = bus1.compareTo(bus2) < 0; // should be +
    boolean correctComparison2 = bus2.compareTo(bus1) > 0; // should be -
    
    //test passes if both comparisons are true
    if(correctComparison1 && correctComparison2 == true) {
      return true;
    }
    
    // test fails otherwise
    return false;
  }

  /**
   * Tests that compareTo returns the correct value (0) when comparing a bus with the same arrival
   * time, route name, and direction.
   * 
   * @return true if the test passes, false otherwise.
   */
  private static boolean testBusCompareToSameBus() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    // compare bus1 to bus2 and vice versa
    boolean correctComparison1 = bus1.compareTo(bus2) == 0; // should return 0
    boolean correctComparison2 = bus2.compareTo(bus1) == 0; // should return 0

    // test passes if both comparisons return 0
    return correctComparison1 && correctComparison2;
  }

  /**
   * Tests that isValidBST returns true for an empty BST.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTEmpty() {
    
    BusStopTree busStopTree = new BusStopTree(1);
    
    boolean result = busStopTree.isValidBST(busStopTree.getRoot());
    
    return result;
  }


  /**
   * Tests that isValidBST returns false for an invalid BST.
   * 
   * Should use a tree with depth > 2. Make sure to include a case where the left subtree contains a
   * node that is greater than the right subtree. (See the example in the spec for more details.)
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTInvalid() {
    
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "10:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"07:00", "05:00", "14:00", "16:00", "18:00"};
    String[] stopTimes3 = {"05:00", "02:00", "09:00", "11:00", "13:00"};
    String[] stopTimes4 = {"07:00", "13:00", "14:00", "16:00", "18:00"};
    String[] stopTimes5 = {"05:00", "15:00", "09:00", "11:00", "13:00"};
    String[] stopTimes6 = {"05:00", "09:00", "09:00", "11:00", "13:00"};
    String[] stopTimes7 = {"07:00", "20:00", "14:00", "16:00", "18:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 = BusRoute.dummyRoute("A1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 = BusRoute.dummyRoute("A1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 = BusRoute.dummyRoute("A1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    BusRoute route4 = BusRoute.dummyRoute("A1", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes4);
    BusRoute route5 = BusRoute.dummyRoute("A1", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes5);
    BusRoute route6 = BusRoute.dummyRoute("A1", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes6);
    BusRoute route7 = BusRoute.dummyRoute("A1", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes7);
    
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    Bus bus5 = new Bus(BusStop.getStop(2), route5);
    Bus bus6 = new Bus(BusStop.getStop(2), route6);
    Bus bus7 = new Bus(BusStop.getStop(2), route7);
    
    
    // Create a BST with an invalid structure
    Node<Bus> node6 = new Node<>(bus6, null, null);
    Node<Bus> node5 = new Node<>(bus5, null, null);
    Node<Bus> node7 = new Node<>(bus7, null, null);
    Node<Bus> node4 = new Node<>(bus4, node6, node5);
    Node<Bus> node2 = new Node<>(bus2, null, null);
    Node<Bus> node1 = new Node<>(bus1, node4, node7);
    Node<Bus> root = new Node<>(bus3, node2, node1 );
    
    return !BusStopTree.isValidBST(root);
    
  }


  /**
   * Tests that isValidBST returns true for a valid BST.
   * 
   * Should use a tree with depth > 2.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTValid() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    int[] stopIds2 = {9, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"07:00", "09:00", "14:00", "16:00", "18:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds2, stopTimes2);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route5 =
        BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes2);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    Bus bus5 = new Bus(BusStop.getStop(2), route5);
    
    // Create a BST with an valid structure
    BusStopTree invalidBST = new BusStopTree(2);

    // Add buses to the tree in a way that violates the BST property
    invalidBST.addBus(bus1);
    invalidBST.addBus(bus2);
    invalidBST.addBus(bus3);
    invalidBST.addBus(bus4);
    invalidBST.addBus(bus5);
    
    return invalidBST.isValidBST(invalidBST.getRoot());
  }


  /**
   * Tests that addBus correctly adds a bus to an empty BST.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBusEmpty() {
    
    BusStopTree empty = new BusStopTree(1);
    
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    BusRoute route1 = BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    
    empty.addBus(bus1);
    
    if(empty.contains(bus1) && empty.size() == 1) {
      return true;
    }
    
    else {
      return false;
    }
    
  }

  /**
   * Tests that addBus correctly adds a bus to a non-empty BST.
   * 
   * Each time you add a bus, make sure that 1) addBus() returns true, 2) the BST is still valid, 3)
   * the BST size has been incremented.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBus() {
    
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"07:00", "09:00", "14:00", "16:00", "18:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route5 =
        BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes2);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    Bus bus5 = new Bus(BusStop.getStop(2), route5);
    
    // Create a BST with an invalid structure
    BusStopTree BST = new BusStopTree(2);

    // Add buses to the tree in a way that violates the BST property
    BST.addBus(bus1);
    BST.addBus(bus2);
    BST.addBus(bus3);
    BST.addBus(bus4);
    BST.addBus(bus5);
    
    boolean check4 = BST.size() == 5;
    
    BusRoute route6 =
        BusRoute.dummyRoute("ROUTE 5", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus6 = new Bus(BusStop.getStop(2), route6);
    
    boolean check = BST.addBus(bus6);
    boolean check2 = BST.isValidBST(BST.getRoot());
    boolean check3 = BST.size() == 6;
   
    
    return check && check2 && check3 && check4;
    
  }

  /**
   * Tests that addBus returns false when adding a duplicate bus. The BST should not be modified
   * (same size).
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBusDuplicate() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"07:00", "09:00", "14:00", "16:00", "18:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route5 =
        BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes2);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    Bus bus5 = new Bus(BusStop.getStop(2), route5);
    
    // Create a BST with an invalid structure
    BusStopTree BST = new BusStopTree(2);

    // Add buses to the tree in a way that violates the BST property
    BST.addBus(bus1);
    BST.addBus(bus2);
    BST.addBus(bus3);
    BST.addBus(bus4);
    BST.addBus(bus5);
    
    boolean check4 = BST.size() == 5;
    
    BusRoute route6 =
        BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes2);
    Bus bus6 = new Bus(BusStop.getStop(2), route6);
    
    boolean check = !BST.addBus(bus6);
    boolean check3 = BST.size() == 5;
   
    
    return check && check3 && check4;
  }


  /**
   * Tests that contains returns true when the BST contains the Bus, and false otherwise.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testContains() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"07:00", "09:00", "14:00", "16:00", "18:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route5 =
        BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes2);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    Bus bus5 = new Bus(BusStop.getStop(2), route5);
    
    // Create a BST with an invalid structure
    BusStopTree BST = new BusStopTree(2);

    // Add buses to the tree in a way that violates the BST property
    BST.addBus(bus1);
    BST.addBus(bus2);
    BST.addBus(bus3);
    BST.addBus(bus4);
    
    boolean check1 = BST.contains(bus1);
    boolean check2 = !BST.contains(bus5);
    
    return check1 && check2;
  }


  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is the node passed in as the root node parameter.
   * 
   * @return
   */
  public static boolean testGetFirstNodeAfterRoot() {
    
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"07:00", "09:00", "14:00", "16:00", "18:00"};
    String[] stopTimes3 = {"07:00", "11:00", "14:00", "16:00", "18:00"};
    String[] stopTimes4 = {"07:00", "12:00", "14:00", "16:00", "18:00"};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes4);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    
    BusStopTree BST = new BusStopTree(2);
    BST.addBus(bus1);
    BST.addBus(bus2);
    BST.addBus(bus3);
    BST.addBus(bus4);
    
    // Test cases
    
    boolean check1 = BST.getFirstNodeAfter(LocalTime.parse("06:00"), BST.getRoot()).getValue().equals(bus1);
    boolean check2 = BST.getFirstNodeAfter(LocalTime.parse("08:00"), BST.getRoot()).getValue().equals(bus2);
    boolean check3 = BST.getFirstNodeAfter(LocalTime.parse("10:00"), BST.getRoot()).getValue().equals(bus3);
    boolean check4 = BST.getFirstNodeAfter(LocalTime.parse("14:00"), BST.getRoot()) == null;
   
    
    return check1 && check2 && check3 && check4;
    
  }

  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is in the left subtree.
   * 
   * @return
   */
  public static boolean testGetFirstNodeAfterLeftSubtree() {
    BusStop stop = BusStop.getStop(2);

    BusRoute route1 =
        BusRoute.dummyRoute("A1", BusRoute.BusDirection.OUTGOING, new int[] {stop.getStopId()}, new String[] {"12:00"});
    BusRoute route2 =
        BusRoute.dummyRoute("A1", BusRoute.BusDirection.OUTGOING, new int[] {stop.getStopId()}, new String[] {"11:00"});
    BusRoute route3 =
        BusRoute.dummyRoute("A1", BusRoute.BusDirection.OUTGOING, new int[] {stop.getStopId()}, new String[] {"13:00", "15:00"});
    BusRoute route4 =
        BusRoute.dummyRoute("A1", BusRoute.BusDirection.OUTGOING, new int[] {stop.getStopId()}, new String[] {"09:00", "15:00"});
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    
    BusStopTree BST = new BusStopTree(2);
    BST.addBus(bus1);
    BST.addBus(bus2);
    BST.addBus(bus3);
    BST.addBus(bus4);
    
    // Test cases
    
    Node<Bus> expected = BST.getFirstNodeAfter(LocalTime.parse("09:00"), BST.getRoot());
    boolean check1 = expected != null && expected.getValue().equals(bus4);
    
    // Add a bus with an arrival time after the target time
    BusRoute route5 =
            BusRoute.dummyRoute("ROUTE 5", BusRoute.BusDirection.INCOMING, new int[] {stop.getStopId()}, new String[] {"10:00"});
    Bus bus5 = new Bus(BusStop.getStop(2), route5);
    BST.addBus(bus5);

    // Test case 2
    Node<Bus> expected1 = BST.getFirstNodeAfter(LocalTime.parse("09:30"), BST.getRoot());
    boolean check2 = expected1 != null && expected1.getValue().equals(bus5);

    return check1 && check2;  // Negate the check to catch the issue
   

  }

  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is in the right subtree.
   * 
   * @return
   */
  public static boolean testGetFirstNodeAfterRightSubtree() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"07:00", "09:00", "14:00", "16:00", "18:00"};
    String[] stopTimes3 = {"07:00", "11:00", "14:00", "16:00", "18:00"};
    String[] stopTimes4 = {"07:00", "12:00", "14:00", "16:00", "18:00"};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes4);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    
    BusStopTree BST = new BusStopTree(2);
    BST.addBus(bus1);
    BST.addBus(bus2);
    BST.addBus(bus3);
    BST.addBus(bus4);
    
    // Test cases
    
    boolean check1 = BST.getFirstNodeAfter(LocalTime.parse("10:00"), BST.getRoot()).getRight().getValue().equals(bus4);
   
    return check1;
  }

  /**
   * Tests the creation of a BusFilteredIterator where NONE of the buses go to the destination.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToEmpty() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"07:00", "09:00", "14:00", "16:00", "18:00"};
    String[] stopTimes3 = {"07:00", "11:00", "14:00", "16:00", "18:00"};
    String[] stopTimes4 = {"07:00", "12:00", "14:00", "16:00", "18:00"};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes4);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    
    BusStopTree BST = new BusStopTree(2);
    BST.addBus(bus1);
    BST.addBus(bus2);
    BST.addBus(bus3);
    BST.addBus(bus4);

    BusStop destination = BusStop.getStop(6);
    
    Iterator<Bus> busIterator = BST.getNextBusesTo(LocalTime.parse("10:00"), destination);

    // Expecting no buses, as none go to the destination
    return !busIterator.hasNext();
  }

  /**
   * Tests the creation of a BusFilteredIterator where SOME of the buses go to the destination.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToSome() {

    BusStop stop = BusStop.getStop(6);
    BusStop stop2 = BusStop.getStop(11);
    
    BusRoute route1 =
        BusRoute.dummyRoute("A1", BusRoute.BusDirection.OUTGOING, new int[] {stop.getStopId()}, new String[] {"10:00"});
    BusRoute route2 =
        BusRoute.dummyRoute("A2", BusRoute.BusDirection.OUTGOING, new int[] {stop.getStopId(), stop2.getStopId()}, new String[] {"11:00", "15:00"});
    BusRoute route3 =
        BusRoute.dummyRoute("A3", BusRoute.BusDirection.OUTGOING, new int[] {stop.getStopId(), stop2.getStopId()}, new String[] {"08:00", "15:00"});
    BusRoute route4 =
        BusRoute.dummyRoute("A4", BusRoute.BusDirection.OUTGOING, new int[] {stop.getStopId(), stop2.getStopId()}, new String[] {"12:00", "15:00"});
    Bus bus1 = new Bus(stop, route1);
    Bus bus2 = new Bus(stop, route2);
    Bus bus3 = new Bus(stop, route3);
    Bus bus4 = new Bus(stop, route4);
    
    BusStopTree BST = new BusStopTree(stop.getStopId());
    BST.addBus(bus1);
    BST.addBus(bus2);
    BST.addBus(bus3);
    BST.addBus(bus4);
    

    Iterator<Bus> busIterator = BST.getNextBusesTo(LocalTime.of(9, 00), stop2);
    
    if(busIterator.hasNext() && busIterator.next().equals(bus1)) {
      return false;
    }
    
    busIterator = BST.getNextBusesTo(LocalTime.of(9, 0), stop2);
    
    boolean foundBus2 = false;
    boolean foundBus3 = false;
    
    while(busIterator.hasNext()) {
      Bus nextBus = busIterator.next();
      
      if (nextBus.equals(bus2)) {
        foundBus2 = true;
      }
      
      else if(nextBus.equals(bus4)) {
        foundBus3 = true;
      }
      
      else {
        return false;
      }
    }
    
    // Expecting no buses, as none go to the destination
    return foundBus2 && foundBus3;
    }

  /**
   * Tests the creation of a BusFilteredIterator where ALL of the buses go to the destination.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToAll() {

    BusStop stop = BusStop.getStop(6);
    BusStop stop2 = BusStop.getStop(11);
    
    BusRoute route1 =
        BusRoute.dummyRoute("A1", BusRoute.BusDirection.OUTGOING, new int[] {stop.getStopId(), stop2.getStopId()}, new String[] {"10:00", "15:00"});
    BusRoute route2 =
        BusRoute.dummyRoute("A2", BusRoute.BusDirection.OUTGOING, new int[] {stop.getStopId(), stop2.getStopId()}, new String[] {"11:00", "15:00"});
    BusRoute route3 =
        BusRoute.dummyRoute("A3", BusRoute.BusDirection.OUTGOING, new int[] {stop.getStopId(), stop2.getStopId()}, new String[] {"12:00", "15:00"});
    BusRoute route4 =
        BusRoute.dummyRoute("A4", BusRoute.BusDirection.OUTGOING, new int[] {stop.getStopId(), stop2.getStopId()}, new String[] {"13:00", "15:00"});
    Bus bus1 = new Bus(stop, route1);
    Bus bus2 = new Bus(stop, route2);
    Bus bus3 = new Bus(stop, route3);
    Bus bus4 = new Bus(stop, route4);
    
    BusStopTree BST = new BusStopTree(stop.getStopId());
    BST.addBus(bus1);
    BST.addBus(bus2);
    BST.addBus(bus3);
    BST.addBus(bus4);
    

    Iterator<Bus> busIterator = BST.getNextBusesTo(LocalTime.of(9, 00), stop2);
    
    boolean foundBus1 = false;
    if(busIterator.hasNext() && busIterator.next().equals(bus1)) {
      foundBus1 = true;
    }
    
    
    boolean foundBus2 = false;
    boolean foundBus3 = false;
    boolean foundBus4 = false;
    
    
    while(busIterator.hasNext()) {
      Bus nextBus = busIterator.next();
      
      if (nextBus.equals(bus2)) {
        foundBus2 = true;
      }
      
      else if(nextBus.equals(bus3)) {
        foundBus3 = true;
      }
      
      else if(nextBus.equals(bus4)) {
        foundBus4 = true;
      }
      
      else {
        return false;
      }
    }
    
    
    // Expecting no buses, as none go to the destination
    return foundBus1 && foundBus2 && foundBus3 && foundBus4;
    }

  
  public static void main(String[] args) {
    // Populate BusStop with dummy data. This only has to be done once.
    BusStop.createDummyStops();

    System.out
        .println("testBusCompareToDifferentArrivalTime: " + testBusCompareToDifferentArrivalTime());
    System.out.println("testBusCompareToSameArrivalTimeDifferentRoute: "
        + testBusCompareToSameArrivalTimeDifferentRoute());
    System.out.println("testBusCompareToSameArrivalTimeSameRouteDifferentDirection: "
        + testBusCompareToSameArrivalTimeSameRouteDifferentDirection());
    System.out.println("testBusCompareToSameBus: " + testBusCompareToSameBus());
    System.out.println("testIsValidBSTEmpty: " + testIsValidBSTEmpty());
    System.out.println("testIsValidBSTInvalid: " + testIsValidBSTInvalid());
    System.out.println("testIsValidBSTValid: " + testIsValidBSTValid());
    System.out.println("testAddBusEmpty: " + testAddBusEmpty());
    System.out.println("testAddBus: " + testAddBus());
    System.out.println("testAddBusDuplicate: " + testAddBusDuplicate());
    System.out.println("testContains: " + testContains());
    System.out.println("testGetFirstNodeAfterRoot: " + testGetFirstNodeAfterRoot());
    System.out.println("testGetFirstNodeAfterLeftSubtree: " + testGetFirstNodeAfterLeftSubtree());
    System.out.println("testGetFirstNodeAfterRightSubtree: " + testGetFirstNodeAfterRightSubtree());
    System.out.println("testGetNextBusesToEmpty: " + testGetNextBusesToEmpty());
    System.out.println("testGetNextBusesToSome: " + testGetNextBusesToSome());
    System.out.println("testGetNextBusesToAll: " + testGetNextBusesToAll());
  }

}
