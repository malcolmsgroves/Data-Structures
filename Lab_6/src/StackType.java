/* 
 * 		Malcolm Groves
 * 		12 October 2016
 * 
 * 		Specifications for the functionality of a stack.
 * 		The interface is parameterized so an implementation
 * 		of the interface will be a stack that can hold any
 * 		type of element. (Documentation is adapted from
 * 		QueueType interface provide by Prof. Majercik).
 * 
 */

public interface StackType<E> {

	// Purpose: This method inserts an element at the top
	//          of the stack
	// Parameters: The element to be inserted.
	// Return Value: None.
	//
    public void push(E element);

    
	// Purpose: This method removes element at the top
	//          of the stack and returns it.
	// Parameters: None.
	// Return Value: The element removed.
	//
    public E pop();

    
	// Purpose: This method returns a reference to the
    //          element at the top of the stack
	// Parameters: None.
	// Return Value: A reference to the element at the
    //               top of the stack.
	//
    public E top();	

    
	// Purpose: This method returns the size of the stack.
	// Parameters: None.
	// Return Value: The size of the stack.
	//
    public int size();

    
	// Purpose: This method returns a boolean value
    //          indicating whether the stack is empty.
	// Parameters: None.
	// Return Value: True if the stack is empty; 
    //               otherwise, false.
    //
    public boolean isEmpty();

    
	// Purpose: This method removes all the elements
    //          in the queue.
	// Parameters: None.
	// Return Value: None.
    //
    public void clear();
    
}