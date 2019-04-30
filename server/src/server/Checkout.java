package server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Checkout {

	JFrame frame;
	private JTextField textField;
	ClientConnection cc;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Checkout window = new Checkout();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public Checkout(ClientConnection conn) {
		cc = conn;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JButton button = new JButton("Back");
		button.setBounds(18, 213, 114, 29);
		frame.getContentPane().add(button);
		JButton btnNewButton = new JButton("Log out");
		btnNewButton.setBounds(15, 243, 117, 29);
		frame.getContentPane().add(btnNewButton);
		JButton btnNewButton_1 = new JButton("Confirm");
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnNewButton_1.setBounds(144, 178, 128, 37);
		frame.getContentPane().add(btnNewButton_1);
		JLabel lblNewLabel = new JLabel("Checkout");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(164, 6, 101, 37);
		frame.getContentPane().add(lblNewLabel);
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(90, 91, 230, 47);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}

}
