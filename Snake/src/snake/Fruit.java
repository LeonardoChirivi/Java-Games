package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

/**
 * Class representing fruit eaten by the snake.
 * It's a 10x10 rectangle.
 * 
 * @author Leonardo Chirivì
 *
 */
public class Fruit extends Tile {

	Random rand = new Random();

	public Fruit() {
		setRandomLocation();
	}
	
	/**
	 * Renders the fruit on screen.
	 * @param g {@link java.awt.Graphics}
	 */
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		g2.fillRect(this.x, this.y, WIDTH, HEIGHT);
	}
	
	/**
	 * When the fruit is eaten, new location is calculated generating a random number
	 * in within panel dimension.
	 * {@link #roundCoordinate(int)}.
	 */
	public void setRandomLocation() {
		this.x = roundCoordinate(rand.nextInt(560));
		this.y = roundCoordinate(rand.nextInt(320));
	}
	
	/**
	 * Rounds random generated number to nearest multiple of 10, since
	 * the game is on a 10x10 grid, hence cannot have points like (121; 56), or
	 * according to {@link Panel#collide(SnakeTile, Fruit)} they will never collide. 
	 * @param c random number to be rounded
	 * @return rounded number
	 */
	private int roundCoordinate(int c) {
		c /= 10.0;
		c = Math.round(c);
		c *= 10;
		return (int) c;
	}
	
}
