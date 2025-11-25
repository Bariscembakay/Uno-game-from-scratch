package uno;

import java.util.ArrayList;
import java.util.Collections;

import cards.ActionCard;
import cards.Card;
import cards.NumberCard;
import cards.WildCard;

public class UnoDeck {

	/**
	 * Creates a Uno Deck class and can shuffle
	 */

	private ArrayList<Card> deck = new ArrayList<Card>();
	private String[] colours = { "Red", "Yellow", "Blue", "Green" };

	public UnoDeck() { // Generates cards and creates deck

		for (String colour : colours) {
			deck.add(new NumberCard(colour, 0));

			for (int i = 1; i <= 9; i++) {
				deck.add(new NumberCard(colour, i));
				deck.add(new NumberCard(colour, i));
			}

			for (int i = 1; i <= 2; i++) {
				deck.add(new ActionCard(colour, "Draw Two"));
				deck.add(new ActionCard(colour, "Reverse"));
				deck.add(new ActionCard(colour, "Skip"));
			}
		}

		for (int i = 1; i <= 4; i++) {
			deck.add(new WildCard("Wild", "Draw Four"));
			deck.add(new WildCard("Wild", "Wild"));
		}
	}

	public void shuffle() {
		Collections.shuffle(deck);
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

}
