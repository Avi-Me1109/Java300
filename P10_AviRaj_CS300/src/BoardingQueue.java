//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Main BoardingQueue class with main heap methods
// Course: CS 300 Fall 2023
//
// Author: Avi Raj Balam
// Email: abalam@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;
import java.util.NoSuchElementException;

public class BoardingQueue implements PriorityQueueADT<Passenger> {

  private Passenger[] heap;
  private int size;
  private static final int DEFAULT_CAPACITY = 10;

  /**
   * Constructs an empty BoardingQueue with the given capacity
   *
   * @param capacity Capacity of this boarding queue
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer (greater than zero)
   */
  public BoardingQueue(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity must be a positive integer greater than zero.");
    }
    this.heap = new Passenger[capacity];
    this.size = 0;
  }

  /**
   * Constructs an empty BoardingQueue with the default capacity
   */
  public BoardingQueue() {
    this(DEFAULT_CAPACITY);
  }

  /**
   * Returns the capacity of this boarding queue.
   *
   * @return Capacity of the boarding queue.
   */
  public int capacity() {
    return heap.length;
  }

  /**
   * Clears the boarding queue by filling the heap array with null and setting size to 0.
   */
  public void clear() {
    Arrays.fill(heap, null);
    size = 0;
  }

  /**
   * Checks if the boarding queue is full.
   *
   * @return true if the queue is full, false otherwise.
   */
  public boolean isFull() {
    return size == heap.length;
  }

  /**
   * Converts the boarding queue to an array containing only the elements (no nulls) up to the
   * current size.
   *
   * @return An array representing the current elements of the boarding queue.
   */
  public Passenger[] toArray() {
    return Arrays.copyOf(heap, size);
  }

  /**
   * Creates a deep copy of the boarding queue.
   *
   * @return A deep copy of the boarding queue.
   */
  public BoardingQueue deepCopy() {
    BoardingQueue copy = new BoardingQueue(heap.length);
    copy.size = size;
    copy.heap = Arrays.copyOf(heap, heap.length);
    return copy;
  }

  /**
   * Returns a String representing this boarding queue, where each Passenger in the queue is listed
   * on a separate line, in order from smallest to greatest, meaning in their boarding order.
   *
   * @return a String representing this boarding queue, and an empty String "" if this queue is
   *         empty.
   */
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    BoardingQueue deepCopy = deepCopy();
    while (!deepCopy.isEmpty()) {
      result.append(deepCopy.dequeue().toString()).append("\n");
    }
    return result.toString().trim();
  }


  /**
   * Restores the min-heap of the priority queue by percolating its root down the tree. If the
   * element at the given index does not violate the min-heap ordering property (it is smaller than
   * its smallest child), then this method does not modify the heap. Otherwise, if there is a heap
   * violation, then swap the element with the correct child and continue percolating the element
   * down the heap.
   *
   * We assume that index is in bounds (greater or equal to zero and less than size).
   *
   * @param index index of the element in the heap to percolate downwards
   */
  protected void percolateDown(int index) {
    int leftChild = 2 * index + 1;
    int rightChild = 2 * index + 2;
    int smallest = index;

    if (leftChild < size && heap[leftChild].compareTo(heap[smallest]) < 0) {
      smallest = leftChild;
    }

    if (rightChild < size && heap[rightChild].compareTo(heap[smallest]) < 0) {
      smallest = rightChild;
    }

    if (smallest != index) {
      swap(index, smallest);
      percolateDown(smallest);
    }
  }

  /**
   * Restores the min-heap invariant of this priority queue by percolating a leaf up the heap. If
   * the element at the given index does not violate the min-heap invariant (it is greater than its
   * parent), then this method does not modify the heap. Otherwise, if there is a heap violation,
   * swap the element with its parent and continue percolating the element up the heap. We assume
   * that index is in bounds (greater or equal to zero and less than size).
   *
   * @param index index of the element in the heap to percolate upwards
   */
  protected void percolateUp(int index) {
    if (index > 0) {
      int parent = (index - 1) / 2;
      if (heap[index].compareTo(heap[parent]) < 0) {
        swap(index, parent);
        percolateUp(parent);
      }
    }
  }

  /**
   * Swaps two elements in the heap array.
   *
   * @param i Index of the first element to swap.
   * @param j Index of the second element to swap.
   */
  private void swap(int i, int j) {
    Passenger temp = heap[i];
    heap[i] = heap[j];
    heap[j] = temp;
  }

  /**
   * Checks if the boarding queue is empty.
   *
   * @return true if the queue is empty, false otherwise.
   */
  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   * Returns the current size of the boarding queue.
   *
   * @return The current size of the queue.
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Retrieves the passenger with the highest priority (root of the min-heap).
   *
   * @return The passenger with the highest priority.
   * @throws NoSuchElementException if the queue is empty.
   */
  @Override
  public Passenger peekBest() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty. Cannot peek.");
    }
    return heap[0];
  }

  /**
   * Enqueues a passenger into the boarding queue while maintaining the min-heap property.
   *
   * @param e The passenger to enqueue.
   * @return true if the enqueue was successful, false if the queue is full.
   */
  @Override
  public boolean enqueue(Passenger e) {
    if (isFull()) {
      return false;
    }
    heap[size] = e;
    percolateUp(size);
    size++;

    return true;

  }

  /**
   * Dequeues the passenger with the highest priority (root of the min-heap).
   *
   * @return The passenger with the highest priority.
   * @throws NoSuchElementException if the queue is empty.
   */
  @Override
  public Passenger dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty. Cannot dequeue.");
    }
    Passenger root = heap[0];
    heap[0] = heap[size - 1];
    heap[size - 1] = null;
    size--;
    percolateDown(0);
    return root;
  }
}
