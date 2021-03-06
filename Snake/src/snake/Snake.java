package snake;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Class representing the snake.
 * The snake is just an {@link java.util.ArrayList}, that keeps getting update.
 * 
 * @author Leonardo Chirivì
 *
 */
public class Snake {

	public enum Directions { LEFT, RIGHT, DOWN, UP }
	private Directions direction;

	private ArrayList<SnakeTile> tiles = new ArrayList<SnakeTile>();

	private int SPEED = 10;

	public Snake() {
		direction = Directions.RIGHT;
		tiles.add(new SnakeTile(30, 50));
		tiles.add(new SnakeTile(40, 50));
		tiles.add(new SnakeTile(50, 50));
	}

	/**
	 *Method to add new tile to the snake.
	 *Just looks for the last tile added, and based on it's coordinates
	 *and direction defines correct coordinates for the new tile.
	 *This method occurs whenever the snake eats a fruit, and the tile
	 *will be inserted at the 0th index of the tiles ArrayList.
	 */
	public void addTile() {
		int tailX = 0;
		int tailY = 0;
		switch (this.direction) {
		case LEFT:
			tailX = tiles.get(0).getX() + 10;
			tailY = tiles.get(0).getY();
			break;
		case RIGHT:
			tailX = tiles.get(0).getX() - 10;
			tailY = tiles.get(0).getY();
			break;
		case UP:
			tailX = tiles.get(0).getX();
			tailY = tiles.get(0).getY() + 10;
			break;
		case DOWN:
			tailX = tiles.get(0).getX();
			tailY = tiles.get(0).getY() - 10;
			break;
		}
		tiles.add(0, new SnakeTile(tailX, tailY));
	}
	
	/**
	 * Updates snake position based on it's direction.
	 * First updated all tiles coordinates and then
	 * assign new coordinate to head.
	 */
	public void update() {
		for( int i = 0; i <= tiles.size()-2; i++ ) {
			tiles.get(i).setX( tiles.get(i+1).getX() );
			tiles.get(i).setY( tiles.get(i+1).getY() );
		}
		
		switch (this.direction) {
		case LEFT:
			getHead().moveX( -SPEED );
			break;
		case RIGHT:
			getHead().moveX( SPEED );
			break;
		case UP:
			getHead().moveY( -SPEED );
			break;
		case DOWN:
			getHead().moveY( SPEED );
			break;
		}
	}
	
	/**
	 * Checks if the snake have collided whit itself.
	 * Will occur whenever the head coordinates are the same as one of
	 * the tails that form it's body.
	 * 
	 * @return true is collides, false otherwise
	 */
	public boolean collideItSelf() {
		for( int i = 0; i < tiles.size()-1; i++ ){
			if( ( getHead().getX() == tiles.get(i).getX() ) && ( getHead().getY() == tiles.get(i).getY() ) )
				return true;
		}
		return false;
	}
	
	/**
	 * Draws the snake to the screen.
	 * @param g graphic component
	 */
	public void draw(Graphics g) {
		for( SnakeTile tile : tiles ) 
			tile.draw(g);
	}
	
	/**
	 * Gets the snake head, will be the last element of the tails ArrayList.
	 * @return snake head tile
	 */
	public SnakeTile getHead() {
		return tiles.get(tiles.size()-1);
	}
	
	/**
	 * Return current snake direction.
	 * @return snake direction
	 */
	public Directions getDirection() {
		return this.direction;
	}
	
	/**
	 * Sets new snake direction whenever user presses an arrow key.
	 * @param direction new direction based on user's input
	 */
	public void setDirection( Directions direction ) {
		this.direction = direction;
	}
	
	/**
	 * Returns the list containing all snake tiles.
	 * @return ArrayList of SnakeTiles
	 */
	public ArrayList<SnakeTile> getTiles() {
		return this.tiles;
	}
}
