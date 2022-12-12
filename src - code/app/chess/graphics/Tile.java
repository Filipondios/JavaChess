package app.chess.graphics;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import app.chess.core.Piece;
import app.chess.utils.Resources;

public final class Tile extends JPanel {
		
	private JLabel pieceLabel = null;
	private boolean isLight = false;
	private Piece piece;
		
	public Tile(final int color, final int piece_id) {
		
		if(color == 0) {
			this.setBackground(Resources.lightColor);	
			isLight = true;
		} else this.setBackground(Resources.darkColor);
				
		this.piece = new Piece(piece_id);
			
		if(piece_id!=0) {
			pieceLabel = new JLabel();
			pieceLabel.setIcon(new FlatSVGIcon("pieces/" + Resources.selected_piece_theme + "/" + piece + ".svg", 50, 50));
			this.add(pieceLabel);
		}
	}
	
	public void setThemeColor(Color light, Color dark) {
		this.setBackground(isLight? light : dark);
	}
	
	public void setPieceTheme(String themeName) {
		if(pieceLabel == null) return;
		pieceLabel.setIcon(new FlatSVGIcon("pieces/" + themeName + "/" + piece + ".svg", 50, 50));
	}
}