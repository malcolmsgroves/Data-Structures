/*
 * 		Malcolm Groves
 * 		15 November 2016
 * 
 * 		This class contains methods to find all
 * 		the words in a word search board. Words
 * 		can be found using a naive method, a binary
 * 		search method, or a prefix binary search
 * 		method. 
 * 
 * 		Board is read from a file in a files folder
 * 		and stored in a character array.
 * 
 * 		Code is adapted from Professor
 * 		Majercik's starter code.
 * 
 */


import java.io.File;
import java.util.Scanner;


public class WordSearch {

	// To identify the different solution methods
	public static enum SolutionMethod {
		NAIVE, BINARY_SEARCH, USE_PREFIXES
	}

	// To hold the puzzle. 
	private static final int MAX_ROWS = 50;
	private static final int MAX_COLS = 50;
	char[][] board = new char[MAX_ROWS][MAX_COLS];


	// The actual number of rows and columns.
	int numRows;
	int numCols;

	// To signal that the puzzle is in a legal format
	boolean goodPuzzle;

	// The user gets to specify the minimum word length
	int minWordLength;

	// Holds the words that we are searching for
	Dictionary dictionary;

	// To get input from the user
	private static Scanner scan = new Scanner(System.in);



	// The constructor creates the dictionary and reads the puzzle.
	// If the puzzle is good, it prints it out and gets the minimum 
	// length from the user
	public WordSearch() {

		dictionary = new Dictionary();

		goodPuzzle = readPuzzle();
		if (goodPuzzle) {
			printPuzzle();
			System.out.println("Read puzzle with " + numRows + " rows and " + numCols + " columns");

			System.out.print("Minimum word length: ");
			minWordLength = scan.nextInt();
			System.out.println();
		}

	}


	// Purpose: This method reads in a Word Search puzzle from a file.
	// Parameters: None.
	// Return Value: True, if the puzzle was in a legal format; 
	//				 otherwise, false.
	//
	public boolean readPuzzle() {

		Scanner fileScan = null;
		
		try {

			System.out.println();
			System.out.print("Please enter the puzzle file name without the \".txt\" extension: ");
			String boardFileName = scan.next();

			// The boards are in folder called "files" and the
			// user will enter the filename without the .txt file
			// extension, so we must add those.
			boardFileName = "files/" + boardFileName + ".txt";
			fileScan = new Scanner(new File(boardFileName));

			// Make sure the file has something in it.
			if (!fileScan.hasNext()) {
				System.out.println("Error: puzzle file is empty");
				numRows = 0;
				numCols = 0;
				return false;
			}

			// Get the first line; all the other lines should be
			// the same length (which is the number of columns).
			String line = fileScan.nextLine();
			numCols = line.length();
			// Put the first line in the board array.
			for (int i = 0; i < numCols; i++) {
				board[0][i] = Character.toLowerCase(line.charAt(i));
			}

			// Read the rest of the puzzle lines and put them in 
			// the board array; keep track of the number of lines (rows)
			for (numRows = 1; fileScan.hasNext(); ++numRows) {
				line = fileScan.nextLine();

				// Make sure it’s the right length.
				if (line.length() != numCols) {
					System.out.println("Error: puzzle is not rectangular");
					numRows = 0;
					numCols = 0;
					return false;
				}

				// Put this puzzle line in board.
				for (int i = 0; i < numCols; i++) {
					board[numRows][i] = Character.toLowerCase(line.charAt(i));
				}
			}

			fileScan.close();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(fileScan != null) fileScan.close();
		}

		// Puzzle was legal.
		return true;
	}



	// Purpose: This method returns the value of goodPuzzle,
	//          which indicates whether the current puzzle is
	//          in a legal format.
	//
	public boolean isGoodPuzzle() {
		return goodPuzzle;
	}



	// Purpose: This method solves the puzzle with the specified method.
	// Parameters: The solution method to use.
	// Return Value: The time taken to solve the puzzle.
	//
	public double solve(SolutionMethod method) {

		long startTime = System.nanoTime();

		int numMatches = 0;

		if (method == SolutionMethod.NAIVE) {
			numMatches = solveNaively();
		}
		else if (method == SolutionMethod.BINARY_SEARCH) {
			numMatches = solveWithBinarySearch();
		}
		else if (method == SolutionMethod.USE_PREFIXES) {
			numMatches = solveWithPrefixes();
		}

		long endTime = System.nanoTime();
		double timeInSeconds = (endTime - startTime) / 1e9; // The number of nanoseconds in a second is 1e9


		System.out.println("Found " + numMatches + " matches");

		return timeInSeconds;
	}



