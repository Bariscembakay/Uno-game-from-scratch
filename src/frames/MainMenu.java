package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uno.Account;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {
	
	/**
	 * Main Menu of application
	 * Users can see leaderboard, can load past games, can start a new game.
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu(null);
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
	
	public MainMenu(Account account) {
		setTitle("MAIN MENU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 650);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("New button");
		button.setBounds(502, 183, -195, 118);
		contentPane.add(button);
		
		JButton NewGameButton = new JButton("NEW GAME"); // Enter new game menu frame
		NewGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewGameMenu newwindow = new NewGameMenu();
				newwindow.setAccount(account);
				newwindow.setVisible(true);
				dispose();
			}
		});
		NewGameButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		NewGameButton.setBounds(221, 162, 380, 153);
		contentPane.add(NewGameButton);
		
		JButton LeaderBoard = new JButton("LEADERBOARD"); // Enter Leader board frame
		LeaderBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Leaderboard(account);
				dispose();
			}
		});
		LeaderBoard.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LeaderBoard.setBounds(137, 372, 211, 105);
		contentPane.add(LeaderBoard);
		
		JButton btnNewButton = new JButton("LOAD GAME"); // Enter Load Game frame
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoadPage(account);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(444, 372, 227, 105);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Username : " + account.getUserName());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(30, 30, 571, 81);
		contentPane.add(lblNewLabel_1);
	
	}
}
