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

public class Gui extends JFrame implements ActionListener {
	
    JLabel lblName;
    JLabel lblAge;
    JLabel lblMark;
    JTextField txtName;
    JTextField txtAge;
    JTextField txtMark;
    JButton btnProcess;
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
		
        this.setTitle("Simple Sample");
        this.setSize(320, 240);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lblName = new JLabel("Name: ");
        lblName.setBounds(10, 10, 90, 21);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(105, 10, 90, 21);
        add(txtName);

        lblAge = new JLabel("Age: ");
        lblAge.setBounds(10, 35, 90, 21);
        add(lblAge);

        txtAge = new JTextField();
        txtAge.setBounds(105, 35, 90, 21);
        add(txtAge);

        lblMark = new JLabel("Mark: ");
        lblMark.setBounds(10, 60, 90, 21);
        add(lblMark);

        txtMark = new JTextField();
        txtMark.setBounds(105, 60, 90, 21);
        add(txtMark);

        btnProcess = new JButton("Process");
        btnProcess.setBounds(200, 40, 90, 21);
        btnProcess.addActionListener(this);
        add(btnProcess);

        txtS = new JTextArea();
        txtS.setBounds(10, 85, 290, 120);
        add(txtS);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnProcess)) {
            try 
            {
                try 
                {
					sendCommand(Integer.toString(messageCount++));
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

    public void sendCommand(String command) throws UnknownHostException, IOException, InterruptedException 
    {
    	cc.sendStringToServer(command);
    	serverResponse = cc.getServerResponse();
    	System.out.println("Client: " + serverResponse);
    }
}


//Socket s = new Socket("localhost", 3001);
//DataOutputStream p = new DataOutputStream(s.getOutputStream());

//String name = txtName.getText();
//int mark = Integer.parseInt(txtMark.getText());
//int age = Integer.parseInt(txtAge.getText());

//p.writeUTF("test");
//p.flush();

// Here we read the details from server

//BufferedReader response = new BufferedReader(new InputStreamReader(s.getInputStream()));
//txtS.setText("The server respond: " + response.readLine());
//p.close();
//response.close();
//s.close();

