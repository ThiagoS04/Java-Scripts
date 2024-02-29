/* Thiago Schuck 15 February 2024
 * This is the bags project, it's goal is to use an ADT bag to determine the probability of picking a coin
 * This is the coin parent class
 */
package bags_project;

public class GenericCoin implements CoinInterface{

	//Declare fields
	private String orientation;
	private int headsCount;
	private int tailsCount;
	
	//Default contstructor
	public GenericCoin() {
		
		orientation = "";
		
	}
	
	/**Method to flip coin
	 * Take the mod 2 of a random number from 0-100
	 * if the mod is even set orientation to heads, otherwise tails
	 * Increment count
	 */
	@Override
	public void flipCoin() {
		
		//Create random number
		int number = (int) (Math.random() * 100);
		
		//Take mod
		if (number % 2 == 0) {	//Check if mod is even
			
			//Set orientation to heads
			orientation = "heads";
			
			//Increment count
			headsCount++;
			
		}else {
			
			//Set orientation to tails
			orientation = "tails";
			
			//Increment count
			tailsCount++;
			
		}
		
	}
	
	/**Getter methods
	 * headsCount
	 * tailsCount
	 * orientation
	 */
	
	
	@Override
	public int getHeadsCount() {
		return headsCount;
	}

	@Override
	public int getTailsCount() {
		return tailsCount;
	}
	
	@Override
	public String getOrientation() {
		return orientation;
	}

	@Override
	public double getValue() {
		return this.getValue();
	}
	
}
