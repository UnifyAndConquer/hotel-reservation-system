package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;
import javax.swing.JScrollPane;

public class ChooseRoomWindow extends JFrame implements ActionListener
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
    ClientConnection cc;
    ArrayList<String> data = new ArrayList<String>();
    
    public static void main(String args[]) throws UnknownHostException, IOException 
    {
    	new ChooseRoomWindow();
    }

	public ChooseRoomWindow() throws UnknownHostException, IOException
	{
		this.setTitle("Choose dates and room preferences");
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
		
		String labels[] = { "A", "B", "C", "D", "E", "F", "G", "H" };
	    JFrame frame = new JFrame("Selecting JList");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JList jlist = new JList(labels);
	    JScrollPane scrollPane1 = new JScrollPane(jlist);
	    frame.add(scrollPane1, BorderLayout.CENTER);
	    frame.setSize(600, 400);

	    MouseListener mouseListener = new MouseAdapter() 
	    {
	      public void mouseClicked(MouseEvent mouseEvent) 
	      {
	        JList theList = (JList) mouseEvent.getSource();
	        if (mouseEvent.getClickCount() == 2) 
	        {
	          int index = theList.locationToIndex(mouseEvent.getPoint());
	          if (index >= 0) 
	          {
	            Object o = theList.getModel().getElementAt(index);
	            System.out.println("Double-clicked on: " + o.toString());
	          }
	        }
	      }
	    };
	    jlist.addMouseListener(mouseListener);

	    frame.setVisible(true);
		
		
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
    			SetDatesWindow setDates = new SetDatesWindow(cc);
    			setDates.setVisible(true);
    			this.setVisible(false);
    			break;
    			
    		case 7:
//    			CheckoutWindow checkout = new CheckoutWindow(cc);
//    			checkout.setVisible(true);
//    			this.setVisible(false);
//    			break;
    			
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
