import java.util.ArrayList;

public class Suggestion {

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	private CharacterCard character;
	private WeaponCard weapon;
	private RoomCard room;
	ArrayList<Card> cardList;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Suggestion(CharacterCard character, WeaponCard weapon, RoomCard room) {
		this.character = character;
		this.weapon = weapon;
		this.room = room;

		this.cardList = new ArrayList<Card>();
		this.cardList.add(character);
		this.cardList.add(weapon);
		this.cardList.add(room);
	}

	//------------------------
	// INTERFACE
	//------------------------

	public CharacterCard getCharacter() {
		return this.character;
	}
	public WeaponCard getWeapon() {
		return this.weapon;
	}
	public RoomCard getRoom() {
		return this.room;
	}
	public String getCardList() {
		String temp = "\n+"+"0: "+this.character.getCardName();
		temp += "\n"+"1: "+this.weapon.getCardName();
		temp += "\n"+"2: "+this.room.getCardName();
		return temp;
	}

}