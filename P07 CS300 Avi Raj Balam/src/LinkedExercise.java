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
 * This class represents a node in a linked list of exercises. Each node contains an Exercise object
 * and a reference to the next LinkedExercise node in the sequence.
 * 
 * @author Avi Raj Balam
 */

public class LinkedExercise {

    // Fields
    private Exercise exercise;          // The Exercise object stored in this node
    private LinkedExercise next;        // Reference to the next LinkedExercise node in the linked list

    // Constructors

    /**
     * Creates a new LinkedExercise node with the given Exercise data and a reference to the next node.
     * 
     * @param data The Exercise object to be stored in this node
     * @param next The next LinkedExercise node in the linked list
     */
    public LinkedExercise(Exercise data, LinkedExercise next) {
        this.exercise = data;
        this.next = next;
    }

    /**
     * Creates a new LinkedExercise node with the given Exercise data and no reference to the next node.
     * 
     * @param data The Exercise object to be stored in this node
     */
    public LinkedExercise(Exercise data) {
        this(data, null);
    }

    // Methods

    /**
     * Gets the next LinkedExercise node in the linked list.
     * 
     * @return The next LinkedExercise node
     */
    public LinkedExercise getNext() {
        return next;
    }

    /**
     * Sets the next LinkedExercise node in the linked list.
     * 
     * @param node The LinkedExercise node to be set as the next node
     */
    public void setNext(LinkedExercise node) {
        this.next = node;
    }

    /**
     * Gets the Exercise object stored in this node.
     * 
     * @return The Exercise object
     */
    public Exercise getExercise() {
        return exercise;
    }

    /**
     * Overrides the toString method to provide a string representation of the LinkedExercise node.
     * 
     * @return A string representation of the LinkedExercise node, including Exercise data and next node information
     */
    @Override
    public String toString() {
        if (next != null) {
            return exercise.toString() + " -> ";
        } else {
            return exercise.toString() + " -> END";
        }
    }
}
