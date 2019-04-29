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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Gui extends JFrame implements ActionListener 
{
    JLabel lblAge;
    JLabel lblFirst;
    JLabel lblLast;
    JLabel lblPass;
    JLabel lblMail;
    JTextField txtPass;
    JTextField txtAge;
    JTextField txtFirst;
    JTextField txtLast;
    JTextField txtMail;
    JButton btnDone;
    JButton btnLogin;
    JTextArea txtS;
    ClientConnection cc;
    String serverResponse;
    int nextWindow;
    ArrayList<String> data = new ArrayList<String>();
    
    
    public static void main(String[] args) throws UnknownHostException, IOException 
    {
        Socket s = new Socket("127.0.0.1", 3001); // initialize client socket
        ClientConnection conn = new ClientConnection(s);
		conn.start();
		
		new Gui(conn);
    }

    public Gui(ClientConnection conn) throws UnknownHostException, IOException 

    {
    	cc = conn;
    	
        this.setTitle("Create account");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lblFirst = new JLabel("First Name: ");
        lblFirst.setBounds(10, 10, 90, 21);
        add(lblFirst);

        txtFirst = new JTextField();
        txtFirst.setBounds(105, 10, 90, 21);
        add(txtFirst);

        lblLast = new JLabel("Last Name: ");
        lblLast.setBounds(10, 35, 90, 21);
        add(lblLast);

        txtLast = new JTextField();
        txtLast.setBounds(105, 35, 90, 21);
        add(txtLast);

        lblAge = new JLabel("Date of birth: ");
        lblAge.setBounds(10, 60, 90, 21);
        add(lblAge);

        txtAge = new JTextField();
        txtAge.setBounds(105, 60, 90, 21);
        add(txtAge);
        
        lblMail = new JLabel("Email: ");
        lblMail.setBounds(10, 85, 90, 21);
        add(lblMail);

        txtMail = new JTextField();
        txtMail.setBounds(105, 85, 90, 21);
        add(txtMail);
        
        lblPass = new JLabel("Password: ");
        lblPass.setBounds(10, 110, 90, 21);
        add(lblPass);

        txtPass = new JTextField();
        txtPass.setBounds(105, 110, 90, 21);
        add(txtPass);

	    
	    btnDone = new JButton("Done");
	    btnDone.addActionListener(new ActionListener() 
	    {
		    @Override
		    public void actionPerformed(ActionEvent e) 
		    {
		        if (e.getSource().equals(btnDone)) 
		        {
		            try 
		            {
		                try 
		                {
		                	String command = "DONE;"+ txtFirst.getText() + "," + txtLast.getText() + "," + txtAge.getText() + "," + txtPass.getText() + "," + txtMail.getText();
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
	    
        btnDone.setBounds(200, 40, 90, 20);
        btnDone.addActionListener(this);
        add(btnDone);
        
        
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() 
	    {
		    @Override
		    public void actionPerformed(ActionEvent e) 
		    {
		        if (e.getSource().equals(btnLogin)) 
		        {
		            try 
		            {
		                try 
		                {
		                	String command = "LOGIN;,";
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
	    
        btnLogin.setBounds(200, 80, 90, 20);
        btnLogin.addActionListener(this);
        add(btnLogin);
        
        this.setVisible(true);
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
    			// go to set dates
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

