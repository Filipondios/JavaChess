package app.pieces;

public class Piece {
	
	/* Pieces values/IDs are used bit by bit like a counter. Imagine that we want identificate
	 * a white pawn. To express the value of that pawn, we make the OR operation for the bits
	 * of pawn (2 = 00010) and white (8 = 01000) so we get the value of a white pawn (01010).
	 * 
	 * The only exception here is a piece that represents an empty tile, with the value of 
	 * none (0), that dont needs to make the num with white or black.
	 * 
	 * As a simple note, we can also calculate the value of a piece with the value of the piece
	 * and the color, resulting the same as the OR bit operation, cause the bits that represents
	 * the pieces are from 0 to 2 and for the colors from the 3 to 4 don't they intercede with 
	 * each other bits. */
	
	public static final int none = 0;
	public static final int king = 1;
	public static final int pawn = 2;
	public static final int knight = 3;
	public static final int bishop = 4;
	public static final int rook = 5;
	public static final int queen = 6;
	public static final int white = 8;
	public static final int black = 16;
	
}
