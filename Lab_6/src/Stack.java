/*
 * 		Malcolm Groves
 * 		12 October 2016
 * 
 * 		This class implements the StackType interface. Both
 * 		the interface and this implementation are parameterized 
 * 		so that a stack that can hold any type of element.
 * 
 * 		Aspects of this class are adopted from Professor 
 * 		Majercik's Queue class from Blackboard.
 */


import java.util.ArrayList;

public class Stack<E> implements StackType<E>{

	// A stack is implemented with an ArrayList
	private ArrayList<E> stack;


	// Creates an empty stack.
	public Stack() {
		stack = new ArrayList<E>();
	}


	// Creates a stack with numElements capacity.
	public Stack(int numElements) {
		stack = new ArrayList<E>(numElements);
	}


	// Purpose: This method inserts an element at the top
	//          of the stack
	// Parameters: The element to be inserted.
	// Return Value: None.
	//
	public void push(E element) {
		stack.add(element);
	}


	// Purpose: This method removes element at the top
	//          of the stack and returns it.
	// Parameters: None.
	// Return Value: The element removed.
	//
	public E pop() {
		
		if (isEmpty()) {
			return null;
		}
		
		// Find the index of the last element in
		// ArrayList and remove the element at that index
		int topIndex = stack.size() - 1;
		E topElement = stack.get(topIndex);
		stack.remove(topIndex);
		
		return topElement;

	}


	// Purpose: This method returns a reference to the
	//          element at the top of the stack, but
	//          does not remove it.
	// Parameters: None.
	// Return Value: A reference to the element at the
	//               top of the stack.
	//
	public E top() {

		if (isEmpty()) {
			return null;
		}
		
		// return the last element in the Arraylist
		int topIndex = stack.size() - 1;
		return stack.get(topIndex);
	}


	// Purpose: This method returns the size of the stack.
	// Parameters: None.
	// Return Value: The size of the stack.
	//
	public int size() {
		return stack.size();
	}


	// Purpose: This method returns a boolean value
	//          indicating whether the stack is empty.
	// Parameters: None.
	// Return Value: True if the stack is empty; 
	//               otherwise, false.
	//
	public boolean isEmpty() {
		return stack.size() == 0;
	}


	// Purpose: This method removes all the elements
	//          in the stack.
	// Parameters: None.
	// Return Value: None.
	//
	public void clear() {
		stack.clear();
	}

}
