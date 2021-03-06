package snake;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

/**
 * Frame that holds the game panel.
 * 
 * @author Leonardo Chirivì
 *
 */
public class Board extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JLabel scoredPointsLabel;
	private JLabel scoreLabel;
	private Panel panel;
	
	public Board() {
		UIManager.getCrossPlatformLookAndFeelClassName();
		this.setBounds(100, 100, 600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Snake");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		scoreLabel = new JLabel("Score:");
		scoreLabel.setBounds(34, 12, 45, 15);
		getContentPane().add(scoreLabel);
		
		scoredPointsLabel = new JLabel("0000");
		scoredPointsLabel.setBounds(91, 12, 70, 15);
		getContentPane().add(scoredPointsLabel);
		
		panel = new Panel(scoredPointsLabel);
		panel.setBounds(12, 32, 570, 330);
		getContentPane().add(panel);
		
		this.setVisible(true);
	}	
}











