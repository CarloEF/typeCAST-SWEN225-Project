package swen225.cluedo;

public abstract class Tile {
	private boolean upWall;
	private boolean downWall;
	private boolean leftWall;
	private boolean rightWall;
	
	private int x;
	private int y;
	
	private CluedoObject object;
	
	public Tile(boolean up, boolean down, boolean left, boolean right, int x, int y) {
		this.upWall = up;
		this.downWall = down;
		this.leftWall = left;
		this.rightWall = right;
		
		this.x = x;
		this.y = y;
		
		this.object = null;
	}
	
	public void setObject(CluedoObject newObject) {
		object = newObject;
	}
	
	public boolean hasUpWall() {
		return upWall;
	}
	
	public boolean hasDownWall() {
		return downWall;
	}
	
	public boolean hasLeftWall() {
		return leftWall;
	}
	
	public boolean hasRightWall() {
		return rightWall;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public abstract String toString();
}
