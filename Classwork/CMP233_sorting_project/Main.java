/** Thiago Schuck February 29 2024
 * This is a sorting project, this is the client file */
package sorting_project;

public class Main {

	public static void main(String[] args) {
		
		//Declare fields
		Integer[] unsortedArray;	//Randomly filled
		int arraySize;			//Random from 10-20
		
		//Randomly fill array
		arraySize = (int) (Math.random() * 11) + 10;		//Randomly get array size
		unsortedArray = new Integer[arraySize];
		for (int i = 0; i < arraySize; i++) {
			
			unsortedArray[i] = (int) (Math.random() * 10);	//Fill array with random numbers from 0-9			
		}
		
		//Print unsorted array
		System.out.println("Unsorted array:");
		for(int e : unsortedArray) {
			
			System.out.print(e + " ");
			
		}
		System.out.println("\n");			//Blank new line
		
		//Sort array with selection sort
		Integer[] selectionSortedArray = unsortedArray.clone();		//Separate array to sort; won't have to refill unsorted array
		
		System.out.println("Sorting array using a selection sort...");
		for (int index = 0; index <= arraySize; index++) {
			
			ArraySorter.selectionSort(selectionSortedArray, index);
			for(int e : selectionSortedArray) {
				
				System.out.print(e + " ");
				
			}
			System.out.println("");
			
		}
		
		//Print sorted array
		System.out.println("Selection sorted array:");
		for(int e : selectionSortedArray) {
			
			System.out.print(e + " ");
			
		}
		System.out.println("\n");
		
		//Sort array with insertion sort
		Integer[] insertionSortedArray = unsortedArray.clone();
		
		System.out.println("Sorting array using an insertion sort...");
		for (int index = 0; index <= arraySize; index++) {
			
			ArraySorter.insertionSort(insertionSortedArray, index);
			for(int e : insertionSortedArray) {
				
				System.out.print(e + " ");
				
			}
			System.out.println("");
			
		}
		
		//Print sorted array
		System.out.println("Insertion sorted array:");
		for(int e : insertionSortedArray) {
			
			System.out.print(e + " ");
			
		}
		System.out.println("\n");

		//Counting sort
		System.out.println("Sorting array using a counting sort...");
		int[] countingSortedArray = countingSort(unsortedArray, arraySize);
		
		//Print sorted array
		System.out.println("Counting sorted array:");
		for(int e : countingSortedArray) {
			
			System.out.print(e + " ");
			
		}
		System.out.println("\n");
		
		//Part b and d
		System.out.println("The time required for the counting sort is O(2n).\n"
				+ "The algorithm goes through an array of size n once counting up unique numbers\n"
				+ "Then fills the array based on the instances of unique numbers which would be n times");
		
		System.out.println("This algorithm is useful as a general sorting algorithm because\n"
				+ "The worst case (2n) is better than the worst case of the selection and insertion sort (n^2)");
		
	}
	
	/** Counting sort method
	 * @param unsortedArray
	 * @param arraySize
	 * @return sorted array
	 * Takes unsorted array and creates sorted array using a counting sort
	 */
	public static int[] countingSort(Integer[] unsortedArray, int arraySize) {
		
		//Declare fields
		int[] countingSortedArray = new int[arraySize];
		int[] counters = new int[10];
		
		//Get counters
		for(int e = 0; e < arraySize; e++) {
			
			//counters array at value of element of unsortedArray, + 1
			counters[unsortedArray[e]] += 1;
			
		}
		
		//Print counters
		System.out.println("Counters:");
		for(int e: counters) {
			
			System.out.print(e + ", ");
			
		}
		System.out.println("");

		//Fill sorted array
		int counter = 0;			//Counter to store where last element of array is
		for (int i = 0; i < counters.length; i++) {			//Loop through unique numbers
			
			for (int j = 0; j < counters[i]; j++) {			//Loop through number of times unique element appears
				
				//Array at last element in array + instance
				countingSortedArray[counter] = i;
				counter++;
				
			}
			
		}
		
		//Return sorted array
		return countingSortedArray;
		
	}

}

