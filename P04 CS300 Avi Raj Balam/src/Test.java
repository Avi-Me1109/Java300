import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Test extends ExceptionalCourseEnrollment{
  
  
  public Test(String courseName, int enrollmentCapacity, int waitlistCapacity) {
    super(courseName, enrollmentCapacity, waitlistCapacity);
    // TODO Auto-generated constructor stub
  }

  public void loadRoster(File rosterFile) throws FileNotFoundException, DataFormatException {
    try (Scanner scanner = new Scanner(rosterFile)) {
        while (scanner.hasNextLine() && !isRosterFull()) {
            String line = scanner.nextLine();

            if (!line.isEmpty()) {
                // Parse the line into a StudentRecord
                StudentRecord student = lineToRecord(line);

                // Check if the roster is full after adding the student
                if (!isRosterFull()) {
                    enrollOneStudent(student);
                } 
                
                else {
                    // The course capacity would be exceeded by loading that student
                    throw new IllegalStateException("The course capacity would be exceeded by loading that student!");
                }
            }
        }
    } catch (FileNotFoundException e) {
        // Handle the case where the file is not found
        System.err.println("Could not find that file!");
    }
  }

    private StudentRecord lineToRecord(String line) throws DataFormatException {
      // Remove extra whitespace at the beginning and end of the line
      line = line.trim();

      // Split the line into components using the comma and space as the separator
      String[] lineParts = line.split(", ");

      if (lineParts.length != 4) {
          throw new DataFormatException("Line is not correctly formatted.");
      }

      // Extract individual components
      String name = lineParts[0];
      String email = lineParts[1];
      String campusID = lineParts[2];
      boolean preReqValue;
      
      
      
      try {
          preReqValue = Boolean.parseBoolean(lineParts[3]);
      } catch (IllegalArgumentException e) {
          throw new DataFormatException("Invalid preReqValue. Must be a boolean value.");
      }

      
      // Create and return a StudentRecord object
      return new StudentRecord(name, email, campusID, preReqValue);
      
      
    }
    
  public static void main (String [] args) {
   
    Test test = null;
    
    File file = new File("saved_roster.txt");
    
    try {
      test.loadRoster(file);
    }
   
    catch(Exception e) {
      System.out.print(e.getMessage());
    }
    
  }
  
}
