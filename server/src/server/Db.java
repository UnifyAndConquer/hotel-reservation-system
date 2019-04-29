package server;


import java.sql.*;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.concurrent.TimeUnit;


public class Db {
	public Db() {}

//	public static void main(String[] args) {

//insert_client("naldo","bejjani","14-10-1999","wqd","ndq@ddasw.dqdq");
//select_clients();
//	} 
	public static void select_clients()
	{
		String url = "jdbc:mysql://localhost:3306/HotelData";
		try 
		{
			Connection conn =DriverManager.getConnection(url, "root", "MyNewPass");

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select* from client");

			while(rs.next())
			{
				System.out.println("first name: "+rs.getString("first_name")+ ", last name: "+ rs.getString("last_name")+", Date of birth :"+ rs.getDate("date_of_birth")+", mail: "+rs.getString("mail"));
			}

		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}
	public static void select_client(String mail)
	{		String url = "jdbc:mysql://localhost:3306/HotelData";
		try 
		{
			Connection conn =DriverManager.getConnection(url, "root", "MyNewPass");

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select* from client");

			while(rs.next())
			{
				if(rs.getString("mail").equals(mail));
				{
					System.out.println(rs.getString("first_name")+ " "+ rs.getString("last_name")+" "+ rs.getDate("date_of_birth")+" "+rs.getString("mail"));
					break;
				}
			}

		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}
	public static int sign_in(String mail,String pass)
	{
		try 
		{
			String url = "jdbc:mysql://localhost:3306/HotelData";
			Connection conn =DriverManager.getConnection(url, "root", "MyNewPass");

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select* from client");

			while(rs.next())
			{
				if(rs.getString("mail").equals(mail)&&rs.getString(pass).equals(pass));
				{
					
					return rs.getInt("idclient");
				}
			}

		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
		return -1;
	}
	
	public static void select_rooms()
	{		String url = "jdbc:mysql://localhost:3306/HotelData";
		try 
		{
			Connection conn =DriverManager.getConnection(url, "root", "MyNewPass");

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select* from rooms");
			boolean smok;
			while(rs.next())
			{
				if(rs.getInt("smoking")==1)
					smok=true;
				else smok=false;
				System.out.print(rs.getInt("idrooms")+ " smoking:");
				System.out.print(smok);
				System.out.print(", ");
				System.out.println("price: "+ rs.getInt("price")+", number of persons: "+ rs.getInt("numberOfPersons"));
			}

		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}

	}
	public static int select_room_price(int room_id)
	{		String url = "jdbc:mysql://localhost:3306/HotelData";
		try 
		{
			Connection conn =DriverManager.getConnection(url, "root", "MyNewPass");

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select* from rooms");
			
			while(rs.next())
			{ if(rs.getInt("idrooms")==room_id)
				return rs.getInt("price");
			
			}
			
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
		return -1;

	}
	public static boolean insert_client(String first,String last,String dob, String pass,String mail)
	{
		String url = "jdbc:mysql://localhost:3306/HotelData";
		String sql="insert into client (first_name,last_name,date_of_birth,pass,mail) values('"+first+"','" +last+ "',STR_TO_DATE('"+dob+ "', '%d-%m-%Y'),'"+ pass +"','"+mail+"');";

		try 
		{
			Connection conn =DriverManager.getConnection(url, "root", "MyNewPass");

			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("select* from client");

			while(rs.next())
			{
				if(rs.getString("mail").equals(mail))
				{
					
					return false;
				}
			}
			stmt.executeUpdate(sql);

			

		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
		return true;
	}

	public static void insert_room(boolean smoking,int numberOfPersons,int price)
	{
		String url = "jdbc:mysql://localhost:3306/HotelData";
		int intsmoking;
		if(smoking==true)
			intsmoking=1;
		else
			intsmoking=0;

		String sql="insert into rooms (smoking,price,numberOfPersons) values(smoking: "+intsmoking+", price: "+price+", numberofpers: "+numberOfPersons+")";
		try 
		{
			Connection conn =DriverManager.getConnection(url, "root", "MyNewPass");

			Statement stmt = conn.createStatement();

			stmt.executeUpdate(sql);

		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}
	public static boolean insert_reservation(int client_id,int room_id,String checkin, String checkout)
	{
		String url = "jdbc:mysql://localhost:3306/HotelData";
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");

		boolean o=false;
		try { 
			
			java.util.Date date1 =  myFormat.parse(checkin);
		    java.util.Date date2 = myFormat.parse(checkout);
		    long diff = date2.getTime() - date1.getTime();
		    
		    diff=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		
		
		String sql="insert into reservation(roomid,clientid,checkin,checkout,price) values('"+room_id+"','" +client_id+ "',STR_TO_DATE('"+checkin+ "', '%d-%m-%Y')"+ ",STR_TO_DATE('"+checkout+ "', '%d-%m-%Y'),"+select_room_price( room_id)*diff+");";

			Connection conn =DriverManager.getConnection(url, "root", "MyNewPass");

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select* from reservation");
			
			while(rs.next())
			{
			        o = date1.getTime() <= rs.getDate("checkout").getTime() && rs.getDate("checkin").getTime() <= date2.getTime(); 
			    }
			if(o==false)
			stmt.executeUpdate(sql);
			

		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
		return !o;
	}
	public static void select_reservations()
	{		String url = "jdbc:mysql://localhost:3306/HotelData";
		try 
		{
			Connection conn =DriverManager.getConnection(url, "root", "MyNewPass");

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select* from reservation");

			while(rs.next())
			{
				System.out.println("room_id: "+rs.getString("roomid")+ ", client_id: "+ rs.getString("clientid")+", check-in :"+ rs.getDate("checkin")+", check-out "+rs.getDate("checkout"));
			}

		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}
	public static void edit_password(String pass, int client_id)
	{
		String url = "jdbc:mysql://localhost:3306/HotelData";
		String sql="UPDATE client SET pass = '"+pass+"' WHERE idclient = "+client_id+";";
			
		try 
		{
			Connection conn =DriverManager.getConnection(url, "root", "MyNewPass");

			Statement stmt = conn.createStatement();

			stmt.executeUpdate(sql);

		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}
	public static void edit_mail(String mail, int client_id)
	{		String url = "jdbc:mysql://localhost:3306/HotelData";
		String sql="UPDATE client SET mail = '"+mail+"' WHERE idclient = "+client_id+";";
			
		try 
		{
			Connection conn =DriverManager.getConnection(url, "root", "MyNewPass");

			Statement stmt = conn.createStatement();

			stmt.executeUpdate(sql);

		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}
	public static boolean edit_res(int reservation_id,String checkin,String checkout)
	{
		String url = "jdbc:mysql://localhost:3306/HotelData";
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");

		boolean o=false;
		try { 
			
			java.util.Date date1 =  myFormat.parse(checkin);
		    java.util.Date date2 = myFormat.parse(checkout);
		    long diff = date2.getTime() - date1.getTime();
		    
		    diff=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		
		
		String sql="UPDATE reservation set checkin ="+ "STR_TO_DATE('"+ checkin + " ', '%d-%m-%Y'),checkout=STR_TO_DATE('"+checkout+ "', '%d-%m-%Y') where idreservation="+reservation_id+";";
		
		Connection conn =DriverManager.getConnection(url, "root", "MyNewPass");

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select* from reservation");
			
			while(rs.next())
			{
			        o = date1.getTime() <= rs.getDate("checkout").getTime() && rs.getDate("checkin").getTime() <= date2.getTime(); 
			    }
			if(o==false)
			stmt.executeUpdate(sql);
			

		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
		return !o;
	
		
	}
//	public static String prefrences(int nbofpers,String checkin,String checkout,boolean smoking)
//	{
//		
//		String s="";
//		try { 
//
//
//		
//		String url = "jdbc:mysql://localhost:3306/HotelData";
//		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
//		Connection conn =DriverManager.getConnection(url, "root", "MyNewPass");
//		java.util.Date date1 =  myFormat.parse(checkin);
//	    java.util.Date date2 = myFormat.parse(checkout);
//		Statement stmt = conn.createStatement();
//		ResultSet rs = stmt.executeQuery("select* from reservation;");
//		int i[]=new int[30];
//		int count=0;
//		while(rs.next())
//		{
//			i[count]=rs.getInt("idrooms");
//		}
//		ResultSet rt=stmt.executeQuery("select* from rooms;");
//		boolean o=false;
//		boolean eq=false;
//		while(rt.next())
//		{
//			
//		eq=false;
//		
//		for(int j=0;j<30;j++)
//		{
//		        o = date1.getTime() <= rs.getDate("checkout").getTime() && rs.getDate("checkin").getTime() <= date2.getTime(); 
//		        if(o==false)
//		        	s+=rs.getInt("roomid")+" ";
//		        if(rt.getInt("idroom")==rs.getInt("roomid"))
//		        	eq=true;
//		    }
//		if(eq==false)
//		s+=rt.getInt("idrooms");
//		}
//		}
//		catch (Exception exc)
//		{
//			exc.printStackTrace();
//		}
//			
//		
//		return s;
//		
//
//	
//}
}
