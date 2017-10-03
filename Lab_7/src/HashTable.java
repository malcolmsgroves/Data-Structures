/*
 * 
 * 		Malcolm Groves
 * 		18 November 2016
 * 
 * 		A HashTable class that stores Entry objects
 * 		in chained buckets. Entry objects contain
 * 		a GameBoard object and a board value. Contains
 * 		methods to insert a new Entry object, find
 * 		a GameBoard, and remove an Entry object.
 * 
 * 		Hash codes are compressed to fit into the hash
 * 		table index range. Variance method calculates
 * 		the variance between the chain lengths of the
 * 		buckets
 * 
 * 		
 */
import java.util.ArrayList;

public class HashTable {

	// Instance variables
	private int numBuckets;
	private int counter;
	

	// Store hash table as array of ArrayLists 
	ArrayList<Entry>[] hashTable;


	// Purpose: Constructor for HashTable class that
	//			sets the number of buckets
	//			and creates an array of ArrayLists of
	//			size equal to the number of buckets.
	// Parameters: 	Integers for the number of buckets
	// Return: NA, constructor
	//
	@SuppressWarnings("unchecked")
	public HashTable(int numBuckets) {
		this.numBuckets = numBuckets;
		counter = 0;
		hashTable = new ArrayList[numBuckets];
	}

	// Purpose: Create an Entry object from a board 
	//			and value and then insert the Entry
	//			object into the hash table.
	// Parameters: 	The GameBoard object and the value 
	//				of the GameBoard
	// Return: The Entry object that was inserted
	//
	public Entry insert(GameBoard board, int value) {
		
		Entry newEntry = new Entry(board, value);
		
		// Calculate compressed hash code for entry object
		int hashCode = board.hashCode();
		int compressedHashCode = compress(hashCode);

		// If there is no existing bucket at the Entry hash code,
		// create a new ArrayList.
		if(hashTable[compressedHashCode] == null) {
			hashTable[compressedHashCode] = new ArrayList<Entry>();
		}
		
		// Iterate through the ArrayList bucket at the hash code
		for(int i = 0; i < hashTable[compressedHashCode].size(); ++i) {
			
			Entry currEntry = hashTable[compressedHashCode].get(i);
			
			// If the new Entry is already in the bucket,
			// just return the Entry
			if(currEntry.getBoard().equals(board)) {
				return newEntry;
			}
		}

		// If new Entry is not in the hash chain, add it
		hashTable[compressedHashCode].add(newEntry);
		
		// add an entry to the counter
		++counter;

		return newEntry;
	}

	// Purpose: Search for a given GameBoard in the hash table.
	// Parameters: The GameBoard object for which to search.
	// Return: 	The Entry object associated with the GameBoard if
	//			found, otherwise null.
	//
	public Entry find(GameBoard board) {
		
		// Calculate GameBoard compressed hash code
		int hashCode = board.hashCode();
		int compressedHashCode = compress(hashCode);
		
		// Get the bucket at the hash code value
		ArrayList<Entry> bucket = hashTable[compressedHashCode];
		
		// If the bucket exists
		if(bucket != null) {
			
			// Search through bucket for the desired GameBoard
			for(int i = 0; i < bucket.size(); ++i) {
				
				// If found, return the Entry object
				if(board.equals(bucket.get(i).getBoard())) {
					return bucket.get(i);
				}
			}
		}
		
		// If not found, return null
		return null;
	}

	// Purpose: Remove a specific Entry from the hash table
	// Parameters: The GameBoard object that you want to remove
	// Return: 	The removed entry if the GameBoard is found, 
	//			null if not found.
	//
	public Entry remove(GameBoard board) {
		
		// Calculate the Gameboard compressed hash code
		int hashCode = board.hashCode();
		int compressedHashCode = compress(hashCode);
		
		// Get the bucket at the hash code
		ArrayList<Entry> bucket = hashTable[compressedHashCode];
		
		// If the bucket exists
		if(bucket != null) {
			
			// Search through the bucket for the desired GameBoard
			for(int i = 0; i < bucket.size(); ++i) {
				
				// If found, remove the Entry and return it
				if(board.equals(bucket.get(i).getBoard())) {
					--counter;
					return bucket.remove(i);
				}
			}
		}
		
		// If GameBoard was not found, return null
		return null;
	}

	
	// Purpose: Check if the hash table is empty
	// Parameters: none
	// Return: 	boolean, true if hash table is empty,
	// 			false if there is an entry in the table.
	//
	public boolean isEmpty() {
		
		// true if counter equals 0
		return counter == 0;
	}

	// General get method for the hash table
	// load factor
	public int getLoadFactor() {
		
		int numEntries = 0;

		// Iterate through every bucket in the hash table
		for(int i = 0; i < numBuckets; ++i) {
			
			// If the bucket exists
			if(hashTable[i] != null) {
				
				// add chain length to array and sum
				numEntries += hashTable[i].size();;
			}
		}
		
		return numEntries / numBuckets;
	}

	// General get method for the number of 
	// buckets in the hash table
	public int getNumBuckets() {
		return numBuckets;
	}

	// Purpose: Compress a given hash code so it fits
	//			within the indices of the hash table.
	// Parameters: uncompressed hash code, integer
	// Return: integer, compressed hash code
	//
	public int compress(int hashCode) {
		return(((13 * hashCode) + 37) % 16908799) % numBuckets;
	}

	// Purpose: Calculate the variance of the chain lengths
	//			of all the buckets in the hash table
	// Parameters: None
	// Return: double, chain length variance
	//
	public double calcVariance() {

		// Make an array of the chain length values
		int[] chainLengths = new int[numBuckets];
		
		int sum = 0;

		// Iterate through every bucket in the hash table
		for(int i = 0; i < numBuckets; ++i) {
			
			// If the bucket exists
			if(hashTable[i] != null) {
				
				// add chain length to array and sum
				chainLengths[i] = hashTable[i].size();
				sum += chainLengths[i];
			}
			
			// else, chain length is 0
			else {
				chainLengths[i] = 0;
			}
		}

		// Calculate the mean chain length
		double mean = sum / numBuckets;
		
		double sumResidualSquared = 0;

		// Iterate through every chain length value to 
		// calculate the variance:
		//		var = (1 / (N - 1)) * SUM((X_i - X_avg)^2)
		for(int i = 0; i < numBuckets; ++i) {
			
			// Calculate the difference between the chain length
			// and the mean
			double valueMeanDifference = chainLengths[i] - mean;
			
			// add squared difference to sumResidualSquared
			sumResidualSquared += valueMeanDifference * valueMeanDifference;
		}

		// Calculate and return variance
		return sumResidualSquared / (numBuckets - 1);
	}
}


