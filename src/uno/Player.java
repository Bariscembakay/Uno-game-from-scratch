package uno;

import java.util.ArrayList;

import cards.Card;

public class Player {

	/**
	 * Player Class Which holds player informations such cards and Uno conditions
	 * Has method to interact with cards
	 * 
	 */

	private ArrayList<Card> cards_in_hands = new ArrayList<Card>();
	private String name;
	private boolean UNO;
	private boolean declaredUNO;

	public Player(String name) {
		this.name = name;
		this.UNO = false;
		this.declaredUNO = false;
	}

	public void drawCard(Card card) { // player draws card and that's why loses Uno conditions
		setUNO(false);
		setDeclaredUNO(false);
		cards_in_hands.add(card);
	}

	public Card playCard(Card card) { // player play a card and returns it
		cards_in_hands.remove(card);
		if (this.getSizeofHand() == 1)
			setUNO(true);
		return card;
	}

	public boolean isDeclaredUNO() {
		return declaredUNO;
	}

	public void setDeclaredUNO(boolean declaredUNO) {
		this.declaredUNO = declaredUNO;
	}

	public boolean isUNO() {
		return UNO;
	}

	public void setUNO(boolean uNO) {
		UNO = uNO;
	}

	public ArrayList<Card> getCards_in_hands() {
		return cards_in_hands;
	}

	public void setCards_in_hands(ArrayList<Card> cards_in_hands) {
		this.cards_in_hands = cards_in_hands;
	}

	public String getName() {
		return name;
	}

	public int getSizeofHand() {
		return cards_in_hands.size();
	}

	/**
	 * To see which cards can be played. Uses Card's playable method.
	 * 
	 * @param validColor
	 * @param validValue
	 * @return
	 */

	public ArrayList<Card> playableCards(String validColor, String validValue) {
		ArrayList<Card> playableCards = new ArrayList<Card>();

		for (Card card : cards_in_hands) {
			if (card.playable(validColor, validValue))
				playableCards.add(card);
		}
		return playableCards;
	}

	/**
	 * To decide whether player can play a card or not
	 * 
	 * @param validColor
	 * @param validValue
	 * @return
	 * 
	 */

	public boolean canPlay(String validColor, String validValue) {
		if (this.playableCards(validColor, validValue).size() != 0)
			return true;
		else
			return false;
	}

	public int getPointsInHand() { // To calculated result in the end of game
		int total = 0;
		for (Card card : cards_in_hands) {
			total += card.getPoint();
		}
		return total;
	}
}
