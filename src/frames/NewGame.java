package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uno.Account;
import uno.Game;
import uno.Player;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class NewGame extends JFrame {
	
	/**
	 * Game Frame Class
	 * Users can see session name, direction, other players names, other players card number,
	 * number of cards in pile, which cards can be played, player who has turn.
	 * 
	 * Users can interact with cards, UNO button, UNO alert button, pile deck
	 * 
	 * Users can save game to continue after
	 * 
	 * I did not use a pause button, because i used next button to continue game so if user do not press "next"
	 * game will be paused already.
	 * 
	 * if game finishes, a new frame will open which displays winner and they score.
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int player_num;
	private Account account;
	private ArrayList<Player> players;
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	private ArrayList<JLabel> playersJLabels = new ArrayList<JLabel>();
	private ArrayList<JLabel> countJLabels = new ArrayList<JLabel>();
	private ArrayList<JLabel> UnoJLabels = new ArrayList<JLabel>();
	private Game game;
	private JLabel lastCard;
	private int playerIndex;
	private JLabel nameOfPlayer;
	private JButton drawPile;
	private String sessionName;
	private JLabel directionJLabel;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game game = new Game("", 10, "");
					game.initializeGame();
					NewGame frame = new NewGame(new Account("b", "a", "0", "0", "0", "0"), game, 0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	/**
	 * 
	 * @param account (which user playing)
	 * @param game (game object in background)
	 * @param playerI (player index to decide turn)
	 * 
	 */
	
	public NewGame(Account account, Game game, int playerI) {
		this.sessionName = game.getSessionName();
		this.player_num = game.getPlayerNumber();
		this.account = account;
		this.playerIndex = playerI;
		this.game = game;
		players = game.getPlayers();

		setTitle("UNO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lastCard = new JLabel();
		lastCard.setOpaque(true);
		this.lastCard = lastCard;
		lastCard.setHorizontalAlignment(SwingConstants.CENTER);
		lastCard.setBackground(new Color(0, 255, 0));
		lastCard.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lastCard.setBounds(334, 217, 123, 204);
		contentPane.add(lastCard);

		JButton b1 = new JButton(); // to play first card for user
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerIndex = game.userPlay(0);
				updateChanges();
				isWinner(0);
				uptadeButtons();
				makeUnable();
			}
		});
		b1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		b1.setForeground(Color.BLACK);
		b1.setBounds(10, 616, 60, 90);
		contentPane.add(b1);

		JButton b2 = new JButton();
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerIndex = game.userPlay(1);
				updateChanges();
				uptadeButtons();
			}
		});
		b2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		b2.setForeground(new Color(0, 0, 0));
		b2.setBounds(80, 616, 60, 90);
		contentPane.add(b2);

		JButton b3 = new JButton();
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerIndex = game.userPlay(2);
				updateChanges();
				isWinner(0);
				uptadeButtons();
			}
		});
		b3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		b3.setForeground(new Color(0, 0, 0));
		b3.setBounds(150, 616, 60, 90);
		contentPane.add(b3);

		JButton b4 = new JButton();
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerIndex = game.userPlay(3);
				updateChanges();
				isWinner(0);
				uptadeButtons();
			}
		});
		b4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		b4.setForeground(new Color(0, 0, 0));
		b4.setBounds(220, 616, 60, 90);
		contentPane.add(b4);

		JButton b5 = new JButton();
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerIndex = game.userPlay(4);
				updateChanges();
				isWinner(0);
				uptadeButtons();
			}
		});
		b5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		b5.setForeground(new Color(0, 0, 0));
		b5.setBounds(290, 616, 60, 90);
		contentPane.add(b5);

		JButton b6 = new JButton();
		b6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerIndex = game.userPlay(5);
				updateChanges();
				isWinner(0);
				uptadeButtons();
			}
		});
		b6.setForeground(new Color(0, 0, 0));
		b6.setBounds(360, 616, 60, 90);
		contentPane.add(b6);

		JButton b7 = new JButton();
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerIndex = game.userPlay(6);
				updateChanges();
				isWinner(0);
				uptadeButtons();
			}
		});
		b7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		b7.setForeground(new Color(0, 0, 0));
		b7.setBounds(430, 616, 60, 90);
		contentPane.add(b7);

		JButton b8 = new JButton("");
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerIndex = game.userPlay(7);
				updateChanges();
				isWinner(0);
				uptadeButtons();
			}
		});
		b8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		b8.setForeground(new Color(0, 0, 0));
		b8.setBounds(500, 616, 60, 90);
		contentPane.add(b8);

		JButton b9 = new JButton("");
		b9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		b9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerIndex = game.userPlay(8);
				updateChanges();
				isWinner(0);
				uptadeButtons();
			}
		});
		b9.setForeground(new Color(0, 0, 0));
		b9.setBounds(570, 616, 60, 90);
		contentPane.add(b9);

		JButton b10 = new JButton("");
		b10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		b10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerIndex = game.userPlay(9);
				isWinner(0);
				updateChanges();
				uptadeButtons();
			}
		});
		b10.setForeground(new Color(0, 0, 0));
		b10.setBounds(640, 616, 60, 90);
		contentPane.add(b10);

		JButton b11 = new JButton("");
		b11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		b11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerIndex = game.userPlay(10);
				updateChanges();
				uptadeButtons();
			}
		});
		b11.setForeground(new Color(0, 0, 0));
		b11.setBounds(710, 616, 60, 90);
		contentPane.add(b11);

		JLabel TURN = new JLabel("TURN :");
		TURN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		TURN.setBounds(68, 82, 60, 36);
		contentPane.add(TURN);

		JLabel nameOfPlayer = new JLabel(game.getPlayers().get(playerIndex).getName());
		this.nameOfPlayer = nameOfPlayer;
		nameOfPlayer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameOfPlayer.setBounds(172, 82, 180, 36);
		contentPane.add(nameOfPlayer);

		JButton unoAlert = new JButton("UNO ALERT"); // to alert a bots Uno if they did not say Uno.
		unoAlert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean alert = false;
				for (Player player : players) {
					if (player.isUNO() && !player.isDeclaredUNO()) {
						alert = true;

						try {
							BufferedWriter writer = new BufferedWriter(
									new FileWriter(".\\Logs\\" + sessionName + ".txt", true));
							writer.append(player.getName() + " did not say UNO and alerted\n");
							writer.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}

						if (game.getPile_deck().size() == 0)
							game.reshuffle();
						player.drawCard(game.get_last_card(game.getPile_deck()));
						game.getPile_deck().remove(game.getPile_deck().size() - 1);
						updateChanges();
					}
				}
				if (!alert) {
					if (game.getPile_deck().size() == 0)
						game.reshuffle();
					players.get(0).drawCard(game.get_last_card(game.getPile_deck()));
					game.getPile_deck().remove(game.getPile_deck().size() - 1);
				}
			}
		});
		unoAlert.setBounds(10, 544, 130, 36);
		contentPane.add(unoAlert);

		JButton ContinueButton = new JButton("NEXT"); // to bots moves and continue the game
		ContinueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (players.get(0).isUNO() && !players.get(playerI).isDeclaredUNO()) {
					try {
						BufferedWriter writer = new BufferedWriter(
								new FileWriter(".\\Logs\\" + sessionName + ".txt", true));
						writer.append(account.getUserName() + " did not say UNO and alerted\n");
						game.drawCard(0);
						writer.close();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				int currentPlayer = playerIndex;
				if (playerIndex != 0) {
					playerIndex = game.play(playerIndex);
					updateChanges();
					uptadeButtons();
					makeUnable();
				} else {
					updateChanges();
					makeEnable();
				}
				isWinner(currentPlayer);
			}
		});
		ContinueButton.setBounds(640, 544, 130, 36);
		contentPane.add(ContinueButton);

		JButton SAVEGAME = new JButton("SAVE GAME"); // this button calls save method from game class
		SAVEGAME.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.saveGame(playerIndex, account);
				new MainMenu(account);
				dispose();
			}
		});
		SAVEGAME.setBounds(640, 504, 130, 36);
		contentPane.add(SAVEGAME);

		JLabel lblNewLabel = new JLabel("PLAYER"); // Labels to display Card Numbers and Players names
		lblNewLabel.setBounds(552, 68, 107, 13);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("CARD Count");
		lblNewLabel_2.setBounds(677, 68, 99, 13);
		contentPane.add(lblNewLabel_2);

		JLabel p1 = new JLabel("New label");
		p1.setBounds(552, 100, 110, 13);
		contentPane.add(p1);

		JLabel p2 = new JLabel("New label");
		p2.setBounds(552, 120, 110, 13);
		contentPane.add(p2);

		JLabel p3 = new JLabel("New label");
		p3.setBounds(552, 140, 110, 13);
		contentPane.add(p3);

		JLabel p4 = new JLabel("New label");
		p4.setBounds(552, 160, 110, 13);
		contentPane.add(p4);

		JLabel p5 = new JLabel("New label");
		p5.setBounds(552, 180, 110, 13);
		contentPane.add(p5);

		JLabel p6 = new JLabel("New label");
		p6.setBounds(552, 200, 110, 13);
		contentPane.add(p6);

		JLabel p7 = new JLabel("New label");
		p7.setBounds(552, 220, 110, 13);
		contentPane.add(p7);

		JLabel p8 = new JLabel("New label");
		p8.setBounds(552, 240, 110, 13);
		contentPane.add(p8);

		JLabel p9 = new JLabel("New label");
		p9.setBounds(552, 260, 110, 13);
		contentPane.add(p9);

		JLabel p10 = new JLabel("New label");
		p10.setBounds(552, 280, 110, 13);
		contentPane.add(p10);

		JLabel c1 = new JLabel("New label");
		c1.setBounds(677, 100, 45, 13);
		contentPane.add(c1);

		JLabel c2 = new JLabel("New label");
		c2.setBounds(677, 120, 45, 13);
		contentPane.add(c2);

		JLabel c3 = new JLabel("New label");
		c3.setBounds(677, 140, 45, 13);
		contentPane.add(c3);

		JLabel c4 = new JLabel("New label");
		c4.setBounds(677, 160, 45, 13);
		contentPane.add(c4);

		JLabel c5 = new JLabel("New label");
		c5.setBounds(677, 180, 45, 13);
		contentPane.add(c5);

		JLabel c6 = new JLabel("New label");
		c6.setBounds(677, 200, 45, 13);
		contentPane.add(c6);

		JLabel c7 = new JLabel("New label");
		c7.setBounds(677, 220, 45, 13);
		contentPane.add(c7);

		JLabel c8 = new JLabel("New label");
		c8.setBounds(677, 240, 45, 13);
		contentPane.add(c8);

		JLabel c9 = new JLabel("New label");
		c9.setBounds(677, 260, 45, 13);
		contentPane.add(c9);

		JLabel c10 = new JLabel("New label");
		c10.setBounds(677, 280, 45, 13);
		contentPane.add(c10);

		JButton drawpile = new JButton(game.getPileNumber()); //draw pile button which displays number of cards in deck 
		drawpile.addActionListener(new ActionListener() {     //and can be drawn card 
			public void actionPerformed(ActionEvent e) {
				playerIndex = game.drawCard(playerIndex);
				updateChanges();
				uptadeButtons();
			}
		});
		this.drawPile = drawpile;
		drawpile.setBounds(201, 217, 123, 204);
		contentPane.add(drawpile);

		JButton UNO = new JButton("UNO"); // Uno button to declare UNO
		UNO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedWriter writer = new BufferedWriter(
							new FileWriter(".\\Logs\\" + sessionName + ".txt", true));
					writer.append(players.get(playerIndex).getName() + "said UNO.\n");
					writer.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				if (players.get(0).getSizeofHand() == 2) {
					players.get(0).setDeclaredUNO(true);
				} else {
					if (game.getPile_deck().size() == 0)
						game.reshuffle();
					players.get(0).drawCard(game.get_last_card(game.getPile_deck()));
					game.getPile_deck().remove(game.getPile_deck().size() - 1);
				}
			}
		});
		UNO.setBounds(10, 504, 130, 36);
		contentPane.add(UNO);

		JLabel lblNewLabel_1 = new JLabel("Session Name : " + sessionName);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 10, 340, 52);
		contentPane.add(lblNewLabel_1);

		JLabel lblDrecton = new JLabel("DIRECTION :");
		lblDrecton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDrecton.setBounds(68, 128, 133, 36);
		contentPane.add(lblDrecton);

		JLabel directionLabel = new JLabel("Clockwise");
		directionLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		directionLabel.setBounds(172, 126, 180, 36);
		contentPane.add(directionLabel);
		this.directionJLabel = directionLabel;
		
		JButton b12 = new JButton("");
		b12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerIndex = game.userPlay(11);
				updateChanges();
				isWinner(0);
				uptadeButtons();	
			}
		});
		b12.setForeground(Color.BLACK);
		b12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		b12.setBounds(150, 504, 60, 90);
		contentPane.add(b12);
		
		JButton b13 = new JButton("");
		b13.setForeground(Color.BLACK);
		b13.setFont(new Font("Tahoma", Font.PLAIN, 15));
		b13.setBounds(220, 504, 60, 90);
		contentPane.add(b13);
		b13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerIndex = game.userPlay(12);
				updateChanges();
				isWinner(0);
				uptadeButtons();	
			}
		});
		
		JButton b14 = new JButton("");
		b14.setForeground(Color.BLACK);
		b14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		b14.setBounds(290, 504, 60, 90);
		contentPane.add(b14);
		b14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerIndex = game.userPlay(13);
				updateChanges();
				isWinner(0);
				uptadeButtons();	
			}
		});
		
		JButton b15 = new JButton("");
		b15.setForeground(Color.BLACK);
		b15.setFont(new Font("Tahoma", Font.PLAIN, 15));
		b15.setBounds(360, 504, 60, 90);
		contentPane.add(b15);
		b15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerIndex = game.userPlay(14);
				updateChanges();
				isWinner(0);
				uptadeButtons();	
			}
		});
		
		JButton b16 = new JButton("");
		b16.setForeground(Color.BLACK);
		b16.setFont(new Font("Tahoma", Font.PLAIN, 15));
		b16.setBounds(430, 504, 60, 90);
		contentPane.add(b16);
		b16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerIndex = game.userPlay(15);
				updateChanges();
				isWinner(0);
				uptadeButtons();	
			}
		});
		
		JButton b17 = new JButton("");
		b17.setForeground(Color.BLACK);
		b17.setFont(new Font("Tahoma", Font.PLAIN, 15));
		b17.setBounds(500, 504, 60, 90);
		contentPane.add(b17);
		b17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerIndex = game.userPlay(16);
				updateChanges();
				isWinner(0);
				uptadeButtons();	
			}
		});
		
		JButton b18 = new JButton("");
		b18.setForeground(Color.BLACK);
		b18.setFont(new Font("Tahoma", Font.PLAIN, 15));
		b18.setBounds(570, 504, 60, 90);
		contentPane.add(b18);
		
		JLabel u10 = new JLabel("New label");
		u10.setBounds(732, 280, 45, 13);
		contentPane.add(u10);
		
		JLabel u9 = new JLabel("New label");
		u9.setBounds(732, 260, 45, 13);
		contentPane.add(u9);
		
		JLabel u8 = new JLabel("New label");
		u8.setBounds(732, 240, 45, 13);
		contentPane.add(u8);
		
		JLabel u7 = new JLabel("New label");
		u7.setBounds(732, 220, 45, 13);
		contentPane.add(u7);
		
		JLabel u6 = new JLabel("New label");
		u6.setBounds(732, 200, 45, 13);
		contentPane.add(u6);
		
		JLabel u5 = new JLabel("New label");
		u5.setBounds(732, 180, 45, 13);
		contentPane.add(u5);
		
		JLabel u4 = new JLabel("New label");
		u4.setBounds(732, 160, 45, 13);
		contentPane.add(u4);
		
		JLabel u3 = new JLabel("New label");
		u3.setBounds(732, 140, 45, 13);
		contentPane.add(u3);
		
		JLabel u2 = new JLabel("New label");
		u2.setBounds(732, 120, 45, 13);
		contentPane.add(u2);
		
		JLabel u1 = new JLabel("New label");
		u1.setBounds(732, 100, 45, 13);
		contentPane.add(u1);
		b18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerIndex = game.userPlay(17);
				updateChanges();
				isWinner(0);
				uptadeButtons();	
			}
		});

		JLabel[] UnoLabels = { u1, u2, u3, u4, u5, u6, u7, u8, u9, u10 };
		JLabel[] playersLabels = { p1, p2, p3, p4, p5, p6, p7, p8, p9, p10 };
		JLabel[] countLabels = { c1, c2, c3, c4, c5, c6, c7, c8, c9, c10 };
		JButton[] buttonsArray = { b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18 };
		
		for(JLabel label : UnoLabels) {
			this.UnoJLabels.add(label);
		}

		for (JLabel player : playersLabels) { // to add buttons, labels, to array lists
			this.playersJLabels.add(player);
		}
		for (JLabel count : countLabels) {
			this.countJLabels.add(count);
		}
		for (JButton button : buttonsArray) {
			buttons.add(button);
		}
		
		for (int i = 0; i < player_num; i++) { // to display players name and card numbers 
			countJLabels.get(i).setText(Integer.toString(game.getPlayers().get(i).getSizeofHand()));
			playersJLabels.get(i).setText(game.getPlayers().get(i).getName());
		}

		for (int i = player_num; i < 10; i++) {
			countJLabels.get(i).setText("");
			playersJLabels.get(i).setText("");
			UnoJLabels.get(i).setText("");
		}

		updateChanges(); // to update changes  
		uptadeButtons(); 

		makeUnable(); // Unables all buttons
		makeEnable(); // Enables buttons when they can be used
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public void setPlayer_num(int player_num) {
		this.player_num = player_num;
	}

	public void uptadeButtons() { // When card changed due to play, this method updates cards values and colors in the users hand
		for (int i = 0; i < game.userHand().size(); i++) {
			buttons.get(i).setText(cardValue(game.userHand().get(i).getValue())); 
			buttons.get(i).setBackground(cardColor(game.userHand().get(i).getColor()));
			buttons.get(i).setVisible(true); // if card will exits again buttons will be visible again
		}
		for (int i = game.userHand().size(); i < 18; i++) { // if card does not exits buttons will unvisible
			buttons.get(i).setVisible(false);
		}
	}

	public void updateChanges() { // update other changes
		lastCard.setText(cardValue(game.get_last_card(game.getDiscard_deck()).getValue())); // update last card of discard deck
		lastCard.setBackground(cardColor(game.get_last_card(game.getDiscard_deck()).getColor()));
		nameOfPlayer.setText(game.getPlayers().get(playerIndex).getName()); // update name of player who got turn
		drawPile.setText(game.getPileNumber()); // update number of cards in pile deck
		directionJLabel.setText((game.isDirection() ? "Clockwise" : "Counterclockwise")); // update direction info
		makeUnable(); // If not players turn this method will unable all buttons
		for (int i = 0; i < player_num; i++) { // displays each players card number
			countJLabels.get(i).setText(Integer.toString(players.get(i).getSizeofHand()));
			UnoJLabels.get(i).setText((game.getPlayers().get(i).isDeclaredUNO() ? "UNO" : ""));
		}
	}

	public void makeUnable() { //unables all buttons when its not users turn
		for (JButton button : buttons) {
			button.setEnabled(false);
		}
		drawPile.setEnabled(false);
	}

	public void makeEnable() { // enables playable buttons when its users turn
		for (int i = 0; i < players.get(0).getSizeofHand(); i++) {
			if (game.playableCards().contains(players.get(0).getCards_in_hands().get(i))) {
				buttons.get(i).setEnabled(true);
			}
		}
		drawPile.setEnabled(true);
	}
	/**
	 * 
	 * @param playerIndex
	 * 
	 * checks whether this player won the game
	 * if so, calculates score enters to winner frame with required informations
	 * 
	 * 
	 */

	public void isWinner(int playerIndex) {
		if (players.get(playerIndex).getSizeofHand() == 0) { // checks whether players card run out
			int score = 0;
			for (int i = 0; i < player_num; i++) {
				score += players.get(i).getPointsInHand(); // calculates score by summing others points
			}

			try { // writes what happened to log
				BufferedWriter writer = new BufferedWriter(new FileWriter(".\\Logs\\" + sessionName + ".txt", true));
				writer.append("\n" + players.get(playerIndex).getName() + " won the session " + sessionName + "\n");
				writer.append("Score : " + score);
				writer.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

			new WinnerFrame(account, players.get(playerIndex).getName(), score, (playerIndex == 0 ? true : false));
			dispose(); // new frame with required info and dispose previous
		}
	}

	public Color cardColor(String color) { // To determine Buttons color
		if (color.equals("Green"))
			return Color.GREEN;
		else if (color.equals("Red"))
			return Color.RED;
		else if (color.equals("Blue"))
			return Color.BLUE;
		else if (color.equals("Yellow"))
			return Color.YELLOW;
		else
			return Color.ORANGE;
	}

	public String cardValue(String value) { // to determine Buttons text
		if (value.equals("Draw Four"))
			return "+4";
		else if (value.equals("Draw Two"))
			return "+2";
		else if (value.equals("Skip"))
			return "SK";
		else if (value.equals("Wild"))
			return "WI";
		else if (value.equals("Reverse"))
			return "RE";
		else
			return value;
	}
}
