package uno;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import cards.Card;
import frames.ColorChoose;

public class Game {

	/**
	 * Game class
	 * 
	 * Has properties and interactions of UNO game
	 * 
	 */

	private ArrayList<Card> pile_deck = new ArrayList<Card>();
	private ArrayList<Card> discard_deck = new ArrayList<Card>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private Player userPlayer;
	private boolean direction;
	private String validValue;
	private String validColor;
	private String sessionName;
	private int playerNumber;
	private String userName;
	private SecureRandom random = new SecureRandom();
	Scanner sc = new Scanner(System.in);

	public String getSessionName() {
		return sessionName;
	}

	public boolean isDirection() {
		return direction;
	}

	public ArrayList<Card> getPile_deck() {
		return pile_deck;
	}

	public String getPileNumber() {
		return Integer.toString(pile_deck.size());
	}

	/**
	 * Constructor of game class a new game can be generated or a saved game can be
	 * created and modified creates players and user player
	 * 
	 * @param sessionName
	 * @param player_number
	 * @param userName
	 * 
	 */

	public Game(String sessionName, int player_number, String userName) {
		this.sessionName = sessionName;
		this.playerNumber = player_number;
		this.userName = userName;
		direction = true;
		players.add(new Player(userName));
		for (int i = 1; i < player_number; i++) {
			players.add(new Player("Bot" + i));
		}
		userPlayer = players.get(0);
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void initializeGame() { // a method to initialize a new game. Distributes the cards and if first card is
									// a undesired card
		// reshuffles again

		UnoDeck deck = new UnoDeck();
		deck.shuffle();
		pile_deck = deck.getDeck();

		for (Player player : players) {
			for (int i = 1; i <= 7; i++) {
				player.drawCard(pile_deck.get(pile_deck.size() - 1));
				pile_deck.remove(pile_deck.size() - 1);
			}
		}
		discard_deck.add(pile_deck.get(pile_deck.size() - 1));
		pile_deck.remove(pile_deck.size() - 1);
		validColor = get_last_card(discard_deck).getColor();
		validValue = get_last_card(discard_deck).getValue();
		while (validColor.equals("Wild") || validValue.equals("Reverse") || validValue.equals("Skip")
				|| validValue.equals("Draw Two")) {
			reshuffle();
			discard_deck.add(pile_deck.get(pile_deck.size() - 1));
			pile_deck.remove(pile_deck.size() - 1);
			validColor = get_last_card(discard_deck).getColor();
			validValue = get_last_card(discard_deck).getValue();
		}

	}

	public Card get_last_card(ArrayList<Card> cards) { // Most interaction with discard deck and pile deck about last
														// card, its a shortcut.
		return cards.get(cards.size() - 1);
	}

	public ArrayList<Card> getDiscard_deck() {
		return discard_deck;
	}

	public void reshuffle() { // reshuffles discard deck and pile deck, if pile deck finishes it will used

		for (int i = discard_deck.size() - 1; i > 0; i--) {
			pile_deck.add(discard_deck.get(i));
			discard_deck.remove(i);
		}
		Collections.shuffle(pile_deck);
		discard_deck.add(get_last_card(pile_deck));
		pile_deck.remove(get_last_card(pile_deck));

	}
	// Because of the GUI, became a dead code.
	/*
	 * public void play() { int playerIndex = 5; int totalPenalty = 0; String
	 * validValue = this.get_last_card(discard_deck).getValue(); String validColor =
	 * this.get_last_card(discard_deck).getColor(); boolean skip = false; boolean
	 * isPenaltDrawn = false;
	 * 
	 * while (!this.isEnded() && playerIndex != 0) {
	 * 
	 * System.out.println(); System.out.println(players.get(playerIndex).getName() +
	 * "    " + get_last_card(discard_deck).toString());
	 * System.out.println(players.get(playerIndex).getCards_in_hands());
	 * 
	 * try { Thread.sleep(1000); } catch (InterruptedException e) {
	 * e.printStackTrace(); }
	 * 
	 * if(skip) { skip = false; validColor = get_last_card(discard_deck).getColor();
	 * validValue = "You can play";
	 * 
	 * } else { if (players.get(playerIndex).canPlay(validColor, validValue)) {
	 * discard_deck.add(players.get(playerIndex).playCard(players.get(playerIndex).
	 * playableCards(validColor, validValue).get(0))); } else { if (totalPenalty ==
	 * 0) totalPenalty += 1; else isPenaltDrawn = true;
	 * 
	 * for(int i = totalPenalty; i > 0; i--) { if(pile_deck.size() == 0)
	 * this.reshuffle();
	 * players.get(playerIndex).drawCard(get_last_card(pile_deck));
	 * pile_deck.remove(pile_deck.size()-1); } totalPenalty = 0; } if
	 * (!get_last_card(discard_deck).isDiscarded()) {
	 * 
	 * if(get_last_card(discard_deck).getValue() == "Draw Two") { totalPenalty += 2;
	 * } if(get_last_card(discard_deck).getValue() == "Draw Four") { totalPenalty +=
	 * 4; }
	 * 
	 * if(this.get_last_card(discard_deck).getValue() == "Reverse") direction =
	 * !direction;
	 * 
	 * if(get_last_card(discard_deck).getValue() == "Skip") { skip = true; }
	 * if(get_last_card(discard_deck).getColor() == "Wild") { validColor = "Green";
	 * validValue = get_last_card(discard_deck).getValue(); } else { validColor =
	 * get_last_card(discard_deck).getColor(); validValue =
	 * get_last_card(discard_deck).getValue(); }
	 * get_last_card(discard_deck).setDiscarded(true); }
	 * 
	 * if (isPenaltDrawn) { validValue = "You can play"; isPenaltDrawn = false; }
	 * 
	 * } get_last_card(discard_deck).setDiscarded(true);
	 * System.out.println(players.get(playerIndex).getCards_in_hands());
	 * System.out.println();
	 * newGame.updateLastCard(get_last_card(discard_deck).toString()); if(direction)
	 * if(playerIndex == players.size() - 1) playerIndex = 0; else playerIndex ++;
	 * else if(playerIndex == 0) playerIndex = players.size() - 1; else playerIndex
	 * --; } }
	 */
	// Because of the GUI, became a dead code.

	/**
	 * draw card method checks is there a card in pile deck returns player index of
	 * next player
	 * 
	 * @param playerIndex
	 * @return
	 */

	public int drawCard(int playerIndex) {
		if (pile_deck.size() == 0)
			this.reshuffle();
		players.get(playerIndex).drawCard(get_last_card(pile_deck));
		pile_deck.remove(pile_deck.size() - 1);

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(".\\Logs\\" + sessionName + ".txt", true));
			writer.append(players.get(playerIndex).getName() + " drawn a card" + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return turn(playerIndex);
	}

	public Player getUserPlayer() {
		return userPlayer;
	}

	public void setUserPlayer(Player userPlayer) {
		this.userPlayer = userPlayer;
	}

	public String getValidValue() {
		return validValue;
	}

	public void setValidValue(String validValue) {
		this.validValue = validValue;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getValidColor() {
		return validColor;
	}

	public void setPile_deck(ArrayList<Card> pile_deck) {
		this.pile_deck = pile_deck;
	}

	public void setDiscard_deck(ArrayList<Card> discard_deck) {
		this.discard_deck = discard_deck;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
	}

	public int turn(int playerIndex) { // returns player index of next player according to direction
		if (direction)
			if (playerIndex == players.size() - 1)
				playerIndex = 0;
			else
				playerIndex++;
		else if (playerIndex == 0)
			playerIndex = players.size() - 1;
		else
			playerIndex--;
		return playerIndex;
	}

	public boolean isEnded() { // to find out game is ended
		for (Player player : players) {
			if (player.getSizeofHand() == 0)
				return true;
		}
		return false;
	}

	public String winner() { // returns winner
		for (Player player : players) {
			if (player.getSizeofHand() == 0)
				return player.getName();
		}
		return "No winner";
	}

	public ArrayList<Card> userHand() {
		return players.get(0).getCards_in_hands();
	}

	/**
	 * Play method for bot players If a bot can play a card plays randomly among
	 * playable cards; if cannot, draws a card Cards effects applied immediately
	 * after played card, valid color and values determined Returns player index of
	 * next player
	 * 
	 * @param playerIndex
	 * @return
	 */

	public int play(int playerIndex) {

		if (players.get(playerIndex).canPlay(validColor, validValue)) {

			int decision = random.nextInt(players.get(playerIndex).playableCards(validColor, validValue).size()); 
			// decides a card randomly among playable cards
			discard_deck.add(players.get(playerIndex)
					.playCard(players.get(playerIndex).playableCards(validColor, validValue).get(decision)));

			validValue = get_last_card(discard_deck).getValue();
			validColor = get_last_card(discard_deck).getColor();

			if (players.get(playerIndex).getSizeofHand() == 1) { // Uno decision, %96 says uno
				int probability = random.nextInt(0, 100);
				if (probability < 96) {
					players.get(playerIndex).setDeclaredUNO(true);
					try {
						BufferedWriter writer = new BufferedWriter(
								new FileWriter(".\\Logs\\" + sessionName + ".txt", true));
						writer.append(players.get(playerIndex).getName() + "said UNO.\n");
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(".\\Logs\\" + sessionName + ".txt", true));
				writer.append(players.get(playerIndex).getName() + " played " + validColor + " " + validValue + "\n");
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (validValue.equals("Skip")) { // skips next player

				try {
					BufferedWriter writer = new BufferedWriter(
							new FileWriter(".\\Logs\\" + sessionName + ".txt", true));
					writer.append(players.get(turn(playerIndex)).getName() + " was skipped" + "\n");
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

				return turn(turn(playerIndex));
			}

			else if (validValue.equals("Draw Two")) { // next player draws two card and skips they turn

				drawCard(turn(playerIndex));
				drawCard(turn(playerIndex));
				return turn(turn(playerIndex));
			}

			else if (validValue.equals("Draw Four")) { // next player draws four card and skips they turn

				drawCard(turn(playerIndex));
				drawCard(turn(playerIndex));
				drawCard(turn(playerIndex));
				drawCard(turn(playerIndex));
				validColor = colorChoosing(); // player choose color

				try {
					BufferedWriter writer = new BufferedWriter(
							new FileWriter(".\\Logs\\" + sessionName + ".txt", true));
					writer.append(
							players.get(playerIndex).getName() + " chosed " + validColor + " as a valid color" + "\n");
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

				return turn(turn(playerIndex));
			}

			else if (validValue.equals("Wild")) { // player choose color
				validColor = colorChoosing();

				try {
					BufferedWriter writer = new BufferedWriter(
							new FileWriter(".\\Logs\\" + sessionName + ".txt", true));
					writer.append(
							players.get(playerIndex).getName() + " chosed " + validColor + " as a valid color" + "\n");
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			else if (validValue.equals("Reverse")) { // direction changer
				direction = !direction;

				try {
					BufferedWriter writer = new BufferedWriter(
							new FileWriter(".\\Logs\\" + sessionName + ".txt", true));
					writer.append("Direction changed " + (direction ? "clockwise" : "counterclockwise")
							+ " is new direction" + "\n");
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			return turn(playerIndex);

		} else { // if bot cannot play, draws a card

			drawCard(playerIndex);
			return turn(playerIndex);
		}
	}

	/**
	 * Same as bot play method, except user can choose what they going to play Takes
	 * a integer to decide which card will be played Creates new frame to decide
	 * color
	 * 
	 * @param cardIndex
	 * @return
	 */

	public int userPlay(int cardIndex) {
		int playerIndex = 0;

		discard_deck
				.add(players.get(playerIndex).playCard(players.get(playerIndex).getCards_in_hands().get(cardIndex)));
		validValue = get_last_card(discard_deck).getValue();
		validColor = get_last_card(discard_deck).getColor();

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(".\\Logs\\" + sessionName + ".txt", true));
			writer.append(players.get(playerIndex).getName() + " played " + validColor + " " + validValue + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (validValue.equals("Skip")) {

			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(".\\Logs\\" + sessionName + ".txt", true));
				writer.append(players.get(turn(playerIndex)).getName() + " was skipped" + "\n");
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return turn(turn(playerIndex));
		}

		else if (validValue.equals("Draw Two")) {

			drawCard(turn(playerIndex));
			drawCard(turn(playerIndex));
			return turn(turn(playerIndex));
		}

		else if (validValue.equals("Draw Four")) {

			drawCard(turn(playerIndex));
			drawCard(turn(playerIndex));
			drawCard(turn(playerIndex));
			drawCard(turn(playerIndex));
			new ColorChoose(this); // new frame to choose color

			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(".\\Logs\\" + sessionName + ".txt", true));
				writer.append(
						players.get(playerIndex).getName() + " chosed " + validColor + " as a valid color" + "\n");
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return turn(turn(playerIndex));
		}

		else if (validValue.equals("Wild")) {
			new ColorChoose(this);

			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(".\\Logs\\" + sessionName + ".txt", true));
				writer.append(
						players.get(playerIndex).getName() + " chosed " + validColor + " as a valid color" + "\n");
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		else if (validValue.equals("Reverse")) {
			direction = !direction;

			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(".\\Logs\\" + sessionName + ".txt", true));
				writer.append("Direction changed " + (direction ? "clockwise" : "counterclockwise")
						+ " is new direction" + "\n");
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return turn(playerIndex);
	}

	public String colorChoosing() { // bot's color choosing algorithm
		int color = random.nextInt(1, 5);
		if (color == 1)
			return "Yellow";
		else if (color == 2)
			return "Green";
		else if (color == 3)
			return "Blue";
		else
			return "Red";
	}

	public ArrayList<Card> playableCards() {
		return players.get(0).playableCards(validColor, validValue);
	}

	public void setValidColor(String validColor) {
		this.validColor = validColor;
	}

	public void saveGame(int playerIndex, Account account) { // Saves game informations to user's directory with session
																// name.

		try {
			BufferedWriter writer = new BufferedWriter(
					new FileWriter(".\\SavedGames\\" + account.getUserName() + "\\" + sessionName + ".txt", true));
			writer.append(sessionName + "\n");
			writer.append(playerNumber + "\n");
			writer.append(playerIndex + "\n");
			writer.append(userName + "\n");
			writer.append(direction + "\n");
			writer.append(validColor + "\n");
			writer.append(validValue + "\n");
			writer.append(pile_deck + "\n");
			writer.append(discard_deck + "\n");
			for (Player player : players) { // players' informations
				writer.append(player.getName() + "\n");
				writer.append(player.getCards_in_hands() + "\n");
				writer.append(player.isUNO() + "\n");
				writer.append(player.isDeclaredUNO() + "\n");
			}
			writer.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
