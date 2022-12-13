package app.chess.utils;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class that contains all the relevant configuration of the app.
 * @author Filipondios
 */
public class Resources {
	
	/** Array with all the available pieces themes names */
	public static final String[] pieces_themes = new File("src - images/pieces").list();	
	
	/** {@link HashMap} that contains all the board themes. A theme is made up with a theme
	 * ID (String key of the HashMap), and two Colors (light and dark) associated to the theme.
	 * The first color in the list of colours has to be the light one, and the second the dark one.*/
	public static final HashMap<String, ArrayList<Color>> board_themes = getBoardThemes();
	
	/** Actual selected piece theme */
	public static String selected_piece_theme = null;
	
	/** Actual selected board theme */
	public static String selected_board_theme = null;

	/** Light color of the board theme */
	public static Color lightColor = null;
	
	/** Dark color of the board theme */
	public static Color darkColor = null;
	
	/** Side of the app's game board (screen width/4). */
	public static final int side = Toolkit.getDefaultToolkit().getScreenSize().width>>2;
	
	/**
	 * Reads the available board themes from the file (src - data/board_themes) and stores them
	 * in the {@link #board_themes} structure. 
	 * @return A {@link HashMap} with all the available themes.
	 *  */
	private static HashMap<String, ArrayList<Color>> getBoardThemes() {
		Scanner scan = null;
		HashMap<String, ArrayList<Color>> themes = null;
		ArrayList<Color> colors = null;
		String line, themeName;
		String[] items, rgb;
		
		try{
			scan = new Scanner(new File("src - data/board_themes"));
		}catch(Exception e){
			System.out.println("Error when getting board_themes  --> " + e.getMessage());
			System.exit(-1);
		}
		
		themes = new HashMap<>();
		
		while(scan.hasNextLine()){
			line = scan.nextLine().trim();
			if (line.isEmpty()) continue;
			if (line.startsWith("#")) continue;
			
			items = line.split(" ");
			themeName = items[0];
			
			colors = new ArrayList<Color>(2);
			
			rgb = items[1].split("[rgb]");
			colors.add(new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2])));
			rgb = items[2].split("[rgb]");
			colors.add(new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2])));
			themes.put(themeName, colors);
		}
		return themes;
	}
}
