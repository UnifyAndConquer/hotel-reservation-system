package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.SwingUtilities;

class SimpleClient extends DefaultGui {
	
	public static void main(String args[])  throws UnknownHostException, IOException
	{
		SwingUtilities.invokeLater(() -> {
			try {
				SimpleClient client;
				client = new SimpleClient(3001, "client");
				client.createGui();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		});
	}

    public SimpleClient(int port, String name) throws IOException
    {
    	super(port, name);
        socket = new Socket("localhost", port);
        inputScanner = new Scanner(socket.getInputStream());
        out = new PrintStream(socket.getOutputStream());
        new MyWorker(inputScanner, this).execute();
    }    
}