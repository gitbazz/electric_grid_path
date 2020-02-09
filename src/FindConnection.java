
/**
 * Finds a path from the WPC cell to the destination cell C
 * 
 * @author Bazillah Zargar
 */

import java.io.IOException;

public class FindConnection {
	// reference the object representing the city map where WPC and C are located
	Map cityMap;

	/**
	 * Constructor for the class
	 * 
	 * @param the given input file
	 */
	public FindConnection(String filename) {
		try {
			cityMap = new Map(filename);
		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
		}
	}

	/**
	 * Find a path from the WPC cell to the destination cell C by using a stack
	 * 
	 * @param pass as command line argument the name of the input file
	 */
	public static void main(String[] args) {
		// Verify that the program was invoked with the correct number of arguments
		if (args.length < 1) {
			System.out.println("You must provide the name of the input file");
			System.exit(0);
		}
		String mapFileName = args[0];

		FindConnection path = new FindConnection(args[0]);

		// Create an empty stack
		ArrayStack stack = new ArrayStack();

		// Get the starting WPC cell
		MapCell cell = path.cityMap.getStart();

		// Push the starting cell into the stack and mark the cell as inStack
		stack.push(cell);
		cell.markInStack();

		while (!stack.isEmpty() && !cell.isCustomer()) {
			// Peek at the top of the stack to get the current cell
			MapCell currentCell = (MapCell) stack.peek();

			// If the current cell is the destination, exit the loop
			if (currentCell.isCustomer()) {
				System.out.println("The destination has been reached.");
				System.out.println(
						"There are " + stack.size() + " cells in the path from the initial cell to the destination.");
				break;
			}

			/**
			 * Find the best unmarked neighbouring cell. If this cell exists, push it into
			 * the stack and mark it as inStack.
			 */
			if (path.bestCell(currentCell) != null) {
				stack.push(path.bestCell(currentCell));
				path.bestCell(currentCell).markInStack();
			}
			/**
			 * If there are no unmarked neighbouring cells that can be added to the path,
			 * pop the top cell from the stack and mark it as outOfStack.
			 */

			else {
				stack.pop();
				currentCell.markOutStack();
			}

			cell = currentCell;
		}
		if (stack.isEmpty()) {
			System.out.println("The destination was not reached.");

		}

	}

	/**
	 * Find a path from the WPC cell to the destination cell C by using a stack
	 * 
	 * @param the current cell
	 * @return the best cell to continue the path from the current one
	 */
	private MapCell bestCell(MapCell cell) {
		MapCell bestCell = null;

		// If current cell is power station or omni switch
		if (cell.isPowerStation() || cell.isOmniSwitch()) {
			/**
			 * Check if neighbour exists and if neighbour cell is unmarked. Are any of the
			 * neighbours customers --> go straight there.
			 */
			for (int i = 0; i < 4; i++) {
				if ((cell.getNeighbour(i) != null) && (!cell.getNeighbour(i).isMarked())
						&& (cell.getNeighbour(i).isCustomer())) {
					bestCell = cell.getNeighbour(i);
					return bestCell;
				}
			}

			/**
			 * Check if neighbour exists and if neighbour cell is unmarked. Are any of the
			 * neighbours omni switches, if so go to the cell with the lowest index.
			 */
			for (int i = 0; i < 4; i++) {
				if ((cell.getNeighbour(i) != null) && (!cell.getNeighbour(i).isMarked())
						&& (cell.getNeighbour(i).isOmniSwitch())) {
					bestCell = cell.getNeighbour(i);
					return bestCell;
				}
			}

			/**
			 * Check if neighbour exists and if neighbour cell is unmarked. Are any of the
			 * neighbours vertical or horizontal switches, if so go to the cell with the
			 * lowest index.
			 */
			for (int i = 0; i < 4; i++) {
				if ((cell.getNeighbour(i) != null) && (!cell.getNeighbour(i).isMarked())
						&& ((((i == 0 || i == 2) && cell.getNeighbour(i).isVerticalSwitch()))
								|| ((i == 1 || i == 3) && cell.getNeighbour(i).isHorizontalSwitch()))) {
					bestCell = cell.getNeighbour(i);
					return bestCell;
				}
			}
		}

		// If current cell is a vertical switch
		else if (cell.isVerticalSwitch()) {
			/**
			 * Check if neighbour exists and if neighbour cell is unmarked. Are any of the
			 * upper or lower neighbours customers, if so go straight there.
			 */
			for (int i = 0; i < 4; i += 2) {
				if ((cell.getNeighbour(i) != null) && (!cell.getNeighbour(i).isMarked())
						&& (cell.getNeighbour(i).isCustomer())) {
					bestCell = cell.getNeighbour(i);
					return bestCell;
				}
			}

			/**
			 * Check if neighbour exists and if neighbour cell is unmarked. Are any of the
			 * upper or lower neighbours omni switches, if so go to the cell with the lowest
			 * index.
			 */
			for (int i = 0; i < 4; i += 2) {
				if ((cell.getNeighbour(i) != null) && (!cell.getNeighbour(i).isMarked())
						&& (cell.getNeighbour(i).isOmniSwitch())) {
					bestCell = cell.getNeighbour(i);
					return bestCell;
				}
			}

			/**
			 * Check if neighbour exists and if neighbour cell is unmarked. Are any of the
			 * upper or lower neighbours vertical switches, if so go to the cell with the
			 * lowest index.
			 */
			for (int i = 0; i < 4; i++) {
				if ((cell.getNeighbour(i) != null) && (!cell.getNeighbour(i).isMarked())
						&& cell.getNeighbour(i).isVerticalSwitch()) {
					bestCell = cell.getNeighbour(i);
					return bestCell;
				}
			}
		}

		// If current cell is a horizontal switch
		else if (cell.isHorizontalSwitch()) {
			/**
			 * Check if neighbour exists and if neighbour cell is unmarked. Are any of the
			 * right or left neighbours customers, if so go straight there.
			 */
			for (int i = 1; i < 4; i += 2) {
				if ((cell.getNeighbour(i) != null) && (!cell.getNeighbour(i).isMarked())
						&& (cell.getNeighbour(i).isCustomer())) {
					bestCell = cell.getNeighbour(i);
					return bestCell;
				}
			}

			/**
			 * Check if neighbour exists and if neighbour cell is unmarked. Are any of the
			 * right or left neighbours omni switches, if so go to the cell with the lowest
			 * index.
			 */
			for (int i = 1; i < 4; i += 2) {
				if ((cell.getNeighbour(i) != null) && (!cell.getNeighbour(i).isMarked())
						&& (cell.getNeighbour(i).isOmniSwitch())) {
					bestCell = cell.getNeighbour(i);
					return bestCell;
				}
			}

			/**
			 * Check if neighbour exists and if neighbour cell is unmarked. Are any of the
			 * upper or lower neighbours horizontal switches, if so go to the cell with the
			 * lowest index.
			 */
			for (int i = 1; i < 4; i++) {
				if ((cell.getNeighbour(i) != null) && (!cell.getNeighbour(i).isMarked())
						&& cell.getNeighbour(i).isHorizontalSwitch()) {
					bestCell = cell.getNeighbour(i);
					return bestCell;
				}
			}
		}

		return bestCell;
	}

}
