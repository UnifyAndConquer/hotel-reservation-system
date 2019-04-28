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
	ThreadLocal<Integer> state = ThreadLocal.withInitial(()-> 6);			// server state is local to each thread
	ThreadLocal<Boolean> login = ThreadLocal.withInitial(()-> false);		// set to true upon login
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
				System.out.println(textIn);
				
//				StateHandler sh = new StateHandler(state.get(), textIn, login.get());   // pass thread state and client input to StateHandler constructor
//				state.set(sh.getNextState());							   // update thread state after transition has been carried out
//				login.set(sh.getLogin()); 								   // update login state		
//				String textOut = sh.getOutput();						   // return output of state transition to client
				
				sendStringToClient(textIn);
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



//public void sendStringToAllClients(String text)
//{
//	for (int index = 0; index < server.connections.size(); index++)
//	{
//		ServerConnection sc = server.connections.get(index);
//		sc.sendStringToClient("connection " + index + ":" + text);
//	}
//}
