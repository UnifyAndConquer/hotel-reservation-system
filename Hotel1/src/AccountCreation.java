import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.SystemColor;

public class AccountCreation extends JFrame {

	JFrame frame;
	private JTextField txtDateOfBirth;
	private JList list;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JList list_1;
	private JScrollPane scrollPane_1;
	private JList list_2;
	private JScrollPane scrollPane_2;
	private JPasswordField passwordField;
	private JTextField textField;
	private JPasswordField passwordField_1;
	private JPanel panel_5;
	private JTextField textField_1;
	private JPanel panel_6;
	private JTextField textField_2;
	private JPanel panel_1;
	private JTextField textField_3;
	private JTextField txtCreateAnAccount;
	private JPanel panel_2;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountCreation window = new AccountCreation();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public AccountCreation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 643, 623);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(UIManager.getColor("Button.background"));
		layeredPane.setOpaque(true);
		layeredPane.setBounds(44, 6, 495, 630);
		frame.getContentPane().add(layeredPane);
		
		txtDateOfBirth = new JTextField();
		txtDateOfBirth.setText("Date of Birth:\n");
		txtDateOfBirth.setBounds(26, 197, 102, 26);
		layeredPane.add(txtDateOfBirth);
		txtDateOfBirth.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 235, 49, 67);
		layeredPane.add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"dd", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectedIndex(0);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "E-mail address", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(26, 314, 213, 50);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(6, 15, 201, 29);
		panel.add(textField);
		textField.setColumns(10);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(87, 235, 85, 67);
		layeredPane.add(scrollPane_1);
		
		list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"mm", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_1.setSelectedIndex(0);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(200, 235, 62, 67);
		layeredPane.add(scrollPane_2);
		
		list_2 = new JList();
		scrollPane_2.setViewportView(list_2);
		list_2.setModel(new AbstractListModel() {
			String[] values = new String[] {"yyyy", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_2.setSelectedIndex(0);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(26, 564, 117, 29);
		layeredPane.add(btnRegister);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Password", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(26, 437, 213, 53);
		layeredPane.add(panel_4);
		panel_4.setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(6, 18, 201, 29);
		panel_4.add(passwordField);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "Confirm password", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(26, 502, 213, 53);
		layeredPane.add(panel_3);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(6, 18, 201, 29);
		panel_3.add(passwordField_1);
		
		panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(null, "Phone number", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(26, 376, 213, 50);
		layeredPane.add(panel_5);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(6, 15, 201, 29);
		panel_5.add(textField_1);
		
		panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new TitledBorder(null, "Last name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(26, 73, 213, 50);
		layeredPane.add(panel_6);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(6, 15, 201, 29);
		panel_6.add(textField_2);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "First name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(26, 135, 213, 50);
		layeredPane.add(panel_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(6, 15, 201, 29);
		panel_1.add(textField_3);
		
		panel_2 = new JPanel();
		panel_2.setBounds(166, 6, 177, 41);
		layeredPane.add(panel_2);
		panel_2.setLayout(null);
		
		txtCreateAnAccount = new JTextField();
		txtCreateAnAccount.setBackground(SystemColor.window);
		txtCreateAnAccount.setEditable(false);
		txtCreateAnAccount.setBounds(0, 0, 177, 41);
		panel_2.add(txtCreateAnAccount);
		txtCreateAnAccount.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtCreateAnAccount.setText("  Create an account");
		txtCreateAnAccount.setColumns(10);
	}
}
