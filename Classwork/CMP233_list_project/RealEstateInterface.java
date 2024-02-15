/**Thiago Schuck 9 February 2024
 * This is a real estate listing project
 * This is an interface for the RealEstateList class */
package list_project;

public interface RealEstateInterface<T> {
	
	//Declare methods
	
	/**Get method
	 * @param index 
	 * Returns the listing at a specific position in the list*/
	public T getLot(int index);

	/**Set method
	 * @param listing
	 * @param index
	 * Adds listing to listings at index*/
	public void setLot(int index, T listing);

	/**To string method
	 * @param index
	 * Takes index and calls toString method in HouseListing */
	public String toString(int index);

	/**Add method
	 * @param house listing
	 * Adds a house listing into the RealEstateList */
	public void addLot(T listing);
	
	/**Method to get number of listings in list
	 * @return list.size() method*/
	public int getSize();

	/**Method to get every listings
	 * @return string of every listing
	 * Loops through listings list, calls toString method and saves into string*/
	public String getListings();

	/**Delete method
	 * @param lot number
	 * Deletes a listing using the lot number */
	public void deleteLot(int lotNum);

}
