/** Thiago Schuck 7 May 2024
 This program is a chess game against a player or an AI
 This is the pawn piece class*/
package objects;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{

	//Declare fields
	private static String pieceName = "P";
	private String startingPos;			//Position at start of game
	private List<String> possibleMoves;		//Squares possible to move to
	private String[] files = {"a", "b", "c", "d", "e", "f", "g", "h"};
	
	//Default constructor
	Pawn() {
		
		super();
		startingPos = "d-4";
		
	}
	
	/** Constructor with color and piece number
	 * @param color - player color
	 * @param number - which iteration
	 * @param promotion - if piece being created is a promoting pawn
	 */
	Pawn(String color, int iteration) {
		
		//Call superclass constructor
		super(color, pieceName);
		
		//Determine starting position
		if (color.equals("WHITE")) {
			
			startingPos = files[iteration - 1] + "-2";			//iteration - 1 to avoid index out of bounds error
			
		} else {
			
			startingPos = files[iteration - 1] + "-7";
			
		}
		
		//Set position in superclass and on chess board
		super.setCurrentPos(startingPos);
		ChessBoard.addPiece(this);
		
		//Initialize possible moves ArrayList
		possibleMoves = new ArrayList<String>();
		
	}
	
	/**Method to find possible moves
	 * Determines possible moves based off piece, location, and other surrounding pieces
	 */
	public void findPossibleMoves() {
		
		//Save forward direction
		int forward;
		
		//Save horizontal and vertical positions
		int row = getRow();
		String fileString = getFile();
		
		//Parse file to char for easier manipulation
		char file = fileString.toCharArray()[0];
		
		//Create new file char and int row to manipulate
		char newFile = file;
		int newRow = row;
		
		if (this.getColor().equals("WHITE")) {
			
			forward = 1;
			
		} else {
			
			forward = -1;
			
		}
		
		//Check if pawn on starting square
		if (this.getCurrentPos() == startingPos) {
			
			//Add two squares in front of pawn to possibleMoves ArrayList
			addPossibleMove(newFile, newRow + forward);
			addPossibleMove(newFile, newRow + forward * 2);
			
		}
		
		//Check if square in front is empty
		newRow += forward;
		checkMove(newFile, newRow);
		
		//Check diagonal capture squares
		for (int i = -1; i < 2; i += 2) {			//Loop twice for left and right diagonals
			
			newFile += i;

			//Check if square is valid
			if (isValidSquare(newFile, newRow)) {

				//Check if square is occupied
				if (!squareIsEmpty(newFile, newRow)) {			//If square empty, do nothing

					//Check if piece is capturable
					if(capturablePiece(newFile, newRow)) {
						
						//If square is valid and piece is capturable, add to possibleMoves ArrayList
						addPossibleMove(newFile, newRow);
						
					}
					
				}
				
			}
			
			//Reset file value
			newFile = file;
			
		}
		
		//Reset row value
		newRow = row;
		
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
				
			} else {
				
				return false;			//Pawn has special capture squares
				
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
	
	public void promote(String piece) {
		
		//Determine which piece to promote to
		switch (piece) {
		
		case "Q":
			
			//Create new piece object
			Queen queen = new Queen(this.getColor(), true);		//player color, promoting pawn
			
			//Set new object position to pawn position
			queen.setCurrentPos(this.getCurrentPos());
			
			//Add new object to chess board
			ChessBoard.addPiece(queen);
			
			break;
			
		case "R":
			
			//Create new piece object
			Rook rook = new Rook(this.getColor(), 0, true);		//player color, iteration, promoting pawn
			
			//Set new object position to pawn position
			rook.setCurrentPos(this.getCurrentPos());
			
			//Add new object to chess board
			ChessBoard.addPiece(rook);
			
			break;
			
			
		case "K":
			
			//Create new piece object
			Knight knight = new Knight(this.getColor(), 0, true);		//player color, iteration, promoting pawn
			
			//Set new object position to pawn position
			knight.setCurrentPos(this.getCurrentPos());
			
			//Add new object to chess board
			ChessBoard.addPiece(knight);
			
			break;
			
			
		case "B":
		
			//Create new piece object
			Bishop bishop = new Bishop(this.getColor(), 0, true);		//player color, iteration, promoting pawn
			
			//Set new object position to pawn position
			bishop.setCurrentPos(this.getCurrentPos());
			
			//Add new object to chess board
			ChessBoard.addPiece(bishop);
			
			break;
			
		}
		
	}

}
