//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Represents an exceptional course enrollment system.
// Course:   CS 300 Fall 2023
//
// Author:   Avi Raj Balam
// Email:    ablam@wisc.edu
// Lecturer: (Hobbes LeGault or Mark Mansi or Mouna Kacem)

///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         Danyal Maqbool (help in loadRoster autograder understanding)
// Online Sources:  https://www.programiz.com/java-programming/online-compiler/ (help in quick test compiling)
//                  https://www.w3schools.com/java/java_files_read.asp (Help in reading files)
//                  https://www.geeksforgeeks.org/how-to-remove-all-white-spaces-from-a-string-in-java/(help in removing whitespaces)
//                  https://www.tutorialspoint.com/split-string-with-comma-in-java#:~:text=To%20split%20a%20string%20with,%5B%2C%5D"%2C%200)%3B (help in splitting strings)
//                 
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class ExceptionalCourseEnrollment {
    private String courseName;
    private ArrayList<StudentRecord> roster;
    private int enrollmentCapacity;
    private ArrayList<StudentRecord> waitlist;
    private int waitlistCapacity;

    /**
     * Constructor for ExceptionalCourseEnrollment. Initializes all the fields with the corresponding inputs.
     * The roster and waitlist ArrayLists must be empty.
     *
     * @param courseName         the name of the course
     * @param enrollmentCapacity the capacity of the course roster (INCLUSIVE, between 0 and 250)
     * @param waitlistCapacity   the capacity of the waitlist (must be GREATER than 0 and LESS OR EQUAL TO than the enrollmentCapacity)
     * @throws IllegalArgumentException with message "Course name must not be blank or empty" if course name is blank or empty
     * @throws IllegalArgumentException with message "Enrollment capacity must be between 15 and 250!" if enrollment capacity is not between 15 and 250, inclusive
     * @throws IllegalArgumentException with message "Waitlist capacity must be between 0 and enrollment capacity!" if waitlistCapacity is larger than enrollmentCapacity or less than zero
     */
    public ExceptionalCourseEnrollment(String courseName, int enrollmentCapacity, int waitlistCapacity) {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name must not be blank or empty");
        }
        if (enrollmentCapacity < 0 || enrollmentCapacity > 250) {
            throw new IllegalArgumentException("Enrollment capacity must be between 0 and 250!");
        }
        if (waitlistCapacity <= 0 || waitlistCapacity > enrollmentCapacity) {
            throw new IllegalArgumentException("Waitlist capacity must be between 0 and enrollment capacity!");
        }

        this.courseName = courseName;
        this.enrollmentCapacity = enrollmentCapacity;
        this.waitlistCapacity = waitlistCapacity;
        this.roster = new ArrayList<>();
        this.waitlist = new ArrayList<>();
    }

    /**
     * Checks if the roster is full.
     *
     * @return true if the size of the roster is equal to the enrollment capacity, false otherwise.
     */
    public boolean isRosterFull() {
        return roster.size() == enrollmentCapacity;
    }

    /**
     * Checks if the waitlist is full.
     *
     * @return true if the size of the waitlist is equal to the waitlist capacity, false otherwise.
     */
    public boolean isWaitlistFull() {
        return waitlist.size() == waitlistCapacity;
    }

    /**
     * Checks if the course enrollment is closed. A course enrollment is considered closed if both the roster and the waitlist are full.
     *
     * @return true if both the roster and the waitlist are full, false otherwise.
     */
    public boolean isCourseEnrollmentClosed() {
        return isRosterFull() && isWaitlistFull();
    }

    /**
     * Getter for course name
     *
     * @return the name of the course
     */
    public String getName() {
        return courseName;
    }

    /**
     * Returns a deep copy (NOT the deepest) of this course enrollment's roster
     *
     * @return a deep copy of the roster, and null if roster is null
     */
    public ArrayList<StudentRecord> deepCopyRoster() {
        if (roster != null) {
            return new ArrayList<>(roster);
        } else {
            return null;
        }
    }

    /**
     * Returns a deep copy (NOT the deepest) of this course enrollment's waitlist
     *
     * @return a deep copy of the waitlist, and null if waitlist is null
     */
    public ArrayList<StudentRecord> deepCopyWaitlist() {
        if (waitlist != null) {
            return new ArrayList<>(waitlist);
        } else {
            return null;
        }
    }

    /**
     * Expands the enrollment capacity of the course by the increase amount. Does not affect the waitlist at all.
     *
     * @param increase the non-negative amount to increase the capacity by
     * @throws IllegalArgumentException with message "Increase amount must be greater than zero!" if increase is not larger than zero
     */
    public void expandEnrollmentCapacity(int increase) {
        if (increase <= 0) {
            throw new IllegalArgumentException("Increase amount must be greater than zero!");
        }
        enrollmentCapacity += increase;
    }

    /**
     * Prints the list of all the students in the waitlist of the course, with respect to the following format.
     */
    public void printWaitlist() {
        System.out.println("Waitlist capacity: " + waitlistCapacity);
        if (waitlist.isEmpty()) {
            System.out.println("The waitlist is empty.");
        } else {
            for (int i = 0; i < waitlist.size(); i++) {
                System.out.println((i + 1) + ". " + waitlist.get(i));
            }
        }
    }

    /**
     * Returns the student record object that has an exact match with campusID in the list passed as input.
     */
    public static StudentRecord searchById(String campusID, ArrayList<StudentRecord> list) {
        for (StudentRecord student : list) {
            if (student.getCampusID().equals(campusID)) {
                return student;
            }
        }
        throw new NoSuchElementException("No student record found!");
    }

    /**
     * Appends (adds to the end) the student record to the waitlist if the waitlist has space, the student isn't already on the waitlist, isn't already enrolled in the course, and they meet the preReqs.
     *
     * @param student valid StudentRecord of student to be added
     * @throws IllegalArgumentException if the student is already on the waitlist with message "That student is already on the waitlist!"
     * @throws IllegalArgumentException if the student is already enrolled in the course with message "That student is already enrolled!"
     * @throws IllegalStateException if the waitlist is full with the message "The waitlist is full!"
     * @throws IllegalStateException if the student does not have satisfactory prerequisites with message "The prerequisites are not satisfied for that course!"
     */
    public void addWaitlist(StudentRecord student) {
        if (waitlist.contains(student)) {
            throw new IllegalArgumentException("That student is already on the waitlist!");
        }
        if (roster.contains(student)) {
            throw new IllegalArgumentException("That student is already enrolled!");
        }
        if (isWaitlistFull()) {
            throw new IllegalStateException("The waitlist is full!");
        }
        if (!student.isPrerequisiteSatisfied()) {
            throw new IllegalStateException("The prerequisites are not satisfied for that course!");
        }
        waitlist.add(student);
    }

    /**
     * Enrolls one student given their StudentRecord if certain conditions are met.
     *
     * @param student valid StudentRecord of student to be added
     * @throws IllegalStateException if the student is already enrolled with message "That student is already enrolled!"
     * @throws IllegalStateException if the course is full with message "The course is full!"
     * @throws IllegalStateException if the student does not have satisfactory prerequisites with message "That student has not satisfied the prerequisites!"
     */
    public void enrollOneStudent(StudentRecord student) {
        if (roster.contains(student)) {
            throw new IllegalStateException("That student is already enrolled!");
        }
        if (isRosterFull()) {
            throw new IllegalStateException("The course is full.");
        }
        if (!student.isPrerequisiteSatisfied()) {
            throw new IllegalStateException("That student has not satisfied the prerequisites!");
        }
        roster.add(student);
    }

    /**
     * Removes a student from the roster if a matching campusID is found.
     *
     * @param student valid StudentRecord of student to be removed
     * @throws NoSuchElementException if there is no matching student record found with message "There is no matching student in the roster"
     */
    public void dropCourse(StudentRecord student) {
        if (!roster.remove(student)) {
            throw new NoSuchElementException("There is no matching student in the roster");
        }
    }

    /**
     * Removes a student from the waitlist if a matching campusID is found.
     *
     * @param student valid StudentRecord of student to be removed
     * @throws NoSuchElementException if there is no matching student record found with message "No matching student record found!"
     */
    public void removeStudentFromWaitlist(StudentRecord student) {
        if (!waitlist.remove(student)) {
            throw new NoSuchElementException("No matching student record found!");
        }
    }

    /**
     * Enrolls all students from the waitlist who meet the preReqs and until the course is full.
     */
    public void enrollFromWaitlist() {
        for (int i = 0; i < waitlist.size(); i++) {
            StudentRecord student = waitlist.get(i);
            if (!isRosterFull() && student.isPrerequisiteSatisfied()) {
                roster.add(student);
                waitlist.remove(i);
                i--; // Adjust the index after removal
            }
        }
    }

    /**
     * Writes the course enrollment information to a text file.
     *
     * @param filename the name of the file to write to
     * @throws FileNotFoundException if the file cannot be created or written to
     */
    public void saveRoster(String filename) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            writer.println("Course Name: " + courseName);
            writer.println("Enrollment Capacity: " + enrollmentCapacity);
            writer.println("Waitlist Capacity: " + waitlistCapacity);
            writer.println("Roster:");
            for (StudentRecord student : roster) {
                writer.println(student);
            }
            writer.println("Waitlist:");
            for (StudentRecord student : waitlist) {
                writer.println(student);
            }
        }
    }

    /**
     * Parses a line of text and constructs a StudentRecord object.
     *
     * @param line - A line of text containing student information.
     * @return A StudentRecord object created from the parsed line.
     * @throws DataFormatException - Thrown if the line is not correctly formatted or contains invalid data.
     */
    private StudentRecord lineToRecord(String line) throws DataFormatException {
        line = line.trim();
        line = line.replaceAll(" ", "");
        String[] lineParts = line.split(",");

        if (lineParts.length != 4) {
            throw new DataFormatException("Line is not correctly formatted.");
        }

        String name = lineParts[0];
        String email = lineParts[1];
        String campusID = lineParts[2];
        boolean preReqValue;

        try {
            preReqValue = Boolean.parseBoolean(lineParts[3]);
        } catch (IllegalArgumentException e) {
            throw new DataFormatException("Invalid preReqValue. Must be a boolean value.");
        }

        return new StudentRecord(name, email, campusID, preReqValue);
    }

    /**
     * Creates a formatted string representation of the roster.
     *
     * @return a string containing the details of all students in the roster, one student per line.
     */
    public String rosterToString() {
        StringBuilder rosterString = new StringBuilder();

        for (StudentRecord student : roster) {
            rosterString.append(student.toString()).append("\n");
        }

        return rosterString.toString();
    }

    /**
     * Loads the roster from a text file.
     *
     * @param rosterFile the file containing the roster information
     * @throws FileNotFoundException if the file cannot be found
     * @throws DataFormatException if a line in the file is not formatted correctly
     */
    public void loadRoster(File rosterFile) throws FileNotFoundException, DataFormatException {
        try {
            Scanner scanner = new Scanner(rosterFile);
            while (scanner.hasNextLine()) {
                try {
                    StudentRecord add = lineToRecord(scanner.nextLine());

                    if (isRosterFull()) {
                        throw new IllegalStateException("The course capacity would be exceeded by loading that student!");
                    }

                    try {
                        enrollOneStudent(add);
                    } catch (IllegalStateException e) {
                    }
                } catch (DataFormatException e) {
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Could not find that file!");
        }
    }
}