	// Purpose: Look for every word in the dictionary by looking at
	//			every position in every direction for each word and
	//			calling checkWord.
	//			Print words found and the positions of the words.
	// Paramters: None
	// Return: Number of words found
	// 
	public int solveNaively() {

		// instance variables for method
		int numWordsFound = 0;
		String currWord;

		// look at every word in the dictionary
		for(int i = 0; i < dictionary.size(); ++i) {
			currWord = dictionary.getEntry(i);

			// if the word is long enough
			if(currWord.length() >= minWordLength) {

				// for every row and every column
				for(int r = 0; r < numRows; ++r) {
					for(int c = 0; c < numCols; ++c) {

						// look left and right, up and down
						for(int hor = -1; hor < 2; ++hor) {
							for(int vert = -1; vert < 2; ++vert) {

								// if direction is not 0
								if(!(hor == 0) || !(vert == 0)) {

									// check position for currWord
									// in the given direction
									numWordsFound += 
											checkWord(r, c, vert, 
													hor, currWord);
								}
							}
						}
					}
				}
			}
		}
		return numWordsFound;

	}

	// Purpose: At a given position and direction on the board, check 
	//			for a given word in the dictionary. To be used with
	//			solveNaively.
	// Parameters: 	Integers for starting row and column, integers
	//				for the row direction and column direction, and
	//				a string that is the desired word.
	// Return: 1 if the word is found, 0 if the word is not found.
	//
	public int checkWord(int baseRow, int baseCol,
			int rowDelta, int colDelta, String word) {

		int wordLength = word.length();

		// Calculate the end coordinates of the word
		int rowBound = baseRow + rowDelta * (wordLength - 1);
		int colBound = baseCol + colDelta * (wordLength - 1);

		// If the end coordinates of the word are in the puzzle
		if(rowBound > -1 && rowBound < numRows) {
			if(colBound > -1 && colBound < numCols) {

				// check if the word is at that position 
				// in that direction by checking every character
				for(int i = 0; i < wordLength; ++i) {

					char currChar = board[baseRow + rowDelta * i][baseCol + colDelta * i];

					// if the character on the board does not
					// match the appropriate character in the 
					// word, word is not found, return 0
					if(currChar != word.charAt(i)) {
						return 0;
					}
				}

				// Print word that is found and the coordinates
				// of the first and last letters
				System.out.println("Found \"" + word + "\" at (" +
						baseRow + ", " + baseCol + ") to (" + rowBound +
						", " + colBound + ")");

				return 1;
			}
		}

		// else, word not found, return 0
		return 0;

	}



	// Purpose: Check for words in every position in every
	// 			direction using checkDirectionUsingBinarySearch.
	// Parameters: none
	// Return: an integer of the number of words found
	//
	public int solveWithBinarySearch() {

		int numWordsFound = 0;

		// for every row and every column
		for(int r = 0; r < numRows; ++r) {
			for(int c = 0; c < numCols; ++c) {

				// check left and right, up and down
				for(int hor = -1; hor < 2; ++hor) {
					for(int vert = -1; vert < 2; ++vert) {

						// if direction is not zero
						if(!(hor == 0) || !(vert == 0)) {

							// check position for any words
							// using binary search
							numWordsFound += 
									checkDirectionUsingBinarySearch(
											r, c, vert, hor);
						}
					}
				}
			}
		}

		return numWordsFound;
	}


