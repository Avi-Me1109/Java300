//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////

//
// Title:    Hiring Class
// Course:   CS 300 Fall 2023
//
// Author:   Avi Raj Balam
// Email:    abalam@wisc.edu
// Lecturer: (Hobbes LeGault or Mark Mansi or Mouna Kacem)
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Online Sources: https://www.geeksforgeeks.org/how-to-find-all-elements-in-a-given-array-except-for-the-first-one-using-javascript/
//
///////////////////////////////////////////////////////////////////////////////
// Below is a javadoc class header to complete
/**
 * 
 * The Hiring class implements various candidate selection methods, including greedy and recursive algorithms, 
 * to optimize the hiring process based on factors such as candidate availability, cost, and required work hours.
 * 
 * @author Avi Raj Balam
 */

public class Hiring {

    public Hiring() {
        // Default constructor
    }

    /**
     * Greedy algorithm to hire candidates based on available hours.
     * @param candidates List of available candidates
     * @param hired List of candidates already hired
     * @param hiresLeft Number of hires left
     * @return List of hired candidates
     */
    public static CandidateList greedyHiring(CandidateList candidates, CandidateList hired, int hiresLeft) {
        if (hiresLeft == 0) {
            return hired;  // Return the current hired list
        }

        if (candidates.size() <= hiresLeft) {
            // Hire all available candidates if there are fewer or equal hires left than available candidates
            for (Candidate candidate : candidates) {
                hired = hired.withCandidate(candidate);
            }
            return hired;
        }

        int covered_hours = hired.numCoveredHours();
        for (Candidate candidate : candidates) {
            CandidateList remainingCandidates = candidates.withoutCandidate(candidate);
            CandidateList updatedHired = hired.withCandidate(candidate);
            CandidateList after_hire = greedyHiring(remainingCandidates, updatedHired, hiresLeft - 1);
            
            int max_covered_hours = after_hire.numCoveredHours();
            
            if (max_covered_hours > covered_hours) {
                return after_hire; // Return the updated hired list if it covers more hours
            }
        }

        return hired;
    }

    /**
     * Recursive method to find the optimal set of candidates meeting minimum required hours with the least cost.
     * @param candidates List of available candidates
     * @param hired List of candidates already hired
     * @param minHours Minimum hours to cover
     * @return Optimal set of hired candidates
     */
    public static CandidateList minCoverageHiring(CandidateList candidates, CandidateList hired, int minHours) {
        if (minHours == 0) {
            return new CandidateList(); // Base case: no hours to cover
        }

        if (candidates.isEmpty()) {
            return null; // Base case: no candidates left to hire
        }

        CandidateList withoutFirst = minCoverageHiring(candidates.withoutCandidate(candidates.get(0)), hired, minHours);
        CandidateList currentCandidate = new CandidateList();
        currentCandidate.add(candidates.get(0));

        // Check if the current candidate's hours fit into the remaining minimum hours required
        if (currentCandidate.numCoveredHours() <= minHours) {
            CandidateList withFirst = new CandidateList(hired);
            withFirst.withCandidate(currentCandidate.get(0));
            CandidateList withFirstRecursive = minCoverageHiring(candidates.withoutCandidate(candidates.get(0)), withFirst, minHours - currentCandidate.numCoveredHours());

            // Compare solutions and return the optimal set
            if (withFirstRecursive != null) {
                if (withoutFirst == null || withFirstRecursive.totalCost() < withoutFirst.totalCost()) {
                    return withFirstRecursive;
                }
            }
        }

        // If hiring the first candidate is not optimal, return the solution without hiring them
        return withoutFirst;
    }

    /**
     * Finds the optimal set of candidates considering hours and cost using a recursive approach.
     * @param candidates List of available candidates
     * @param hired List of candidates already hired
     * @param hiresLeft Number of hires left
     * @return Optimal set of hired candidates
     */
    public static CandidateList optimalHiring(CandidateList candidates, CandidateList hired, int hiresLeft) {
        CandidateList bestSelection = new CandidateList();

        if (hiresLeft <= 0) {
            return bestSelection;
        }

        for (int i = 0; i < candidates.size(); i++) {
            Candidate candidate = candidates.get(i);
            CandidateList remainingCandidates = new CandidateList(candidates.subList(i + 1, candidates.size()));

            CandidateList withCandidate = optimalHiring(remainingCandidates, hired.withCandidate(candidate), hiresLeft - 1);
            CandidateList withoutCandidate = optimalHiring(remainingCandidates, hired, hiresLeft);

            if (withCandidate.numCoveredHours() > withoutCandidate.numCoveredHours()) {
                return withCandidate;
            }

            if (withCandidate.numCoveredHours() == withoutCandidate.numCoveredHours()) {
                if (withCandidate.totalCost() < withoutCandidate.totalCost()) {
                    return withCandidate;
                }
                return withoutCandidate;
            }
        }

        return bestSelection;
    }
}
