package uno;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import frames.Leaderboard;

public class Account implements Comparable<Account> {

	/**
	 * Account Class Accounts hold informations about Users and their stats
	 * 
	 */

	private String userName;
	private String passWord;
	private int totalScore;
	private int numberWin;
	private int numberLose;
	private int numberGame;
	private ArrayList<String> savedGames = new ArrayList<String>();
	private static HashMap<String, Account> accounts = new HashMap<String, Account>();

	public Account(String userName, String passWord, String totalScore, String numberGame, String numberWin,
			String numberLose) {
		this.userName = userName;
		this.passWord = passWord;
		this.totalScore = Integer.parseInt(totalScore);
		this.numberWin = Integer.parseInt(numberWin);
		this.numberGame = Integer.parseInt(numberGame);
		this.numberLose = Integer.parseInt(numberLose);
		Account.accounts.put(userName, this);
		Leaderboard.getAccounts().add(this);

		new File("./SavedGames/" + userName).mkdirs(); // Creating saved game directory for each User

	}

	public void addSavedGame(String fileName) {
		savedGames.add(fileName);
	}

	public static HashMap<String, Account> getAccounts() {
		return accounts;
	}

	public static void setAccounts(HashMap<String, Account> accounts) {
		Account.accounts = accounts;
	}

	public void gainScore(int scoreGained) {
		totalScore += scoreGained;
	}

	public void gainWin() {
		numberWin += 1;
		numberGame += 1;
	}

	public void gainLose() {
		numberLose += 1;
		numberGame += 1;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public String getUserName() {
		return userName;
	}

	public boolean isCorrectPasword(String passWord) { // For checking password securely
		return (this.passWord.equals(passWord) ? true : false);
	}

	@Override
	public String toString() { // To save each account in a file
		return userName + " " + passWord + " " + totalScore + " " + numberGame + " " + numberWin + " " + numberLose;
	}

	public static void updateLeaderboard() { // This method update leaderboard in a order and saves accounts to file.
		
		Collections.sort(Leaderboard.getAccounts());
		Collections.reverse(Leaderboard.getAccounts());

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt", false));

			for (Account account : Leaderboard.getAccounts())
				writer.write(account + "\n");

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int compareTo(Account other) { // Compare method for comparable interface to order accounts

		if (this.totalScore > other.totalScore)
			return 1;
		else if (this.totalScore < other.totalScore)
			return -1;
		else
			return 0;

	}

	public int getNumberWin() {
		return numberWin;
	}

	public int getNumberLose() {
		return numberLose;
	}

	public int getNumberGame() {
		return numberGame;
	}

}
