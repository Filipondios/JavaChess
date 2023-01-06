package app.chess.pieces;

import java.util.List;
import javax.swing.JLabel;

import com.formdev.flatlaf.extras.FlatSVGIcon;

public abstract class Piece extends JLabel {
	
	public static final int BLACK = 0;
	public static final int WHITE = 1;
	
	public final int color;
	
	public Piece(final int color, char type) {
		this.color = color;
		this.setIcon(new FlatSVGIcon("pieces/"+ type + color + ".svg", 60, 60));
	}
	
	public abstract List<Move> calculateMoves();
}