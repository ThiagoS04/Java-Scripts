/* Thiago Schuck 15 February 2024
 * This is the bags project, it's goal is to use an ADT bag to determine the probability of picking a coin
 * This is the coin class
 */
package bags_project;

public class Coin extends GenericCoin	 {

	//Declare fields
	private String name;
	private double value;
	
	//Default Constructor
	public Coin() {
		
		super();
		name = "default";
		value = 0;
		
	}
	
	/**Constructor with name
	 * @param name
	 */
	public Coin(String name) {
		
		super();
		this.name = name;
		value = 0;
		
	}

	/**Constructor with name and value
	 * @param name
	 * @param value
	 */
	public Coin(String name, double value) {
		
		super();
		this.name = name;
		this.value = value;
		
	}
	
	@Override
	public String toString() {
		return "Coin [name=" + name + ", value=" + value + "]";
	}

	/**Getter and Setter methods
	 * name
	 * value
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	/** Comparison method
	 * @param coin
	 * @return true if coin is the same type
	 */
	public boolean equals(Coin coin) {
		
		if (this.getName().equals(coin.getName())) {
			
			return true;
			
		}else{
			
			return false;
			
		}
		
	}
	
}
