//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: TicketQueue Iterator Class
// Course: CS 300 Fall 2023
//
// Author: Avi Raj Balam
// Email: abalam@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator class for the TicketQueue, allowing iteration over TicketSiteUser elements.
 */
public class TicketQueueIterator implements Iterator<TicketSiteUser> {

  // Instance variable
  private TicketQueue userQueue;

  /**
   * Constructs a TicketQueueIterator with a reference to the TicketQueue to be iterated.
   *
   * @param queue The TicketQueue to be iterated.
   * @throws IllegalArgumentException if the provided queue is null.
   */
  public TicketQueueIterator(TicketQueue queue) {
    if (queue == null) {
      throw new IllegalArgumentException("Queue cannot be null.");
    }
    this.userQueue = queue.deepCopy();
  }

  /**
   * Checks if there is another TicketSiteUser in the queue.
   *
   * @return true if there is another TicketSiteUser, false otherwise.
   */
  @Override
  public boolean hasNext() {
    return !this.userQueue.isEmpty();
  }

  /**
   * Retrieves the next TicketSiteUser in the queue.
   *
   * @return The next TicketSiteUser in the queue.
   * @throws NoSuchElementException if there are no more TicketSiteUsers in the queue.
   */
  @Override
  public TicketSiteUser next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException("No more TicketSiteUsers in the queue.");
    }
    return userQueue.dequeue();
  }
}
