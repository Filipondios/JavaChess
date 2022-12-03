package app.board;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.formdev.flatlaf.FlatDarkLaf;
import java.util.ArrayList;
import java.util.Scanner;

public class Board extends JFrame{
	
	JPanel contentpane;
	
	public Board() {
		
		final Toolkit screen = Toolkit.getDefaultToolkit();
		final Dimension screen_size = screen.getScreenSize();

		this.setSize(screen_size.width>>2, screen_size.width>>2);		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setTitle("JavaChess");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		contentpane = new JPanel();
		contentpane.setLayout(new GridLayout(8,8));
		
		ArrayList<String> data = load("src - config/init_game");
		
		int count = 0;
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++, count++)
				contentpane.add(new Tile((i+j)%2!=0? 1 :0, data.get(count)));
		
		this.add(contentpane);
	}
	
	private static ArrayList<String> load(final String path) {
		Scanner sc = null;
		try { sc = new Scanner(new File(path)); }
		catch (FileNotFoundException e) { e.printStackTrace(); }
		
		String line;
		String items[];
		ArrayList<String> pieces = new ArrayList<String>(70);
		
		while(sc.hasNext()) {
			line = sc.nextLine().trim();
			if(line.startsWith("%") || line.isEmpty()) continue;

			items = line.split(" ");				

			if(line.startsWith("@")) {
				for (int i = 1; i < items.length; i++)
					pieces.add(items[i].equals("none")? null : items[i]);
				continue;
			}
			if(line.startsWith("$")) {
				for (int i = 1; i < items.length; i++)
					pieces.add(items[i]);
			}
		}
		sc.close();
		return pieces;
	}
	
	public static void main(String[] args) {
		try {
			javax.swing.UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		new Board();
	}
}
