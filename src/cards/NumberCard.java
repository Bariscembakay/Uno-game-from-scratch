package cards;

public class NumberCard extends Card {

	/**
	 * Class and Constructor for Number Cards
	 * 
	 * @param colour
	 * @param value
	 */

	public NumberCard(String colour, int value) {
		super(colour, Integer.toString(value));
		this.point = value;
	}
}
