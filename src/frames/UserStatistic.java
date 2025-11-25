package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uno.Account;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserStatistic extends JFrame {

	/**
	 * A frame for user stats.
	 * Users can see detailed information about other users stats.
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
					UserStatistic frame = new UserStatistic(new Account("b", "a", "0", "0", "0", "0"),
							new Account("ba", "a", "0", "0", "0", "0"));
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
	 * first account parameter for displaying chosen players data.
	 * second account parameter for current user.
	 * 
	 * @param account
	 * @param currentAccount
	 */
	public UserStatistic(Account account, Account currentAccount) {
		setTitle("UserStats");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel userName = new JLabel( // Displaying Users name and rank
				Integer.toString(Leaderboard.getAccounts().indexOf(account) + 1) + ") " + account.getUserName());
		userName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		userName.setBounds(10, 20, 366, 92);
		contentPane.add(userName);

		JLabel lblNewLabel = new JLabel("STATS :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 110, 100, 70);
		contentPane.add(lblNewLabel);

		JLabel totalScore = new JLabel("Total Score : " + account.getTotalScore());
		totalScore.setBounds(10, 180, 366, 37);
		contentPane.add(totalScore);

		JLabel numWin = new JLabel("Number of Win : " + account.getNumberWin());
		numWin.setBounds(10, 227, 168, 37);
		contentPane.add(numWin);

		JLabel NumberOfGame = new JLabel("Number of Game : " + account.getNumberGame());
		NumberOfGame.setBounds(10, 274, 366, 37);
		contentPane.add(NumberOfGame);

		JLabel avgScore = new JLabel((account.getNumberGame() == 0 ? " Avarage Score : 0"
				: "Avarage Score : " + account.getTotalScore() / (double) account.getNumberGame()));
		avgScore.setBounds(10, 321, 366, 37);
		contentPane.add(avgScore);

		JLabel WinLoseRatio = new JLabel("Win Rate : "
				+ (account.getNumberGame() == 0 ? "-" : account.getNumberWin() / (double) account.getNumberGame() * 100
				+ "%"));
		WinLoseRatio.setBounds(10, 368, 366, 37);
		contentPane.add(WinLoseRatio);

		JButton btnNewButton = new JButton("RETURN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Leaderboard(currentAccount);
				dispose();
			}
		});
		btnNewButton.setBounds(10, 415, 168, 46);
		contentPane.add(btnNewButton);

		JLabel numLose = new JLabel("Number of Lose : " + account.getNumberLose());
		numLose.setBounds(188, 227, 188, 37);
		contentPane.add(numLose);
	}
}
