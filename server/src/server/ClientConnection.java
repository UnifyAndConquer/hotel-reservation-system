package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class ClientConnection extends Thread
{
	
	Socket s;
	DataInputStream din;
	DataOutputStream dout;
	boolean shouldRun = true;
	volatile public String serverResponse;

	public ClientConnection(Socket socket)
	{
		s = socket;
	}
	
	public void sendStringToServer(String text)
	{
		System.out.println("ClientConnection sends: "+text);
		try {
			dout.writeUTF(text);
			dout.flush();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			close();
		}
	}
	
	public String getServerResponse()
	{
		return(serverResponse);
	}
	
	
	public void run()
	{
		try {
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			
			while(shouldRun)
			{
				try
				{
					while (din.available() == 0)
					{
						try
						{
							Thread.sleep(1);
						}
						catch (InterruptedException e)
						{
							e.printStackTrace();
						}
					}
				
					serverResponse = din.readUTF();
//					System.out.println(serverResponse);
				}
				catch (IOException e)
				{
					e.printStackTrace();
					close();
				}
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			close();
		}
	}
	
	public void close()
	{
		try
		{
			din.close();
			dout.close();
			s.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public Boolean serverResponds() throws InterruptedException {
		String s1=serverResponse;
		
		while(serverResponse==s1)
		{
			TimeUnit.MILLISECONDS.sleep(10);
//			System.out.println("inside loop");
		}
		
		return(true);
	}
}
