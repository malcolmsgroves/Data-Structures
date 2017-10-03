/*
 *		Malcolm Groves
 *		26 October 2016
 *		
 *		This class reads a set of words from a
 *		file and makes an ArrayList of strings
 *		that can be used as a dictionary. Includes
 *		a binary search method to determine if a 
 *		word is included in the dictionary, as well
 *		as other necessary methods (e.g. get/set)
 */

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Dictionary {

	// Create string array list to store
	private ArrayList<String> dictionary = new ArrayList<String>();

	// To read puzzle and solution files.
	private Scanner scan;
	private Scanner scanUserInput = new Scanner(System.in);


	// Purpose: Default constructor for dictionary that
	//			reads user-provided file into dictionary 
	//			object.
	// Parameters: none
	// Return: NA (constructor)
	public Dictionary() {

		// get dictionary file name from user
		System.out.println("Enter the filename of the"
				+ " dictionary you want \nto use without"
				+ " '.txt' extension:");
		String filename = scanUserInput.next();

		// dictionary file will be txt file in folder called
		// files, so add these parts
		filename = "files/" + filename + ".txt";

		readDictionary(filename);
		System.out.println("Dictionary has " + size() + " words");
		System.out.println();
	}


	// Purpose: Read words from a file and add them to
	// 			the dictionary of words (string array list)
	// Parameters: Filename of dictionary
	// Return: Void
	//
	public void readDictionary(String filename) {

		try {
			// create new scan object to read file
			scan = new Scanner(new File(filename));

			String currLine = new String();

			// read each line and add string to dictionary
			while(scan.hasNext()) {
				currLine = scan.next();
				dictionary.add(currLine);
			}


		}
		catch (Exception e) {
			e.printStackTrace();
		}

		// make sure dictionary is sorted, will be important
		// for binary search
		Collections.sort(dictionary);
		
		scan.close();
	}

	// Purpose: Determine whether a string is in
	// 			dictionary
	// Parameters: String to find in dictionary
	// Return: 	Boolean describing whether or not
	//			string is in dictionary
	public boolean binarySearch(String str) {

		// Set upper and lower bounds of search
		int min = 0;
		int max = dictionary.size() - 1;

		// to control for case, make string lower case
		str = str.toLowerCase();

		// Search for word by halving the search region
		// of the alphabetized dictionary
		while (max >= min) {

			// find middle of the search region
			int middle = (max + min) / 2;

			// determine alphabetical order of the string
			// at middle and the search string
			int compareStrings = dictionary.get(middle).compareToIgnoreCase(str);

			// if compareStrings = 0, string is found
			if(compareStrings == 0) {
				return true;
			}

			// else, reset search bounds in order to
			// search half that might contain desired word
			if(compareStrings > 0) {
				max = middle - 1;
			}
			else {
				min = middle + 1;
			}
		}

		// if word was not found, return false
		return false;
	}

	// Purpose: Determine whether a prefix is in
	// 			dictionary
	// Parameters: Prefix to find in dictionary
	// Return: 	Boolean describing whether or not
	//			string is in dictionary
	public boolean binaryPrefixSearch(String prefix) {

		// Set upper and lower bounds of search
		int min = 0;
		int max = dictionary.size() - 1;

		// to control for case, make all strings lower case
		prefix = prefix.toLowerCase();

		// Search for word by halving the search region
		// of the alphabetized dictionary
		while (max >= min) {

			// find middle of the search region
			int middle = (max + min) / 2;

			// get prefix of middle word and substring
			// so that it is <= length of prefix
			String middlePrefix = dictionary.get(middle);
			int endIndex;
			
			// if prefix is longer, crop to middlePrefix length
			if(prefix.length() > middlePrefix.length()) {
				endIndex = middlePrefix.length();
			}
			
			// otherwise, crop to prefix length
			else {
				endIndex = prefix.length();
			}
			middlePrefix = middlePrefix.substring(0, endIndex);

			// compare the alphabetical order of the two strings
			int compareStrings = middlePrefix.compareToIgnoreCase(prefix);

			// if compareStrings = 0, prefix is found
			if(compareStrings == 0) {
				return true;
			}

			// else, reset search bounds in order to
			// search half that might contain desired word
			if(compareStrings > 0) {
				max = middle - 1;
			}
			else {
				min = middle + 1;
			}
		}

		// if word was not found, return false
		return false;
	}
	// Purpose: Get entry at given index in dictionary.
	// Parameter: Index of desired entry.
	// Return: Desired entry string
	//
	public String getEntry(int index) {

		String entry = dictionary.get(index);
		return entry;
	}

	// Purpose: Get size of dictionary
	// Parameters: None
	// Return: Size of dictionary
	//
	public int size() {
		return dictionary.size();
	}

}
