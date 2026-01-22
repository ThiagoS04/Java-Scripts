/** Thiago Schuck 7 May 2024
 This program is a chess game against a player or an AI
 This is the piece superclass*/
package objects;

import java.util.List;

public class Piece {

	//Declare fields
	private String color;
	private String currentPos;
	private String pieceName;
	private List<String> possibleMoves;		//Squares possible to move to
	
	//Default constructor
	Piece() {
		
		color = "gray";
		
	}
	
	//Constructor with piece color and name
	Piece(String color, String name) {
		
		this.color = color;
		this.pieceName = name;
		
	}
	
	/**Method to get file and row position
	 * Gets current position and splits it into the horizontal and vertical position
	 * @return split position
	 */
	public String[] getSplitPos() {
		
		//Get split position
		String[] splitPosition = currentPos.split("-");
		
		//Return split position
		return splitPosition;
		
	}
	
	/**Method to get row position
	 * get split position
	 * @return char row
	 */
	protected String getFile() {
		
		//Get position
		String[] position = getSplitPos();
		
		//Return row
		return position[0];
		
	}
	
	/**Method to get file position
	 * Get split position
	 * @return int file
	 */
	protected int getRow() {
		
		//Get position
		String[] position = getSplitPos();
		
		//Return row
		return Integer.parseInt(position[1]);
		
	}
	
	//Getter methods
	public String getColor() {
		return color;
	}
	public String getCurrentPos() {
		return currentPos;
	}
	
	public String getPieceName() {
		return pieceName;
	}
	
	public List<String> getPossibleMoves() {
		return possibleMoves;
	}

	//Setter methods
	public void setCurrentPos(String pos) {
		currentPos = pos;
	}
	
	public void setPossibleMoves(List<String> moves) {
		possibleMoves = moves;
	}
	
}
