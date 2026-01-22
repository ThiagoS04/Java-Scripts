/**Thiago Schuck 9 February 2024
 * This is a real estate listing project
 * This is an interface for the class of names */
package list_project;

import java.util.ArrayList;
import java.util.List;

public class RealEstateList<T> implements RealEstateInterface<T> {
	
	//Declare fields
	List<T> listings = new ArrayList<T>();

	/**Default constructor */
	public RealEstateList() {}
	
	/**Get method
	 * @param index
	 * Returns listing using lotNum */
	@Override
	public T getLot(int index) {
		return listings.get(index);
	}

	/**Set method
	 * @param listing
	 * @param index
	 * Adds listing to listings at index*/
	@Override
	public void setLot(int index, T listing) {
		listings.set(index, listing);
	}
	
	/**Add method
	 * @param house listing
	 * Adds a house listing to the end of the list */
	@SuppressWarnings("unchecked")
	@Override
	public void addLot(T listing) {
		listings.add(listing);
	}
	
	/**Delete method
	 * @param lot number
	 * Deletes a listing using the lot number */
	@Override
	public void deleteLot(int lotNum) {
		listings.remove(lotNum);
	}
	
	/**To string method
	 * @param index
	 * Takes index and calls toString method in HouseListing */
	public String toString(int index) {
		return listings.get(index).toString();
	}
	
	/**Method to get every listings
	 * @return string of every listing
	 * Loops through listings list, calls toString method and saves into string*/
	@Override
	public String getListings() {
		
		//Create string to hold all listings
		String allListings = "";
		
		//Loop through all listings and add toString to allListings
		for (int i = 0; i < listings.size(); i++) {
			
			//Add toString to allListings
			allListings += listings.get(i).toString();
			allListings += "\n";
			
		}
		
		//Return string of all listings
		return allListings;
		
	}
	
	/**Method to get number of listings in list
	 * @return list.size() method*/
	public int getSize() {
		return listings.size();
	}
	

}
