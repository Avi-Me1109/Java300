//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////

//
// Title:    Hiring Testing
// Course:   CS 300 Fall 2023
//
// Author:   Avi Raj Balam
// Email:    abalam@wisc.edu
// Lecturer: (Hobbes LeGault or Mark Mansi or Mouna Kacem)
//
///////////////////////////////////////////////////////////////////////////////
// Below is a javadoc class header to complete
/**
 * This class is the testing method for the Hiring class
 * 
 * @author Avi Raj Balam
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HiringTesting extends HiringTestingUtilities{
  

  /**
   * Tests the base case of the greedyHiring method.
   * 
   * @return True if the base test for greedyHiring is successful, else False
   */
  public static boolean greedyHiringBaseTest() {
    
    CandidateList test_list = new CandidateList();
    boolean[] a_hours = {false, true, true, false, true, false};
    Candidate a = new Candidate(a_hours, 20);
    
    boolean[] b_hours = {true, true, true, true, true, true};
    Candidate b = new Candidate(b_hours, 10);
    
    boolean[] c_hours = {true, false, false, false, true, false};
    Candidate c = new Candidate(b_hours, 15);
    
    boolean determiner = true;
    
    test_list.add(a);
    test_list.add(b);
    
    CandidateList Hired_list = new CandidateList();
    
    //base case: hires = 0
    Hired_list = Hiring.greedyHiring(test_list, Hired_list, 0);
  
    
    if(!(Hired_list.numCoveredHours() == 0)) {
      determiner = false;
    }
    
    //base case: adding all candidates as number of hires higher than number of candidates
    test_list.add(c);
    Hired_list = Hiring.greedyHiring(test_list, Hired_list, 4);
    //System.out.println(Hired_list);
    
    if(!HiringTestingUtilities.compareCandidateLists(test_list, Hired_list)) {
      determiner = false;
    }
    
//    System.out.print(determiner);

    return determiner;
  
  }
  
  /**
   * Tests the recursive implementation of the greedyHiring method.
   * 
   * @return True if the recursive test for greedyHiring is successful, else False
   */
  public static boolean greedyHiringRecursiveTest() {
    
    int hires = 2;
    boolean [] c1_available = {true, false, true, true};
    Candidate candidate1 = new Candidate(c1_available, 10);
    boolean [] c2_available = {false, false, true, false};
    Candidate candidate2 = new Candidate(c2_available, 20);
    boolean [] c3_available = {false, true, true, false};
    Candidate candidate3 = new Candidate(c3_available, 30);
    
    CandidateList candidates = new CandidateList();
    candidates.add(candidate1);
    candidates.add(candidate2);
    candidates.add(candidate3);
    
    
    CandidateList hiredList = new CandidateList();
    hiredList = Hiring.greedyHiring(candidates, hiredList, hires);
    
    CandidateList expectedHiredList = new CandidateList();
    expectedHiredList.add(candidate1);
    expectedHiredList.add(candidate3);
    
    return HiringTestingUtilities.compareCandidateLists(hiredList, expectedHiredList);
 

  }
  
  /**
   * Tests the base case of the optimalHiring method.
   * 
   * @return True if the base test for optimalHiring is successful, else False
   */
  public static boolean optimalHiringBaseTest() {
      CandidateList candidates = new CandidateList();
      // Add candidates to the list

      CandidateList hired = new CandidateList();
      // Add candidates to the hired list

      int hiresLeft = 0; // Base case: no more hires left

      CandidateList result = Hiring.optimalHiring(candidates, hired, hiresLeft);

      // Verify that the method returns an empty list when no hires are left
      if(!(result.numCoveredHours() == 0)) {
        return false;
      }
      
      else {
        return true;
      }
  }
  
  /**
   * Tests the recursive implementation of the optimalHiring method.
   * 
   * @return True if the recursive test for optimalHiring is successful, else False
   */
  public static boolean optimalHiringRecursiveTest() {
    CandidateList testList1 = new CandidateList();
    Candidate candidate1 = new Candidate(new boolean[]{true, true, false, false}, 20);
    Candidate candidate2 = new Candidate(new boolean[]{true, false, true, false}, 15);
    Candidate candidate3 = new Candidate(new boolean[]{false, true, true, false}, 10);

    testList1.add(candidate1);
    testList1.add(candidate2);
    testList1.add(candidate3);

    CandidateList hiredCandidates = new CandidateList();
    CandidateList optimalCandidates = Hiring.optimalHiring(testList1, hiredCandidates, 2);
    

    CandidateList expectedSelection = new CandidateList();
    expectedSelection.add(candidate1);
    expectedSelection.add(candidate2);

    return HiringTestingUtilities.compareCandidateLists(optimalCandidates, expectedSelection);
  }
  
  /**
   * Tests the base case of the minCoverageHiring method.
   * 
   * @return True if the base test for minCoverageHiring is successful, else False
   */
  public static boolean minCoverageHiringBaseTest() {
    
    CandidateList candidates = new CandidateList();
    // Add candidates to the list

    
    CandidateList hired = new CandidateList();
    // Add candidates to the hired list

    int minHours = 0; // Base case: no hours to cover

    CandidateList result = Hiring.minCoverageHiring(candidates, hired, minHours);
    
    minHours = 5;
    CandidateList result1 = Hiring.minCoverageHiring(candidates, hired, minHours);

    // Verify that the method returns an empty list when there are no hours to cover
    if(!((result.numCoveredHours() == 0) && (result1.numCoveredHours() == 0))) {
      return false;
    }
    
    else {
      return true;
    }
   
  }

  /**
   * Tests the recursive implementation of the minCoverageHiring method.
   * 
   * @return True if the recursive test for minCoverageHiring is successful, else False
   */
  public static boolean minCoverageHiringRecursiveTest() {
    CandidateList testList = new CandidateList();

    boolean[] aHours = {false, true, true};
    Candidate a = new Candidate(aHours, 20);

    boolean[] bHours = {true, true, true};
    Candidate b = new Candidate(bHours, 10);

    boolean[] cHours = {true, false, false};
    Candidate c = new Candidate(cHours, 15);

    testList.add(a);
    testList.add(b);

    CandidateList hiredList = new CandidateList();
    hiredList.add(c);
    CandidateList minCoverage = Hiring.minCoverageHiring(testList, hiredList, 3);
    //System.out.println(minCoverage);
    
    ArrayList<CandidateList> actualhiredList = HiringTestingUtilities.allMinCoverageSolutions(testList, 3);
    actualhiredList.add(hiredList);
    
    
    return !HiringTestingUtilities.compareCandidateLists(actualhiredList, minCoverage);
  }
  
  /**
   * Fuzz test for the optimalHiring method using random inputs.
   * 
   * @return True if the optimalHiring method passes the fuzz test, else False
   */
  public static boolean optimalHiringFuzzTest() {
    
    int num_candidates = 7;
    CandidateList candidates = HiringTestingUtilities.generateRandomInput(3, num_candidates, 20);
    
    Random random = new Random();
    
    int desired_hires = random.nextInt(1,num_candidates);
    
    CandidateList hiredCandidates = new CandidateList();
    CandidateList optimalCandidates = Hiring.optimalHiring(candidates, hiredCandidates, 2);
    
    ArrayList<CandidateList> expectedSelection = HiringTestingUtilities.allOptimalSolutions(candidates, desired_hires);

    return HiringTestingUtilities.compareCandidateLists(expectedSelection, optimalCandidates);
    
  }
  
  /**
   * Fuzz test for the minCoverageHiring method using random inputs.
   * 
   * @return True if the minCoverageHiring method passes the fuzz test, else False
   */
  public static boolean minCoverageFuzzTest() {
    
    CandidateList testList = HiringTestingUtilities.generateRandomInput(3, 3, 20);

    CandidateList hiredList = new CandidateList();
    CandidateList minCoverage = Hiring.minCoverageHiring(testList, hiredList, 3);
    
    ArrayList<CandidateList> actualhiredList = HiringTestingUtilities.allMinCoverageSolutions(testList, 3);
    actualhiredList.add(hiredList);
    
    
    return !HiringTestingUtilities.compareCandidateLists(actualhiredList, minCoverage);
    
  }



  /**
   * Main method to run and check all test cases.
   */
  public static void main (String [] args) {
    
    HiringTesting hiringTesting = new HiringTesting();
    boolean test1 = hiringTesting.greedyHiringBaseTest();
    boolean test2 = hiringTesting.greedyHiringRecursiveTest();
    boolean test3 = hiringTesting.optimalHiringRecursiveTest();
    boolean test4 = hiringTesting.optimalHiringBaseTest();
    boolean test5 = hiringTesting.minCoverageHiringRecursiveTest();
    boolean test6 = hiringTesting.minCoverageHiringBaseTest();
    boolean test7 = hiringTesting.optimalHiringFuzzTest();
    boolean test8 = hiringTesting.minCoverageFuzzTest();
    
    
  }
  
}
