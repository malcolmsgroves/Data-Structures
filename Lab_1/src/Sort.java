/*
 * 		Author: Malcolm Groves
 * 		Sorting Algorithms
 * 		13 September 2016
 * 
 * 		This program contains two sorting methods:
 * 			1) Insertion Sort
 * 			2) Selection Sort
 * 		The user provides the number of items in the lists
 * 		and the program generates sorted and unsorted
 * 		lists that are then sorted by both programs. The user
 * 		can opt to print the lists. Runtimes for each sorting
 * 		method are printed.
 * 
 * 		
 */

import java.util.Scanner;
import java.util.Random;

public class Sort {

	private static Scanner scan = new Scanner(System.in);
	private static Random rand = new Random();

	private static final int MIN_RAND_NUM = -100;
	private static final int RAND_NUM_RANGE = 200;
	private static final double NUM_NANOS_IN_SEC = 1000000000;
	private static final int PRINT_LIST = 1;


	// Purpose: This is the main method of the program and it
	// 			includes the program sorting functionalities.
	// Parameters:	The user provides the length of the lists 
	// 				to sort whether they want to print lists. 
	// Return Value: void
	public static void main(String[] args) {

		// Initialize user-provided values with incompatible values
		int printCommand = -1;
		int numCount = -1;

		// Get the list lengths from user, assume user provides an integer
		while (numCount <= 0) {
			System.out.print("How Many Numbers Do You Want To Sort?:  ");
			numCount = scan.nextInt();
		}

		// Generate the sorted and random lists
		int[] sortedList = sortListGen(numCount);
		int[] unsortedList = randListGen(numCount);

		// Get printing-preferences from user
		while (!(printCommand == 0) && !(printCommand == 1)) {
			System.out.print("Print Lists? (0 = NO, 1 = YES):  ");
			printCommand = scan.nextInt();
		}

		newLine(3); // print new lines in console

		long startTime, endTime;
		double secondsUnsortedSelection;
		startTime = System.nanoTime();   // start time recorded

		// Sort unsorted list with selection sort
		int[] unsortedSelection = selectionSort(unsortedList);

		endTime = System.nanoTime();   //end time recorded
		secondsUnsortedSelection = (endTime - startTime) / NUM_NANOS_IN_SEC;

		// Print lists before and after sorting at user's request
		if (printCommand == PRINT_LIST) {
			System.out.println("Unsorted Random Numbers:");
			printArray(unsortedList);
			System.out.println("Sorted:");
			printArray(unsortedSelection);
			newLine(1);
		}
		
		// Print runtime
		System.out.println("Selection Sort for random numbers: "
				+ secondsUnsortedSelection + "seconds");
		newLine(1);

		double secondsUnsortedInsertion;
		startTime = System.nanoTime();   // start time recorded

		// Sort unsorted list with Insertion Sort
		int[] unsortedInsertion = insertionSort(unsortedList);

		endTime = System.nanoTime();   //end time recorded
		secondsUnsortedInsertion = (endTime - startTime) / NUM_NANOS_IN_SEC;

		// Print lists before and after sorting at user's request
		if (printCommand == PRINT_LIST) {
			System.out.println("Unsorted Random Numbers:");
			printArray(unsortedList);
			System.out.println("Sorted:");
			printArray(unsortedInsertion);
			newLine(1);
		}
		
		// Print runtime
		System.out.println("Insertion Sort for random numbers: "
				+ secondsUnsortedInsertion + "seconds");
		newLine(2);

		double secondsSortedSelection;
		startTime = System.nanoTime();   // start time recorded

		// Sort sorted list with Selection Sort
		int[] sortedSelection = selectionSort(sortedList);

		endTime = System.nanoTime();   //end time recorded
		secondsSortedSelection = (endTime - startTime) / NUM_NANOS_IN_SEC;

		// Print lists before and after sorting at user's request
		if (printCommand == PRINT_LIST) {
			System.out.println("Already Sorted Numbers:");
			printArray(sortedList);
			System.out.println("Sorted:");
			printArray(sortedSelection);
			newLine(1);
		}

		// Print runtime
		System.out.println("Selection Sort for already sorted numbers: "
				+ secondsSortedSelection + "seconds");
		newLine(1);

		double secondsSortedInsertion;
		startTime = System.nanoTime();   // start time recorded

		// Sort sorted list with Insertion Sort
		int[] sortedInsertion = insertionSort(sortedList);

		endTime = System.nanoTime();   //end time recorded
		secondsSortedInsertion = (endTime - startTime) / NUM_NANOS_IN_SEC;

		// Print unsorted lists and sorted lists at user's request
		if (printCommand == PRINT_LIST) {
			System.out.println("Already Sorted Numbers:");
			printArray(sortedList);
			System.out.println("Sorted:");
			printArray(sortedInsertion);
			newLine(1);
		}
		
		// Print runtime
		System.out.println("Insertion Sort for already sorted numbers: "
				+ secondsSortedInsertion + "seconds");
	}

