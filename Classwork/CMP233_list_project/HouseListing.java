/**Thiago Schuck 9 February 2024
 * This is a real estate listing project
 * This is the class for the house listing*/
package list_project;

public class HouseListing {
	
	//Declare fields
	int lotNumber;
	int sqFt;
	int numBedrooms;
	Name owner;
	double price;
	
	/**Default constructor
	 * Sets all fields to 0 or default */
	public HouseListing() {
		
		lotNumber = 0;
		sqFt = 0;
		numBedrooms = 0;
		owner = new Name();
		price = 0.0;
		
	}
	
	/**Constructor with all fields
	 * @param lotNumber	- lot number of property
	 * @param owner - owner of property
	 * @param price - price of property
	 * @param sqFt - property square feet
	 * @param numBedrooms - # of bedrooms in property
	 */
	public HouseListing(int lotNumber, String firstName, String lastName, double price, int sqFt, int numBedrooms) {
		
		this.lotNumber = lotNumber;
		this.sqFt = sqFt;
		this.numBedrooms = numBedrooms;
		this.owner = new Name(firstName, lastName);
		this.price = price;
		
	}

	/**Lot number getter method
	 * @return lot number */
	public int getLotNumber() {
		return lotNumber;
	}
	
	/**Lot number setter method
	 * @param lotNumber */
	public void setLotNumber(int lotNumber) {
		this.lotNumber = lotNumber;
	}

	/**Square feet getter method
	 * @return sqFt */
	public int getSqFt() {
		return sqFt;
	}

	/**Square feet setter method
	 * @param sqFt */
	public void setSqFt(int sqFt) {
		this.sqFt = sqFt;
	}

	/**Number of bedrooms getter method
	 * @return numBedrooms*/
	public int getNumBedrooms() {
		return numBedrooms;
	}

	/**Number of bedrooms setter method
	 * @param numBedrooms */
	public void setNumBedrooms(int numBedrooms) {
		this.numBedrooms = numBedrooms;
	}

	/**Owner getter method
	 * @return owner */
	public Name getOwner() {
		return owner;
	}

	/**Owner setter method
	 * @param owner */
	public void setOwner(Name owner) {
		this.owner = owner;
	}

	/**Price getter method
	 * @return price*/
	public double getPrice() {
		return price;
	}

	/**Price setter method
	 * @param price */
	public void setPrice(double price) {
		this.price = price;
	}

	/**toString method
	 * @return lotNumber, sqFt, numBedroms, owner, price */
	@Override
	public String toString() {
		return "HouseListing [Lot Number=" + lotNumber + ", square ft=" + sqFt + ", Number of Bedrooms=" + numBedrooms + ", Owner="
				+ owner + ", Price=" + String.format("%,.2f", price) + "]";
	}
	
	
	
	
	

}