	// Purpose: At a given position and direction on the puzzle board,
	//			find all words that are in the dictionary. Search dictionary
	//			for words using binarySearch.
	// Parameters: 	Integers for starting row and column and increment integers
	//				for the row direction and column direction.
	// Return: Integer number of words found.
	//
	public int checkDirectionUsingBinarySearch(int baseRow, int baseCol,
			int rowDelta, int colDelta) {

		// true if row and column are on the board
		boolean validRow = true;
		boolean validCol = true;

		int numWordsFound = 0;
		String currWord = "";

		int currRow = baseRow;
		int currCol = baseCol;

		// index for increments
		int i = 0;

		// while the end of the word is on the board
		while(validRow && validCol) {

			// add the character to currWord
			currWord += board[currRow][currCol];

			// if word is at least the minWordLength
			if(currWord.length() >= minWordLength) {

				// if word is in the dictionary
				if(dictionary.binarySearch(currWord)) {

					++numWordsFound;

					// print word and coordinates for where word
					// begins and ends
					System.out.println("Found \"" + currWord + "\" at (" +
							baseRow + ", " + baseCol + ") to (" + currRow +
							", " + currCol + ")");
				}
			}

			// increment index i
			++i;

			// calculate the row and column of the next char
			currRow = baseRow + i * rowDelta;
			currCol = baseCol + i * colDelta;

			// check if the new row and new column are on board
			validRow = (currRow > -1 && currRow < numRows);
			validCol = (currCol > -1 && currCol < numCols);
		}

		return numWordsFound;
	}



	// Purpose: Check for words at every position in every
	// 			direction using checkDirectionUsingPrefixes.
	// Parameters: none
	// Return: an integer of the number of words found
	//
	public int solveWithPrefixes() {

		int numWordsFound = 0;

		// for every row and every column
		for(int r = 0; r < numRows; ++r) {
			for(int c = 0; c < numCols; ++c) {

				// look left and right, up and down
				for(int hor = -1; hor < 2; ++hor) {
					for(int vert = -1; vert < 2; ++vert) {

						// if the direction is not 0
						if(!(hor == 0) || !(vert == 0)) {

							// check position and direction for
							// words using prefix search method
							numWordsFound += 
									checkDirectionUsingPrefixes(
											r, c, hor, vert);
						}
					}
				}
			}
		}

		return numWordsFound;
	}


	// Purpose: Search in a given direction from a given position
	//			for words in the dictionary. Stop looking if the 
	//			current string is not a prefix found in the dictionary.
	// Parameters: 	Integers for starting row and column and increment integers
	//				for the row direction and column direction.
	// Return: Integer number of words found.
	//
	public int checkDirectionUsingPrefixes(int baseRow, int baseCol, 
			int rowDelta, int colDelta) {
		
		// booleans that are true if the current row and column
		// are on the board
		boolean validRow = true;
		boolean validCol = true;
		
		int numWordsFound = 0;
		String currWord = "";
		
		int currRow = baseRow;
		int currCol = baseCol;
		
		// index for the current character
		int i = 0;

		// while the current row and column are on board
		while(validRow && validCol) {

			// add new character to current word
			currWord += board[currRow][currCol];
			
			// if the currWord is not a prefix in the
			// dictionary, break out of the loop
			if(!dictionary.binaryPrefixSearch(currWord)) {
				break;
			}
			
			// if the currWord is at least as long as minWordLength
			if(currWord.length() >= minWordLength) {
				
				// if the currWord is in the dictionary
				if(dictionary.binarySearch(currWord)) {
					
					++numWordsFound;
					
					// print word and coordinates for where the word
					// begins and ends
					System.out.println("Found \"" + currWord + "\" at (" +
							baseRow + ", " + baseCol + ") to (" + currRow +
							", " + currCol + ")");
				}
			}

			++i;

			// calculate row and column of next character
			currRow = baseRow + i * rowDelta;
			currCol = baseCol + i * colDelta;
			
			// check if the new row and new column are on board
			validRow = (currRow > -1 && currRow < numRows);
			validCol = (currCol > -1 && currCol < numCols);
		}

		return numWordsFound;
	}


	// Purpose: This method prints out the puzzle.
	// Parameters: None.
	// Return Value: None.
	//
	public void printPuzzle() {

		System.out.println();
		System.out.println("The Board: ");
		for (int r = 0; r < numRows; r++){
			for (int c = 0; c < numCols; c++){
				if (board[r][c] == 0)
					System.out.print("_ ");
				else {
					String nextChar = board[r][c] + " ";
					System.out.print(nextChar.toUpperCase());
				}
			}
			System.out.println();
		}
		System.out.println();

	}



}
