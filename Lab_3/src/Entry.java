/*
 * 		Author: Malcolm Groves
 * 		Entry Class
 * 		22 September 2016
 * 
 * 		This class is used to store descriptive information
 * 		about people. It contains instance variables for first
 * 		name, last name, age, and whether or not the individual
 * 		is a student. It is also set up to function as a linked
 * 		list, so it has an instance variable next that is the 
 * 		next entry object in the list.
 */

public class Entry {

	// instance variables
	public String firstName;
	public String lastName;
	public int age;
	public boolean isStudent;
	public Entry next;

	// Purpose: Construct default entry object  
	// Parameters: None
	// Return: Default entry object
	//
	public Entry() {
		firstName = null;
		lastName = null;
		age = 0; 	
		isStudent = false;
		next = null;
	}

	// Purpose: Construct a new entry object.
	// Parameters: 	first name, last name, age, and student
	// 				status of new entry
	// Return: 	Entry object with a null next. Other instance
	// 			variables are set to parameters.
	//
	public Entry(String firstName, String lastName, int age,
			boolean isStudent) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.isStudent = isStudent;
		this.next = null;
	}

	// getters and setters: self-documenting
	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public int getAge() {
		return this.age;
	}

	public boolean getIsStudent() {
		return this.isStudent;
	}

	public Entry getNext () {
		return this.next;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setIsStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}

	public void setNext(Entry next) {
		this.next = next;
	}

	// Purpose: Generate string with the entry's data
	// Parameters: None
	// Return: Formatted string with entry data
	//
	public String toString() {

		// if student, print "student"
		if(isStudent) {
			return firstName + " " + lastName + ", " +
					age + ", student";
		}

		// if not a student, print "not a student"
		return firstName + " " + lastName + ", " +
		age + ", not a student";

	}


}
