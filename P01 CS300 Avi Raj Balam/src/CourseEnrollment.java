//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Course Enrollment Class with main Methods
// Course:   CS 300 Fall 2023
//
// Author:   Avi Raj Balam
// Email:    abalam@wisc.edu
// Lecturer: (Hobbes LeGault or Mark Mansi or Mouna Kacem)
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Online Sources: https://www.geeksforgeeks.org/different-ways-to-declare-and-initialize-2-d-array-in-java/# (making entire row null)
//
///////////////////////////////////////////////////////////////////////////////
// Below is a javadoc class header to complete
/**
 * The CourseEnrollment class is a Java utility for managing student enrollment, waitlisting, and related operations in a course.
 * 
 * @author Avi Raj Balam
 *
 */

public class CourseEnrollment {

  /**
   * Create an empty new list String[][] with a given capacity. The list should be empty, meaning
   * that it must contain null references only. For instance if the capacity of the list to create
   * is 4, the returned array must contain 4 null references.
   * 
   * @param capacity maximum number of students to be stored in the list. We assume that capacity is
   *                 greater than zero.
   * @return the created list which contains null references only
   */
  public static String[][] createEmptyList(int capacity) {

    String[][] List = new String[capacity][];

    return List;

  }

  /**
   * Prints the list of all the students enrolled in the course, with respect to the following
   * format.
   * 
   * Maximum capacity: enrollment_capacity<BR>
   * Number of enrolled students: size<BR>
   * 1. name (email, campusId)<BR>
   * 2. name (email, campusId)<BR>
   * ...
   * 
   * Every entry must be in a newline. Each of the students records is printed in the format:
   * "order. name (email, campusId)"
   * 
   * where order represents index+1 of the student records in roster (orders are in the range
   * 1..size and NOT in the range 0..size-1), name, email, and campusId represent the name, email
   * address, and campusId of the enrolled student.
   * 
   * We assume all inputs are valid.
   * 
   * @param roster an oversize two-dimensional of strings storing student records. When roster[i] is
   *               not null, it references a one-dimensional array of strings whose length is
   *               exactly three, and where roster[i][0], roster[i][1], and roster[i][2]
   *               respectively represent the name, email address, and campus Id of the student
   *               record stored at index i.
   * @param size   the number of student records stored in roster.
   */
  public static void printRoster(String[][] roster, int size) {

    System.out.println("Maximum capacity: " + roster.length);
    System.out.println("Number of enrolled students: " + size);

    for (int i = 0; i < size; i++) {

      System.out.print((i+1) + ". ");
      System.out.print(roster[i][0] + " (");
      System.out.print(roster[i][1] + ", ");
      System.out.print(roster[i][2] + ")");
      System.out.println();

    }


  }

  /**
   * Prints the list of all the students in the waitlist the course, with respect to the following
   * format.
   * 
   * Waitlist capacity: waitlist_capacity<BR>
   * 1. name (email, campusId)<BR>
   * 2. name (email, campusId)<BR>
   * ...
   * 
   * Every entry must be in a newline. Each of the students records is printed in the format:
   * "order. name (email, campusId)"
   * 
   * where order starts at 1 for the student stored at index 0, name, email, and campusId represent
   * the name, email address, and campusId of the waitlisted student.
   * 
   * We assume all inputs are valid.
   * 
   * @param waitlist a perfect size non-compact two-dimensional of strings storing student records.
   *                 When waitlist[i] is not null, it references a one-dimensional array of strings
   *                 whose length is exactly three, and where waitlist[i][0], waitlist[i][1], and
   *                 waitlist[i][2] respectively represent the name, email address, and campus Id of
   *                 the student record stored at index i.
   */
  public static void printWaitlist(String[][] waitlist) {

    int a = waitlist.length;
    System.out.println("Waitlist capacity: " + a);

    int count = 0;
    for (int i = 0; i < a; i++) {

      if (waitlist[i] != null) {
        
        count++;
        System.out.print((count) + ". ");
        System.out.print(waitlist[i][0] + " (");
        System.out.print(waitlist[i][1] + ", ");
        System.out.print(waitlist[i][2] + ")");
        System.out.println();
      }

    }

  }


