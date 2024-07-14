package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface crud {
  boolean login(Connection con,int user_id,String password,int choice) throws SQLException;
  void insertEmpData(Connection con,String emp_name,String password,String gender,int age,String aadhar_no,String phone_no,int emp_role) throws SQLException;
  void deleteEmpData(Connection con,int id) throws SQLException;
  ResultSet fetchUserdata(Connection con,int emp_role) throws SQLException;
  ResultSet checkRoomAvailability(Connection con,int room_size,String room_type) throws SQLException;
  Map<String, Float> getMenu(Connection con) throws SQLException;
  ResultSet roomPriceData(Connection con,int roomNo) throws SQLException;
  void addCustomerData(Connection con,String name, String password, String gender,int age, String aadharNo, String phoneNo,
			int i,int roomId,String check_in_date) throws SQLException;
  void changeAvailabilityData(Connection con,int roomId,int i) throws SQLException;
  ResultSet fetchCustomerData(Connection con, int roomNo) throws SQLException;
  void deleteCustomerData(Connection con, int roomNo) throws SQLException;
  void changePassData(Connection con, int roomNo, String pass) throws SQLException;
  void updateCustomerAmountData(Connection con, int roomNo, float amount) throws SQLException;
}
