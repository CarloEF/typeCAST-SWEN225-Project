package swen225.cluedo;

import java.util.List;
import java.util.ArrayList;

public class Player implements CluedoObject {
	private List<Card> hand;
	private String name;
	private Tile tile;
	
	/**
	 * 
	 * @param name
	 * @param tile
	 */
	public Player(String name) {
		this.name = name;
		this.hand = new ArrayList<Card>();
		this.tile = null;
	}
	
	/**
	 * 
	 * @param card
	 */
	public void addCard(Card card) {
		this.hand.add(card);
	}
	
	/**
	 * 
	 * @param newTile
	 */
	public void moveToTile(Tile newTile) {
		if (tile != null) {
			tile.setObject(null);
		}
		newTile.setObject(this);
		tile = newTile;
	}
	
	/**
	 * 
	 */
	public Tile getTile() {
		return tile;
	}
	
	/**
	 * 
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Moves the player to the room
	 * @param room - the room to move to
	 */
	public void moveToRoom(Room room) {
		Tile newTile = room.getFreeTile();
		
		moveToTile(newTile);
	}
}
