package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerConnection extends Thread
{
	Socket socket;
	Server server;
	DataInputStream din;
	DataOutputStream dout;
	ThreadLocal<Integer> num = ThreadLocal.withInitial(()-> server.num);	// connection number
	ThreadLocal<Integer> state = ThreadLocal.withInitial(()-> 1);			// server state is local to each thread
	ThreadLocal<Boolean> login = ThreadLocal.withInitial(()-> false);		// set to true upon login
	ThreadLocal<Integer> id = ThreadLocal.withInitial(()-> 0);
	ThreadLocal<String> checkIn = new ThreadLocal();
	ThreadLocal<String> checkOut = new ThreadLocal();
	
	boolean shouldRun = true;
	
	public ServerConnection(Socket socket, Server server)
	{
		super("ServerConnectionThread");
		this.socket = socket;
		this.server = server;
	}
	
	public void sendStringToClient(String text)
	{
		try 
		{
			dout.writeUTF(text);
			dout.flush();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		try 
		{
			din = new DataInputStream(socket.getInputStream());
			dout = new DataOutputStream(socket.getOutputStream());
			
			while (shouldRun)
			{
				while(din.available() == 0)
				{
					try {
						Thread.sleep(1);
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
				
				String textIn = din.readUTF();
				System.out.println("Server receives: "+textIn);
				
				StateHandler sh = new StateHandler(state.get(), textIn, login.get());   // pass thread state and client input to StateHandler constructor
				state.set(sh.getNextState());							   // update thread state after transition has been carried out
				System.out.println("Next state: "+state.get());
				
				login.set(sh.getLogin()); 								   // update login state	
				id.set(sh.getId());
				String textOut = sh.getOutput();						   // return output of state transition to client
				
				sendStringToClient(textOut);
			}
			
			din.close();
			dout.close();
			socket.close();
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}

