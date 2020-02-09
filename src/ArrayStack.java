/**
 * Implements a stack using an array
 * 
 * @author Bazillah Zargar
 */

public class ArrayStack<T> implements ArrayStackADT<T> {
	// stores the data items of the stack
	private T[] stack;

	// stores the position of the last of data item in the stack
	private int top = -1;

	// Creates an empty stack with a default initial capacity of 20
	public ArrayStack() {
		stack = (T[]) (new Object[20]);
	}

	/**
	 * Creates an empty stack using an array of length equal to the value of the
	 * parameter.
	 * 
	 * @param initial capacity (i.e. length of stack)
	 */
	public ArrayStack(int initialCapacity) {
		stack = (T[]) new Object[initialCapacity];
	}

	/**
	 * Adds dataItem to the top of the stack. If the array storing the data items is
	 * full, increases its capacity
	 */
	public void push(T dataItem) {
		T[] revisedStack;

		if (size() == stack.length) {
			/**
			 * If the capacity of the array is smaller than 100, then the capacity of the
			 * array is increased by a factor of 2.
			 */
			if (stack.length < 100 && (stack[stack.length - 1] != null)) {
				revisedStack = (T[]) (new Object[stack.length * 2]);
				for (int i = 0; i < stack.length; i++) {
					revisedStack[i] = stack[i];
				}
				stack = revisedStack;
			}
			/**
			 * If the capacity of the array is greater than or equal to 100, then the
			 * capacity of the array is increased by 50.
			 */
			if (stack.length >= 100 && (stack[stack.length - 1] != null)) {
				revisedStack = (T[]) (new Object[stack.length + 50]);
				for (int i = 0; i < stack.length; i++) {
					revisedStack[i] = stack[i];
				}
				stack = revisedStack;
			}

		}
		// Adds data item to stack if the stack size is less than stack length/capacity
		if (size() < stack.length) {
			stack[++top] = dataItem;

		}
	}

	/**
	 * Removes and returns the data item at the top of the stack.
	 * 
	 * @return item at top of stack
	 */
	public T pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("Error: Empty Stack");
		}
		T item = stack[top];
		stack[top] = null;
		top--;

		T[] revisedStack;

		/**
		 * If after removing a data item from the stack the number of data items
		 * remaining is smaller than one third of the length of the array and the length
		 * of the array is larger than 20, shrinks the size of the array by half
		 */
		if (stack.length > 20 && (size() < stack.length / 3)) {
			revisedStack = (T[]) (new Object[stack.length / 2]);
			for (int i = 0; i < stack.length / 2; i++) {
				revisedStack[i] = stack[i];
			}
			stack = revisedStack;
		}

		return item;
	}

	/**
	 * @return the data item at the top of the stack
	 */
	public T peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("Error: Empty Stack");
		}
		return stack[top];
	}

	/**
	 * @return true if the stack is empty and and false otherwise
	 */
	public boolean isEmpty() {
		return (top == -1);
	}

	/**
	 * @return the number of data items in the stack
	 */
	public int size() {
		return (top + 1);
	}

	/**
	 * @return the length or capacity of the array stack
	 * 
	 */
	public int length() {
		return stack.length;
	}

	/**
	 * @return a String representation of the stack of the form: “Stack: elem1,
	 *         elem2, …”, where elem i is a String representation of the i-th
	 *         element of the stack
	 */
	public String toString() {
		String result = "Stack: ";

		for (int i = 0; i <= top; i++) {
			result = result + stack[i].toString();
			if (i + 1 != size()) {
				result = result + ", ";
			}
		}
		return result;
	}

}
