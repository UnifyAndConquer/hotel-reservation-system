import java.sql.*;
import javax.swing.*;


public class mysqlConnection {
	
	Connection conn = null;
	
	public static Connection dbConnector()                                     //void main(String[] args)
	{
		try {
			Class.forName("com.mysql.cj.log.Slf4JLogger");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UsersData", "root", "Musonius76");
			
			//JOptionPane.showMessageDialog(null, "Connection established");		//Statement myStatement = conn.createStatement();
			
			return conn;		//ResultSet myResultSet = myStatement.executeQuery("select * from Users");
			
					//while (myResultSet.next()) {
				
					//System.out.println(myResultSet.getString("First name") + " " + myResultSet.getString("Last name"));
			//}
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
	}

}
