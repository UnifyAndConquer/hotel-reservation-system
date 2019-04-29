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
    
    int messageCount = 0;
    
    public static void main(String[] args) throws UnknownHostException, IOException 
    {
        new Gui();
    }

    public Gui() throws UnknownHostException, IOException 
    {
		Socket s = new Socket("127.0.0.1", 3001); // initialize client socket
		cc = new ClientConnection(s);
		cc.start();
		
        this.setTitle("Hotel reservation system");
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

//        btnDone = new JButton("Done");

        
//        btnLogin = new JButton("Login");
//        btnLogin.setBounds(200, 80, 90, 20);
//        btnLogin.addActionListener(this);
//        add(btnLogin);

	    
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
							sendCommand(command);
//							System.out.println(command);
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
        
        this.setVisible(true);
    }
    

    public void sendCommand(String command) throws UnknownHostException, IOException, InterruptedException 
    {
    	cc.sendStringToServer(command);
    	
    	if(cc.serverResponds())
    	{
    		System.out.println(cc.getServerResponse());
    	}
    }

	@Override
	public void actionPerformed(ActionEvent e) {}
}

