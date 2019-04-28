package server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Testclient {
	
	ClientConnection cc;
	
//	public static void main(String args[]) throws UnknownHostException, IOException
//	{
//		new Testclient();
//	}
	
	// constructor
	public Testclient()
	{
		try
		{
			Socket s = new Socket("127.0.0.1", 3001); // initialize client socket
//			cc = new ClientConnection(s, this);
//			cc.start();
			
//			listenForInput();
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
//	public void listenForInput()
//	{
//		System.out.println("enter text: ");
//		
//		Scanner console = new Scanner(System.in);
//		
//		while (true)
//		{
//			while (!console.hasNextLine())  // while nothing is coming from the console
//			{
//				try 
//				{
//					Thread.sleep(1);
//				}
//				catch (InterruptedException e)
//				{
//					e.printStackTrace();
//				}
//			}
//			
//			String input = console.nextLine();  //take input from console
//			
//			if (input.toLowerCase().equals("quit"))
//			{
//				break;
//			}
//			cc.sendStringToServer(input);
//		}
//		cc.close();
//		console.close();
//	}
}





