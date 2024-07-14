package businessLogic;
import java.sql.SQLException;

import Backend.DBHome;
import Backend.allQuery;
public class index {
	static allQuery al=new allQuery();
	
	public static boolean validateUser(int user_id,String password,int choice) throws SQLException {
		
		
		al.login(DBHome.getCon(), user_id, password, choice);
		return false;
		
	}
	
	
	
	
	
	
	
	
}
