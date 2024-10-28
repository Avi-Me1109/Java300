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
 * This class implements a linked list of exercises and provides methods to manipulate the list.
 * It also implements the ListADT interface with Exercise as the generic type.
 * 
 * @author Avi Raj Balam
 */

import java.util.NoSuchElementException;

public class WorkoutBuilder implements ListADT<Exercise> {

    // Fields
    private int cooldownCount;      // Count of COOLDOWN exercises in the list
    private LinkedExercise head;    // Reference to the first node in the linked list
    private int primaryCount;       // Count of PRIMARY exercises in the list
    private int size;               // Size of the linked list
    private LinkedExercise tail;    // Reference to the last node in the linked list
    private int warmupCount;        // Count of WARMUP exercises in the list

    // Methods

    /**
     * Gets the size of the linked list.
     * 
     * @return The size of the linked list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Gets the count of WARMUP exercises in the list.
     * 
     * @return The count of WARMUP exercises
     */
    public int getWarmupCount() {
        return warmupCount;
    }

    /**
     * Gets the count of PRIMARY exercises in the list.
     * 
     * @return The count of PRIMARY exercises
     */
    public int getPrimaryCount() {
        return primaryCount;
    }

    /**
     * Gets the count of COOLDOWN exercises in the list.
     * 
     * @return The count of COOLDOWN exercises
     */
    public int getCooldownCount() {
        return cooldownCount;
    }

    /**
     * Checks if the linked list is empty.
     * 
     * @return True if the linked list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0 && head == null && tail == null;
    }

    /**
     * Clears the linked list by resetting all fields to their initial values.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
        warmupCount = 0;
        primaryCount = 0;
        cooldownCount = 0;
    }

    /**
     * Finds the index of the first occurrence of the specified Exercise in the linked list.
     * 
     * @param findObject The Exercise to find in the list
     * @return The index of the first occurrence, or -1 if not found
     */
    @Override
    public int indexOf(Exercise findObject) {
        LinkedExercise current = head;
        int index = 0;

        while (current != null) {
            if (current.getExercise().equals(findObject)) {
                return index;
            }
            current = current.getNext();
            index++;
        }

        return -1;
    }

