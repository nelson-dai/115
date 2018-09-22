/**
 * CSC115 Assignment 2 : Containers.
 * ListIndexOutOfBoundsException.java.
 * Created for use by CSC115 Spring 2016
 */

/*
 * NOTE TO PROGAMMER:
 * Do not alter this file!
 */

/**
 * An exception thrown when the index of a List ADT is out of bounds.
 */ 
public class ListIndexOutOfBoundsException extends RuntimeException {

	/**
	 * Creates an exception.
	 * @param msg The message to the calling program.
	 */
	public ListIndexOutOfBoundsException(String msg) {
		super(msg);
	}

	/**
	 * Creates an exception without a message.
	 */
	public ListIndexOutOfBoundsException() {
		super();
	}
}
