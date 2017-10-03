/*
 * 	
 * 		Malcolm Groves
 * 		28 October 2016
 * 
 * 		This is a boggle class that can read
 * 		a boggle board and store it as an object.
 * 		Contains methods that make it possible
 * 		to play boggle against the computer and
 * 		score the game, where the computer gets
 * 		every word the user does not get.
 */

import java.util.Scanner;
import java.io.File;


public class Boggle {

	// Constants
	private static final char BLANK = '0';
	private static final int DIMENSION = 4;
	private static final String ESCAPE = "!";

	// Instance objects for game
	private char[][] board = new char[DIMENSION][DIMENSION];
	private Dictionary dictionary = new Dictionary();
	private WordList computerWords = new WordList(true);
	private WordList userWords = new WordList(false);

	// Scanner objects
	private Scanner scan;
	private static Scanner scanUserInput = new Scanner(System.in);

	// Purpose: Default Boggle constructor, read
	//			a boggle board into the object. Get file
	//			name of board from user.
	// Parameters: None
	// Return: NA, constructor
	public Boggle() {
		System.out.println("Enter board name without '.txt':");
		String boardName = scanUserInput.next();
		readBoard(boardName);
		printBoard();
	}

	// Purpose: Play boggle, allowing the user to go first,
	//			and the computer to find all the words the
	//			user did not find.
	// Parameters: None
	// Return: void
	//
	public void play() {

		String playBoggle = "maybe";

		// ask user whether or not they want to play boggle
		while(!playBoggle.equals("y") && !playBoggle.equals("n")) {
			System.out.println("Would you like to challenge " +
					"the computer\nto a game of boggle? (y/n)");
			playBoggle = scanUserInput.next();
		}

		// If they want to play, let them
		if(playBoggle.equals("y")) {
			// Find all the words, then let the user play
			computerPlay();
			userPlay();

			// Disallow all words the user found
			computerWords.disallow(userWords);

			userWords.print(); 
			computerWords.print();
		}

		// otherwise, computer plays by itself and prints the words
		else {
			computerPlay();
			computerWords.print();
		}

	}


	// Purpose: This method allows the user to play boggle
	// Parameters: None
	// Return: void
	//
	public void userPlay() {

		printBoard();
		// Header with instructions
		System.out.println("Enter unique and valid "
				+ "words with three or more letters: ");
		System.out.println("Enter '" + ESCAPE + "' when done");

		// get user word
		String userInput = scanUserInput.next();

		// Loop through as long as user wants to enter words
		while(!userInput.equals(ESCAPE)) {

			// If word their word isn't in dictionary, reject
			if(!dictionary.binarySearch(userInput)) {
				System.out.println("INVALID WORD: " + userInput +
						" is not in the dictionary");
			}

			// If word is too short (< 3 letters), reject
			else if(userInput.length() < 3) {
				System.out.println("INVALID WORD: words must" +
						" be longer than 3 letters");
			}

			// If word is not on the board (check computer list), reject
			else if(!computerWords.search(userInput)) {
				System.out.println("INVALID WORD: " + userInput +
						" is not on the board");
			}

			// If word has already been played, reject
			else if(userWords.search(userInput)) {
				System.out.println("INVALID WORD: you have" +
						" already used " + userInput);
			}

			// If word is good, add it to the user's list 
			// and tell them their score
			else {
				userWords.add(userInput);
				int score = WordList.score(userInput);
				System.out.println("\"" + userInput + 
						"\" counts, congrats!  + " +
						score + " points");
			}

			// Print the board after every entry and get 
			// new word from user
			printBoard();
			userInput = scanUserInput.next();
		}
	}

	// Purpose: Have the computer find all the words
	// 			on the board by calling the recursive method
	//			searchPosition at every position on the board
	// Parameters: None
	// Return: Void
	//
	public void computerPlay() {

		// Search all board coordinates for words
		for (int r = 0; r < DIMENSION; r++) {
			for (int c = 0; c < DIMENSION; c++) {
				searchPosition("", r, c);
			}
		}
	}

	// Purpose: Search for all words starting from
	//			a given position, recursively.
	// Parameters: 	The existing string and the coordinates
	//				of the current position
	// Return: void
	//
	public void searchPosition(String str, int r, int c) {

		String newString;

		// stop if the position is out of bounds
		if (r < 0 || r >= DIMENSION || c < 0 || c >= DIMENSION) {
			return;
		}

		// stop if the position is blank
		if (board[r][c] == BLANK) {
			return;
		}

		// add character to current string
		char currChar = board[r][c];

		// If character is 'q', add a 'u'
		if(currChar == 'q') {
			newString = str + currChar + 'u';
		}
		// otherwise, just add it to the string
		else {
			newString = str + currChar;
		}

		// base case to ensure the prefix is in dictionary
		if(!dictionary.binaryPrefixSearch(newString)) {
			return;
		}

		// Blank out current position
		board[r][c] = BLANK;

		// If current string is a valid word, add to word list
		if(isValid(newString)) {
			computerWords.add(newString);
		}

		// Extend word by looking in every direction recursively
		for (int rOff = -1; rOff <= 1; rOff++) {
			for (int cOff = -1; cOff <= 1; cOff++) {
				//do not look at the same position again
				if (rOff != 0 || cOff != 0)
					searchPosition(newString, r + rOff, c + cOff);
			}
		}

		// reset position to original character
		board[r][c] = currChar;
	}

	// Purpose: Check if a given string is valid
	// Parameters: String to check
	// Return: Boolean, true if word is valid
	//
	public boolean isValid(String str) {

		// word is too short if < 3 letters
		if(str.length() < 3) {
			return false;
		}

		// word is good if it is in dictionary
		if(dictionary.binarySearch(str)) {
			return true;
		}

		// if it wasn't in dictionary, return false
		return false;


	}


	// Purpose: Read a file into the boggle object
	// Parameters: Filename of the board
	// Return: void
	//
	public void readBoard(String fileName) {

		try {
			// The boards are in folder called "files" and the
			// user will enter the filename without the .txt file
			// extension, so we must add those.
			String puzzleFileName = "files/" + fileName + ".txt";
			scan = new Scanner(new File(puzzleFileName));

			// Get the position at each index in file
			// and add to the boggle board
			for (int i = 0; i < DIMENSION; i++) {
				String row = scan.next();
				for (int j = 0; j < DIMENSION; j++) {
					board[i][j] = row.charAt(j);
				}
			}
			scan.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Purpose: Print out the board in a nicely formatted way.
	// Parameters: None.
	// Return Value: None.
	//
	public void printBoard(){

		System.out.println();
		System.out.println("THE BOARD:");

		// every row
		for (int r = 0; r < DIMENSION; r++){
			// every column
			for (int c = 0; c < DIMENSION; c++){
				
				// print blank for utility in case you want
				// to check the board in the middle of a search
				if (board[r][c] == BLANK)
					System.out.print("_ ");
				
				// print character at the position
				else
					System.out.print(Character.toUpperCase(board[r][c]) + " ");
			}
			System.out.println();
		}
		System.out.println();

	}

	// Purpose: General setter for the boggle board
	// Parameter: Character to set and relevant coordinates
	// Return: NA
	public void setPosition(char newChar, int row, int col) {
		board[row][col] = newChar;
	}

	// Purpose: General getter for the boggle board
	// Parameter: Coordinates of desired character
	// Return: Character at given position
	//
	public char getPosition(int row, int col) {
		return board[row][col];
	}

}
