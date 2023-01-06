package app.chess.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import app.chess.pieces.Bishop;
import app.chess.pieces.King;
import app.chess.pieces.Knight;
import app.chess.pieces.Pawn;
import app.chess.pieces.Piece;
import app.chess.pieces.Queen;
import app.chess.pieces.Rook;

public final class ChessBoard extends JFrame {

	int board_width = Toolkit.getDefaultToolkit().getScreenSize().width>>2;
	int square_width;	
	
	protected static int creation_error = 0;
	protected static final String NORMAL_START_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
	private JPanel contentPanel;
	
	public ChessBoard(String fen) {
		board_width += (int) board_width*.18; 
		square_width = board_width/8;
		
		this.setSize(board_width, board_width);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("FiliChess");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(8,8));
		
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				contentPanel.add(new Tile(i+j, i, j));
		this.add(contentPanel);
		int err = loadPositionsFromFen(fen);
		
		if(err==-1) {
			creation_error = -1;
			this.dispose();
		}else {
			this.setVisible(true);
		}
	}
	
	public int loadPositionsFromFen(String fen) {
		
		String fenBoard = fen.split(" ")[0];
		
		if (fenBoard.isBlank() || fenBoard.isEmpty())
			return -1;
		
		int file = 0, rank = 0;
		
		for (char symbol : fenBoard.toCharArray()) {
			if(symbol == '/') {
				file = 0;
				rank++;
			} else {
				if(Character.isDigit(symbol)) {
					file += Character.getNumericValue(symbol);
				} else {
					int pieceColor = Character.isUpperCase(symbol)? 1 : 0;
					Piece pieceType = null;
					
					if(symbol == 'r' || symbol =='R')
						pieceType = new Rook(pieceColor, symbol);
					else if(symbol == 'n' || symbol =='N')
						pieceType = new Knight(pieceColor, symbol);
					else if(symbol == 'b' || symbol =='B')
						pieceType = new Bishop(pieceColor, symbol);
					else if(symbol == 'q' || symbol =='Q')
						pieceType = new Queen(pieceColor, symbol);
					else if(symbol == 'k' || symbol =='K')
						pieceType = new King(pieceColor, symbol);
					else if(symbol == 'p' || symbol =='P')
						pieceType = new Pawn(pieceColor, symbol);
					else
						return -1;
					
					Tile obj = (Tile) contentPanel.getComponent(rank*8+file);
					obj.setPiece(pieceType);					
					file++;
				}
			}
		}
		return 0;
	}

	private final class Tile extends JPanel {
		
		final Color light = new Color(235,210,183);
		final Color dark  = new Color(161,111,90);
		final Color light_selected = new Color(248, 236, 90);
		final Color dark_selected = new Color(218, 196, 49);

		final char[] rows = {'1', '2', '3', '4', '5', '6', '7', '8'};
		final char[] cols = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
		
		static Tile selected = null;
		
		private final int row;
		private final int col;
		
		public Tile(final int color, final int row, final int col) {
			
			boolean cond = color%2 != 0;
			this.setBackground(cond? light : dark);
			this.row = row;
			this.col = col;
			
			this.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
										
					if(getComponentCount()==0)
						return;
			
					if(selected == null) {
						setBackground(cond? light_selected : dark_selected);
						selected = mirror();
					}
					else if(mirror().equals(selected)) {
						selected = null;
						setBackground(cond? light : dark);
						return;
					}
					else {
						Color bg = selected.getBackground();
						selected.setBackground(bg.equals(light_selected)? light : dark);
						selected = mirror();
						setBackground(cond? light_selected : dark_selected);
					}
				}
			});
		}

		public void setPiece(Piece piece) {
			this.add(piece);
		}
		
		private Tile mirror() {
			return this;
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(getBackground().equals(light)? dark : light);
			g.setFont(new Font("Consolas", Font.BOLD, 13));
			
			if(col == 0) g.drawString(rows[row]+"", 5, 15);
			if(row == 7) g.drawString(cols[col]+"", square_width-13, square_width-10);
		}
	}
	
	public static void main(String[] args) {	
		try { javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
		} catch (Exception ex) { ex.printStackTrace(); }
				
		/*String fen = args[0];
		
		if(args.length == 0 || args.length > 1) {
			System.out.println("Incorrect number of parameters. Only 1 parameter is necessary: FEN String.");
			System.exit(0);
		}*/
		
		//new ChessBoard(fen);
		
		/*if(creation_error == -1) {
			System.out.println("Cannot parse the String \"" + fen + "\" to a FEN configuration.\n"
					+ "Loading default board...");
			new ChessBoard(ChessBoard.NORMAL_START_FEN);
		}*/
		
		new ChessBoard(ChessBoard.NORMAL_START_FEN);
	}
}