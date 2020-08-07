public class Suggestion {

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	private WeaponCard weapon;
	private CharacterCard character;
	private RoomCard room;
	
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