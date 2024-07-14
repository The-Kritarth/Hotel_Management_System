package services;

import java.sql.SQLException;

public interface SuperAdminInterface {
    void addAdmin() throws SQLException;
    void removeAdmin() throws SQLException;
}
