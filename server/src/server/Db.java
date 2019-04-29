package server;


import java.sql.*;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
//give tarek the search method 
// tell him i changed edit reservation and insert res
// give him delete reservation



public class Db {

	public static void main(String[] args) {
		boolean [] b=	prefrences(2,"12-02-2011","12-05-2012",true);
		for(int i =0;i<b.length;i++)
			System.out.println(b[i]);
	} 
	public static void select_clients()
	{
		String url = "jdbc:mysql://localhost:3306/new_schema";
		try 
		{
			Connection conn =DriverManager.getConnection(url, "root", "naldo");

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
	{		String url = "jdbc:mysql://localhost:3306/new_schema";
	try 
	{
		Connection conn =DriverManager.getConnection(url, "root", "naldo");

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
			String url = "jdbc:mysql://localhost:3306/new_schema";
			Connection conn =DriverManager.getConnection(url, "root", "naldo");

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
	{		String url = "jdbc:mysql://localhost:3306/new_schema";
	try 
	{
		Connection conn =DriverManager.getConnection(url, "root", "naldo");

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
	{		String url = "jdbc:mysql://localhost:3306/new_schema";
	try 
	{
		Connection conn =DriverManager.getConnection(url, "root", "naldo");

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
		String url = "jdbc:mysql://localhost:3306/new_schema";
		String sql="insert into client (first_name,last_name,date_of_birth,pass,mail) values('"+first+"','" +last+ "',STR_TO_DATE('"+dob+ "', '%d-%m-%Y'),'"+ pass +"','"+mail+"');";

		try 
		{
			Connection conn =DriverManager.getConnection(url, "root", "naldo");

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
		String url = "jdbc:mysql://localhost:3306/new_schema";
		int intsmoking;
		if(smoking==true)
			intsmoking=1;
		else
			intsmoking=0;

		String sql="insert into rooms (smoking,price,numberOfPersons) values(smoking: "+intsmoking+", price: "+price+", numberofpers: "+numberOfPersons+")";
		try 
		{
			Connection conn =DriverManager.getConnection(url, "root", "naldo");

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
		String url = "jdbc:mysql://localhost:3306/new_schema";
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");

		boolean o=false;
		try { 

			java.util.Date date1 =  myFormat.parse(checkin);
			java.util.Date date2 = myFormat.parse(checkout);
			long diff = date2.getTime() - date1.getTime();

			diff=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);


			String sql="insert into reservation(roomid,clientid,checkin,checkout,price) values('"+room_id+"','" +client_id+ "',STR_TO_DATE('"+checkin+ "', '%d-%m-%Y')"+ ",STR_TO_DATE('"+checkout+ "', '%d-%m-%Y'),"+select_room_price( room_id)*diff+");";

			Connection conn =DriverManager.getConnection(url, "root", "naldo");

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
	{		String url = "jdbc:mysql://localhost:3306/new_schema";
	try 
	{
		Connection conn =DriverManager.getConnection(url, "root", "naldo");

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
		String url = "jdbc:mysql://localhost:3306/new_schema";
		String sql="UPDATE client SET pass = '"+pass+"' WHERE idclient = "+client_id+";";

		try 
		{
			Connection conn =DriverManager.getConnection(url, "root", "naldo");

			Statement stmt = conn.createStatement();

			stmt.executeUpdate(sql);

		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}
	public static void edit_mail(String mail, int client_id)
	{		String url = "jdbc:mysql://localhost:3306/new_schema";
	String sql="UPDATE client SET mail = '"+mail+"' WHERE idclient = "+client_id+";";

	try 
	{
		Connection conn =DriverManager.getConnection(url, "root", "naldo");

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
		String url = "jdbc:mysql://localhost:3306/new_schema";
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");

		boolean o=false;
		try { 

			java.util.Date date1 =  myFormat.parse(checkin);
			java.util.Date date2 = myFormat.parse(checkout);
			long diff = date2.getTime() - date1.getTime();

			diff=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);


			String sql="UPDATE reservation set checkin ="+ "STR_TO_DATE('"+ checkin + " ', '%d-%m-%Y'),checkout=STR_TO_DATE('"+checkout+ "', '%d-%m-%Y') where idreservation="+reservation_id+";";

			Connection conn =DriverManager.getConnection(url, "root", "naldo");

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select* from reservation");
			int i=0;
			while (rs.next())
			{
				if(reservation_id==rs.getInt("idreservation"))
					i=rs.getInt("roomid");
			}
			rs.close();
			if(i==0) 
				return false;
			rs= stmt.executeQuery("select* from reservation");

			while(rs.next())
			{
				if(rs.getInt("roomid")==i)
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
	public static boolean[] prefrences(int nbofpers,String checkin,String checkout,boolean smoking)
	{

		boolean[] available= new boolean[10];

		String url = "jdbc:mysql://localhost:3306/new_schema";
		try 
		{
			SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date date1 =  myFormat.parse(checkin);
			java.util.Date date2 = myFormat.parse(checkout);
			Connection conn =DriverManager.getConnection(url, "root", "naldo");

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select* from reservation;");
			Arrays.fill(available, true);
			while(rs.next())
			{	


				if(date1.getTime() <= rs.getDate("checkout").getTime() && rs.getDate("checkin").getTime() <= date2.getTime())
				{
					available[rs.getInt("roomid")-1]=false;
				}
			}
			rs.close();
			rs=stmt.executeQuery("select* from rooms");
			while(rs.next())
			{
				if(rs.getInt("smoking")==1&&smoking==false)
					available[rs.getInt("idrooms")-1]=false;
				if(rs.getInt("smoking")==0&&smoking==true)
					available[rs.getInt("idrooms")-1]=false;
				if(rs.getInt("numberOfPersons")!=nbofpers)
					available[rs.getInt("idrooms")-1]=false;
			}
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}

		return available;



	}
	public static void delete_res(int idreservation)
	{
		String url = "jdbc:mysql://localhost:3306/new_schema";

		String sql="delete from reservation where idreservation="+idreservation+";";
		try
		{
			Connection conn =DriverManager.getConnection(url, "root", "naldo");

			Statement stmt = conn.createStatement();

			stmt.executeUpdate(sql);

		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}
}