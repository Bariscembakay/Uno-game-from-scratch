package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cards.ActionCard;
import cards.Card;
import cards.NumberCard;
import cards.WildCard;
import uno.Account;
import uno.Game;
import uno.Player;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;

public class LoadPage extends JFrame {
	
	/**
	 * Frame to load desired game.
	 * In the beginning finds saved games for current user from its special directory created by account class
	 * After that, displays every saved games session names with buttons
	 * For chosen button, reads the relevant file which has session name
	 * After read creates game object and modify game object with relevant informations 
	 * Creates Card and Player objects and add those to game object
	 * After game is created, creates NewGame frame (frame for Uno game itself) and dispose this frame
	 */

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	private ArrayList<String> savedGames = new ArrayList<String>();
	private Account account;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadPage frame = new LoadPage(new Account("Admin", "admin", "0", "0", "0", "0"));
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
	
	public LoadPage(Account account) {
		this.account = account;
		this.savedGames();
		
		setTitle("Load Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true);
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SAVED GAMES");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(10, 10, 560, 100);
		contentPane.add(lblNewLabel);
		
		/**
		 * to return main menu
		 */
		
		JButton btnNewButton = new JButton("Main Menu"); 
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(20, 40, 120, 50);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainMenu(account);
				dispose();
			}
		});

		for (int i = 0; i < savedGames.size(); i++) { 
			buttons.add(new JButton(savedGames.get(i))); // Buttons are being created for each game
			buttons.get(i).setBounds(40, 120 + 70 * i, 500, 60);
			contentPane.add(buttons.get(i));
			String fileName = buttons.get(i).getText();
			buttons.get(i).addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) { // For any button, it starts loading the game
					
					try { // Game informations are being read
						BufferedReader reader = new BufferedReader(new FileReader(".\\SavedGames\\" + account.getUserName()+ "\\" + fileName + ".txt"));
						String[] lines = new String[9];
						for(int i = 0; i < 9; i++) {
							lines[i] = reader.readLine();
						}
						Game game = new Game(lines[0], Integer.parseInt(lines[1]), lines[3]); //Game Object created
						
						game.setDirection(Boolean.valueOf(lines[4])); // Game informations being modified
						game.setValidColor(lines[5]);
						game.setValidValue(lines[6]);
						game.setPile_deck(generateCards(lines[7]));
						game.setDiscard_deck(generateCards(lines[8]));
						
						ArrayList<Player> players = new ArrayList<Player>();
						
						String playerName = reader.readLine();
						String playerCards = reader.readLine();
						String playerisUNO = reader.readLine();
						String playerisDeclaredUNO = reader.readLine();
						
						while(playerName != null) { // player informations being read
							
							Player player = new Player(playerName); // player objects being created and being modified
							player.setDeclaredUNO(Boolean.valueOf(playerisDeclaredUNO));
							player.setUNO(Boolean.valueOf(playerisUNO));
							player.setCards_in_hands(generateCards(playerCards));
							
							playerName = reader.readLine();
							playerCards = reader.readLine();
							playerisUNO = reader.readLine();
							playerisDeclaredUNO = reader.readLine();
							
							players.add(player); 
						}
						
						game.setPlayers(players); // Players added to game
											
						NewGame loadedGame = new NewGame(account, game, Integer.parseInt(lines[2])); // Frame has been created
						loadedGame.setVisible(true); // Ready to play the loaded game
						
						reader.close();
						dispose();
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
			});
		}
		
	}
	/**
	 * Method for creating Cards from string lines
	 * Card lists being saved and written as a array list
	 * This method takes String line which has card names
	 * With extracting card names creates card objects and adds to a new list
	 * Returns new list
	 * 
	 * @param line
	 * @return
	 */
	
	private ArrayList<Card> generateCards(String line){
		String subString = line.substring(1,line.length() - 1); // deletes [] chars
		List<String> pileDeckStrings = Arrays.asList(subString.split(", ")); // splits each card name and saves to a list
		ArrayList<Card> cards = new ArrayList<Card>(); 
		
		for(int i = 0; i < pileDeckStrings.size(); i++) { // Decides which card has this names and creates related card object
			String[] cardString = pileDeckStrings.get(i).split(" "); 
			
			//Creates wild cards
			if(cardString[0].equals("Wild")) { 
				if (cardString.length == 3) cards.add(new WildCard("Wild", "Draw Four"));
				else cards.add(new WildCard("Wild", "Wild"));
			}
			
			//Creates action cards
			else if (cardString[1].equals("Draw") || cardString[1].equals("Reverse") || cardString[1].equals("Skip")) {
				if (cardString.length == 3) cards.add(new ActionCard(cardString[0], "Draw Two"));
				else cards.add(new ActionCard(cardString[0], cardString[1]));
			}
			
			//Creates number cards
			else {
				cards.add(new NumberCard(cardString[0], Integer.parseInt(cardString[1])));
			}
		}
		return cards;
	}
	
	/**
	 * extracts saved game names from users' directory
	 * adds them to a array list 
	 */
	
	private void savedGames() {
		File folder = new File(".\\SavedGames\\" + account.getUserName()); //related directory
		for(File file : folder.listFiles()) {
			String fileName = file.getName().substring(0,file.getName().length() - 4); // Takes session name
			savedGames.add(fileName);
		}
	}

}
