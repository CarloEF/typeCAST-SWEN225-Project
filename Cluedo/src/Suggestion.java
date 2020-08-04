public class Suggestion {

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	WeaponCard weapon;
	CharacterCard character;
	RoomCard room;
	
	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Suggestion(WeaponCard weapon, CharacterCard character, RoomCard room) {
		this.weapon = weapon;
		this.character = character;
		this.room = room;
	}

	//------------------------
	// INTERFACE
	//------------------------

	public WeaponCard getWeapon() {
		return this.weapon;
	}
	public CharacterCard getCharacter() {
		return this.character;
	}
	public RoomCard getRoom() {
		return this.room;
	}
}