package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uno.Game;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ColorChoose extends JFrame {
	/**
	 *  Asks for valid color in the game, According to chosen button, sets game's valid color
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
					ColorChoose frame = new ColorChoose(new Game("Try", 10, "Admin"));
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

	public ColorChoose(Game game) { 
		setTitle("Choose Color");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setVisible(true);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("GREEN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setValidColor("Green");
				dispose();
			}
		});
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(54, 10, 85, 109);
		contentPane.add(btnNewButton);

		JButton btnBlue = new JButton("BLUE");
		btnBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setValidColor("Blue");
				dispose();
			}
		});
		btnBlue.setBackground(new Color(30, 144, 255));
		btnBlue.setBounds(267, 10, 85, 109);
		contentPane.add(btnBlue);

		JButton btnRed = new JButton("RED");
		btnRed.setBackground(new Color(255, 0, 0));
		btnRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setValidColor("Red");
				dispose();
			}
		});
		btnRed.setBounds(54, 144, 85, 109);
		contentPane.add(btnRed);

		JButton btnYellow = new JButton("YELLOW");
		btnYellow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setValidColor("Yellow");
				dispose();
			}
		});
		btnYellow.setBackground(Color.YELLOW);
		btnYellow.setBounds(267, 144, 85, 109);
		contentPane.add(btnYellow);
	}

}
