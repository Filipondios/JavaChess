package app.chess.graphics;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import app.chess.core.Board;
import app.chess.utils.Resources;

public class LauncherFrame extends javax.swing.JFrame {

	final JLabel JLabelBoard;   // Label above the Board theme selector
    final JLabel JLabelPieces;  // Label above the Pieces theme selector
    final JLabel PreviewJlabel; // Label above the Board preview
    final JLabel LooksLabel; //  Label above the App theme selector
    final JComboBox<String> BoardBox; // Board theme selector
    final JComboBox<String> PiecesBox; // Pieces theme selector
    final JComboBox<String> LooksBox; // App theme selector
    final JButton CancelButton; // Cancel start button
    final JButton StartButton; // Start JavaChess button
    final Color white; // A white color
    final Color black; // A black color
    
    protected static ArrayList<Tile> boardTiles; // Game Board tiles (initialized here)
    protected static JPanel gameBoard; // Game Board JPanel (initialized here)
    protected static Color background; // Future background of some items of the app
    protected static Color foreground; // Future foreground of some items of the app
    
    public LauncherFrame() {
    	
    	// Initialize the properties
    	gameBoard = new javax.swing.JPanel();
        PreviewJlabel = new javax.swing.JLabel("Game Board preview:");
        JLabelBoard = new javax.swing.JLabel("Board Theme:");
        BoardBox = new javax.swing.JComboBox<>();
        JLabelPieces = new javax.swing.JLabel("Pieces Theme:");
        PiecesBox = new javax.swing.JComboBox<>();
        StartButton = new javax.swing.JButton("Start");
        CancelButton = new javax.swing.JButton("Cancel");
        LooksLabel = new javax.swing.JLabel("App Theme:");
        LooksBox = new javax.swing.JComboBox<>();
        
        foreground = white = new Color(248,248,248);
        background = black = new Color(38,38,38);
        
        // GameFrame basic settings
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("JavaChess Launcher");
        this.setResizable(false);

        // Themes selector: Board
        String[] IDs = new String[Resources.board_themes.size()];
        int count = 0;
        
        for (String id : Resources.board_themes.keySet())
			IDs[count++] = id;

        BoardBox.setModel(new javax.swing.DefaultComboBoxModel<>(IDs));
        Resources.selected_board_theme = (String) BoardBox.getSelectedItem(); 
        
        ArrayList<Color> colors = Resources.board_themes.get(Resources.selected_board_theme);
        Resources.lightColor = colors.get(0);
        Resources.darkColor = colors.get(1);
        colors.clear();
        
        // Themes selector: Pieces
        PiecesBox.setModel(new javax.swing.DefaultComboBoxModel<>(Resources.pieces_themes));
        Resources.selected_piece_theme = (String) PiecesBox.getSelectedItem();

        // Themes selector: App
        LooksBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Dark", "Light"}));
        
        // Simulated Board Panel settings (Game preview)
        gameBoard.setLayout(new java.awt.GridLayout(8,8));
        gameBoard.setBackground(black);
        boardTiles = new ArrayList<>(64);
        
        count = 0;
        for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++, count++) {
				Tile actual = new Tile(((i+j)%2 != 0)? 1 : 0, Board.squares[count]);
				boardTiles.add(actual);
				gameBoard.add(actual);
			}
		}
		
		// Buttons actions
		StartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new GameFrame();
                dispose();
            }
        });
				
		CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {    
            	System.exit(0);
        	}
        });

		// Combo boxes actions
		BoardBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ArrayList<Color> scheme = Resources.board_themes.get((String) BoardBox.getSelectedItem());                
                Color light = scheme.get(0);
                Color dark = scheme.get(1);
                
                for (Tile tile : boardTiles)
					tile.setThemeColor(light, dark);
            }
        });

		PiecesBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {    
            	String theme = (String) PiecesBox.getSelectedItem();     	
            	
            	for (Tile tile : boardTiles)
					tile.setPieceTheme(theme);
        	}
        });	
		
		LooksBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {    
            	
            	String theme = (String) LooksBox.getSelectedItem();
            	LookAndFeel lookAndFeel = javax.swing.UIManager.getLookAndFeel();
            	
            	if((lookAndFeel.toString().contains(theme))) return;
            	
            	try {
            		if(theme.equals("Dark")) {
            			lookAndFeel = new FlatDarkLaf();
            			foreground = white;
            			background = black;
            		}
            		else {
            			lookAndFeel = new FlatLightLaf();
            			foreground = black;
            			background = white;
            		}
            		UIManager.setLookAndFeel(lookAndFeel);
            		SwingUtilities.updateComponentTreeUI(mirror());	            		
            	
            	} catch (Exception ex) { 
            		ex.printStackTrace(); 
            	}
        	}
        });	

        // GameFrame specific settings
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(JLabelPieces)
                        .addComponent(BoardBox, 0, 214, Short.MAX_VALUE)
                        .addComponent(PiecesBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JLabelBoard)
                        .addComponent(LooksLabel)
                        .addComponent(LooksBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(StartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gameBoard, javax.swing.GroupLayout.PREFERRED_SIZE, Resources.side, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PreviewJlabel))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PreviewJlabel)
                    .addComponent(JLabelBoard))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(gameBoard, javax.swing.GroupLayout.PREFERRED_SIZE, Resources.side, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BoardBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JLabelPieces)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PiecesBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LooksLabel)
                        .addGap(9, 9, 9)
                        .addComponent(LooksBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CancelButton)
                            .addComponent(StartButton))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        pack();
    }
    
    private JFrame mirror() {
    	return this;
    }
    
    public static void main(String args[]) {
    	
      	try { javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
		} catch (Exception ex) { ex.printStackTrace(); }
    	
    	new app.chess.core.Board();
    	
    	java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	new LauncherFrame().setLocationRelativeTo(null);;
            }
        });
    }           
}
