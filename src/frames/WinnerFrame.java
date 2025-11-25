package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uno.Account;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinnerFrame extends JFrame {
	
	/**
	 * Winner Frame
	 * Displays winner and winners' score
	 * Also updates accounts stats.
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
					WinnerFrame frame = new WinnerFrame(null, null, 0, false);
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
	 * @param account (for current account) 
	 * @param winnerName (for displaying name of winner)
	 * @param score (to display score)
	 * @param isUser (to find out winner is User or not)
	 */
	
	public WinnerFrame(Account account, String winnerName, int score, boolean isUser) {
		setTitle("Game Over");
		if (isUser) {
			account.gainScore(score);
			account.gainWin();
		} else {
			account.gainLose();
		}
		Account.updateLeaderboard();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel(winnerName + " WON");
		lblNewLabel.setBounds(160, 60, 140, 60);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Score : " + score);
		lblNewLabel_1.setBounds(160, 120, 140, 30);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Main Menu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu mainMenu = new MainMenu(account);
				mainMenu.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(160, 190, 100, 25);
		contentPane.add(btnNewButton);
		setVisible(true);
	}
}
