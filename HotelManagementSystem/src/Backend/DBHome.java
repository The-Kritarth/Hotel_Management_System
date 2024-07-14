package Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBHome {
	
	
    private static Connection con;
	
	public static void dbConnect() throws ClassNotFoundException, SQLException
	{
		// 1. Loading Driver Class
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// 2. Establishing connection with DATABASE
		setCon(DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", ""));
		tableCreation.insertStaticData(con);
		tableCreation.createTable(getCon());
		
		tableCreation.insertFoodData(con);
		//tableCreation.insertRoleData(con);
		System.out.println(getCon());
	}
	
	public static void dbDisconnect() throws SQLException {
		getCon().close();
	}
	
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		dbConnect();
		

	}

	public static Connection getCon() {
		return con;
	}

	public static void setCon(Connection con) {
		DBHome.con = con;
	}

}
