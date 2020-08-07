package swen225.cluedo;

import java.util.List;
import java.util.ArrayList;

public class Player implements CluedoObject {
	private List<Card> hand;
	private String name;
	private String tileValue;
	private Tile tile;
	
	/**
	 * 
	 * @param name
	 * @param tile
	 */
	public Player(String name, String tileValue) {
		this.name = name;
		this.tileValue = tileValue;
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
	 * @param murderSugg
	 * @param weaponSugg
	 * @param roomSugg
	 * @return
	 */
	public List<Card> getRefutes(Player murdererSugg, Weapon weaponSugg, Room roomSugg) {
		List<Card> refuteCards = new ArrayList<Card>();
		
		for (Card card : hand) {
			if (card == murdererSugg || card == weaponSugg || card == roomSugg) {
				refuteCards.add(card);
			}
		}
		
		return refuteCards;
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
	
	public List<Card> getCards() {
		return hand;
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
	
	public String toString() {
		return tileValue;
	}
}
