package services;

import java.sql.SQLException;

public interface customerInterface {
	
	void changePassword() throws SQLException;
	void orderFood() throws SQLException;
	void feedback();

}
