//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Iterator class for specific stations
// Course: CS 300 Fall 2023
//
// Author: Avi Raj Balam
// Email: abalam@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BusFilteredIterator extends Object implements Iterator<Bus> {

    private Iterator<Bus> baseIterator;
    private BusStop destination;
    private Bus next;

    /**
     * Constructs a new BusFilteredIterator with the specified base iterator and destination.
     *
     * @param iterator    The base iterator for all buses.
     * @param destination The destination bus stop to filter buses.
     */
    public BusFilteredIterator(Iterator<Bus> iterator, BusStop destination) {
        this.baseIterator = iterator;
        this.destination = destination;
        this.advanceToNext();
    }

    /**
     * Advances the iterator to the next bus that goes to the specified destination.
     */
    private void advanceToNext() {
        while (this.baseIterator.hasNext()) {
            Bus bus = baseIterator.next();
            if (bus.goesTo(destination)) {
                next = bus;
                return;
            }
        }
        // If we reach here, no more matching buses
        next = null;
    }

    /**
     * Checks if there is another bus in the iteration that goes to the specified destination.
     *
     * @return true if there is another matching bus, false otherwise.
     */
    @Override
    public boolean hasNext() {
        
      return next!= null;
      
      
    }

    /**
     * Returns the next bus in the iteration that goes to the specified destination.
     *
     * @return The next matching bus.
     * @throws NoSuchElementException if there are no more matching buses.
     */
    @Override
    public Bus next() {
        Bus currentBus = next;
        this.advanceToNext();
        return currentBus;
    }
}
