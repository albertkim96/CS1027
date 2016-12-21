/**
 * This class provides a main method to solve a maze.
 * 
 * @author jlee2967
 *
 */
import java.io.*;

public class ImLostToo {
	
	/**
	 * Main method solving the maze.
	 * @param args command line argument represents the maze file which will be solved
	 */
	public static void main (String[] args) {
		
		try{
			
			// Checks if maze file is provided properly.
			if(args.length < 1){
				throw new IllegalArgumentException("Please provide a Maze file as a command line argument");
			}
		
			// Generates maze object using file.
			String fileName = args[0];
			Maze maze = new Maze(fileName);
			
			// Generates hexagon object referencing start tile of maze, and set its steps to 0.
			Hexagon start = maze.getStart();
			start.setSteps(0);
		
			// Generates a Priority Queue of hexagons, and enquese the start tile with its steps plus the 
			//		distance to end of maze as its priority.
			LinkedPriorityQueue<Hexagon> hexagonQueue = new LinkedPriorityQueue<Hexagon>();
			hexagonQueue.enqueue(start, start.getSteps() + start.distanceToEnd(maze));
			start.setStarted();

			// reference variable to help keep track of what tile we are working with.
			Hexagon current;
			// boolean variable to keep track if the end of maze is found or not.
			boolean flag = false;
			// integer that helps keep track of number of steps
			int count = 0;
			int stepsToEnd = 0;
		
			// loops while stack is not empty and we have not found the end.
			while(!hexagonQueue.isEmpty() && !flag){
				
				// increment the count for each step
				count++;
				// make current reference to top of the hexagonStack
				current = hexagonQueue.dequeue();
			
				// if end have been found, change flag to true and end the loop
				if(current.isEnd()){
					flag = true;
					current.setFinished();
					maze.repaint();
					stepsToEnd = (int) current.getSteps();
					break;
				}	
			
				// when current tile is not the end
				else{
					
					// loop through the neighbours of current tile 
					for(int i = 0; i <= 5; i++){
						
						Hexagon neighbour = current.getNeighbour(i);
						// if neighbour exists, not in Priority Queue, never been dequeued, and ins't 
						// 		a wall, enqueue the neighbours in to Priority Queue.
						if(current.getNeighbour(i) != null
								&& !current.getNeighbour(i).isEnqueued()
								&& !current.getNeighbour(i).isDequeued()
								&& !current.getNeighbour(i).isWall()){
							
							neighbour.setSteps(current.getSteps()+1);
							hexagonQueue.enqueue(neighbour,neighbour.getSteps()+neighbour.distanceToEnd(maze));
							neighbour.setEnqueued();
						}
					}
				}
				
				// if current tile is not start, then dequeue it 
				if(!current.isStart()){
					current.setDequeued();
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
			System.out.println("The number of steps to get to finish: " + stepsToEnd);
			System.out.println("Tiles remain in the queue: "+ hexagonQueue.size());
			System.out.println("Number of steps taken: " + count);
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
