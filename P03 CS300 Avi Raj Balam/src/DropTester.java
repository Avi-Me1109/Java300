
public class DropTester {
  
  public static void main(String [] args) {
      
      DropAssignmentGroup dropGroup = new DropAssignmentGroup(0.2, 2);
      SimpleAssignment[] assignments = new SimpleAssignment[]{
        new SimpleAssignment(100, false),
        new SimpleAssignment(90, false),
        new SimpleAssignment(80, false),
        new SimpleAssignment(70, false),
        new SimpleAssignment(60, false),
        new SimpleAssignment(50, false),
        new SimpleAssignment(40, false),
        new SimpleAssignment(30, false),
        new SimpleAssignment(20, false),
        new SimpleAssignment(10, false)
      };
      
      assignments[0].complete(50);
      assignments[1].complete(20);
      assignments[2].complete(30);

      
      dropGroup.addAssignments(assignments);
      
      //System.out.print(dropGroup.getTotalPossible());
      
      boolean a = dropGroup.isComplete();
      System.out.print(a);
     
      

      
    }
    
  }
  
