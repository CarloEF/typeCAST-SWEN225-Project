package swen225.cluedo;

public class InaccessibleTile extends Tile {

	public InaccessibleTile(boolean up, boolean down, boolean left, boolean right, int x, int y) {
		super(up, down, left, right, x, y);
		
		
	}
	
	public String toString() {
		return " ";
	}
}
