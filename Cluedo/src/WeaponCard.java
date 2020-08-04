public class WeaponCard extends Card {

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public WeaponCard(String name)
	{
		super(name);
	}

	//------------------------
	// INTERFACE
	//------------------------

	public String toString() {
		if (this.getCardName() == "Candlestick") {
			return "c";
		}
		else if (this.getCardName() == "Dagger") {
			return "d";
		}
		else if (this.getCardName() == "Lead Pipe") {
			return "l";
		}
		else if (this.getCardName() == "Revolver") {
			return "r";
		}
		else if (this.getCardName() == "Rope") {
			return "r";
		}
		else if (this.getCardName() == "Spanner") {
			return "s";
		}
		else return null; // Should be unreachable: Only 6 weapons.

	}

}