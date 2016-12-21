import java.io.*;

/**
 * Class that represents list of parts by reading lines from txt file.
 * @author jlee2967
 *
 */

public class PartsInventory {
	
	private final int DEFAULT_SIZE = 3;	// default size of array.
	final int NOT_FOUND = 1;			// returned value if certain value isn't
										// in array of list.
	
	/* Attributes declarations */
	private Part[] partsList;			// array of parts.
	private int numberOfParts = 0;			// current number of parts in list.
	
	/**
	 * Constructor creates part array of default size by reading a file
	 * @param filename text file including the parts
	 */
	public PartsInventory(String filename) {
		// declares maximum size of array to be default size.
		int max = DEFAULT_SIZE;
		
		// create a new part array
		partsList = new Part[max];
		
		// reads file using methods from InStringFile.
		InStringFile in = new InStringFile(filename);
		
		// reads file till the file reaches to end(endOfFile() returning false) while
		// creating part objects and adding it to part array.
		while (in.endOfFile() == false) {
			
			// reads each line
			String line = in.read();
			
			// splits the line by space.
			String[] words = line.split("\\s+");
			
			// after breaking the line space, creates part object with assigning
			// each words with variable.
			String partName = words[0];
			double partPrice = Double.parseDouble(words[1]);
			int partQuantity = Integer.parseInt(words[2]);
			
			// adds new part to the part array using addPart.
			addPart(partName, partPrice, partQuantity);
		}
	}
	
	/**
	 * Constructor creates part array of max size by reading a file.
	 * @param filename txt file including the parts.
	 * @param max maximum size of array.
	 */
	public PartsInventory(String filename, int max) {
		// create a new part array
		partsList = new Part[max];
		
		// reads file using methods from InStringFile.
		InStringFile in = new InStringFile(filename);
		
		// reads file till the file reaches to end(endOfFile() returning false) while
		// creating part objects and adding it to part array.
		while (in.endOfFile() == false) {
			
			// reads each line
			String line = in.read();
			
			// splits the line by space.
			String[] words = line.split("\\s+");
			
			// after breaking the line space, creates part object with assigning
			// each words with variable.
			String partName = words[0];
			double partPrice = Double.parseDouble(words[1]);
			int partQuantity = Integer.parseInt(words[2]);
			// adds new part to the part array using addPart.
			addPart(partName, partPrice, partQuantity);
		}
	}
	
	/**
	 * addPart method adds a new Part object to a Part list.
	 * @param name Part name.
	 * @param price Part price.
	 * @param quantity Part quantity.
	 */
	public void addPart(String name, double price, int quantity) {
		
		// creates new Part object.
		Part Part = new Part(name, price, quantity);
		
		// add it to the array of friends
		partsList[numberOfParts] = Part;
		numberOfParts++;
		
		// if array is not big enough, double maximum size of array.
		if (numberOfParts == partsList.length) {
			expandCapacity();
		}
	}
	
	/** 
	 * searchPart method returns the index of Part list where it has the
	 * specified name.
	 * @param name Part name.
	 * @return index of Part of specified name.
	 */
	public int searchPart(String name) {
		
		int index = NOT_FOUND;
		
		// searches list for specified name. If found, changes index to 
		// value of i, and returns that value. If not, it returns value
		// of NOT_FOUND.
		for (int i = 0; i < numberOfParts && index == NOT_FOUND; i ++) {
			if (partsList[i].getPartName().equals(name)) {
				index = i;
			}
		}
		return index;
	}
	
	/**
	 * getPartObject is getter method returning the Part object in Part
	 * array at specified index.
	 * @param index location of the Part array.
	 * @return Part object at specific index of Part array.
	 */
	public Part getPartObject(int index){
		return partsList[index];
	}
	
	/**
	 * getPartPrice is getter method returning the Price of Part object in
	 * Part array at specified index.
	 * @param index location of the Part array.
	 * @return Price of Part object at specific index of Part array.
	 */
	public double getPartPrice(int index){
		return partsList[index].getPartPrice();
	}
	
	/**
	 * getPartQuantity is getter method returning the Quantity of Part object 
	 * in Part array at specified index.
	 * @param index location of the Part array.
	 * @return Quantity of Part object at specific index of Part array.
	 */
	public int getPartQuantity(int index){
		return partsList[index].getPartQuantity();
		
	}
	
	/**
	 * getNumberOfParts is getter method returning the number of Part objects
	 * in Part array.
	 * @return number of parts in part array.
	 */
	public int getNumberOfParts(){
		return numberOfParts;
	}
	
	/**
	 * removePart method removes the specified Part object from the Part array
	 * @param part Part object to be removed
	 */
	public void removePart(Part part) {
		int index = searchPart(part.getPartName());
		partsList[index] = partsList[numberOfParts - 1];
		partsList[numberOfParts] = null;
		numberOfParts--;
	}
	
	/**
	 * expandCapacity() method creates a new array to store Part objects with
	 * twice the capacity of the existing one.
	 */
	public void expandCapacity() {
		Part[] largerList = new Part[numberOfParts * 2];
		
		for (int i = 0; i < numberOfParts; i++){
			largerList[i] = partsList[i];
		}
		
		partsList = largerList;
	}
	
	/**
	 * toString method returns a string representation of all Part objects
	 * in the list
	 * @return part name, price, and quantity 
	 */
	public String toString() {
		String part = "";
		for (int i = 0; i < numberOfParts; i++) {
			part = part + partsList[i].toString() + "\n";
		}
		return part;
	}
}