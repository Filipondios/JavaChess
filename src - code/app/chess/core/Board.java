package app.chess.core;

public class Board {
	
	public static int[] squares;
	
	public Board() {
		squares = new int[64];
		
		/* Black pieces at the top of the board */
		squares[0] = Piece.black | Piece.rook;
		squares[1] = Piece.black | Piece.knight;
		squares[2] = Piece.black | Piece.bishop;
		squares[3] = Piece.black | Piece.queen;
		squares[4] = Piece.black | Piece.king;
		squares[5] = squares[2];
		squares[6] = squares[1];
		squares[7] = squares[0];
		
		squares[8] = Piece.black | Piece.pawn;
		squares[9] = squares [8];
		squares[10] = squares [8];
		squares[11] = squares [8];
		squares[12] = squares [8];
		squares[13] = squares [8];
		squares[14] = squares [8];
		squares[15] = squares [8];

		/* White pieces at the end of the board */
		squares[48] = Piece.white | Piece.pawn;
		squares[49] = squares [48];
		squares[50] = squares [48];
		squares[51] = squares [48];
		squares[52] = squares [48];
		squares[53] = squares [48];
		squares[54] = squares [48];
		squares[55] = squares [48];
		
		squares[56] = Piece.white | Piece.rook;
		squares[57] = Piece.white | Piece.knight;
		squares[58] = Piece.white | Piece.bishop;
		squares[59] = Piece.white | Piece.queen;
		squares[60] = Piece.white | Piece.king;
		squares[61] = squares[58];
		squares[62] = squares[57];
		squares[63] = squares[56];
	}
}