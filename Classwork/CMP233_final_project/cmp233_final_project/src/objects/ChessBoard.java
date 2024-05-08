/** Thiago Schuck 7 May 2024
 This program is a chess game against a player or an AI
 This class stores the pieces on the board */
package objects;

import java.util.Hashtable;

public class ChessBoard {

	//Create board array
	private static Piece[][] board = new Piece[8][8];
	private static Hashtable<String, Integer> files = new Hashtable<String,Integer>() {{		//<file letter, corresponding number>
	
		//Fill files data
		put("a", 0);
		put("b", 1);
		put("c", 2);
		put("d", 3);
		put("e", 4);
		put("f", 5);
		put("g", 6);
		put("h", 7);
		
	}};
	
	/** Method to add piece to board
	 * @param piece to add
	 * get row and file
	 * put piece into array
	 */
	public static void addPiece(Piece piece) {
		
		//Define row and file
		int row = piece.getRow() - 1;			//-1 to avoid ArrayIndexOutOfBoundsException
		int file = files.get(piece.getFile());
		
		//Set piece to corresponding position in array
		board[row][file] = piece;
		
		//Set piece's new possible moves
		
		
	}
	
	/** Method to remove piece from board
	 * @param piece to add
	 * get row and file
	 * remove piece from array
	 */
	public static void removePiece(Piece piece) {
		
		//Define row and file
		int row = piece.getRow();
		int file = files.get(piece.getFile());
		
		//Set piece to corresponding position in array
		board[row - 1][file] = null;
		
	}
	
	/** Method to get piece at specific position
	 * @param file
	 * @param row
	 * @return piece in array
	 * Gets piece in board array using file and row, returns null if empty
	 */
	public static Piece getPiece(String file, int row) {
		
		return board[row][files.get(file)];
		
	}
	
	/** Method to print board
	 * Prints each row by thirds
	 * Top third contains the top border
	 * Middle third contains the piece color
	 * Bottom third contains the bottom border and piece name
	 */
	public static void printBoard() {
		
		//Initialize board, piece, color, and name
		String pieceColor;
		String pieceName;
		String[] fileList = {"a", "b", "c", "d", "e", "f", "g", "h"};
		
		System.out.println("\n-------------------------------------------");			//Top border for board
		for (int row = 7; row >= 0; row--) {			//Loop for each row
			
			System.out.print("  ");		//Align
			
			for (int file = 0; file < 8; file++) {			//Loop to print top third of each file in row
				
				System.out.print(" ___ ");
				
			}
			
			System.out.println();			//Finished printing top third of row
			System.out.print("  ");		//Align
			
			for (int file = 0; file < 8; file++) {				//Loop to print middle third of each file in row
				
				//Create reference to piece object
				Piece piece = getPiece(fileList[file], row);
				
				//Format piece color
				if (piece == null) {		//If null, set to 3 spaces
					
					pieceColor = "|   |";
					
				} else {
				
					pieceColor = "| " + piece.getColor().charAt(0) + " |";
				}
				
				System.out.print(pieceColor);
				
			}
			
			System.out.println();			//Finished printing middle third of row
			System.out.print(row + 1 + " ");	//Print row number
			
			for (int file = 0; file < 8; file++) {
				
				//Create reference to piece object
				Piece piece = getPiece(fileList[file], row);
				
				//Format piece color
				if (piece == null) {		//If null, set to 3 spaces
					
					pieceName = "|___|";
					
				} else {
				
					pieceName = "|_" + piece.getPieceName() + "_|";
				}
				
				System.out.print(pieceName);
				
			}
			
			System.out.println();			//Finished printing row
			
		}
		
		System.out.print(" ");		//Align
		//Print file letters after finished printing all rows
		for (int file = 0; file < 8; file++) {
			
			System.out.print("  " + fileList[file] + "  ");
			
		}
		
		System.out.println("\n-------------------------------------------");			//Bottom border for board
		
	}
	
	/** Method that calls method to find possible moves for each piece after all have been added
	 * 	Loops through every square on board
	 * 	Checks subclass of piece
	 * 	Calls method to find possible moves of that specific piece type
	 */
	public static void initializePossibleMoves() {
		
		//Create list of files
		String[] fileList = {"a", "b", "c", "d", "e", "f", "g", "h"};
		
		for (int row = 0; row < 8; row++) {			//Loop through each row
			
			for (int file = 0; file < 8; file++) {			//Loop through each file for each row
				
				//Create reference
				Piece piece = getPiece(fileList[file], row);
				
				//Cast unique piece to piece to call method to find possible moves
				if (determinePiece(piece).equals("K")) {
					
					((King) piece).findPossibleMoves();
					
				} else if (determinePiece(piece).equals("Q")) {
					
					((Queen) piece).findPossibleMoves();
					
				} else if (determinePiece(piece).equals("R")) {
					
					((Rook) piece).findPossibleMoves();
					
				} else if (determinePiece(piece).equals("B")) {
					
					((Bishop) piece).findPossibleMoves();
					
				} else if (determinePiece(piece).equals("N")) {
					
					((Knight) piece).findPossibleMoves();
					
				} else if (determinePiece(piece).equals("P"))  {
					
					((Pawn) piece).findPossibleMoves();
					
				}
				
			}
			
		}
		
	}
	
	/** Method to determine subclass of piece
	 * @param Piece
	 * @return string representing subclass of Piece
	 */
	private static String determinePiece(Piece piece) {
		
		//Create string to return
		String subclass = "";
		
		//Determine piece
		if (piece instanceof King) {
			
			subclass = "K";
			
		} else if (piece instanceof Queen) {
			
			subclass = "Q";
			
		} else if (piece instanceof Rook) {
			
			subclass = "R";
			
		} else if (piece instanceof Bishop) {
			
			subclass = "B";
			
		} else if (piece instanceof Knight) {
			
			subclass = "N";
			
		} else if (piece instanceof Pawn)  {
			
			subclass = "P";
			
		}
		
		//Return subclass
		return subclass;
		
	}
	
}
