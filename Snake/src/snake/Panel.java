package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import snake.Snake.Directions;

/**
 * Game panel that handles the game.
 * 
 * The field is just a grid made of square 10x10, where the snakes moves.
 * That makes easy for rendering fruits and moving the snake or collision detection.
 * Clarified in {@link #collide(SnakeTile, Fruit)}, {@link #collideBounds(SnakeTile)} and {@link Snake#collideItSelf()}.
 * 
 * @author Leonardo Chirivì
 *
 */
public class Panel extends JPanel implements KeyListener, Runnable {
	private static final long serialVersionUID = 1L;

	private final int FPS = 10;
	private int targetTime = 1000/FPS;
	private long start, stop, wait;

	private Snake snake;
	private Fruit fruit;

	private final int POINTS = 10;
	private int score = 0;

	private JLabel label;
	
	private Thread thread;
	private boolean running = false;

	public Panel( JLabel label ) {
		this.setOpaque(true);
		this.setBackground(new Color(51, 51, 51));
		thread = new Thread(this, "First Thread");
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();
		this.label = label;
		snake = new Snake();
		fruit = new Fruit();
		running = true;
		thread.start();
	}
	
	/**
	 * Renders the snake and the fruit, optionally a grid can be drawn on panel 
	 * by uncommenting drawGrid() method.
	 * {@inheritDoc}
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
//		drawGrid(g);
		snake.draw(g);
		fruit.draw(g);
	}
	
	/**
	 * Method that displays game over screen.
	 * 
	 * @param g graphics
	 */
	public void gameOver(Graphics g) {
		g.setColor(new Color(51, 51, 51));
		g.fillRect(0, 0, 570, 330);
		g.setColor(Color.WHITE);
		g.setFont(new Font("default", Font.BOLD, 16));
		g.drawString("Game Over", 225, 150);
	}
	
	/**
	 * Listens for key events and assigns new direction based on user input key.
	 * {@inheritDoc}
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if( !snake.getDirection().equals(Directions.DOWN) )
				snake.setDirection(Directions.UP);
			break;
		case KeyEvent.VK_DOWN:
			if( !snake.getDirection().equals(Directions.UP) )
				snake.setDirection(Directions.DOWN);
			break;
		case KeyEvent.VK_RIGHT:
			if( !snake.getDirection().equals(Directions.LEFT) )
				snake.setDirection(Directions.RIGHT);
			break;
		case KeyEvent.VK_LEFT:
			if( !snake.getDirection().equals(Directions.RIGHT) )
				snake.setDirection(Directions.LEFT);
			break;
		}
	}
	
	/**
	 * Method that checks if the snakes eats the fruits (i.e. collides with).
	 * Since they're on a grid, they collide if x coordinate and y coordinate are the same.
	 * 
	 * @param tile snake body part, will be the snake head
	 * @param fruit rendered fruit
	 * @return true if collide (overlap) false otherwise.
	 */
	public boolean collide(SnakeTile tile, Fruit fruit){
		return (tile.getX() == fruit.getX()) && (tile.getY() == fruit.getY());
	}
	
	/**
	 * Method that checks if the snake collides with panel bounds.
	 * It collides if each of snake's head coordinated exceeds panel's width and height.
	 * 
	 * @param tile
	 * @return
	 */
	public boolean collideBounds(SnakeTile tile){
		return (tile.getX() < 0) || (tile.getX() > 570) || (tile.getY() < 0 ) || (tile.getY() > 330);
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	/**
	 * Game loop.
	 * Thread is started and keeps checking for collisions.
	 * Also updates score and rendering.
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		while (running) {
			start = System.nanoTime();
			if(collideBounds(snake.getHead()) || snake.collideItSelf() ) {
				running = false;
			}
			if(collide(snake.getHead(), fruit)) {
				score += POINTS;
				label.setText(Integer.toString(score));
				snake.addTile();
				fruit.setRandomLocation();
			}
			snake.update();
			repaint();
			stop = System.nanoTime() - start;
			wait = targetTime - stop / 1000000;
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		gameOver(getGraphics());
	}
	
	//draws a grid on panel
//	public void drawGrid(Graphics g) {
//		g.setColor(Color.WHITE);
//		for(int i = 10; i < 570; i += 10)
//			g.drawLine(i, 0, i, 330);
//		for(int i = 10; i < 330; i += 10)
//			g.drawLine(0, i, 570, i);
//	}
}
