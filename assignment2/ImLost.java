
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class provides a main method to solve the maze.
 * @author jlee2967
 *
 */
public class ImLost {

	/**
	 * Main method solving the maze
	 * @param args command line argument represents the maze file which will be solved
	 */
	public static void main(String[] args) {
		
		try{
			
			// Checks if maze file is provided properly
			if(args.length < 1) {
				throw new IllegalArgumentException("Please provide a Maze file as a command line argument");
			}
			
			// generates maze object using file
			String fileName = args[0];
			Maze maze = new Maze(fileName);
			
			// generates hexagon object referencing start tile of maze
			Hexagon start = maze.getStart();
					
			// generates a array stack of hexagons, and pushes the start tile to the stack and set it
			// as start of the maze.
			ArrayStack<Hexagon> hexagonStack = new ArrayStack<Hexagon>();
			hexagonStack.push(start);
			start.setStarted();
					
			// reference variable to help keep track of what tile we are working with.
			Hexagon current;
			// boolean variable to keep track if the end of maze is found or not.
			boolean flag = false;
			// integer that helps keep track of number of steps
			int count = 0;
				
			// loops while stack is not empty, and we have not found the end
			while (!hexagonStack.isEmpty()&& !flag) {
				
				// boolean variable to keep track if there was neighbours around the
				// current tile.
				boolean found = false;
				// increment for count for each step
				count++;
				// make current reference to top of the hexagonStack
				current = hexagonStack.peek();
						
				// if end have been found, change flag to true
				if(current.isEnd()){ 
					current.setFinished();
					flag = true;
				}
				
				// when current tile is not the end
				else {
					
					// loop through the neighbour of current tile
					for (int i = 0; i <= 5; i++) {
						
						// if neighbour exists, not in stack, never been popped, or isn't a wall,
						// make the found to true, and push it onto stack.
						if(current.getNeighbour(i) != null 
								&& !current.getNeighbour(i).isPushed()
								&& !current.getNeighbour(i).isPopped()
								&& !current.getNeighbour(i).isWall()) {
							// neighbour is found
							found = true;
							// push the neighbour onto stack
							current.getNeighbour(i).setPushed();
							hexagonStack.push(current.getNeighbour(i));
							break;
						}	
					}
					
					// if no neighbour have been found, pop the current tile from hexagonStack
					if (!found) {
						current.setPopped();
						hexagonStack.pop();
					}
				}
				
				// repaint the maze
				maze.repaint();
			}
			
			// ending and printing the result
			if(flag) {
				System.out.println("The end has been found.");
			}
			
			else {
				System.out.println("The end could not be found.");
			}
			System.out.println("The number of steps used: " + count);
			System.out.println("Tiles remain in the stack: "+ hexagonStack.size());
		}
		
		// InvalidNeighbourIndexException handling
		catch(InvalidNeighbourIndexException e) {
			System.out.println(e.getMessage());
		}
		// EmptyCollectionException handling
		catch(EmptyCollectionException e) {
			System.out.println(e.getMessage());
		}
		// IllegalArgumentException handling
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		// UnknownMazeCharacterException handling
		catch(UnknownMazeCharacterException e){
			System.out.println(e.getMessage());
		} 
		
		// FileNotFoundException handling
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		} 
		
		// IOException handling
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
}
