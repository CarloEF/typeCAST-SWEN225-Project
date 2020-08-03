package swen225.cluedo;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

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
					tile = new InaccessibleTile(up, down, left, right, x, y);
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
	 * 
	 * @param diceRoll
	 * @param player
	 * @param newX
	 * @param newY
	 * @return
	 */
	public boolean isValidMove(int diceRoll, Player player, int newX, int newY) {
		return true;
	}
	
	/**
	 * 
	 * @param diceRoll
	 * @param player
	 * @param room
	 * @return
	 */
	public boolean isValidMove(int diceRoll, Player player, Room room) {
		return true;
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
		
		text += " ";
		//add numbers on the bottom
		for (int i=0;i<width;i++) {
			if (i+1 < 10) {
				text += i+1;
			} else {
				text += (int)Math.floor((i+1)/10.0);
			}
		}
		text += "\n ";
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