  /**
   * Returns the index of the student record having an exact match with campusId. The search is made
   * in a two-dimensional oversize array. We assume that campusId values are unique.
   * 
   * @param campusId a 10-digits string. We assume that the format of campusID is valid.
   * @param list     an oversize two-dimensional of strings storing student records. When list[i] is
   *                 not null, it references a one-dimensional array of strings whose length is
   *                 exactly three, and where list[i][0], list[i][1], and list[i][2] respectively
   *                 represent the name, email address, and campus Id of the student record stored
   *                 at index i. We assume that (1) list is not null, (2) its length is greater than
   *                 zero, and (3) it stores valid/correct student records. We also assume that when
   *                 i is greater or equal to size, list[i] is null.
   * @param size     the number of student records stored in list.
   * @return the index of the record containing an exact match with campusId, and -1 if no match
   *         found.
   */
  public static int indexOf(String campusId, String[][] list, int size) {
    for (int x = 0; x < size; x++) {
        if (list[x] != null && list[x][2] != null && list[x][2].equals(campusId)) {
            return x; // Found a matching campusId
        }
    }
    return -1; // No match found
}


  /**
   * Returns the index of the student record having an exact match with campusId. The search is made
   * in a two-dimensional non-compact perfect size array. We assume that campusId values are unique.
   * 
   * @param campusId a 10-digits string. We assume that the format of campusID is valid.
   * @param list     a non-compact perfect-size two-dimensional of strings storing student records.
   *                 When list[i] is not null, it references a one-dimensional array of strings
   *                 whose length is exactly three, and where list[i][0], list[i][1], and list[i][2]
   *                 respectively represent the name, email address, and campus Id of the student
   *                 record stored at index i. We assume that (1) list is not null, (2) its length
   *                 is greater than zero, and (3) it stores valid/correct student records. We also
   *                 assume that null references can be at any position of the array list and NOT
   *                 necessary pushed to the back of the array.
   * @return the index of the record containing an exact match with campusId, and -1 if no match
   *         found.
   */
  public static int indexOf(String campusId, String[][] list) {
    for (int x = 0; x < list.length; x++) {
        if (list[x] != null && list[x][2] != null && list[x][2].equals(campusId)) {
            return x; // Found a matching campusId
        }
    }
    return -1; // No match found
}



  // We assume the addWaitlist method is only called when the course reaches its enrollment capacity
  /**
   * Adds the student record {name, email, campusId} to the first available position in the
   * waitlist, meaning to the first null reference, if the course pre-requisites are satisfied, the
   * student record is not already in the waitlist, and there is a room to add the new student
   * record to the waitlist.
   * 
   * This method prints the following message is the student record is successfully added to the
   * waitlist:
   * 
   * "You are successfully added to the waitlist of this course."
   * 
   * This method prints the following error message if the pre-requisites of the course are not
   * satisfied:
   * 
   * "Error: You do not meet the prerequisite(s) for this course."
   * 
   * This method prints the following error message if a matching student record is already in the
   * list:
   * 
   * "Error: You are already in the waitlist of this course."
   * 
   * This method prints the following error message if the waitlist is full (meaning it does not
   * contain any null reference).
   * 
   * "Error: Course closed! Waitlist full."
   * 
   * @param name                  name of a student. We assume that name is not null and is not
   *                              blank.
   * @param email                 email address of the student to add. We assume that email ends
   *                              with "@wisc.edu"
   * @param campusId              10-digits campusId. We assume that campusId is valid
   * @param prerequisiteSatisfied boolean, evaluated to true if the prerequisite(s) for this course
   *                              are satisfied, false otherwise.
   * 
   * @param waitlist              a perfect size two-dimensional non-compact array storing an
   *                              ordered list of student records waiting to secure a spot in the
   *                              course. Every of the student records is a one-dimensional array of
   *                              strings whose length is exactly 3. A student record is represented
   *                              by the triplet {name, email, campusId}. This means that
   *                              roster[i][0], roster[i][1], and roster[i][2] represent the name,
   *                              email address, and campusId of the record stored at index i within
   *                              the roster array. Null references can be at any position in the
   *                              list array.
   * @return true if the student record was successfully added to the waitlist, and false otherwise.
   */
  public static boolean addWaitlist(String name, String email, String campusId,
      boolean prerequisiteSatisfied, String[][] waitlist) {
    // Step 1: Check if the prerequisite is satisfied
    if (!prerequisiteSatisfied) {

      System.out.println("Error: You do not meet the prerequisite(s) for this course.");
      return false;

    }

    // Step 2: Check if the student is already in the waitlist
    for (String[] studentRecord : waitlist) {

      if (studentRecord != null && studentRecord.length == 3 && studentRecord[1].equals(email)
          && studentRecord[2].equals(campusId)) {
        System.out.println("Error: You are already in the waitlist of this course.");
        return false;

      }
    }

    // Step 3: Check if there is space available in the waitlist
    boolean spaceAvailable = false;
    for (int i = 0; i < waitlist.length; i++) {
      if (waitlist[i] == null) {
        spaceAvailable = true;
        // Add the student's record to the first available position
        waitlist[i] = new String[] {name, email, campusId};
        System.out.println("You are successfully added to the waitlist of this course.");
        return true;
      }
    }

    // Step 4: If all checks pass, but there is no space in the waitlist
    if (!spaceAvailable) {
      System.out.println("Error: Course closed! Waitlist full.");
    }

    return false; // Default return if none of the conditions are met.
  }



