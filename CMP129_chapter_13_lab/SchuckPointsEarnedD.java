/**Thiago Schuck November 10 2023
 * This program changes the previous lab to include super and sub classes
 */
package chapter_13_lab;
import java.util.ArrayList;

public class SchuckPointsEarnedD {

	//Instance field
	private double points;

	//Default constructor
	SchuckPointsEarnedD() {
		points = 0;
	}
	
	/**Constructor with points param
	 * 
	 * @param starting points
	 */
	SchuckPointsEarnedD(double p) {
		points += p;
	}
	
	//Setter methods
	/**Calculate and set points earned for one month of purchases
	 * 
	 * @param cups bought in one month
	 */
	public void setPoints(int c) {
		
		//Calculate points earned
		points += calculatePoints(c);
	}
	
	/**Calculate and set points earned
	 * 
	 * @param cups bought
	 */
	public void setPoints(ArrayList<Integer> c) {
		
		//Calculate points earned
		for(int month : c) {
			points += calculatePoints(month);
		}
	}
	
	/**Method to add points
	 * 
	 * @param p
	 */
	public void addPoints(double p) {
		points += p;
	}
	
	//Getter method
	/**Get points
	 * 
	 * @return points of object
	 */
	public double getPoints() {
		return points;
	}
		
	/**Calculate and return points
	 * 
	 * @param cups
	 * @return points
	 */
	private static double calculatePoints(int cups) {
		
		//Declare variables
		double p;
		
		//Calculate points
		if (cups == 0) {			//No cups bought
			p = 0.0;
		}else if (cups == 1) {		//1 cup bought
			p = 2.0;
		}else if (cups == 2) {		//2 cups bought
			p = 5.15;
		}else if (cups == 3) {		//3 cups bought
			p = 8.249;
		}else if (cups == 4) {		//4 cups bought
			p = 11.555;
		}else{						//5 or more cups bought
			p = cups * 2.895;
		}
		
		//Return points
		return p;
	}

	
}