    /**
     * Gets the Exercise at the specified index in the linked list.
     * 
     * @param index The index of the Exercise to retrieve
     * @return The Exercise at the specified index
     * @throws IndexOutOfBoundsException If the index is out of bounds
     */
    @Override
    public Exercise get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        LinkedExercise current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current.getExercise();
    }

    /**
     * Adds a new Exercise to the linked list and updates counts based on the type of the new exercise.
     * 
     * @param newObject The Exercise to add to the list
     */
    @Override
    public void add(Exercise newObject) {
        // Update counts based on the type of the new exercise
        switch (newObject.getType()) {
            case WARMUP:
                warmupCount++;
                break;
            case PRIMARY:
                primaryCount++;
                break;
            case COOLDOWN:
                cooldownCount++;
                break;
        }

        LinkedExercise newNode = new LinkedExercise(newObject);
        size++;

        if (head == null) {
            // If the list is empty, add the first exercise as the head and tail
            head = newNode;
            tail = newNode;
        } else {
            // If the list is not empty, add the new exercise based on its type
            WorkoutType newObjectType = newObject.getType();

            switch (newObjectType) {
                case WARMUP:
                    // Add to the FRONT (head) of the list
                    newNode.setNext(head);
                    head = newNode;
                    break;
                case PRIMARY:
                    // Add after all warm-ups and before any cool-downs or existing primary exercises
                    LinkedExercise current = head;
                    LinkedExercise previous = null;
                    boolean foundWarmup = false;

                    while (current != null && current.getExercise().getType() != WorkoutType.COOLDOWN) {
                        if (current.getExercise().getType() == WorkoutType.WARMUP) {
                            // Found a warm-up, set the flag
                            foundWarmup = true;
                        } else if (foundWarmup) {
                            // Add after the last warm-up and before any cool-downs or existing primary exercises
                            newNode.setNext(current);
                            previous.setNext(newNode);
                            return;
                        }

                        // Move to the next exercise
                        previous = current;
                        current = current.getNext();
                    }

                    // There are no warm-ups or foundWarmup is false, add after the last warm-up (at the END)
                    tail.setNext(newNode);
                    tail = newNode;
                    break;
                case COOLDOWN:
                    // Add to the END (tail) of the list
                    tail.setNext(newNode);
                    tail = newNode;
                    break;
            }
        }
    }

    /**
     * Removes the first occurrence of an Exercise with the specified WorkoutType from the linked list.
     * 
     * @param type The WorkoutType of the Exercise to remove
     * @return The removed Exercise
     * @throws NoSuchElementException If no Exercise of the specified type is found
     */
    public Exercise removeExercise(WorkoutType type) throws NoSuchElementException {
        // Check if the list is empty
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }

        LinkedExercise removedExercise = null;

        // Remove the first occurrence based on the specified WorkoutType
        switch (type) {
            case WARMUP:
                removedExercise = head;
                head = head.getNext();
                removedExercise.setNext(null);

                if (head == null) {
                    // The list is now empty, update the tail as well
                    tail = null;
                }

                warmupCount--;
                break;
            case PRIMARY:
                LinkedExercise current = head;
                LinkedExercise previous = null;

                while (current != null && current.getExercise().getType() != WorkoutType.PRIMARY) {
                    previous = current;
                    current = current.getNext();
                }

                if (current != null) {
                    if (previous == null) {
                        // The first PRIMARY exercise is the head
                        removedExercise = head;
                        head = head.getNext();
                        removedExercise.setNext(null);

                        if (head == null) {
                            // The list is now empty, update the tail as well
                            tail = null;
                        }
                    } else {
                        // Remove the first PRIMARY exercise after warm-ups
                        removedExercise = current;
                        previous.setNext(current.getNext());
                        removedExercise.setNext(null);

                        if (current == tail) {
                            // The removed exercise was the last one, update the tail
                            tail = previous;
                        }
                    }

                    primaryCount--;
                }
                break;
            case COOLDOWN:
                if (head == tail) {
                    // Only one element in the list
                    removedExercise = head;
                    head = null;
                    tail = null;
                } else {
                    LinkedExercise currentTail = head;
                    LinkedExercise previousTail = null;

                    while (currentTail.getNext() != null) {
                        previousTail = currentTail;
                        currentTail = currentTail.getNext();
                    }

                    // Remove the last element (COOLDOWN)
                    removedExercise = currentTail;
                    previousTail.setNext(null);
                    tail = previousTail;
                }

                cooldownCount--;
                break;
        }

        // Check if the removed exercise is null (not found)
        if (removedExercise == null) {
            throw new NoSuchElementException("No exercises of type " + type + " in the list");
        }

        // Update the size and return the removed Exercise
        size--;

        return removedExercise.getExercise();
    }

    /**
     * Removes the Exercise with the specified exercise ID from the linked list.
     * 
     * @param exerciseID The ID of the Exercise to remove
     * @return The removed Exercise
     * @throws NoSuchElementException If no Exercise with the specified ID is found
     */
    public Exercise removeExercise(int exerciseID) throws NoSuchElementException {
        // Check if the list is empty
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }

        LinkedExercise removedExercise = null;
        LinkedExercise current = head;
        LinkedExercise previous = null;

        // Search for the Exercise with the specified ID
        while (current != null) {
            if (current.getExercise().getExerciseID() == exerciseID) {
                removedExercise = current;

                // Update the linked list based on the position of the removed Exercise
                if (previous == null) {
                    // The exercise to remove is the head
                    head = current.getNext();

                    if (head == null) {
                        // The list is now empty, update the tail as well
                        tail = null;
                    }
                } else {
                    // Remove the exercise from the middle or end of the list
                    previous.setNext(current.getNext());

                    if (current == tail) {
                        // The removed exercise was the last one, update the tail
                        tail = previous;
                    }
                }

                // Update counts based on the type of the removed Exercise
                switch (removedExercise.getExercise().getType()) {
                    case WARMUP:
                        warmupCount--;
                        break;
                    case PRIMARY:
                        primaryCount--;
                        break;
                    case COOLDOWN:
                        cooldownCount--;
                        break;
                }

                // Update the size and return the removed Exercise
                size--;
                return removedExercise.getExercise();
            }

            // Move to the next node in the linked list
            previous = current;
            current = current.getNext();
        }

        // If the specified ID is not found, throw an exception
        throw new NoSuchElementException("No exercise with ID " + exerciseID + " in the list");
    }

    /**
     * Overrides the toString method to provide a string representation of the linked list.
     * 
     * @return A string representation of the linked list, including Exercise data and next node information
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        LinkedExercise current = head;
        while (current != null) {
            result.append(current.toString());
            current = current.getNext();
        }

        return result.toString();
    }
}
