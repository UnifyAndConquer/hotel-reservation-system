package server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditProfile {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditProfile window = new EditProfile();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EditProfile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("Profile modifications");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblNewLabel.setBounds(152, 6, 281, 68);
		frame.getContentPane().add(lblNewLabel);
		JLabel lblNewLabel_1 = new JLabel("First name:");
		lblNewLabel_1.setBounds(28, 105, 111, 29);
		frame.getContentPane().add(lblNewLabel_1);
		JLabel lblLastName = new JLabel("Last name:");
		lblLastName.setBounds(28, 162, 111, 29);
		frame.getContentPane().add(lblLastName);
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(54, 219, 54, 29);
		frame.getContentPane().add(lblAge);
		JLabel lblEmailAddress = new JLabel("E-mail address:");
		lblEmailAddress.setBounds(16, 272, 111, 29);
		frame.getContentPane().add(lblEmailAddress);
		textField = new JTextField();
		textField.setBounds(116, 106, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(116, 163, 130, 26);
		frame.getContentPane().add(textField_1);
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(116, 220, 130, 26);
		frame.getContentPane().add(textField_2);
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(127, 273, 130, 26);
		frame.getContentPane().add(textField_3);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( !(textField.getText().matches("[a-zA-Z]+")) || !(textField_1.getText().matches("[a-zA-Z]+")) )
				{
					JOptionPane.showMessageDialog(null, "First and last name fields can only have letters. ");
				}
				else if( !(textField_2.getText().matches("[0-9]+")) )
				{
					JOptionPane.showMessageDialog(null, "The age field can only have numbers. ");
				}
			}
		});
		btnNewButton.setBounds(162, 321, 137, 36);
		frame.getContentPane().add(btnNewButton);
	}

}