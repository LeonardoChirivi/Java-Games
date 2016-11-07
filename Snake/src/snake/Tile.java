package snake;

/**
 * 
 * @author Leonardo Chirivi
 *
 */
public class Tile {
	
	protected int x;
	protected int y;
	protected final int WIDTH = 10;
	protected final int HEIGHT = 10;
	
	public Tile() {}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
