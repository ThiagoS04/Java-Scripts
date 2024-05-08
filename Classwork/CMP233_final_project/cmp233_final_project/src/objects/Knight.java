/** Thiago Schuck 7 May 2024
 This program is a chess game against a player or an AI
 This is the knight piece class*/
package objects;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{

	//Declare fields
	private static String pieceName = "N";
	private String startingPos;			//Position at start of game
	private List<String> possibleMoves;		//Squares possible to move to
	
	//Default constructor
	Knight() {
		
		super();
		startingPos = "d-4";
		
	}
	
	/** Constructor with color and piece number
	 * @param color - player color
	 * @param number - which iteration
	 * @param promotion - if piece being created is a promoting pawn
	 */
	Knight(String color, int iteration, boolean promotion) {
		
		//Call superclass constructor
		super(color, pieceName);
		
		if (!promotion) {
			
			//Determine starting position
			if (color.equals("WHITE")) {
				
				startingPos = "1";
				
			} else {
				
				startingPos = "8";
				
			}
			
			if (iteration == 1) {
				
				startingPos = "b-" + startingPos;
				
			} else {
				
				startingPos = "g-" + startingPos;
				
			}
			
			//Set position in superclass and on chess board
			super.setCurrentPos(startingPos);
			ChessBoard.addPiece(this);
			
		}
		
		//Initialize possible moves ArrayList
		possibleMoves = new ArrayList<String>();
		
	}
	
	/**Method to find possible moves
	 * Determines possible moves based off piece, location, and other surrounding pieces
	 */
	public void findPossibleMoves() {
		
		//Save horizontal and vertical positions
		int row = getRow();
		String fileString = getFile();
		
		//Parse file to char for easier manipulation
		char file = fileString.toCharArray()[0];
		
		//Create new file char and int row to manipulate
		char newFile = file;
		int newRow = row;
		
		//Find possible moves
		for (int i = -1; i < 2; i += 2) {			//Loop twice for positive and negative row/file values
			
			//Up two and right one or down two and left one, depending on iteration
			newRow += i * 2;
			newFile += i;
			
			//Check and add to possibleMoves if square is a possible move with checkMove method
			checkMove(newFile, newRow);
			
			//Restore newFile and newRow
			newFile = file;
			newRow = row;
			
			//Up two and left one or down two and right one, depending on iteration
			newRow -= i * 2;
			newFile += i;
			
			//Check and add to possibleMoves if square is a possible move with checkMove method
			checkMove(newFile, newRow);
			
			//Restore newFile and newRow
			newFile = file;
			newRow = row;
			
			//Left two and down one or right two and up one, depending on iteration
			newFile += i * 2;
			newRow += i;
			
			//Check and add to possibleMoves if square is a possible move with checkMove method
			checkMove(newFile, newRow);
			
			//Restore newFile and newRow
			newFile = file;
			newRow = row;
			
			//Left two and up one or right two and down one, depending on iteration
			newFile -= i * 2;
			newRow += i;
			
			//Check and add to possibleMoves if square is a possible move with checkMove method
			checkMove(newFile, newRow);
			
			//Restore newFile and newRow
			newFile = file;
			newRow = row;
			
		}
		
		//Set possible moves in superclass
		super.setPossibleMoves(possibleMoves);
		
	}
	
	/** Method that checks and adds possible moves
	 * @param file - vertical position
	 * @param row - horizontal position
	 */
	private void checkMove(char file, int row) {
		
		//Check if square is a possible move with canMoveTo method
		if (canMoveTo(file, row)) {			//If square is possible to move to, add to possible moves array
			
			//Call addPossibleMove to add square to possibleMoves ArrayList
			addPossibleMove(file, row);
			
		}
		
	}
	
	/** Method to check if piece can move to square
	 * @param file
	 * @param row
	 * @return true or false
	 * Checks if square is valid
	 * Checks if square is occupied
	 * If occupied checks if square is capturable
	 * If square is valid and empty or the piece on it is capturable returns true
	 * Otherwise returns false
	 */
	private boolean canMoveTo(char file, int row) {
		
		//Check if new square is on board
		if (isValidSquare(file, row)) {
			
			//Check if square above piece is empty
			if (squareIsEmpty(file, row)) {
				
				return true;			//Square is on board, and empty
				
			} else {		//If square is occupied check if piece is capturable
				
				return capturablePiece(file, row);
				
			}
			
		} else {
			
			return false;			//Square is not on board
			
		}
		
	}
	
	/** Method to determine if square is empty
	 * @param file
	 * @param row
	 * @return true if empty, false otherwise
	 */
	private boolean squareIsEmpty(char file, int row) {
		
		if (ChessBoard.getPiece(String.valueOf(file), row - 1) == null) {		//-1 to avoid ArrayIndexOutOfBoundsException
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}
	
	/** Method that determines if square is on the board
	 * @param file
	 * @param row
	 * @return true if square is on the board
	 */
	private boolean isValidSquare(char file, int row) {
		
		if (file <= 'g' && file >= 'a' && row <= 8 && row >= 1) {
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}
	
	/** Method that determines if a piece is capturable
	 * @param file
	 * @param row
	 * @return true if capturable
	 * Check if piece is of same color
	 */
	private boolean capturablePiece(char file, int row) {
		
		//Save reference to piece on square
		Piece pieceOnSquare = ChessBoard.getPiece(String.valueOf(file), row - 1);		//-1 to avoid ArrayIndexOutOfBoundsException
		
		//Check if color of piece on square is the same
		if (pieceOnSquare.getColor().equals(super.getColor())) {
			
			return false;			//Square is occupied by same color piece
			
		} else {
			
			return true;
			
		}
		
	}
	
	/** Method to add possible move to possibleMoves ArrayList
	 * @param file - vertical position
	 * @param row - horizontal position
	 * Concatenate string with file and row
	 * Add string to possibleMoves ArrayList
	 */
	private void addPossibleMove(char file, int row) {
		
		//Create square string to add to ArrayList
		String possibleMove = String.valueOf(file) + "-" + String.valueOf(row);
		
		//Add possible move to ArrayList
		possibleMoves.add(possibleMove);
		
	}
	
}
