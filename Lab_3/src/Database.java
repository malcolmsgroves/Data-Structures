/*
 * 		Author: Malcolm Groves
 * 		Database Class
 * 		22 September 2016
 * 
 * 		This class is a linked-list of Entry objects.
 * 		Entries are sorted alphabetically by last name
 * 		in the linked list. Methods include:
 * 			1) Inserting new entry into linked-list alphabetically
 * 			2) Printing all entries with user-provided last name
 * 			3) Printing all entries in user-provided age range.
 * 			4) Printing all student entries
 * 			5) Printing all entries alphabetically
 * 		Linked-list is book-ended by null head and tail
 * 		entries.
 * 		
 */

public class Database {

	// instance variables
	public Entry head;
	public Entry tail;
	public int NULL_AGE = -1; 

	// Purpose: Construct new empty database object
	// 			with null head
	// Parameters: None
	// Return: Constructor
	//
	public Database() {
		
		head = null; // null head
	}


	// Purpose: Insert a new entry alphabetically into 
	// 			database linked-list
	// Parameters: Entry to sort
	// Return: void
	public void insertEntry(Entry newEntry) {

		Entry curr = head;
		Entry prev = null;

		// iterate through the linked-list database to
		// find where the entry belongs.
		//while(curr.age != NULL_AGE) {
		while(curr != null) {
			// Evaluate whether new entry last name belongs 
			// before or after curr, and break if it belongs before
			int stringComparison = newEntry.lastName.compareTo(curr.lastName);
			if(stringComparison <= 0) break;

			prev = curr;
			curr = curr.next;
		}
		
		// if list is empty or newEntry is  
		// alphabetically first, put entry at the head
		if(prev == null) {
			head = newEntry;
			newEntry.next = curr;
		}

		// otherwise, insert newEntry between prev and curr
		else {
			prev.next = newEntry;
			newEntry.next = curr;
		}

	}


	// Purpose: Find and print all entries with a given last name.
	//			Indicates if no entries with last name are found
	// Parameters: last name to search
	// Return: void
	public void findLastName(String lastName) {

		// track whether an entry has been found
		boolean noEntries = true;

		Entry curr = head;
		System.out.println("Entries with the last name " + lastName + ":");

		// iterate through linked-list and print all entries
		// with specified last name
		while(curr != null) {

			if(lastName.compareTo(curr.lastName) == 0) {
				System.out.println(curr.toString());
				noEntries = false;
			}
			curr = curr.next;
		}

		// indicate if there are no entries with given last name
		if(noEntries) {
			System.out.println(lastName + " is not in the database.");
		}
	}

	// Purpose: Find and print all entries within a given
	// 			age range. Indicate if no entries are found.
	// Parameters: high and low values of age range
	// Return: void
	//
	public void searchAgeRange(int low, int high) {

		// track whether an entry has been found
		boolean noEntries = true;

		Entry curr = head;
		System.out.println("People ages " + low + " to " + high + ":");

		// iterate through linked-list and print all entries
		// within age range
		while(curr != null) {

			if(curr.age <= high && curr.age >= low) {
				System.out.println(curr.toString());
				noEntries = false;
			}
			curr = curr.next;
		}

		// indicate if there are no entries in age range
		if(noEntries) {
			System.out.println("There are no entries ages " +
					low + " to " + high);
		}

	}

	// Purpose: Print all student entries in linked-list
	// 			database. Indicate if there are no students.
	// Parameters: None
	// Return: void
	// 
	public void printStudents() {

		// track whether there are any students
		boolean noStudents = true;

		Entry curr = head;
		System.out.println("Students in Database: ");

		// iterate through linked-list and print all
		// student entries
		while(curr != null) {

			if(curr.isStudent) {
				System.out.println(curr.toString());
				noStudents = false;
			}
			curr = curr.next;
		}

		// indicate if there are no student entries
		if(noStudents) {
			System.out.println("There are no students in the database");
		}
	}

	// Purpose: Print all entries in the database
	// Parameters: none
	// Return: void
	//
	public void print() {

		Entry curr = head;

		// print every entry in linked list
		while(curr != null) {

			System.out.println(curr.toString());
			curr = curr.next;
		}

	}
}
