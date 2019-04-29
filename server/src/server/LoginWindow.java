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

public class LoginWindow extends JFrame implements ActionListener 
{
    JLabel lblPass;
    JLabel lblMail;
    JTextField txtMail;
    JTextField txtPass;
    JButton btnDone;
    JButton btnRegister;
    String serverResponse;
    int nextWindow;
    ClientConnection cc;
    ArrayList<String> data = new ArrayList<String>();
	
	public LoginWindow(ClientConnection conn) throws UnknownHostException, IOException
	{
		this.setTitle("Login");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        cc = conn;
        
        lblPass = new JLabel("Password: ");
        lblPass.setBounds(10, 35, 90, 21);
        add(lblPass);

        txtPass = new JTextField();
        txtPass.setBounds(105, 35, 90, 21);
        add(txtPass);
        
        lblMail = new JLabel("Email: ");
        lblMail.setBounds(10, 10, 90, 21);
        add(lblMail);

        txtMail = new JTextField();
        txtMail.setBounds(105, 10, 90, 21);
        add(txtMail);
        
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
		                	String command = "DONE;";
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
        
        
        btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() 
	    {
		    @Override
		    public void actionPerformed(ActionEvent e) 
		    {
		        if (e.getSource().equals(btnRegister)) 
		        {
		            try 
		            {
		                try 
		                {
		                	String command = "LOGIN;,,,,";
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
	    
        btnRegister.setBounds(200, 80, 90, 20);
        btnRegister.addActionListener(this);
        add(btnRegister);
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
    		case 2:
    			
    		case 6:
    	}
    }
	
	
	@Override
	public void actionPerformed(ActionEvent e) {}
	
}
