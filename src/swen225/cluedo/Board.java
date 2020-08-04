package swen225.cluedo;

import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Board {
	Tile[][] board;
	int width;
	int height;
	
	/**
	 * 
	 * @param width of the board
	 * @param height of the board
	 */
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		
		this.board = new Tile[width][height];
	}
	
	/**
	 * 
	 * @param player
	 * @param newX
	 * @param newY
	 */
	public void movePlayer(Player player, int newX, int newY) {
		
	}
	
	/**
	 * 
	 * @param roomText - text representing the tiles
	 * @param wallText - bitfield text representing the walls on the tiles
	 * @param rooms - a map containing the rooms
	 */
	public void setBoard(String roomText, String wallText, Map<String, Room> rooms) {
		Scanner scan = new Scanner(wallText);
		scan.useDelimiter("/");
		
		for (int i=0, len=roomText.length();i<len;i++) {
			int wallNum = scan.nextInt();
			
			boolean left = false;
			boolean up = false;
			boolean right = false;
			boolean down = false;
			
			if ((wallNum & 1) == 1) {
				left = true;
			}
			if ((wallNum & 2) == 2) {
				up = true;
			}
			if ((wallNum & 4) == 4) {
				right = true;
			}
			if ((wallNum & 8) == 8) {
				down = true;
			}
			
			char room = roomText.charAt(i);
			
			int x = i % width;
			int y = Math.floorDiv(i, width);
			
			Tile tile = null;
			
			switch(room) {
				case 'i':
					tile = new InaccessibleTile(x, y);
					break;
				case 'h':
					tile = new HallwayTile(up, down, left, right, x, y);
					break;
				case 'k':
					tile = new RoomTile(up, down, left, right, x, y, rooms.get("Kitchen"));
					break;
				case 'b':
					tile = new RoomTile(up, down, left, right, x, y, rooms.get("Ball Room"));
					break;
				case 'c':
					tile = new RoomTile(up, down, left, right, x, y, rooms.get("Conservatory"));
					break;
				case 'd':
					tile = new RoomTile(up, down, left, right, x, y, rooms.get("Dining Room"));
					break;
				case 'l':
					tile = new RoomTile(up, down, left, right, x, y, rooms.get("Library"));
					break;
				case 'm':
					tile = new RoomTile(up, down, left, right, x, y, rooms.get("Billiard Room"));
					break;
				case 'a':
					tile = new RoomTile(up, down, left, right, x, y, rooms.get("Hall"));
					break;
				case 's':
					tile = new RoomTile(up, down, left, right, x, y, rooms.get("Study"));
					break;
				case 'o':
					tile = new RoomTile(up, down, left, right, x, y, rooms.get("Lounge"));
					break;
				default:
					System.err.println("Invalid tile - " + room);
			}
			
			board[x][y] = tile;
		}
		scan.close();
		
		//System.out.println(this);
	}
	
	/**
	 * Checks if a move is valid
	 * @param diceRoll - the number of moves to use, determined by a dice roll
	 * @param player - the player to move
	 * @param newX - the new x position to move to
	 * @param newY - the new y position to move to
	 * @return
	 */
	public boolean isValidMove(int diceRoll, Player player, int newX, int newY) {
		
		Tile playerTile = player.getTile();
		
		Tile targetTile = getTile(newX, newY);
		
		Stack<Tile> visitedTiles = new Stack<Tile>();

		visitedTiles.add(playerTile);
		
		return validMove(0, diceRoll, visitedTiles, targetTile);
	}
	
	/**
	 * Recursive method to determine whether move is valid
	 * @param moveNum - number of moves used so far
	 * @param diceRoll - the number of moves to use, determined by a dice roll
	 * @param visited - a stack of tiles already visited
	 * @return
	 */
	private boolean validMove(int moveNum, int diceRoll, Stack<Tile> visited, Tile targetTile) {
		Tile lastTile = visited.peek();
		
		if (moveNum == diceRoll) {
			//could be equals
			if (lastTile == targetTile) {
				return true;
			} else {
				return false;
			}
		}
		
		//terminate if player moves to targetTile early
		if (lastTile == targetTile) {
			return false;
		}
		
		//can't go to invalid tiles or through walls
		if (lastTile.getY() > 0 && !lastTile.hasUpWall()) {
			Tile upperTile = getTile(lastTile.getX(), lastTile.getY()-1);
			
			//can't access inaccessible tiles or already visited tiles
			if (!(upperTile instanceof InaccessibleTile) && !visited.contains(upperTile)) {
				visited.add(upperTile);
				
				if (validMove(moveNum+1, diceRoll, visited, targetTile)) {
					return true;
				}
				visited.pop();
			}
		}
		
		//can't go to invalid tiles or through walls
		if (lastTile.getY() < height-1 && !lastTile.hasDownWall()) {
			Tile lowerTile = getTile(lastTile.getX(), lastTile.getY()+1);
			
			//can't access inaccessible tiles or already visited tiles
			if (!(lowerTile instanceof InaccessibleTile) && !visited.contains(lowerTile)) {
				visited.add(lowerTile);
				
				if (validMove(moveNum+1, diceRoll, visited, targetTile)) {
					return true;
				}
				visited.pop();
			}
		}
		
		//can't go to invalid tiles or through walls
		if (lastTile.getX() > 0 && !lastTile.hasLeftWall()) {
			Tile leftTile = getTile(lastTile.getX()-1, lastTile.getY());
			
			//can't access inaccessible tiles or already visited tiles
			if (!(leftTile instanceof InaccessibleTile) && !visited.contains(leftTile)) {
				visited.add(leftTile);
				
				if (validMove(moveNum+1, diceRoll, visited, targetTile)) {
					return true;
				}
				visited.pop();
			}
		}
		
		//can't go to invalid tiles or through walls
		if (lastTile.getX() < width-1 && !lastTile.hasRightWall()) {
			Tile rightTile = getTile(lastTile.getX()+1, lastTile.getY());
			
			//can't access inaccessible tiles or already visited tiles
			if (!(rightTile instanceof InaccessibleTile) && !visited.contains(rightTile)) {
				visited.add(rightTile);
				
				if (validMove(moveNum+1, diceRoll, visited, targetTile)) {
					return true;
				}
				visited.pop();
			}
		}
		
		return false;
	}
	
	/**
	 * Checks if a move to a room is valid
	 * @param diceRoll - the number of moves that can be used
	 * @param player - the player to move
	 * @param targetRoom - the room to move to
	 * @return
	 */
	public boolean isValidMove(int diceRoll, Player player, Room targetRoom) {
		Tile playerTile = player.getTile();
		
		Stack<Tile> visitedTiles = new Stack<Tile>();

		visitedTiles.add(playerTile);
		
		return validMove(0, diceRoll, visitedTiles, targetRoom);
	}
	
	/**
	 * Recursive method to determine whether a move to a room is valid
	 * @param moveNum
	 * @param diceRoll
	 * @param visited
	 * @return
	 */
	private boolean validMove(int moveNum, int diceRoll, Stack<Tile> visited, Room targetRoom) {
		Tile lastTile = visited.peek();
		
		//can get to a room early with extra moves to spare
		if (lastTile instanceof RoomTile) {
			if (((RoomTile)lastTile).getRoom() == targetRoom) {
				return true;
			}
		}
		
		//used all the moves
		if (moveNum == diceRoll) {
			return false;
		}
		
		//can't go to invalid tiles or through walls
		if (lastTile.getY() > 0 && !lastTile.hasUpWall()) {
			Tile upperTile = getTile(lastTile.getX(), lastTile.getY()-1);
			
			//can't access inaccessible tiles or already visited tiles
			if (!(upperTile instanceof InaccessibleTile) && !visited.contains(upperTile)) {
				visited.add(upperTile);
				
				if (validMove(moveNum+1, diceRoll, visited, targetRoom)) {
					return true;
				}
				visited.pop();
			}
		}
		
		//can't go to invalid tiles or through walls
		if (lastTile.getY() < height-1 && !lastTile.hasDownWall()) {
			Tile lowerTile = getTile(lastTile.getX(), lastTile.getY()+1);
			
			//can't access inaccessible tiles or already visited tiles
			if (!(lowerTile instanceof InaccessibleTile) && !visited.contains(lowerTile)) {
				visited.add(lowerTile);
				
				if (validMove(moveNum+1, diceRoll, visited, targetRoom)) {
					return true;
				}
				visited.pop();
			}
		}
		
		//can't go to invalid tiles or through walls
		if (lastTile.getX() > 0 && !lastTile.hasLeftWall()) {
			Tile leftTile = getTile(lastTile.getX()-1, lastTile.getY());
			
			//can't access inaccessible tiles or already visited tiles
			if (!(leftTile instanceof InaccessibleTile) && !visited.contains(leftTile)) {
				visited.add(leftTile);
				
				if (validMove(moveNum+1, diceRoll, visited, targetRoom)) {
					return true;
				}
				visited.pop();
			}
		}
		
		//can't go to invalid tiles or through walls
		if (lastTile.getX() < width-1 && !lastTile.hasRightWall()) {
			Tile rightTile = getTile(lastTile.getX()+1, lastTile.getY());
			
			//can't access inaccessible tiles or already visited tiles
			if (!(rightTile instanceof InaccessibleTile) && !visited.contains(rightTile)) {
				visited.add(rightTile);
				
				if (validMove(moveNum+1, diceRoll, visited, targetRoom)) {
					return true;
				}
				visited.pop();
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Tile getTile(int x, int y) {
		return board[x][y];
	}
	
	/**
	 * 
	 */
	public String toString() {
		String text = "";
		for (int y=0;y<height;y++) {
			text += (height-y);
			if (height-y < 10) {
				text += " ";
			}
			text += " ";
			for (int x=0;x<width;x++) {
				text += board[x][y];
			}
			text += "\n";
		}
		
		text += "   ";
		//add numbers on the bottom
		for (int i=0;i<width;i++) {
			if (i+1 < 10) {
				text += i+1;
			} else {
				text += (int)Math.floor((i+1)/10.0);
			}
		}
		text += "\n   ";
		for (int i=0;i<width;i++) {
			if (i+1 < 10) {
				text += " ";
			} else {
				text += (i+1)%10;
			}
		}
		
		return text;
	}
	
}
