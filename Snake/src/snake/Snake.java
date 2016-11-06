package snake;

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
		tiles.add(new SnakeTile(50, 50));
		tiles.add(new SnakeTile(40, 50));
		tiles.add(new SnakeTile(30, 50));
	}

	/**
	 *Method to add new tile to the snake.
	 *Just looks for the last tile added, and based on it's coordinates
	 *and direction defines correct coordinates for the new tile.
	 *This method occurs whenever the snake eats a fruit, 
	 */
	public void addTile() {
		int tailX = 0;
		int tailY = 0;
		switch (this.direction) {
		case LEFT:
			tailX = tiles.get(tiles.size()-1).getX() + 10;
			tailY = tiles.get(tiles.size()-1).getY();
			break;
		case RIGHT:
			tailX = tiles.get(tiles.size()-1).getX() - 10;
			tailY = tiles.get(tiles.size()-1).getY();
			break;
		case UP:
			tailX = tiles.get(tiles.size()-1).getX();
			tailY = tiles.get(tiles.size()-1).getY() + 10;
			break;
		case DOWN:
			tailX = tiles.get(tiles.size()-1).getX();
			tailY = tiles.get(tiles.size()-1).getY() - 10;
			break;
		}
		tiles.add(new SnakeTile(tailX, tailY));
	}
	
	/**
	 * Updates snake position based on it's direction.
	 * First it assigns a new coordinate for the head, and then 
	 * updates all tiles coordinates.
	 */
	public void update() {
		int prevX = tiles.get(0).getX();
		int prevY = tiles.get(0).getY();

		switch (this.direction) {
		case LEFT:
			tiles.get(0).moveX( -SPEED );
			break;
		case RIGHT:
			tiles.get(0).moveX( SPEED );
			break;
		case UP:
			tiles.get(0).moveY( -SPEED );
			break;
		case DOWN:
			tiles.get(0).moveY( SPEED );
			break;
		}

		int tempX, tempY;
		for(int i = 1; i < tiles.size(); i++){
			tempX = tiles.get(i).getX();
			tempY = tiles.get(i).getY();
			tiles.get(i).setX(prevX);
			tiles.get(i).setY(prevY);
			prevX = tempX;
			prevY = tempY;
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
		for( int i = tiles.size()-1; i > 0; i-- ){
			if( ( tiles.get(0).getX() == tiles.get(i).getX() ) && ( tiles.get(0).getY() == tiles.get(i).getY() ) )
				return true;
		}
		return false;
	}
	
	/**
	 * Gets the snake head, will be the 0th index of the ArrayList
	 * @return snake head tile
	 */
	public SnakeTile getHead() {
		return tiles.get(0);
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