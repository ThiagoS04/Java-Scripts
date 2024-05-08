/** Thiago Schuck 7 May 2024
 This program is a chess game against a player or an AI
 This is the queen piece class*/
package objects;

import java.util.List;
import java.util.ArrayList;

public class Queen extends Piece{

	//Declare fields
	private static String pieceName = "Q";
	private String startingPos;			//Position at start of game
	private List<String> possibleMoves;		//Squares possible to move to
	
	//Default constructor
	Queen() {
		
		super();
		startingPos = "d-4";
		
	}
	
	/**Constructor with color and promotion boolean
	 * @param color
	 * Calls superclass constructor, determines starting position, and then possible moves
	 */
	Queen(String color, boolean promotion) {
		
		//Call superclass constructor
		super(color, pieceName);
		
		if (!promotion) {			//If piece is not a promoting pawn
			
			//Determine starting position
			if (color.equals("WHITE")) {
				
				startingPos = "d-1";
				
			}else {
				
				startingPos = "d-8";
				
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
		
		for (int dir = 0; dir < 4; dir++) {			//4 different directions (positive and negative)
			
			switch (dir) {
			
			case 0:			//Horizontal
				
				for (int i = -1; i < 2; i += 2) {			//Loop twice for positive and negative directions
					
					//Loop while square is valid
					while (isValidSquare((char)(newFile + i), newRow)) {
						
						newFile += i;
						
						checkMove(newFile, newRow);
						
						//Check if piece is in the way
						if (!squareIsEmpty(newFile, newRow)) break;			//Stop checking in direction if blocked
						
					}
					
					//Reset row/file values
					newFile = file;
					
				}
				break;
				
			case 1:			//Vertical
				
				for (int i = -1; i < 2; i += 2) {			//Loop twice for positive and negative directions
					
					//Loop while square is valid
					while (isValidSquare(newFile, newRow + i)) {
						
						newRow += i;
						
						checkMove(newFile, newRow);
						
						//Check if piece is in the way
						if (!squareIsEmpty(newFile, newRow)) break;
						
					} 
					
					//Reset row/file values
					newRow = row;
					
				}
				break;
				
			case 2:			//Diagonal up right and down left
				
				for (int i = -1; i < 2; i += 2) {			//Loop twice for positive and negative directions
					
					//Loop while square is valid
					while (isValidSquare((char)(newFile + i), newRow + i)) {
						
						newFile += i;
						newRow += i;
						
						checkMove(newFile, newRow);
						
						//Check if piece is in the way
						if (!squareIsEmpty(newFile, newRow)) break;
						
					} 
					
					//Reset row/file values and pieceBlocking boolean
					newFile = file;
					newRow = row;
					
				}
				break;
				
			case 3:			//Diagonal up left and down right
				
				for (int i = -1; i < 2; i += 2) {			//Loop twice for positive and negative directions
					
					//Loop while square is valid
					while (isValidSquare((char)(newFile + i), newRow - i)) {			
						
						newFile += i;
						newRow -= i;
						
						checkMove(newFile, newRow);

						//Check if piece is in the way
						if (!squareIsEmpty(newFile, newRow)) break;
						
					} 
					
					//Reset row/file values and pieceBlocking boolean
					newFile = file;
					newRow = row;
					
				}
				break;
			
			}
			
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
	
	/** Method to determine if square is empty
	 * @param file
	 * @param row
	 * @return true if empty, false otherwise
	 */
	private boolean squareIsEmpty(char file, int row) {
		
		if (ChessBoard.getPiece(String.valueOf(file), row - 1) == null) {
			
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
		Piece pieceOnSquare = ChessBoard.getPiece(String.valueOf(file), row - 1);

		//Check if color of piece on square is the same
		if (pieceOnSquare.getColor().equals(this.getColor())) {
			
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
