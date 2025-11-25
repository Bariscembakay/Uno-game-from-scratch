package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uno.Account;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class Leaderboard extends JFrame {
	
	/**
	 * To show users' rank and can display users' stats.
	 * Can be returned to main menu or a detailed information about specific account can be seen by pushing buttons
	 * Accounts in a order
	 * Buttons display users and labels display their score
	 * 
	 * @param account current user
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static ArrayList<Account> accounts = new ArrayList<Account>();
	private ArrayList<JButton> playerButtons = new ArrayList<JButton>();
	private ArrayList<JLabel> scoreLabels = new ArrayList<JLabel>();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Leaderboard frame = new Leaderboard(null);
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

	public Leaderboard(Account account) {
		setTitle("LEADERBOARD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true);
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton p1 = new JButton("");
		p1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserStatistic(accounts.get(0), account);
				dispose();
			}
		});
		p1.setBounds(10, 67, 180, 30);
		contentPane.add(p1);

		JButton p2 = new JButton("");
		p2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserStatistic(accounts.get(1), account);
				dispose();
			}
		});
		p2.setBounds(10, 107, 180, 30);
		contentPane.add(p2);

		JButton p3 = new JButton("");
		p3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserStatistic(accounts.get(2), account);
				dispose();
			}
		});
		p3.setBounds(10, 147, 180, 30);
		contentPane.add(p3);

		JButton p4 = new JButton("");
		p4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserStatistic(accounts.get(3), account);
				dispose();
			}
		});

		p4.setBounds(10, 187, 180, 30);
		contentPane.add(p4);

		JButton p5 = new JButton("");
		p5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserStatistic(accounts.get(4), account);
				dispose();
			}
		});
		p5.setBounds(10, 227, 180, 30);
		contentPane.add(p5);

		JButton p6 = new JButton("");
		p6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserStatistic(accounts.get(5), account);
				dispose();
			}
		});
		p6.setBounds(10, 267, 180, 30);
		contentPane.add(p6);

		JButton p7 = new JButton("");
		p7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserStatistic(accounts.get(6), account);
				dispose();
			}
		});
		p7.setBounds(10, 307, 180, 30);
		contentPane.add(p7);

		JLabel lblNewLabel = new JLabel("PLAYERS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 10, 120, 47);
		contentPane.add(lblNewLabel);

		JLabel lblScores = new JLabel("Scores");
		lblScores.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblScores.setBounds(239, 10, 120, 47);
		contentPane.add(lblScores);

		JLabel s1 = new JLabel("");
		s1.setBounds(239, 67, 120, 30);
		contentPane.add(s1);

		JLabel s2 = new JLabel("");
		s2.setBounds(239, 107, 120, 30);
		contentPane.add(s2);

		JLabel s3 = new JLabel("");
		s3.setBounds(239, 147, 120, 30);
		contentPane.add(s3);

		JLabel s4 = new JLabel("");
		s4.setBounds(239, 187, 120, 30);
		contentPane.add(s4);

		JLabel s5 = new JLabel("");
		s5.setBounds(239, 227, 120, 30);
		contentPane.add(s5);

		JLabel s6 = new JLabel("");
		s6.setBounds(239, 267, 120, 30);
		contentPane.add(s6);

		JLabel s7 = new JLabel("");
		s7.setBounds(239, 307, 120, 30);
		contentPane.add(s7);

		JButton MainMenu = new JButton("Main Menu");
		MainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainMenu(account);
				dispose();
			}
		});
		MainMenu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		MainMenu.setBounds(392, 10, 163, 52);
		contentPane.add(MainMenu);

		playerButtons.add(p1);
		playerButtons.add(p2);
		playerButtons.add(p3);
		playerButtons.add(p4);
		playerButtons.add(p5);
		playerButtons.add(p6);
		playerButtons.add(p7);
		scoreLabels.add(s1);
		scoreLabels.add(s2);
		scoreLabels.add(s3);
		scoreLabels.add(s4);
		scoreLabels.add(s5);
		scoreLabels.add(s6);
		scoreLabels.add(s7);

		updateLeaderboard(); 

	}

	public static void setAccounts(ArrayList<Account> accounts) {
		Leaderboard.accounts = accounts;
	}

	public static ArrayList<Account> getAccounts() {
		return accounts;
	}
	/**
	 * Updates buttons and labels in correct order by using Account class' static method
	 */

	private void updateLeaderboard() {
		Account.updateLeaderboard();

		for (int i = 0; i < accounts.size(); i++) {
			playerButtons.get(i).setText(accounts.get(i).getUserName());
			scoreLabels.get(i).setText(Integer.toString(accounts.get(i).getTotalScore()));
		}
		for (int i = accounts.size(); i < 7; i++) {
			playerButtons.get(i).setEnabled(false);
			scoreLabels.get(i).setEnabled(false);
		}
	}
}