/** Output
Unsorted array:
7 1 2 9 2 0 8 8 4 1 0 9 3 9 7 9 

Sorting array using a selection sort...
7 1 2 9 2 0 8 8 4 1 0 9 3 9 7 9 
7 1 2 9 2 0 8 8 4 1 0 9 3 9 7 9 
1 7 2 9 2 0 8 8 4 1 0 9 3 9 7 9 
1 2 7 9 2 0 8 8 4 1 0 9 3 9 7 9 
1 2 7 9 2 0 8 8 4 1 0 9 3 9 7 9 
1 2 2 7 9 0 8 8 4 1 0 9 3 9 7 9 
0 1 2 2 7 9 8 8 4 1 0 9 3 9 7 9 
0 1 2 2 7 8 9 8 4 1 0 9 3 9 7 9 
0 1 2 2 7 8 8 9 4 1 0 9 3 9 7 9 
0 1 2 2 4 7 8 8 9 1 0 9 3 9 7 9 
0 1 1 2 2 4 7 8 8 9 0 9 3 9 7 9 
0 0 1 1 2 2 4 7 8 8 9 9 3 9 7 9 
0 0 1 1 2 2 4 7 8 8 9 9 3 9 7 9 
0 0 1 1 2 2 3 4 7 8 8 9 9 9 7 9 
0 0 1 1 2 2 3 4 7 8 8 9 9 9 7 9 
0 0 1 1 2 2 3 4 7 7 8 8 9 9 9 9 
0 0 1 1 2 2 3 4 7 7 8 8 9 9 9 9 
Selection sorted array:
0 0 1 1 2 2 3 4 7 7 8 8 9 9 9 9 

Sorting array using an insertion sort...
7 1 2 9 2 0 8 8 4 1 0 9 3 9 7 9 
7 1 2 9 2 0 8 8 4 1 0 9 3 9 7 9 
1 7 2 9 2 0 8 8 4 1 0 9 3 9 7 9 
1 2 7 9 2 0 8 8 4 1 0 9 3 9 7 9 
1 2 7 9 2 0 8 8 4 1 0 9 3 9 7 9 
1 2 2 7 9 0 8 8 4 1 0 9 3 9 7 9 
0 1 2 2 7 9 8 8 4 1 0 9 3 9 7 9 
0 1 2 2 7 8 9 8 4 1 0 9 3 9 7 9 
0 1 2 2 7 8 8 9 4 1 0 9 3 9 7 9 
0 1 2 2 4 7 8 8 9 1 0 9 3 9 7 9 
0 1 1 2 2 4 7 8 8 9 0 9 3 9 7 9 
0 0 1 1 2 2 4 7 8 8 9 9 3 9 7 9 
0 0 1 1 2 2 4 7 8 8 9 9 3 9 7 9 
0 0 1 1 2 2 3 4 7 8 8 9 9 9 7 9 
0 0 1 1 2 2 3 4 7 8 8 9 9 9 7 9 
0 0 1 1 2 2 3 4 7 7 8 8 9 9 9 9 
0 0 1 1 2 2 3 4 7 7 8 8 9 9 9 9 
Insertion sorted array:
0 0 1 1 2 2 3 4 7 7 8 8 9 9 9 9 

Sorting array using a counting sort...
Counters:
2, 2, 2, 1, 1, 0, 0, 2, 2, 4, 
Counting sorted array:
0 0 1 1 2 2 3 4 7 7 8 8 9 9 9 9 

The time required for the counting sort is O(2n).
The algorithm goes through an array of size n once counting up unique numbers
Then fills the array based on the instances of unique numbers which would be n times
This algorithm is useful as a general sorting algorithm because
The worst case (2n) is better than the worst case of the selection and insertion sort (n^2)
*/
