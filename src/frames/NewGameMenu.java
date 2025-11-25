package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uno.Account;
import uno.Game;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class NewGameMenu extends JFrame {
	
	/**
	 * New Game Menu
	 * Users must choose a session name and player number
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Account account;
	private JTextField sessionName;

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
					NewGameMenu frame = new NewGameMenu();
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
	public NewGameMenu() {
		setTitle("NEW GAME MENU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 650);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select Number of Player");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(145, 138, 316, 100);
		contentPane.add(lblNewLabel);
		
		JButton twoplayersbutton = new JButton("2"); // Each button starts a new game with chosen number of player
		twoplayersbutton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		twoplayersbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game game = new Game(sessionName.getText(), 2, account.getUserName()); // create a game object
				game.initializeGame(); // initialize game because its new game
				NewGame newMenu = new NewGame(account, game, 0); // create new game frame
				newMenu.setVisible(true);
				dispose();
			}
		});
		twoplayersbutton.setBounds(145, 247, 85, 124);
		contentPane.add(twoplayersbutton);
		
		JButton threeplayersbutton = new JButton("3");
		threeplayersbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game game = new Game(sessionName.getText(), 3, account.getUserName());
				game.initializeGame();
				NewGame newMenu = new NewGame(account, game, 0);
				newMenu.setVisible(true);
				dispose();
			}
		});
		threeplayersbutton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		threeplayersbutton.setBounds(240, 247, 85, 124);
		contentPane.add(threeplayersbutton);
		
		JButton fourplayersbutton = new JButton("4");
		fourplayersbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game game = new Game(sessionName.getText(), 4, account.getUserName());
				game.initializeGame();
				NewGame newMenu = new NewGame(account, game, 0);
				newMenu.setVisible(true);
				dispose();
			}
		});
		fourplayersbutton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		fourplayersbutton.setBounds(335, 247, 85, 124);
		contentPane.add(fourplayersbutton);
		
		JButton fiveplayersbutton = new JButton("5");
		fiveplayersbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game game = new Game(sessionName.getText(), 5, account.getUserName());
				game.initializeGame();
				NewGame newMenu = new NewGame(account, game, 0);
				newMenu.setVisible(true);
				dispose();
			}
		});
		fiveplayersbutton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		fiveplayersbutton.setBounds(430, 247, 85, 124);
		contentPane.add(fiveplayersbutton);
		
		JButton sixplayersbutton = new JButton("6");
		sixplayersbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game game = new Game(sessionName.getText(), 6, account.getUserName());
				game.initializeGame();
				NewGame newMenu = new NewGame(account, game, 0);
				newMenu.setVisible(true);
				dispose();
			}
		});
		sixplayersbutton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		sixplayersbutton.setBounds(525, 247, 85, 124);
		contentPane.add(sixplayersbutton);
		
		JButton sevenplayersbutton = new JButton("7");
		sevenplayersbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game game = new Game(sessionName.getText(), 7, account.getUserName());
				game.initializeGame();
				NewGame newMenu = new NewGame(account, game, 0);
				newMenu.setVisible(true);
				dispose();
			}
		});
		sevenplayersbutton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		sevenplayersbutton.setBounds(197, 381, 85, 124);
		contentPane.add(sevenplayersbutton);
		
		JButton eightplayersbutton = new JButton("8");
		eightplayersbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game game = new Game(sessionName.getText(), 8, account.getUserName());
				game.initializeGame();
				NewGame newMenu = new NewGame(account, game, 0);
				newMenu.setVisible(true);
				dispose();
			}
		});
		eightplayersbutton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		eightplayersbutton.setBounds(292, 381, 85, 124);
		contentPane.add(eightplayersbutton);
		
		JButton nineplayersbutton = new JButton("9");
		nineplayersbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game game = new Game(sessionName.getText(), 9, account.getUserName());
				game.initializeGame();
				NewGame newMenu = new NewGame(account, game, 0);
				newMenu.setVisible(true);
				dispose();
			}
		});
		nineplayersbutton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		nineplayersbutton.setBounds(387, 381, 85, 124);
		contentPane.add(nineplayersbutton);
		
		JButton tenplayersbutton = new JButton("10");
		tenplayersbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game game = new Game(sessionName.getText(), 10, account.getUserName());
				game.initializeGame();
				NewGame newMenu = new NewGame(account, game, 0);
				newMenu.setVisible(true);
				dispose();
			}
		});
		tenplayersbutton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		tenplayersbutton.setBounds(482, 381, 85, 124);
		contentPane.add(tenplayersbutton);
		
		JLabel lblEnterASession = new JLabel("Enter a Session Name");
		lblEnterASession.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnterASession.setBounds(54, 52, 221, 76);
		contentPane.add(lblEnterASession);
		
		sessionName = new JTextField();
		sessionName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		sessionName.setBounds(285, 52, 407, 76);
		contentPane.add(sessionName);
		sessionName.setColumns(10);
	}
}