  /**
   * Enrolls one student given their name, email address and campusId in a specific course. The
   * course enrollment is defined by the course roster (a 2D oversize array) and a waitlist (a 2D
   * compact perfect size array).
   * 
   * 
   * If the student (1) has NOT been already enrolled in the course, (2) satisfies the course
   * pre-requisites and (3) the course roster did not reach each capacity, the student record {name,
   * email, campusId} is added to the end of the array roster, and the size of the roster is
   * incremented by one. In this case, the student is successfully enrolled in the course. The
   * method prints the exact following message and it returns the new size of the roster.
   * 
   * "You are successfully enrolled in this class."
   * 
   * In case there is a match with the student record in the waitlist, the student record must be
   * removed off the waitlist after it is successfully added to the roster. Note that the waitlist
   * is a non-compact perfect size 2D array, meaning that setting the reference of an element stored
   * at a given index to null, removes it off the array.
   * 
   * If the student is already enrolled in the course, no changes are made to the contents of the
   * course enrollment lists (roster and waitlist). The method returns the current size of the
   * roster, and prints the exact following message.
   * 
   * "Error: You are already enrolled in this class."
   * 
   * If the roster is full (reached its capacity), this method does not make any changes to the
   * contents of roster or the waitlist. The method prints the exact following message and it
   * returns the current size of the roster.
   * 
   * "The course is full. Please make another selection or try adding yourself to the waitlist."
   * 
   * If the pre-requisites of the course are NOT satisfied, no changes will be made to the course
   * enrollment lists (roster and waitlist). The method prints the exact following message and it
   * returns the current size of the roster.
   * 
   * "Error: You do not meet the prerequisite(s) for this course."
   * 
   * @param name                  name of a student. We assume that name is not null and is not
   *                              blank.
   * @param email                 email address of the student to add. We assume that email ends
   *                              with "@wisc.edu"
   * @param campusId              10-digits campusId. We assume that campusId is valid
   * @param prerequisiteSatisfied boolean, evaluated to true if the prerequisite(s) for this course
   *                              are satisfied, false otherwise.
   * @param roster                2D oversize array which stores the records of students enrolled in
   *                              the course. Every of the student records is a one-dimensional
   *                              array of strings whose length is exactly 3. A student record is
   *                              represented by the triplet {name, email, campusId}. This means
   *                              that roster[i][0], roster[i][1], and roster[i][2] represent the
   *                              name, email address, and campusId of the record stored at index i
   *                              within the roster array.
   * @param size                  the number of student records stored in roster
   * @param waitlist              2D non-compact perfect size arrays storing the records {name,
   *                              email, campusId} of students in the waitlist of this course.
   * @return the size of roster after successfully enrolling the student in the course, or the input
   *         size if the enrollment fails for any reason.
   */
  public static int enrollOneStudent(String name, String email, String campusId,
      boolean prerequisiteSatisfied, String[][] roster, int size, String[][] waitlist) {
    // Step 1: Check if the student is already enrolled
    for (int i = 0; i < size; i++) {
      if (roster[i][2].equals(campusId)) {
        System.out.println("Error: You are already enrolled in this class.");
        return size; // No changes to the roster
      }
    }

    // Step 2: Check if the prerequisites are satisfied
    if (!prerequisiteSatisfied) {
      System.out.println("Error: You do not meet the prerequisite(s) for this course.");
      return size; // No changes to the roster
    }

    // Step 3: Check if the roster is full
    if (size >= roster.length) {
      System.out.println(
          "The course is full. Please make another selection or try adding yourself to the waitlist.");
      return size; // No changes to the roster
    }

    // Step 4: Add the student's record to the end of the roster
    roster[size] = new String[] {name, email, campusId};
    size++;

    // Step 5: Remove the matching student record from the waitlist
    for (int i = 0; i < waitlist.length; i++) {
      if (waitlist[i] != null && waitlist[i][2].equals(campusId)) {
        waitlist[i] = null; // Remove from waitlist
        break; // Stop after removing one matching record
      }
    }

    // Step 6: Print success message and return the new size of the roster
    System.out.println("You are successfully enrolled in this class.");
    return size;
  }



