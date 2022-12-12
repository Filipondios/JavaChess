package app.chess.graphics;

import java.awt.Color;
import java.util.ArrayList;

import app.chess.core.Board;
import app.chess.utils.Resources;

public class LauncherFrame extends javax.swing.JFrame {

	final javax.swing.JLabel JLabelBoard;   // Label above the Board theme selector
    final javax.swing.JLabel JLabelPieces;  // Label above the Pieces theme selector
    final javax.swing.JLabel PreviewJlabel; // Label above the Board preview
    final javax.swing.JComboBox<String> BoardBox; // Board theme selector
    final javax.swing.JComboBox<String> PiecesBox; // Pieces theme selector
    final javax.swing.JButton CancelButton; // Cancel start button
    final javax.swing.JButton StartButton; // Start JavaChess button
    
    public static ArrayList<Tile> boardTiles; // Game Board tiles (initialized here)
    public static javax.swing.JPanel gameBoard; // Game Board JPanel (initialized here)
    
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

        // Simulated Board Panel settings (Game preview)
        gameBoard.setLayout(new java.awt.GridLayout(8,8));
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
				
        // GameFrame specific settings
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JLabelBoard)
                    .addComponent(JLabelPieces)
                    .addComponent(BoardBox, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PiecesBox, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(StartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PreviewJlabel)
                    .addComponent(gameBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(PreviewJlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(gameBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(33, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JLabelBoard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BoardBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JLabelPieces)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PiecesBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CancelButton)
                            .addComponent(StartButton))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pack();
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
