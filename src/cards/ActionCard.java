package cards;

public class ActionCard extends Card {

	/**
	 * Class and Constructor for Action Cards
	 * 
	 * @param colour
	 * @param value
	 */

	public ActionCard(String colour, String value) {
		super(colour, value);
		this.point = 20;
	}
}
