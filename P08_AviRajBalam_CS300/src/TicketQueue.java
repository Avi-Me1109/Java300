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
// Online Sources: https://learn.zybooks.com/zybook/WISCCOMPSCI300Fall2023 (Identifying Queue
//////////////// functionality)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class representing a queue of TicketSiteUser objects with a specified capacity. Implements the
 * QueueADT and Iterable interfaces.
 *
 * @param <TicketSiteUser> The type of elements stored in the queue.
 */
public class TicketQueue implements QueueADT<TicketSiteUser>, Iterable<TicketSiteUser> {

  private LinkedNode<TicketSiteUser> back;
  private int capacity;
  private LinkedNode<TicketSiteUser> front;
  private int size;

  /**
   * Constructs a TicketQueue with the specified capacity.
   *
   * @param capacity The maximum number of elements the queue can hold.
   * @throws IllegalArgumentException if the specified capacity is less than 1.
   */
  public TicketQueue(int capacity) {
    super();

    if (capacity < 1) {
      throw new IllegalArgumentException("Capacity is less than 1!");
    }

    else {
      this.capacity = capacity;
      this.front = null;
      this.back = null;
      this.size = this.size();
    }
  }

  /**
   * Retrieves the capacity of the queue.
   *
   * @return The capacity of the queue.
   */
  public int capacity() {
    return capacity;
  }

  /**
   * Sets a new capacity for the queue.
   *
   * @param newCapacity The new capacity for the queue.
   */
  public void setCapacity(int newCapacity) {
    this.capacity = newCapacity;
  }

  /**
   * Retrieves the current size of the queue.
   *
   * @return The current size of the queue.
   */
  @Override
  public int size() {

    return this.size;

  }

  /**
   * Checks if the queue is full.
   *
   * @return true if the queue is full, false otherwise.
   */
  public boolean isFull() {

    if (this.size() == capacity) {
      return true;
    }

    else {
      return false;
    }

  }

  /**
   * Checks if the queue is empty.
   *
   * @return true if the queue is empty, false otherwise.
   */
  public boolean isEmpty() {

    if (this.size() == 0) {
      return true;
    }

    else {
      return false;
    }
  }

  /**
   * Adds a new TicketSiteUser object to the end of the queue.
   *
   * @param newObject The TicketSiteUser object to be added.
   * @throws IllegalStateException    if the queue is full.
   * @throws IllegalArgumentException if the user cannot buy a ticket.
   */
  public void enqueue(TicketSiteUser newObject) {
    if (isFull()) {
      throw new IllegalStateException("Queue is Full!");
    }

    else if (!newObject.canBuyTicket()) {
      throw new IllegalArgumentException("The user cannot buy a ticket!");
    }

    else {
      LinkedNode<TicketSiteUser> add = new LinkedNode<TicketSiteUser>(newObject);
      if (isEmpty()) {
        front = add;
      } else {
        back.setNext(add);
      }
      back = add;
      size++;
    }

  }


  /**
   * Removes and returns the front element of the queue.
   *
   * @return The front element of the queue.
   * @throws NoSuchElementException if the queue is empty.
   */
  @Override
  public TicketSiteUser dequeue() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is Empty!");
    }

    TicketSiteUser frontData = front.getData();
    front = front.getNext();

    // If there is only one element in the queue
    if (front == null) {
      back = null;
    }

    size--;

    return frontData;
  }


  /**
   * Retrieves the front element of the queue without removing it.
   *
   * @return The front element of the queue.
   * @throws NoSuchElementException if the queue is empty.
   */
  @Override
  public TicketSiteUser peek() {
    if (this.front == null) {
      throw new NoSuchElementException("List Front is Empty.");
    }
    return this.front.getData();
  }


  /**
   * Creates a deep copy of the queue.
   *
   * @return A new TicketQueue with the same elements and capacity.
   */
  public TicketQueue deepCopy() {
    TicketQueue copyQueue = new TicketQueue(capacity);
    LinkedNode<TicketSiteUser> current = front;

    while (current != null) {
      TicketSiteUser originalUser = current.getData();
      // Assuming TicketSiteUser has a constructor that takes another TicketSiteUser
      TicketSiteUser copiedUser = originalUser;
      copyQueue.enqueue(copiedUser);
      current = current.getNext();
    }

    return copyQueue;
  }

  /**
   * Returns a string representation of the queue.
   *
   * @return A string representation of the queue.
   */
  @Override
  public String toString() {
    String s = "";
    LinkedNode<TicketSiteUser> runner = this.front;
    while (runner != null) {
      s += runner.getData() + "\n";
      runner = runner.getNext();
    }
    return s;
  }

  /**
   * Returns an iterator over the elements in the queue.
   *
   * @return An iterator over the elements in the queue.
   */
  @Override
  public Iterator<TicketSiteUser> iterator() {
    return new TicketQueueIterator(this);
  }


}
