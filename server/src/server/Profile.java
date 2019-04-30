package server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Profile {

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
//					Profile window = new Profile();
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
	public Profile(ClientConnection conn) {
		cc = conn;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date = sdf.format(new Date());
		SimpleDateFormat t = new SimpleDateFormat("HH:mm");
		String time = t.format(new Date());
		JLabel lblNewLabel = new JLabel(date);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setBounds(440, 5, 115, 75);
		frame.getContentPane().add(lblNewLabel);
		JLabel lblNewLabel_1 = new JLabel(time);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(440, 72, 105, 33);
		frame.getContentPane().add(lblNewLabel_1);
		JLabel lblUserProfile = new JLabel("User profile");
		lblUserProfile.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblUserProfile.setBounds(218, 25, 148, 69);
		frame.getContentPane().add(lblUserProfile);
		JButton btnNewButton = new JButton("Log out");
		btnNewButton.setBounds(18, 343, 117, 29);
		frame.getContentPane().add(btnNewButton);
		JButton btnNewButton_1 = new JButton("Edit user information");
		btnNewButton_1.setBounds(28, 237, 171, 49);
		frame.getContentPane().add(btnNewButton_1);
		JButton btnNewButton_2 = new JButton("Edit reservation");
		btnNewButton_2.setBounds(257, 150, 161, 49);
		frame.getContentPane().add(btnNewButton_2);
		JLabel lblIfYouWould = new JLabel("If you would like to edit a reservation, \n\n");
		lblIfYouWould.setBounds(6, 72, 257, 56);
		frame.getContentPane().add(lblIfYouWould);
		JLabel lblNewLabel_2 = new JLabel("please enter your reservation number:");
		lblNewLabel_2.setBounds(6, 87, 294, 77);
		frame.getContentPane().add(lblNewLabel_2);
		textField = new JTextField();
		textField.setBounds(270, 112, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		JButton btnCreateANew = new JButton("Create a new reservation");
		btnCreateANew.setBounds(372, 237, 171, 49);
		frame.getContentPane().add(btnCreateANew);
	}

}
