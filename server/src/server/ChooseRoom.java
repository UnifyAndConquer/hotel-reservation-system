package server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JPanel;

public class ChooseRoom {
    ClientConnection cc;
    String serverResponse;
    int nextWindow;

	JFrame frame;
	String[] data = new String[6];   //no more than 30 rooms

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Socket s = new Socket("127.0.0.1", 3001);
//					ClientConnection c = new ClientConnection(s);
//					ArrayList<String> data = new ArrayList<String>();
//					ChooseRoom window = new ChooseRoom(c, data);
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
	public ChooseRoom(ClientConnection conn, ArrayList<String> dat) {
		
		for (int i = 0; i < dat.size(); i ++)
		{
			data[i] = dat.get(i);
			
		}
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
		JLabel lblChooseRoomFeatures = new JLabel("Available rooms\n");
		lblChooseRoomFeatures.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblChooseRoomFeatures.setBounds(215, 6, 180, 64);
		frame.getContentPane().add(lblChooseRoomFeatures);
		

		JPanel panel = new JPanel();
		panel.setBounds(156, 81, 260, 155);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JList list = new JList();
		list.setBounds(0, 0, 260, 155);
		panel.add(list);
		
//		data = new String[] {"pimp", "pimpage"};
		String[] rooms=new String[data.length/2];
		for (int i=0; i<data.length/2-2; i++)
		{
			rooms[i]="Room number: "+data[2*i]+"     Price: "+data[2*i+1];
		}
		
		list.setModel(new AbstractListModel() 
		{
			String[] values = rooms;
			
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JButton btnNewButton = new JButton("Back");
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
		                	String command = "BACK;,";
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
		btnNewButton.setBounds(17, 332, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() 
	    {
		    @Override
		    public void actionPerformed(ActionEvent e) 
		    {
		        if (e.getSource().equals(btnLogOut)) 
		        {
		            try 
		            {
		                try 
		                {
		                	String command = "LOGOUT;";
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
		btnLogOut.setBounds(141, 332, 117, 29);
		frame.getContentPane().add(btnLogOut);
		
		JButton btnNewButton_1 = new JButton("Checkout");
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
		                	String command = "CHECKOUT;,"+list.getSelectedValue()+",";  
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
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnNewButton_1.setBounds(215, 262, 129, 37);
		frame.getContentPane().add(btnNewButton_1);
	}
	
	public void parseInput()  // chop input into command and data
	{ 
		String[] com = serverResponse.split(";");
		nextWindow = Integer.parseInt(com[0]);
	}

	public void sendCommand(String command) throws UnknownHostException, IOException, InterruptedException 
	{
		cc.sendStringToServer(command);

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
		case 2:
			SetDate prof = new SetDate(cc);
			prof.frame.setVisible(true);
			this.frame.setVisible(false);
			break;

		case 7:
			Checkout check = new Checkout(cc);
			check.frame.setVisible(true);
			this.frame.setVisible(false);
			break;

		case 6:
			LoginWindow login = new LoginWindow(cc);
			login.setVisible(true);
			this.frame.setVisible(false);
			break;

		default:
		}
	}
}