  /**
   * Removes the student record whose campusId matches the one provided as input off the roster
   * list. We assume that all the inputs are valid and correctly formatted. We also assume that
   * campusId are unique.
   * 
   * The roster array is an ordered oversize array. This means that a successful removal operation
   * must involve a shift operation if the element to be removed is in the range 0..size-2. This
   * means that this method must preserve the order of precedence of the student records stored in
   * the roster array. The size of roster must be decremented by one after successfully removing the
   * student record off the roster.
   * 
   * This method prints the following message if the course was successfully dropped.
   * 
   * "You just dropped the course. This action cannot be revoked."
   * 
   * This method does not make any changes to the contents of the roster array and prints the
   * following message if there is no student record matching the input campusId:
   * 
   * "Error: You are not enrolled in the course!"
   * 
   * @param campusId 10-digits campusId. We assume that campusId is valid
   * @param roster   2D oversize array storing students records. Every of the student records is a
   *                 one-dimensional array of strings whose length is exactly 3. A student record is
   *                 represented by the triplet {name, email, campusId}. This means that
   *                 roster[i][0], roster[i][1], and roster[i][2] represent the name, email address,
   *                 and campusId of the record stored at index i within the roster array.
   * @param size     number of student records stored in roster
   * @return the size of the roster after the student record was successfully removed, or the same
   *         input size if the drop operation fails for any reason.
   */
  public static int dropCourse(String campusId, String[][] roster, int size) {
    // Step 1: Search for the student record with the matching campusId
    int indexToRemove = -1; // Initialize with a value that indicates the record was not found

    for (int i = 0; i < size; i++) {
      if (roster[i][2].equals(campusId)) {
        indexToRemove = i;
        break; // Stop searching once a matching campusId is found
      }
    }

    // Step 2: Remove the student record and shift elements to fill the gap
    if (indexToRemove >= 0) {
      for (int i = indexToRemove; i < size - 1; i++) {
        // Shift elements to the left to fill the gap
        roster[i] = roster[i + 1];
      }

      // Set the last element to null to remove it
      roster[size - 1] = null;

      // Step 3: Decrement the size of the roster
      size--;

      // Step 4: Print success message
      System.out.println("You just dropped the course. This action cannot be revoked.");
    } else {
      // Step 5: Print error message if no matching campusId is found
      System.out.println("Error: You are not enrolled in the course!");
    }

    // Return the new size of the roster (updated after removal or unchanged)
    return size;


  }

}
