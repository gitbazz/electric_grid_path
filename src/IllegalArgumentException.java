/**
 * @author CS1027
 *
 *         Represents the situation in which the stack is empty
 */

public class IllegalArgumentException extends RuntimeException {
	/**
	 * Sets up this exception with an appropriate message.
	 * 
	 * @param message explains the error that threw the exception
	 */
	public IllegalArgumentException(String message) {
		super(message);
	}
}
