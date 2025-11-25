package cards;

public class WildCard extends Card {

	/**
	 * Class and Constructor for Wild Cards
	 * 
	 * @param colour
	 * @param value
	 */

	public WildCard(String colour, String value) {
		super(colour, value);
		this.point = 50;
	}

}
