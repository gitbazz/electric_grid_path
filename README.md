# Electrical Grid Path

Determines whether the set of electrical switches of Westario Power Company’s grid can be used to form a path from WPC (where the electricity is generated) to a customer house.  

**4 types of map cells:**
  - An initial map cell, where WPC is located
  - Map cell where the house of customer C is situated
  - Map cells containing blocks of houses of other customers
  - Map cells containing electrical switches
  
**3 types of electrical switches:**
  - Omni switches. An omni switch located in a cell L can be used to interconnect all the neighboring map cells of L. A cell L has at most 4 neighboring cells denoted as the north, south, east, and west neighbors. The omni switch can be used to interconnect all neighbors of L 
  - Vertical switches. A vertical switch can be used to connect the north and south neighbors of a map cell
  - Horizontal switches, A horizontal switch can be used to connect the east and west neighbors of a map cell
  
**Valid Paths**
A path the program must satisfy the following conditions:
  - The path can go from the WPC cell or from an omni switch cell to the following neighboring cells:
      - the customer cell 
      -	an omni switch cell
      - the north cell or the south cell, if such a cell is a vertical switch
      - the east cell or the west cell, if such a cell is a horizontal switch.
  - The path can go from a vertical switch cell to the following neighboring cells:
      - The north cell or the south cell, if such a cell is either the customer cell C, an omni switch cell or a vertical switch cell 
  - The path can go from a horizontal switch cell to the following neighboring cells:
      - The east cell or the west cell, if such a cell is either the customer cell C, am omni switch cell, or a horizontal switch cell.

**Next Cell Priority**
  - Prefer the customer cell over other cells
  - If there is no customer cell adjacent to the current cell, then prefer the omni switch cell over the other cells
  - If there is no omni switch cell then chooses the smallest indexed cell that satisfies the conditions described above

**ArrayStack.java** class implements a stack using an array

**FindConnection.java** class that receives the city map as input and finds a path from the WPC cell to the destination cell C

**EmptyStackException** thrown when pop and peek are invoked on an empty stack

**Map.java** class that represents the map of the city including the location of the WPC cell and the C cell

**MapCell.java** class that represents the cells of the map
