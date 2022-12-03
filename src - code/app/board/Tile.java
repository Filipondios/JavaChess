package app.board;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.formdev.flatlaf.extras.FlatSVGIcon;

public final class Tile extends JPanel{
	
	private static final String ICON_PATH = "images/pieces/";	
	public static final Color LIGHT = new Color(201, 156, 123);
	public static final Color DARK  = new Color(127, 86, 79);
		
	public Tile(int color, String icon_path) {
		this.setBackground((color==0)? LIGHT : DARK);
			
		JLabel label = new JLabel();
		FlatSVGIcon icon = (icon_path==null)? null : new FlatSVGIcon(ICON_PATH + icon_path + ".svg");
		label.setIcon(icon);
		this.add(label);
	}
}