	// Purpose: This generates a list of unsorted random integers
	// Parameters: The length of the list to be generated.
	// Return Value: An array containing the unsorted random integers.
	private static int[] randListGen(int numCount){

		// Initialize a list with an appropriate length
		int[] randList = new int[numCount];

		// Generate and assign a random value to each index of array
		for(int i = 0; i < numCount; ++i) {
			randList[i] = MIN_RAND_NUM + rand.nextInt(RAND_NUM_RANGE + 1);
		}
		return randList;
	}

	// Purpose: This generates a list of sorted integers beginning at 0.
	// Parameters: The length of the list to be generated.
	// Return Value: An array containing the sorted integers.
	private static int[] sortListGen(int numCount) {

		// Initialize a list with an appropriate length
		int[] sortList = new int[numCount];

		// Assign each index of the array the value of its index
		for (int i = 0; i < numCount; ++i) {
			sortList[i] = i;
		}

		return sortList;
	}

	// Purpose: This sorts a list using the Selection Sort algorithm,
	//			finding the index of the maximum of the unsorted region
	// 			and moving that value to the bottom of the sorted region.
	// Parameters:  The array to be sorted.
	// Return Value: The array with its values sorted smallest to largest.
	//
	public static int[] selectionSort(int[] sortList) {

		// Get list length and initialize list of appropriate length
		// to store sorted values.
		int listSize = sortList.length;
		int[] tempList = new int[listSize];

		//Copy the list to be sorted so it is not written over
		System.arraycopy(sortList, 0, tempList, 0, listSize);

		// Iterate through the list sorting the numbers from largest to smallest
		for(int numItems = listSize; numItems > 1; --numItems) {

			// Get index of the largest value in unsorted portion
			int maxIndex = findIndexOfMax(tempList, numItems);

			// Move largest value in unsorted portion to the bottom
			// of the sorted portion
			int temp = tempList[numItems - 1];
			tempList[numItems - 1] = tempList[maxIndex];
			tempList[maxIndex] = temp;
		}

		return tempList;
	}

	// Purpose: This sorts a list of integers by iterating through the
	//			list and moving each value to its appropriate relative 
	// 			position in the sorted portion
	// Parameters:  The array to be sorted
	// Return Value: The array with its values sorted smallest to largest.
	//
	public static int[] insertionSort(int[] sortList) {

		// Get the list length and initialize a list of the same
		// length to store sorted values
		int listSize = sortList.length;
		int[] tempList = new int[listSize];

		//copy values from original list to sorting list
		System.arraycopy(sortList, 0, tempList, 0, listSize);

		// Iterate through sorting list, moving values to their
		// appropriate position in sorted portion
		for(int i = 1; i < listSize; ++i) {

			// Get index of next number to sort
			int index = i;

			// While the value at index i is smaller than the value to
			// its left, switch the two and re-evaluate the condition
			while((index > 0) && (tempList[index] < tempList[index - 1])) {
				int temp = tempList[index];
				tempList[index] = tempList[index - 1];
				tempList[index - 1] = temp;
				--index;
			}
		}

		return tempList;
	}

	// Purpose: This iterates through a list and finds the index of the
	// 			maximum value.
	// Parameters: The list that is being sorted, and the number of items
	//			   to search through for maximum, starting at index 0.
	// Return: The index of the maximum value.
	//
	private static int findIndexOfMax(int[] unsorted, int numItems) {

		// Initialize maxIndex as 0, first potential value
		int maxIndex = 0;

		// Iterate through the relevant portion of the list and replace
		// maxIndex if a larger value is found
		for(int i = 1; i < numItems; ++i) {
			if (unsorted[maxIndex] < unsorted[i]) maxIndex = i;
		}

		return maxIndex;
	}

	// Purpose: This prints an array by iterating through the array
	// 			and printing values individually
	// Parameters: The array to be printed
	// Return: void
	//
	public static void printArray(int[] arrayToPrint) {

		// Iterate through array and print values
		for(int d: arrayToPrint) {
			System.out.print(d + "  ");
		}

		// Print new line after array
		newLine(1);
	}

	// Purpose: This prints a new line in the console for
	//			formatting purposes
	// Parameters: The number of new lines to be printed
	// Return: void
	//
	private static void newLine(int numLines) {

		// For every new line requested, print a new line
		for(int i = 0; i < numLines; ++i) {
			System.out.println();
		}
	}
}
