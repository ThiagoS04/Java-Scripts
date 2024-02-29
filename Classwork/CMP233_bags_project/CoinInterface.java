/* Thiago Schuck 15 February 2024
 * This is the bags project, it's goal is to use an ADT bag to determine the probability of picking a coin
 * This is the interface for GenericCoin
 */
package bags_project;

public interface CoinInterface {

	public void flipCoin();
	public int getTailsCount();
	public int getHeadsCount();
	public String getOrientation();
	public double getValue();
	
}
