/*
 * 		Author: Malcolm Groves
 * 		TestDatabase Program
 * 		22 September 2016
 * 
 * 		This program generates random entries and
 * 		inserts them into a linked-list database whose
 * 		length is user-provided.
 * 		Includes a method to create a random database.
 * 		Prints all the entries, all entries in user-
 * 		provided age range, all entries with user-provided
 * 		last name, and all student entries.
 * 		
 */


import java.util.Scanner;
import java.util.Random;


public class TestDatabase {

	// Range of valid ages. 
	private static final int MIN_AGE = 1;
	private static final int MAX_AGE = 120;

	// Array of first names for generating random first names.
	private static String[] firstNames = 		
		{ "Abir", "Angela", "Kanye", "Sonya", "DeRay", "Robert", "Svetlana", "Karl",
				"Sujit", "Niejls", "Alejandro", "Itzhak", "Emily", "Terik", "Nevin", "Linus", "Ebba", 
				"Gwyneth", "Clarissa", "Abbud", "Eric", "Alexei", "Michele", "Artemisia", "Ansari" };

	// Array of last names for generating random last names.
	private static String[] lastNames =
		{ "Borisov", "Johnson", "Ben-Haim", "Kourakis", "Elfasi", "Newton", "Shammas",
				"Kuznetsov", "Marinescu", "Voltaire", "Mckesson", "Ross", "Yang", "Awad", "Lopez",
				"Kraft", "Roitman", "Christakos", "Karlsson", "Freidhof", "Schumacher", "Faure" };

	// To get input and generate random values.
	private static Scanner scan = new Scanner(System.in);
	private static Random rand = new Random();


	public static void main(String[] args) {

		// Get the number of entries the user wants in the database;
		// must be greater than zero
		int numEntries = -1;

		while(numEntries < 1) {
			System.out.print("Enter the number of entries you would"
					+ "like (must be > 0): ");
			numEntries = scan.nextInt();
		}
		
		System.out.println();

		// Create the database by calling the createDatabase method below
		Database personDatabase = createDatabase(numEntries);

		// Print the database
		System.out.println("Database:");
		personDatabase.print();
		System.out.println();

		// Get a last name to search for and call the findLastName method
		// in the Database class
		System.out.print("Name to search for: ");
		String lastNameSearch = scan.next();
		System.out.println();
		personDatabase.findLastName(lastNameSearch);
		System.out.println();

		// Get an age range from the user
		int low = -1;
		int high = -1;
		System.out.println("Print names between two ages, inclusive.");
		
		// lower bound must be from MIN_AGE to MAX_AGE
		while(low < MIN_AGE || low >= MAX_AGE - 1) {
			System.out.print("Enter lower age bound between " + MIN_AGE +
					" and " + MAX_AGE + ": ");
			low = scan.nextInt();
			
			// print error message if low age is not within bounds
			if(low < MIN_AGE || low >= MAX_AGE - 1) {
				System.out.println("Error: Lower bound must be between " +
						MIN_AGE + " and " + MAX_AGE);
			}
		}
		
		System.out.println();
		
		// higher bound must be from lower bound to MAX_AGE
		while(high < low || high > MAX_AGE) {
			System.out.print("Enter higher age bound between " + low +
					" and " + MAX_AGE + ": ");
			high = scan.nextInt();
			
			// print error message if high age is not within bounds
			if(high < low || high > MAX_AGE) {
				System.out.println("Error: Higher bound must be between " +
						low + " and " + MAX_AGE);
			}
		}
		
		System.out.println();
		
		// call searchAgeRange method in Database class
		personDatabase.searchAgeRange(low, high);
		System.out.println();

		// Print out the student entries by calling the printStudents
		// method in the Database class
		personDatabase.printStudents();



	}

	// Purpose: Create a database of random entries. Database
	//			length is determined by parameter.
	// Parameter: Desired length of database
	// Return: 	Database with random entries that is sorted
	// 			alphabetically.
	public static Database createDatabase(int numEntries) {
		
		Database personDatabase = new Database();
		
		// create the specified number of entries
		for(int i = 0; i < numEntries; ++i) {
			
			// generate random values for Entry object instance variables
			String firstName = firstNames[rand.nextInt(firstNames.length)];
			String lastName = lastNames[rand.nextInt(lastNames.length)];
			int age = MIN_AGE + rand.nextInt(MAX_AGE + 1 - MIN_AGE);
			boolean isStudent = rand.nextBoolean();

			// construct new entry and insert into database
			Entry newEntry = new Entry(firstName, lastName, age, isStudent);
			personDatabase.insertEntry(newEntry);
		}
		return personDatabase;
	}





}


