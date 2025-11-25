package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import uno.Account;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class LoginPage extends JFrame {
	
	/**
	 * Login Page 
	 * Users can create accounts by pressing Sign up
	 * Users can login
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JTextField Username_box;
	private JPasswordField Password_box;
	private JLabel entryFeedback;

	/**
	 * Launch the application.
	 */  
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
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
	public LoginPage() {
		getAccountsFromFile();
		setTitle("LOGIN PAGE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 730);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnNewButton = new JButton("LOG IN"); 
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) { // checks password and user name using HashMap.
				if (!Account.getAccounts().containsKey(Username_box.getText())) { // checks whether this user name exits.
					entryFeedback.setText("There is no such User");
				}
				else if (!Account.getAccounts().get(Username_box.getText()).isCorrectPasword(Password_box.getText())) {
					entryFeedback.setText("Incorrect password"); // checks whether password is true or false.
				}
				else {
					new MainMenu(Account.getAccounts().get(Username_box.getText())); // Enter main menu if both true.
					dispose();
				}
			}
		});
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnNewButton.setBounds(33, 536, 247, 112);
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("SIGN UP");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) { // checks this account can be created.
				try {
					if (Account.getAccounts().containsKey(Username_box.getText())) { // checks User name was taken or not.
						entryFeedback.setText("This Username was taken.");
					}
					else if (Password_box.getText().contains(" ") || Username_box.getText().contains(" ")) {
						entryFeedback.setText("Username or pasword cannot contain space."); // checks space exits or not
					}
					else { // create account and write its attributes to accounts file to save
						new Account(Username_box.getText(), Password_box.getText(), "0", "0", "0", "0");
						BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt",true));
						writer.append(Account.getAccounts().get(Username_box.getText()).toString() + "\n");
						writer.close();
						entryFeedback.setText("Sign Up successful.");
					}

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(543, 536, 219, 112);
		panel.add(btnNewButton_1);
		
		Username_box = new JTextField();
		Username_box.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Username_box.setBounds(356, 47, 332, 95);
		panel.add(Username_box);
		Username_box.setColumns(10);
		
		Password_box = new JPasswordField();
		Password_box.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Password_box.setBounds(356, 192, 332, 95);
		panel.add(Password_box);
		Password_box.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Enter Username\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(33, 69, 298, 47);
		panel.add(lblNewLabel);
		
		JLabel lblEnterPassword = new JLabel("Enter Password\r\n");
		lblEnterPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnterPassword.setBounds(33, 214, 289, 47);
		panel.add(lblEnterPassword);
		
		entryFeedback = new JLabel("");
		entryFeedback.setHorizontalAlignment(SwingConstants.CENTER);
		entryFeedback.setFont(new Font("Tahoma", Font.PLAIN, 15));
		entryFeedback.setBounds(33, 339, 789, 71);
		panel.add(entryFeedback);
	}
	
	private void getAccountsFromFile() { // When a new application started, read accounts file and creates past accounts.
		try {
			BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"));
			String line;
			line = reader.readLine();
			while(line != null) {
				String infos[] = line.split(" ");
				new Account(infos[0], infos[1], infos[2], infos[3], infos[4], infos[5]); // it is also reads win / lose / game / point
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
	}
}
