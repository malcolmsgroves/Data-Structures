/*
 * 
 * 		Malcolm Groves
 * 		16 Novemer 2016
 * 
 * 		This class contains a main method that
 * 		finds all the words in a word search
 * 		board using the three methods in the 
 * 		WordSearch class: naively, binary search,
 * 		and prefix search with binary search. The
 * 		runtimes for each method are printed
 * 		so that the user can compare the algorithms.
 * 
 * 		This code is from Professor Majercik's
 * 		Blackboard starter file.
 * 
 */


public class TestWordSearch {

	// Purpose: This is the main method for the class
	//			that runs each solution method and prints
	//			the runtimes.
	// Parameters: none
	// Return: void
	//
	public static void main(String[] args) {

		System.out.println("Welcome to Word Search!");

		// Create the puzzle.
		WordSearch puzzle = new WordSearch();

		// Solve the puzzle using all three approaches and output the solution times.
		if (puzzle.isGoodPuzzle()) {
			System.out.println("Using naive approach:");
			double solutionTime = puzzle.solve(WordSearch.SolutionMethod.NAIVE);
			System.out.println("Solution time: " + solutionTime + " seconds.\n");

			System.out.println("Using binary search approach:");
			solutionTime = puzzle.solve(WordSearch.SolutionMethod.BINARY_SEARCH);
			System.out.println("Solution time: " + solutionTime + " seconds.\n");

			System.out.println("Using binary search approach with prefixes:");
			solutionTime = puzzle.solve(WordSearch.SolutionMethod.USE_PREFIXES);
			System.out.println("Solution time: " + solutionTime + " seconds.\n");
		}
	}

}
