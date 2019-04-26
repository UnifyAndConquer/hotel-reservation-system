import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;
import java.sql.*;
import javax.swing.*;


public class HotelGui {

	private JFrame frame;
	private JTextField txtProject;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HotelGui window = new HotelGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection sqlconn = null;

	/**
	 * Create the application.
	 */
	public HotelGui() {
		initialize();
		sqlconn = mysqlConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Hotel reservation system");
		frame.getContentPane().setBackground(Color.CYAN);
		frame.setBounds(100, 100, 930, 622);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setToolTipText("");
		layeredPane.setBackground(Color.CYAN);
		layeredPane.setBounds(6, 57, 455, 440);
		layeredPane.setOpaque(true);
		frame.getContentPane().add(layeredPane);
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				try {
						String query = "select * from Users where `E-mail Address`=? and `Password`=? ";
						PreparedStatement pst = sqlconn.prepareStatement(query);
						pst.setString(1,textField.getText());
						pst.setString(2,passwordField.getText());
						
						ResultSet rs = pst.executeQuery();
						int counter = 0;
						while(rs.next())
						{
							counter++;
						}
						
						if(counter == 1)
						{
							JOptionPane.showMessageDialog(null, "Correct username and password");
						}
						else if (counter == 0)
						{
							JOptionPane.showMessageDialog(null, "Username or password incorrect. Please try again");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Duplicate encountered");
						}
						
						rs.close();
						pst.close();
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnNewButton.setBounds(93, 177, 117, 29);
		layeredPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New user? Create an account.");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AccountCreation window = new AccountCreation();
					window.frame.setVisible(true);
				} catch (Exception f) {
					f.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnNewButton_1.setBounds(6, 218, 186, 29);
		layeredPane.add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "E-mail address", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 43, 213, 50);
		layeredPane.add(panel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(6, 15, 201, 29);
		panel.add(textField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Password", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(6, 112, 213, 53);
		layeredPane.add(panel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(6, 18, 201, 29);
		panel_1.add(passwordField);
	
		

		
	}
}
