/*
* Name: Hongyu Dai (Nelson) 
* ID:	V00815253
* Date: 2016-02-27
* Filename: ArithExpression.java
* Details: CSC115 Assignment 3
* This class is called when the stack is empty
*/
public class StackEmptyException extends RuntimeException {

	/**
	 * Creates an exception.
	 * @param msg The message to the calling program.
	 */
	public StackEmptyException(String msg) {
		super(msg);
	}

	/**
	 * Creates an exception without a message.
	 */
	public StackEmptyException() {
		super();
	}
}