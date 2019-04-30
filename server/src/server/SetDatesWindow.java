package server;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SetDatesWindow extends JFrame implements ActionListener 
{
	
	JLabel lblArrive;
    JLabel lblDepart;
    JLabel lblPref;
    JLabel lblPeople;
    JTextField txtArrive;
    JTextField txtDepart;
    JTextField txtPref;
    JTextField txtPeople;
    JButton btnSearch;
    JButton btnProfile;
    JButton btnLogout;
    String serverResponse;
    int nextWindow;
    JCheckBox checkBox;
    JLabel label;
    ClientConnection cc;
    ArrayList<String> data = new ArrayList<String>();

    
    public SetDatesWindow(ClientConnection conn) throws UnknownHostException, IOException
    {
    	cc = conn;
    	
        this.setTitle("Choose dates and room preferences");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lblArrive = new JLabel("Arrival Date: ");
        lblArrive.setBounds(10, 10, 90, 21);
        add(lblArrive);

        txtArrive = new JTextField();
        txtArrive.setBounds(105, 10, 90, 21);
        add(txtArrive);

        lblDepart = new JLabel("Departure date: ");
        lblDepart.setBounds(10, 35, 90, 21);
        add(lblDepart);

        txtDepart = new JTextField();
        txtDepart.setBounds(105, 35, 90, 21);
        add(txtDepart);
        
        lblPeople = new JLabel("Number of people: ");
        lblPeople.setBounds(10, 85, 90, 21);
        add(lblPeople);

        txtPeople = new JTextField();
        txtPeople.setBounds(105, 85, 90, 21);
        add(txtPeople);
        
        checkBox = new JCheckBox("");
		checkBox.setBounds(442, 178, 128, 23);
		getContentPane().add(checkBox);
		
		label = new JLabel("Smoking:");
		label.setBounds(382, 178, 73, 19);
		getContentPane().add(label);
        

        btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() 
	    {
		    @Override
		    public void actionPerformed(ActionEvent e) 
		    {
		        if (e.getSource().equals(btnSearch)) 
		        {
		            try 
		            {
		                try 
		                {
		                	String command = "SEARCH;"+txtArrive.getText()+","+txtDepart.getText()+","+checkBox.isSelected()+","+txtPeople.getText();
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
	    
        btnSearch.setBounds(200, 40, 90, 20);
        btnSearch.addActionListener(this);
        add(btnSearch);
        
        
        btnProfile = new JButton("Profile");
        btnProfile.addActionListener(new ActionListener() 
	    {
		    @Override
		    public void actionPerformed(ActionEvent e) 
		    {
		        if (e.getSource().equals(btnProfile)) 
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
	    
        btnProfile.setBounds(200, 80, 90, 20);
        btnProfile.addActionListener(this);
        add(btnProfile);
        
        
        btnLogout = new JButton("Log Out");
        btnLogout.addActionListener(new ActionListener() 
	    {
		    @Override
		    public void actionPerformed(ActionEvent e) 
		    {
		        if (e.getSource().equals(btnLogout)) 
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
	    
        btnLogout.setBounds(200, 120, 90, 20);
        btnLogout.addActionListener(this);
        add(btnLogout);
        
    }
	
	
    public void sendCommand(String command) throws UnknownHostException, IOException, InterruptedException 
    {
    	cc.sendStringToServer(command);
    	
    	if(cc.serverResponds())
    	{
    		System.out.println("Server replies: "+cc.getServerResponse());  // use this to change window and display data if needed
    		serverResponse = cc.getServerResponse();
    		parseInput();
    		goToNextWindow(nextWindow);
    	}
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

    public void goToNextWindow(int window) throws UnknownHostException, IOException
    {
    	switch (window)
    	{
    	case 3:
    		ChooseRoom choose = new ChooseRoom(cc, data);
    		choose.frame.setVisible(true);
    		this.setVisible(false);
    		break;

    	case 9:
    		Profile prof = new Profile(cc);
    		prof.frame.setVisible(true);
    		this.setVisible(false);
    		break;

    	case 5:
    		JOptionPane.showMessageDialog(null, "No rooms of this type are available.");
    		break;

    	case 6:
    		LoginWindow login = new LoginWindow(cc);
    		login.setVisible(true);
    		this.setVisible(false);
    		break;

    	default:
    	}
    }
    
    
	@Override
	public void actionPerformed(ActionEvent e) {}
}
