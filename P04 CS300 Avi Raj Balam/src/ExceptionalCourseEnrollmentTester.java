//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    This utility class implements unit tests to check the correctness of methods defined in the ExceptionalCourseEnrollment class of the Exceptional Course Enrollment System program.
// Course:   CS 300 Fall 2023
//
// Author:   Avi Raj Balam
// Email:    abalam@wisc.edu
// Lecturer: (Hobbes LeGault or Mark Mansi or Mouna Kacem)
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         
// Online Sources:  https://www.programiz.com/java-programming/online-compiler/(Help in quick compiling)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

public class ExceptionalCourseEnrollmentTester {

    /**
     * Test the StudentRecord.equals() method for correctness.
     * 
     * @return true if all test cases pass, false otherwise.
     */
    public static boolean studentRecordEqualsTester() {
        // Define test cases for StudentRecord.equals() method
        try {
            StudentRecord s1 = new StudentRecord("Rob", "rob@wisc.edu", "1234567890", true);
            StudentRecord s2 = new StudentRecord("Joey", "joey@wisc.edu", "1233367890", true);

            // Test case 1: Comparing a student record to itself should return true
            if (!s1.equals(s1)) {
                return false;
            }

            // Test case 2: Comparing student records with different campusID should return false
            if (s1.equals(s2)) {
                return false;
            }

            // Test case 3: Comparing with a String should return false
            if (s1.equals("Not a StudentRecord")) {
                return false;
            }

            // Test case 4: Comparing with null should return false
            if (s1.equals(null)) {
                return false;
            }
        } catch (DataFormatException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    /**
     * Test the constructor of StudentRecord with valid input.
     * 
     * @return true if the constructor is successful, false otherwise.
     */
    public static boolean studentRecordConstructorSuccessful() {
        try {
            StudentRecord s1 = new StudentRecord("Rob", "rob@wisc.edu", "1234567890", true);
        } catch (DataFormatException e) {
            return false;
        }

        return true;
    }

    /**
     * Test the constructor of StudentRecord with invalid input (missing "@" in email).
     * 
     * @return true if the constructor throws DataFormatException as expected, false otherwise.
     */
    public static boolean studentRecordConstructorUnSuccessful() {
        try {
            StudentRecord s1 = new StudentRecord("Rob", "rob.wisc.edu", "1234567890", true);
        } catch (DataFormatException e) {
            return true;
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    /**
     * Test the searchById method for searching a student record in a list.
     * 
     * @return true if all test cases pass, false otherwise.
     */
    public static boolean searchByIdTester() {
        // Create an ArrayList with student records
        ArrayList<StudentRecord> records = new ArrayList<>();

        try {
            StudentRecord s1 = new StudentRecord("Rob", "rob@wisc.edu", "1234567890", true);
            StudentRecord s2 = new StudentRecord("Joey", "joey@wisc.edu", "1233367890", true);
            records.add(s1);
            records.add(s2);

            // Test successful search
            try {
                StudentRecord result = ExceptionalCourseEnrollment.searchById(s1.getCampusID(), records);
                if (result != s1) {
                    return false;
                }
            } catch (NoSuchElementException e) {
                return false;
            }

            // Test unsuccessful search
            try {
                StudentRecord result = ExceptionalCourseEnrollment.searchById("1111167890", records);
                return false; // NoSuchElementException was not thrown as expected
            } catch (NoSuchElementException e) {
                // Check for the error message
                if (!e.getMessage().equals("No student record found!")) {
                    return false;
                }
            }
        } catch (DataFormatException e) {
            System.out.print(e.getMessage());
        }

        return true;
    }

    /**
     * Run all test methods and return the overall test result.
     * 
     * @return true if all tests pass, false otherwise.
     */
    public static boolean runAllTests() {
        boolean det = true;

        det = searchByIdTester();
        det = studentRecordEqualsTester();
        det = studentRecordConstructorSuccessful();
        det = studentRecordConstructorUnSuccessful();

        return det;
    }

    public static void main(String[] args) {
        System.out.println("-----------------------------------------------");
        System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
    }
}
