//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Passenger class to hold the individual elements in the boardingQueue heap class
// Course: CS 300 Fall 2023
//
// Author: Avi Raj Balam
// Email: abalam@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models Passenger objects ready to board an airplane.
 */
public class Passenger implements Comparable<Passenger> {
  // A Passenger MUST be compared to another Passenger, ONLY.

  // Data fields
  private static int orderGen = 1; // generator of arrival orders of passengers
  private String name; // name of this passenger
  private final int ARRIVAL_ORDER; // arrival order of this passenger
  private Group group; // boarding group assigned to this passenger
  private boolean isCheckedIn; // indicates whether this passenger was checked in before boarding
  // the airplane

  /**
   * Constructs a new Passenger given their name, boarding group, and checkedin status
   *
   * @param name        name to be assigned to this Passenger
   * @param group       boarding group to be assigned to this Passenger
   * @param isCheckedIn indicates whether this passenger was checked in, or not
   * @throws IllegalArgumentException if name is null or blank or if group is null
   */
  public Passenger(String name, Group group, boolean isCheckedIn) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("name is null or blank!");
    }

    if (group == null) {
      throw new IllegalArgumentException("boarding group is null!");
    }

    this.name = name;
    this.group = group;
    this.isCheckedIn = isCheckedIn;
    this.ARRIVAL_ORDER = nextOrder();
  }

  /**
   * Generates and returns the arrival order to be assigned to the next Passenger object to be
   * created
   *
   * @return the next generated order
   */
  private static int nextOrder() {
    return orderGen++;
  }

  /**
   * Resets the arrival order generated to 1. This method can be used for testing purposes, or to
   * reset the system.
   */
  protected static void resetPassengerOrder() {
    orderGen = 1;
  }

  /**
   * Gets the name of this passenger
   *
   * @return the name of this passenger
   */
  public String name() {
    return name;
  }

  /**
   * Determines whether this passenger was checked in before boarding the airplane
   *
   * @return true if this passenger was checked in
   */
  public boolean isCheckedIn() {
    return this.isCheckedIn;
  }

  /**
   * Returns a String representation of this Passenger in the following format:
   * <p>
   * <name> from Group <group>
   *
   * @return a String representation of this passenger
   */
  public String toString() {
    return this.name + " from Group " + this.group;
  }

  /**
   * Indicates whether some other object is "equal to" this Passenger.
   *
   * @param obj the reference object with which to compare.
   * @return true if obj is an instance of Passenger and has the exact same name, group, and arrival
   *         order as this Passenger, otherwise return false.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;

    Passenger passenger = (Passenger) obj;

    if (ARRIVAL_ORDER != passenger.ARRIVAL_ORDER)
      return false;
    if (!name.equals(passenger.name))
      return false;
    return group == passenger.group;
  }

  /**
   * Compares this Passenger with another Passenger based on their boarding groups and arrival
   * orders. Passengers are compared with respect to the increasing order of their boarding groups.
   * If two passengers have the same boarding groups, they are compared with respect to the
   * increasing order of their arrival orders.
   *
   * @param otherPassenger the other Passenger to compare
   * @return a negative integer if this Passenger is less than the other input Passenger, a positive
   *         integer if this Passenger is greater than the other input Passenger, and zero if both
   *         Passengers have the same boarding group and arrival order.
   */
  @Override
  public int compareTo(Passenger other) {
    // Compare groups first
    int groupComparison = this.group.compareTo(other.group);

    if (groupComparison == 0) {
      // If the groups are the same, compare arrival orders
      return Integer.compare(this.ARRIVAL_ORDER, other.ARRIVAL_ORDER);
    } else {
      // If groups are different, return the result of group comparison
      return groupComparison;
    }
  }

}
