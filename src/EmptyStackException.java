/**
 * @author Bazillah Zargar
 *
 *         Represents the situation in which no command line argument was
 *         provided
 */

public class EmptyStackException extends RuntimeException {
	/**
	 * Sets up this exception with an appropriate message.
	 * 
	 * @param message explains the error that threw the exception
	 */
	public EmptyStackException(String message) {
		super(message);
	}
}
