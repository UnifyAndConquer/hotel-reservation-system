package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class Server 
{
	ServerSocket ss;
	public int num = 0;
	
	ArrayList<ServerConnection> connections = new ArrayList<ServerConnection>();
	boolean shouldRun = true;
	
	public static void main(String args[]) throws IOException
	{
		System.out.print("Server started");
		new Server();
	}
	
	public Server()
	{
		try
		{
			ss = new ServerSocket(3001);
			while(shouldRun)
			{
				Socket s = ss.accept();
				num ++;
				ServerConnection sc = new ServerConnection(s, this);
				sc.start();
				connections.add(sc);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
	

	
	
	

	
	
//	SQL connection	
	
//	String url = "jdbc:mysql://localhost:3306/HotelData";
//	
//	try 
//	{
//		Connection conn = DriverManager.getConnection(url, "root", "MyNewPass");
//		
//		Statement stmt = conn.createStatement();
//		
//		ResultSet rs = stmt.executeQuery("select * from Users");
//		
//		while(rs.next())
//		{
//			System.out.println(rs.getString("First name"));
//		}
//	
//	}
//	catch (Exception exc)
//	{
//		exc.printStackTrace();
//	}
//	
//}


 // shutdownhook might be useful for later

//static class Message extends Thread 
//{
//  public void run() {
//   	try 
//   	{
//	 		ss.close();
//	        System.out.println("Server closed");
//	 	} 
//   	catch (IOException e) 
//   	{
//	 		e.printStackTrace();
//	 	}
//  } 
//}
