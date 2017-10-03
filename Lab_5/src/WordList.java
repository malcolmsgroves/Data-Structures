/*
 * 		Malcolm Groves
 * 		27 October 2016
 * 	
 * 		This class creates a object that stores words
 * 		found in boggle. The words are stored in 
 * 		alphabetical order. 
 * 
 * 		The class contains methods to insert new
 * 		words in alphabetical order, score
 * 		words, score boggle games, find words, and remove
 * 		words that have already been used.
 * 
 */


import java.util.ArrayList;

public class WordList {

	// Create array list to store words
	private ArrayList<String> wordlist;
	private boolean isComputer;

	// disallow marker: if user finds word, don't count it
	// for the computer, mark it in computer wordlist
	private static String DISALLOW = "\tdisallowed: found by human";
	private static String DISALLOW_SUFFIX = DISALLOW.substring(DISALLOW.length() - 8,
			DISALLOW.length());

	// Purpose: Default constructor for WordList
	// Parameters: None
	// Return: NA, constructor
	//
	public WordList() {
		wordlist = new ArrayList<String>();
	}
	
	// Purpose: Constructor for Wordlist that assigns
	//			a username.
	// Parameters: Boolean, true if this is the computer list
	// Return: NA, constructor
	public WordList(boolean isComputer) {
		wordlist = new ArrayList<String>();
		this.isComputer = isComputer;
	}

	// Purpose: Add a string into the word list
	// 			at the alphabetical position using
	// 			binary search/insertion.
	// Parameters: String to insert
	// Return: void
	//
	public void add(String str) {

		// make string lower case
		str = str.toLowerCase();

		// Set upper and lower bounds of search
		int min = 0;
		int max = wordlist.size() - 1;

		// Search for word by halving the search region
		// of the alphabetized dictionary
		while (max >= min) {

			// find middle of the search region
			int middle = (max + min) / 2;

			// determine alphabetical order of the string
			// at middle and the search string
			int compareStrings = wordlist.get(middle).compareToIgnoreCase(str);

			// if compareStrings = 0, string is in
			// wordlist, do not add new word
			if(compareStrings == 0) {
				return;
			}

			// else, reset search bounds in order to
			// search half where new word belongs
			if(compareStrings > 0) {
				max = middle - 1;
			}
			else {
				min = middle + 1;
			}
		}

		// if word was not found, add it to the wordlist
		wordlist.add(min, str);

	}


	// Purpose: Total the scores for the list
	// Parameters: None
	// Return: Boggle score
	//
	public int sumScore() {

		int score = 0;

		// add the score from every word in the list
		for(int i = 0; i < wordlist.size(); ++i) {
			score += score(wordlist.get(i));
		}

		return score;
	}

	// Purpose: Score a given word in Boggle
	// Parameters: String to score
	// Return: Word score
	//
	public static int score(String str) {

		int wordlength = str.length();

		// if word has been disallowed, score = 0
		if(wordlength > 25) {
			// check if string has suffix unique to disallowed words
			String suffix = str.substring(wordlength - 
					DISALLOW_SUFFIX.length(), wordlength);
			if(suffix.equals(DISALLOW_SUFFIX)) {
				return 0;
			}
		}

		// score the word based on the word length
		if(wordlength <= 4) {
			return 1;
		}
		else if(wordlength == 5) {
			return 2;
		}
		else if(wordlength == 6) {
			return 3;
		}
		else if(wordlength == 7) {
			return 5;
		}

		return 11; // if length > 7
	}

	// Purpose: Disallow all the words from the word list
	// 			that are already in a given list
	// Parameters: 	A wordlist object containing words
	//				to disallow
	// Return: void
	//
	public void disallow(WordList userWords) {

		// iterate through userWords
		for(int i = 0; i < userWords.size(); ++i) {

			// find overlapping words and add disallow marker
			String humanWord = userWords.get(i);
			int humanWordIndex = wordlist.indexOf(humanWord);
			wordlist.set(humanWordIndex, humanWord + DISALLOW);
		}
	}

	// Purpose: Search for a string in the wordlist. Just
	//			iterate through, lists are short.
	// Parameters: String to search for
	// Return: 	Boolean for whether or not string is
	// 			in the wordlist.
	//
	public boolean search(String str) {

		// make string lower case
		str = str.toLowerCase();

		// Iterate through wordlist
		for(int i = 0; i < wordlist.size(); ++i) {

			// evaluate string equality, return if true
			if(wordlist.get(i).equals(str)) {
				return true;
			}
		}

		// if word was not found, return false
		return false;
	}

	// Purpose: Print every word in the wordlist and
	// 			score the wordlist using boggle rules.
	//			Assign username.
	// Parameters: None
	// Return: void
	//
	public void print() {
		
		String listOwnership;
		
		// if this is the user's list, print 
		// YOUR WORDS and YOUR SCORE
		if(isComputer) {
			listOwnership = "COMPUTER";
		}
		// else, assume it is the computer's list, print
		// COMPUTER WORDS and COMPUTER SCORE
		else {
			listOwnership = "YOUR";
		}
		
		// Score the wordlist
		int totalScore = sumScore();
		
		// Print all the words and the score
		System.out.println();
		System.out.println(listOwnership + " WORDS: ");
		
		// Iterate through and print all words
		for(int i = 0; i < size(); ++i) {
			System.out.println(wordlist.get(i));
		}
		System.out.println(listOwnership + " SCORE: " + totalScore);
		System.out.println();
	}
	

	// Purpose: General get utility for the word list
	// Parameters: Index of word
	// Return: String at the given index
	//
	public String get(int index) {
		return wordlist.get(index);
	}

	// Purpose: Get the number of words in list
	// Parameters: none
	// Return: Integer size of the list
	//
	public int size() {
		return wordlist.size();
	}
}
