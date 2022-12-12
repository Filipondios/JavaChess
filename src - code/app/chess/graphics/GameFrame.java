package app.chess.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public final class GameFrame extends JFrame {
	
	public GameFrame() {
		// Settings for the GameFrame
		final int width = Toolkit.getDefaultToolkit().getScreenSize().width>>2;
		this.setSize((int) (width+width*0.18),(int) (width+width*0.18));
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle("JavaChess");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	
								
		// Add the coordinate bars at the sides and the board in the center
		this.setLayout(new BorderLayout());
		this.add(new BorderPanel(null, false), BorderLayout.NORTH); // Empty coordinate bar
		this.add(new BorderPanel(null, false), BorderLayout.EAST); // Empty coordinate bar
		this.add(LauncherFrame.gameBoard, BorderLayout.CENTER); // Board		
		this.add(new BorderPanel(new String[] { " 8 ", " 7 ", " 6 ", " 5 ", " 4 ", " 3 ", " 2 ", " 1 " }, true), BorderLayout.WEST); // Vertical coordinate bar
		this.add(new BorderPanel(new String[] { " a ", " b ", " c ", " d ", " e ", " f ", " g ", " h " }, false), BorderLayout.SOUTH); // Horizontal coordinate bar
	}
	
	private final class BorderPanel extends JPanel{

		public BorderPanel(String[] labels, boolean vertical) {
			this.setBackground(new Color(38,38,38));
			if(labels == null) return;
			
			this.setLayout(vertical? new GridLayout(labels.length,1) : new GridLayout(1, labels.length));
			
			for (String label : labels) {
				JLabel item = new JLabel(label, SwingConstants.CENTER);
				item.setForeground(Color.white);
				this.add(item);
			}
		}	
	}
}