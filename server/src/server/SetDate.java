package server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;
import javax.swing.DropMode;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class SetDate {

	JFrame frame;
	private JLabel label;
	private JCheckBox checkBox;
	private JButton btnNewButton_1;
	private JLabel lblRoomType;
	private JList list;
	private JPanel panel_2;


	private String serverResponse;
	static ClientConnection cc;
	private JScrollPane scrollPane;
	private JList list_1;
	private JScrollPane scrollPane_1;
	private JList list_2;
	private JScrollPane scrollPane_2;
	private JList list_3;
	private JLabel lblCheckinDate;
	private JScrollPane scrollPane_3;
	private JList list_4;
	private JScrollPane scrollPane_4;
	private JList list_5;
	private JScrollPane scrollPane_5;
	private JList list_6;
	private JLabel lblCheckoutDate;
	private JScrollPane scrollPane_6;
	ArrayList<String> data = new ArrayList<String>();
	int nextWindow;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetDate window = new SetDate(cc);
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
	public SetDate(ClientConnection conn) {
		initialize();
	}

	public boolean ci_before_co(int ci_year,
			int co_year,
			int ci_month,
			int co_month, 
			int ci_day,
			int co_day, 
			int room_type) //Checks if check-in date is before check-out date
	{

		if(co_year < ci_year)
		{
			return false;
		}
		else if(co_year > ci_year)
		{
			return true;
		}
		else
		{
			if(co_month < ci_month)
			{
				return false;
			}
			else if (co_month > ci_month)
			{
				return true;
			}
			else
			{
				if(co_day <= ci_day)
				{
					return false;
				}
				else 
				{
					return true;
				}
			}
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ci_year;
				int co_year;
				int ci_month; 
				int co_month; 
				int ci_day; 
				int co_day; 
				int room_type;
				ci_year = list_1.getSelectedIndex() + 2018;
				co_year = list_6.getSelectedIndex() + 2018;
				ci_month = list_2.getSelectedIndex();
				co_month = list_5.getSelectedIndex();
				ci_day = list_1.getSelectedIndex();
				co_day = list_4.getSelectedIndex();
				room_type = list.getSelectedIndex();
				boolean b=ci_before_co(ci_year,
						co_year,
						ci_month, 
						co_month, 
						ci_day, 
						co_day,
						room_type);
				
				if ((list_1.getSelectedIndex() == 0) || (list_2.getSelectedIndex() == 0) || (list_3.getSelectedIndex() == 0))
				{
					JOptionPane.showMessageDialog(null, "Please specify the check-in date. ");
				}
				else if ((list_4.getSelectedIndex() == 0) || (list_5.getSelectedIndex() == 0) || (list_6.getSelectedIndex() == 0))
				{
					JOptionPane.showMessageDialog(null, "Please specify the check-out date. ");
				}

				else if (b== false)
				{
					JOptionPane.showMessageDialog(null, "The check-out date cannot be before the check-in date. ");
				}
				else 
				{
					String room;
					if(room_type == 1)
					{
						room = "single";
					}
					else
					{
						room = "double";
					}
					try {
						String ci_date = Integer.toString(ci_day) + "-" + Integer.toString(ci_month) + "-" + Integer.toString(ci_year);
						String co_date = Integer.toString(co_day) + "-" + Integer.toString(co_month) + "-" + Integer.toString(co_year);
						String command = "SEARCH" + ";" + ci_date + "," + co_date + "," + checkBox.isSelected() + "," + room;
						
						sendCommand(command);
					}
					catch (InterruptedException | IOException e1) 
					{
						e1.printStackTrace();
					}
				}
			}
		});
		btnSearch.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnSearch.setBounds(240, 350, 123, 36);
		frame.getContentPane().add(btnSearch);
		

		JButton btnNewButton = new JButton("View Profile");
		btnNewButton.addActionListener(new ActionListener() 
	    {
		    @Override
		    public void actionPerformed(ActionEvent e) 
		    {
		        if (e.getSource().equals(btnNewButton)) 
		        {
		            try 
		            {
		                try 
		                {
		                	String command = "PROFILE;,";
		                	System.out.println("Client sends: " + command);
		                	sendCommand(command);
						} 
		                catch (InterruptedException e1) 
		                {
							e1.printStackTrace();
						}
		            } 
		            catch (UnknownHostException e1) 
		            {
		                e1.printStackTrace();
		            } 
		            catch (IOException e1) 
		            {
		                e1.printStackTrace();
		            }
		        }
		    }
	    });
		btnNewButton.setBounds(26, 379, 138, 29);
		frame.getContentPane().add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Choose the length of your stay");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(155, 21, 300, 45);
		frame.getContentPane().add(lblNewLabel);

		label = new JLabel("Smoking:");
		label.setBounds(382, 178, 73, 19);
		frame.getContentPane().add(label);

		checkBox = new JCheckBox("");
		checkBox.setBounds(442, 178, 128, 23);
		frame.getContentPane().add(checkBox);

		btnNewButton_1 = new JButton("Log out");
		btnNewButton_1.addActionListener(new ActionListener() 
	    {
		    @Override
		    public void actionPerformed(ActionEvent e) 
		    {
		        if (e.getSource().equals(btnNewButton_1)) 
		        {
		            try 
		            {
		                try 
		                {
		                	String command = "LOGOUT;,";
		                	System.out.println("Client sends: " + command);
		                	sendCommand(command);
						} 
		                catch (InterruptedException e1) 
		                {
							e1.printStackTrace();
						}
		            } 
		            catch (UnknownHostException e1) 
		            {
		                e1.printStackTrace();
		            } 
		            catch (IOException e1) 
		            {
		                e1.printStackTrace();
		            }
		        }
		    }
	    });
		
		btnNewButton_1.setBounds(36, 420, 117, 29);
		frame.getContentPane().add(btnNewButton_1);

		lblRoomType = new JLabel("Room type:");
		lblRoomType.setBounds(382, 222, 89, 16);
		frame.getContentPane().add(lblRoomType);

		panel_2 = new JPanel();
		panel_2.setBounds(460, 223, 58, 36);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(0, 0, 58, 39);
		panel_2.add(scrollPane_6);

		list = new JList();
		scrollPane_6.setViewportView(list);
		list.setSelectedIndices(new int[] {0});
		
		list.setModel(new AbstractListModel() 
		{
			String[] values = new String[] {"Single", "Double"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectedIndex(0);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 125, 58, 58);
		frame.getContentPane().add(scrollPane);

		list_1 = new JList<Integer>();
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"dd", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_1.setSelectedIndex(0);
		scrollPane.setViewportView(list_1);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(94, 125, 84, 58);
		frame.getContentPane().add(scrollPane_1);

		list_2 = new JList<Integer>();
		list_2.setModel(new AbstractListModel() {
			String[] values = new String[] {"mm", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_2.setSelectedIndex(0);
		scrollPane_1.setViewportView(list_2);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(190, 125, 73, 58);
		frame.getContentPane().add(scrollPane_2);

		list_3 = new JList<Integer>();
		list_3.setModel(new AbstractListModel() {
			String[] values = new String[] {"yyyy", "2019", "2020", "2021", "2022"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_3.setSelectedIndex(0);
		scrollPane_2.setViewportView(list_3);

		lblCheckinDate = new JLabel("Check-in date:");
		lblCheckinDate.setBounds(23, 84, 110, 16);
		frame.getContentPane().add(lblCheckinDate);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(26, 264, 58, 58);
		frame.getContentPane().add(scrollPane_3);

		list_4 = new JList<Integer>();
		list_4.setModel(new AbstractListModel() {
			String[] values = new String[] {"dd", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_4.setSelectedIndex(0);
		scrollPane_3.setViewportView(list_4);

		scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(94, 264, 84, 58);
		frame.getContentPane().add(scrollPane_4);

		list_5 = new JList<Integer>();
		list_5.setModel(new AbstractListModel() {
			String[] values = new String[] {"mm", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_5.setSelectedIndex(0);
		scrollPane_4.setViewportView(list_5);

		scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(190, 264, 73, 58);
		frame.getContentPane().add(scrollPane_5);

		list_6 = new JList<Integer>();
		list_6.setModel(new AbstractListModel() {
			String[] values = new String[] {"yyyy", "2019", "2020", "2021", "2022"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_6.setSelectedIndex(0);
		scrollPane_5.setViewportView(list_6);

		lblCheckoutDate = new JLabel("Check-out date:");
		lblCheckoutDate.setBounds(26, 222, 110, 16);
		frame.getContentPane().add(lblCheckoutDate);
	}
	
	public void parseInput()  // chop input into command and data
	{ 
		String[] com = serverResponse.split(";");
		nextWindow = Integer.parseInt(com[0]);
		
		String[] dat = com[1].split(",");
		
		for (int i = 0; i < dat.length; i++)
		{
			data.add(dat[i]);
		}
	}

	public void sendCommand(String command) throws IOException, InterruptedException 
	{
		System.out.println("Sendcommand sends: "+command);
		cc.sendStringToServer(command);
//		System.out.println(nextWindow);

		if(cc.serverResponds())
		{
			System.out.println("Server replies: "+cc.getServerResponse());  // use this to change window and display data if needed
			serverResponse = cc.getServerResponse();
			parseInput();
			System.out.println(nextWindow);
			goToNextWindow(nextWindow);
		}
	}

	public void goToNextWindow(int window) throws UnknownHostException, IOException
	{
		switch (window)
		{
		case 3:
			ChooseRoom choose = new ChooseRoom(cc, data);
			choose.frame.setVisible(true);
			this.frame.setVisible(false);
			break;

		case 9:
			Profile prof = new Profile(cc);
			prof.frame.setVisible(true);
			this.frame.setVisible(false);
			break;


		case 5:
			JOptionPane.showMessageDialog(null, "No rooms of this type are available.");
			break;

		case 6:
			LoginWindow login = new LoginWindow(cc);
			login.setVisible(true);
			this.frame.setVisible(false);
			break;

		default:
		}
	}
	
	public void actionPerformed(ActionEvent e) {}
}