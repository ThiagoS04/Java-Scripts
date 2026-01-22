/**Thiago Schuck 9 February 2024
 * This is a real estate listing project
 * This is the client class */
package list_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	/**Main method
	 * @param args
	 * Allows the user to keep track of, add, delete, and show real estate listings*/
	public static void main(String[] args) {
		
		//Declare fields
		File listingsFile = new File("src/list_project/LISTINGS.txt");	//File of listings
		Scanner userInput = new Scanner(System.in);		//Scanner to read user input
		Scanner read = null;	//Scanner to read file
		try {	//Try to create scanner object
			read = new Scanner(listingsFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found");
		}
		RealEstateInterface<HouseListing> listings = new RealEstateList<HouseListing>();	//Object to hold listings
		int userChoice = 0;	//Int to hold user's choice
		int lotNum, sqFt, numBedrooms;
		String fullName, firstName, lastName;
		double price, minPrice, maxPrice;
		
		//Read file contents and create listings
		while(read.hasNext()) {
			
			//Get listing
			String[] listingData = read.nextLine().split(" ");		//Lot #, first name, last name, price, sqft, num bedrooms
			
			//Declare data from listing
			lotNum = Integer.parseInt(listingData[0]);
			firstName = listingData[1];
			lastName = listingData[2];
			price = Double.parseDouble(listingData[3]);
			sqFt = Integer.parseInt(listingData[4]);
			numBedrooms = Integer.parseInt(listingData[5]);
			
			//Create HouseListing obj using data and add to RealEstateList
			HouseListing houseListing = new HouseListing(lotNum, firstName, lastName, price, sqFt, numBedrooms);
			listings.addLot(houseListing);
			
		}
		read.close();		//Close file
		
		//Loop until user exits
		while (userChoice != 6) {
			
			//Call method to show menu
			System.out.println(showMenu());
			
			//Get user choice
			userChoice = userInput.nextInt();
			
			//Determine user choice
			switch (userChoice) {
			
			case 1:		//Print all listings
				
				//Call method to print all listings
				System.out.println(listings.getListings());
				break;
			
			case 2:		//Add listing
				
				//Get listing information
				System.out.println("Please enter lot number:");		//Lot #
				lotNum = userInput.nextInt();
				
				System.out.println("Please enter the owner's first and last name:");		//Full name
				userInput.nextLine();		//Clear \n so scanner can read full line
				fullName = userInput.nextLine();
				firstName = fullName.split(" ")[0];		//Split full name to get first name
				lastName = fullName.split(" ")[1];		//Split full name to get last name
				
				System.out.println("Please enter the price of the property:");		//Price
				price = userInput.nextDouble();
				
				System.out.println("Please enter the square footage:");		//sqft
				sqFt = userInput.nextInt();
				
				System.out.println("Please enter the number of bedrooms:");		//Num bedrooms
				numBedrooms = userInput.nextInt();
				
				//Create house:isting object and add to RealEstateList
				HouseListing newListing = new HouseListing(lotNum, firstName, lastName, price, sqFt, numBedrooms);
				listings.addLot(newListing);
				break;
				
			case 3:		//Delete listing
				
				//Get lot number
				System.out.println("Please enter the lot number of the listing you wish to delete:");
				lotNum = userInput.nextInt();
				
				//Check if listing exists
				for (int i = 0; i < listings.getSize(); i++) {
					
					if (listings.getLot(i).getLotNumber() == lotNum) {
						
						//Call method to delete listing
						listings.deleteLot(i);
						
						System.out.println("Successfully deleted lot number " + lotNum);
						
					}
					
				}
				break;
				
			case 4:		//Print specific listing
				
				//Get lot number
				System.out.println("Please enter the lot number of the listing you wish to see:");
				lotNum = userInput.nextInt();
				
				//Check if listing exists
				for (int i = 0; i < listings.getSize(); i++) {
					
					if (listings.getLot(i).getLotNumber() == lotNum) {
						
						//Call method to get specific lot
						System.out.println(listings.toString(i));
						
					}
					
				}
				break;
				
			case 5:		//Get listings within price range
				
				//Get price range
				System.out.println("Please enter the lower price range:");		//Min price range
				minPrice = userInput.nextDouble();
				
				System.out.println("Please enter the upper price range:");		//Max price range
				maxPrice = userInput.nextDouble();
				
				//Loop through RealEstateList and print listings inside price range
				for (int i = 0; i < listings.getSize(); i++) {
					
					//Check if listing is in price range
					if (listings.getLot(i).getPrice() >= minPrice && listings.getLot(i).getPrice() <= maxPrice) {
						
						//Show listing
						System.out.println(listings.toString(i));
						
					}
					
				}
				break;
				
			case 6:		//Exit
				
				//Thank user and exit program
				System.out.println("Thank you for using this Real Estate program.");
				userInput.close();
				break;
				
			default:	//Invalid input
				
				System.out.println("Invalid input");
				break;
				
			}
			
		}
		
	}
	
	/**Method to create and return method
	 * @return menu
	 * Create menu string, and then add borders */
	public static String showMenu() {
		
		//Create menu
		String menu = "\nSelect what you would like to do:\n"
				+ "1. Print all listings\n"
				+ "2. Add listing\n"
				+ "3. Delete listing\n"
				+ "4. Print specific listing\n"
				+ "5. Get listings within a price range\n"
				+ "6. Exit program\n";
		
		//Find max line length of menu to create border
		int maxLength = 0;
		for (String line: menu.split("\n")) {
			maxLength = Math.max(maxLength, line.length());
		}
		
		//Create border and add border to menu
		String border = "";
		for (int i = 0; i < maxLength; i++) { border += "=";}	//Create border
		menu = border + menu + border;		//Add border to menu
		
		//Return menu
		return menu;
		
	}
	
}
