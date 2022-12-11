package app.graphics;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import app.board.Board;
import app.pieces.Piece;

public class Frame extends JFrame{

	public Frame() {
		// Settings for the Frame ------------------------------
		final int side =  Toolkit.getDefaultToolkit().getScreenSize().width>>2;
		this.setSize(side, side);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setTitle("JavaChess");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	
		
		// Board Main panel that contains the tiles--------------
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(8,8));
		
		int tile_num = 0;
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++, tile_num++)
				contentPane.add(new Tile( ((i+j)%2 != 0)? 1:0, Board.squares[tile_num]));
		
		this.add(contentPane);
	}
	
	public static void main(String[] args) {
		try {
			javax.swing.UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		new Board();
		new Frame();
	}
	
	private final static class Tile extends JPanel {
			
		private static final Color LIGHT_COLOR = new Color(220, 183, 156);
		private static final Color DARK_COLOR = new Color(127, 86, 79);
		
		public Tile(final int color, final int piece_id) {
			this.setBackground( color==0?  LIGHT_COLOR : DARK_COLOR);	
			
			String path = "";
			int getColor = piece_id & 24;
			int getPiece = piece_id & 7;
			
			switch (getColor) {
				case Piece.white: path = "white"; break; 
				case Piece.black: path = "black"; break;			
			}
			
			switch (getPiece) {
				case Piece.king: path += "/king"; break; 
				case Piece.pawn: path += "/pawn"; break;
				case Piece.knight: path += "/knight"; break; 
				case Piece.bishop: path += "/bishop"; break;
				case Piece.rook: path += "/rook"; break; 
				case Piece.queen: path += "/queen"; break;
			}
			
			JLabel label = new JLabel();
			if(!path.equals("")) {
				FlatSVGIcon icon = new FlatSVGIcon("images/pieces/" + path + ".svg", 50, 50);
				label.setIcon(icon);
			}
			this.add(label);
		}
	}
}
