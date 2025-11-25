package cards;

public abstract class Card {

	/**
	 * Card Abstract Class
	 */

	protected int point;
	protected final String color;
	protected final String value;

	/**
	 * Abstract Card Constructor
	 * 
	 * @param color
	 * @param value
	 * 
	 */

	public Card(String color, String value) {
		this.color = color;
		this.value = value;
	}

	/**
	 * A method for decide this card can be played in this tour.
	 * 
	 * @param validColor
	 * @param validValue
	 * @return
	 */

	public boolean playable(String validColor, String validValue) {
		if (validColor.equals(color) || color == "Wild")
			return true;
		else if (validValue.equals(value))
			return true;
		else
			return false;
	}

	public int getPoint() {
		return point;
	}

	public String getColor() {
		return color;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return color + " " + value;
	}

}
