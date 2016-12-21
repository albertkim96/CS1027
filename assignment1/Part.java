/**
 * Class that represents a parts that is used to assemble a widgets with attributes
 * name, price, and quantity of parts
 * @author jlee2967
 *
 */
public class Part {
	
	/* Attribute declarations */
	
	private String partName;	// part's name
	private double partPrice;	// price of part
	private int partQuantity;	// remaining quantity of part
	
	/**
	 * Constructor initializes the part's name, price, and quantity
	 */
	
	public Part(String partName, double partPrice, int partQuantity){
		this.partName = partName;
		this.partPrice = partPrice;
		this.partQuantity = partQuantity;
	}
	
	/**
	 * getPartName method returns the part's name
	 * @return part's name
	 */
	
	public String getPartName() {
		return partName;
	}
	
	/**
	 * getPartPrice method returns the price of the part
	 * @return price of part
	 */
	
	public double getPartPrice() {
		return partPrice;
	}
	
	/**
	 * getPartQuantity method returns the remaining quantity of the part
	 * @return quantity of part
	 */
	
	public int getPartQuantity() {
		return partQuantity;
	}
	
	/**
	 * setPartName method sets the part's name
	 * @param partName
	 */
	public void setPartName(String partName) {
		this.partName = partName;
	}
	
	/**
	 * setPartPrice method sets the part's price
	 * @param partPrice
	 */
	
	public void setPartPrice(double partPrice) {
		this.partPrice = partPrice;
	}
	
	/**
	 * setPartQuantity method sets the quantity of part
	 * @param partQuantity
	 */
	
	public void setPartQuantity(int partQuantity) {
		this.partQuantity = partQuantity;
	}
	
	/**
	 * equals method compares if parts are equal or not.
	 * @param other other Parts object that this is compared to
	 * @return
	 */
	
	public boolean equals(Part other) {
		if (this.partName.equals(other.partName)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * toString method returns a string representation of part's name, price, and quantity
	 * @return string with part's name, price, and quantity
	 */
	
	public String toString() {
		String s = this.partName + "\t" + this.partPrice + "\t" + this.partQuantity;
		return s;
	}
	
}
