package services;

import java.sql.SQLException;

public interface AdminInterface {
    int addEmployee(String str) throws SQLException;
    int deleteEmployee(String str) throws SQLException;
    void review(String str) throws SQLException;
}
