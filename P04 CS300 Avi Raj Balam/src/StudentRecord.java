//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    This class represents a student record with name, email, campusID, and prerequisite status.
// Course:   CS 300 Fall 2023
//
// Author:   Avi Raj Balam
// Email:    ablam@wisc.edu
// Lecturer: (Hobbes LeGault or Mark Mansi or Mouna Kacem)

///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         
// Online Sources:  https://www.programiz.com/java-programming/online-compiler/ (help in quick test compiling)
//                 
///////////////////////////////////////////////////////////////////////////////

import java.util.zip.DataFormatException;

public class StudentRecord {

    private String name;
    private String email;
    private String campusID;
    private boolean isPreReqSatisfied;

    /**
     * Constructs a StudentRecord object with the provided attributes.
     *
     * @param name     the name of the student
     * @param email    the email of the student
     * @param campusID the campusID of the student
     * @param preReq   the boolean representing if the student satisfies the prerequisites
     * @throws DataFormatException if name, email, or campusID are not valid
     */
    public StudentRecord(String name, String email, String campusID, boolean preReq) throws DataFormatException {
        if (!isValidName(name) || !isValidEmail(email) || !isValidCampusID(campusID)) {
            throw new DataFormatException("Bad name, email, or campusID!");
        }
        this.name = name;
        this.email = email;
        this.campusID = campusID;
        this.isPreReqSatisfied = preReq;
    }

    /**
     * Checks if a name is valid.
     *
     * @param name the student's name
     * @return true if the name is not null and not blank
     */
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    /**
     * Checks if an email is valid.
     *
     * @param email the student's email
     * @return true if the email is valid according to specified criteria
     */
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        if (!email.matches("^[^@\\s]+@[^@\\s]+\\.edu$")) {
            return false;
        }
        int emailLength = email.length();
        return emailLength > 0 && emailLength < 40;
    }

    /**
     * Checks if a campusID is valid.
     *
     * @param campusID the student's campusID
     * @return true if the campusID is not null, 10 digits, and can be parsed to a long
     */
    public static boolean isValidCampusID(String campusID) {
        if (campusID == null) {
            return false;
        }
        campusID = campusID.trim();
        if (campusID.length() != 10) {
            return false;
        }
        try {
            Long.parseLong(campusID);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Retrieves the student's name.
     *
     * @return the student's name
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the student's email.
     *
     * @return the student's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the student's campusID.
     *
     * @return the student's campusID
     */
    public String getCampusID() {
        return campusID;
    }

    /**
     * Checks if the student satisfies the prerequisites.
     *
     * @return true if the student satisfies the prerequisites
     */
    public boolean isPrerequisiteSatisfied() {
        return isPreReqSatisfied;
    }

    /**
     * Compares this StudentRecord to the specified object.
     *
     * @return true if the object is an instance of StudentRecord and has the same campusID
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        StudentRecord that = (StudentRecord) other;
        return campusID.equals(that.campusID);
    }

    /**
     * Returns a string representation of this student record.
     *
     * @return a string in the format: name, email, campusID, preReqValue
     */
    @Override
    public String toString() {
        return name + ", " + email + ", " + campusID + ", " + isPreReqSatisfied;
    }
}
