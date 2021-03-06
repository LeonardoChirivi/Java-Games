package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Class representing a snake tile i.e. snake body part added everytime it eats a fruit.
 * It's made by a 10x10 rectangle.
 * 
 * @author Leonardo Chirivì
 *
 */
public class SnakeTile extends Tile {
	
	public SnakeTile( int x, int y ) {
		super();
		super.x = x;
		super.y = y;
	}
	
	/**
	 * Renders the tile on screen.
	 * @param g {@link java.awt.Graphics}
	 */
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLUE);
		g2.fillRect(x, y, WIDTH, HEIGHT);
	}
	
	/**
	 * Moves x coordinate by adding (or subtracting param is negative) new value to existing coordinate.
	 * @param x value to be added
	 */
	public void moveX( int x ) {
		this.x += x;
	}
	
	/**
	 * Moves y coordinate by adding (or subtracting param is negatve) new value to existing coordinate. 
	 * @param y value to be added
	 */
	public void moveY( int y ) {
		this.y += y;
	}

}
